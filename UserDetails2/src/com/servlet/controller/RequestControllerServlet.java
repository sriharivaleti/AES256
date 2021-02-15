package com.servlet.controller;

import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class RequestControllerServlet
 */
public class RequestControllerServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

    /**
     * Default constructor. 
     */
    public RequestControllerServlet() {
        // TODO Auto-generated constructor stub
    }

	
   
	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		
		Connection con = null;
		ResultSet rs = null;
		String sqlQuery1 = "select UserAccountid from UserAccount where UserAccountName=?";
		String sqlQuery2 = "select EmployeeNumber,EmployeeName,EmployeeFirstName,EmployeeLastName,EmailAddress From Employee where Employeeid =?";
		PreparedStatement pstmt = null;
		try {
			
			
			
			/*jtds-1.3.1.jar */
			Class.forName("net.sourceforge.jtds.jdbc.Driver"); // jdbc:jtds:sqlserver://EXP2012JDK8-VMH:1433/RITCHIEBROS
			String url = "jdbc:jtds:sqlserver://exp2012jdk8-vmh:1433/SANITY17102";			
			con = DriverManager.getConnection(url, "sa", "titan#12");
			pstmt = con.prepareStatement(sqlQuery1);
			pstmt.setString(1, request.getParameter("username"));
			
			rs = pstmt.executeQuery();
			String userid = null;
			if(rs.next())
				userid = rs.getString(1);
			
			pstmt = con.prepareStatement(sqlQuery2);
			pstmt.setString(1, userid);
			rs = pstmt.executeQuery();
			if(rs.next())
				response.getWriter().append("EmployeeNumber :" + rs.getString(1)+"<br>EmployeeName :"+rs.getString(2)+ "<br>EmployeeFirstName :"+rs.getString(3) + 
						"<br>EmployeeLastName :" + rs.getString(4) +"<br>EmailAddress :"+rs.getString(5));
			
			
		}
		catch(Exception e){
			e.printStackTrace();
		}
		
	
	}


	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
