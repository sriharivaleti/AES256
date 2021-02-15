import java.sql.Connection;
import java.sql.DriverManager;

public class JDBCConnectionDemo {

	public static void main(String[] args) {

		Connection con = null;
		try {

			/* sqljdbc4.jar */
			/*
			 * Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver"); con =
			 * DriverManager.getConnection(
			 * "jdbc:sqlserver://localhost:1433;databaseName=Practice", "sa", "titan#12");
			 */
			
			/*jtds-1.3.1.jar */
			Class.forName("net.sourceforge.jtds.jdbc.Driver"); // jdbc:jtds:sqlserver://EXP2012JDK8-VMH:1433/RITCHIEBROS
			String url = "jdbc:jtds:sqlserver://SIVALETI1IN:1433/Practice;encrypt=true;trustServerCertificate=true;ssl=authenticate";
			// String url =	"jdbc:jtds:sqlserver://exp2012jdk8-vmh:1433;databasename=RITCHIEBROS";
			con = DriverManager.getConnection(url, "sa", "titan#12");
			
			/*mssqlserver.jar*/
			
			/*Class.forName("com.microsoft.jdbc.sqlserver.SQLServerDriver");
			String url = "jdbc:microsoft:sqlserver://SIVALETI1IN:1433;DatabaseName=Practice";
			//String url =	"jdbc:jtds:sqlserver://exp2012jdk8-vmh:1433;databasename=RITCHIEBROS";
			con = DriverManager.getConnection(url, "sa", "titan#12");*/
					
			
			//Class.forName("com.microsoft.jdbc.sqlserver.SQLServerDriver");
			/*Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
			String url = "jdbc:sqlserver://SIVALETI1IN:1433/Practice";
			System.out.println("Before url :" + url);
			String databaseConst = ";databasename=";
			String databaseName = url.substring(url.lastIndexOf("/")+1);
			System.out.println(databaseName);
			if(url.toLowerCase().indexOf("databasename") == -1) {
				url = url.substring(0,url.lastIndexOf("/"));
				url = url+databaseConst+databaseName;				
			}
			
			System.out.println("After url :" +url);
			con = DriverManager.getConnection(url, "sa", "titan#12");*/

			System.out.println("Connection Established");

		} catch (Exception e) {
			e.printStackTrace();
			System.out.println(e);
		}
	}

}
