
public class StringReplaceDemo {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		
		String url = "jdbc:microsoft:sqlserver://SIVALETI1IN:1433;DATABASANAME=Practice";
		String databaseConst = ";databasename=";
		String databaseName = url.substring(url.lastIndexOf("/")+1);
		System.out.println(databaseName);
		if(url.indexOf("databasename") == -1) {
			url = url.substring(0,url.lastIndexOf("/"));
			url = url+databaseConst+databaseName;
			
			System.out.println(url);;
		}

	}

}
