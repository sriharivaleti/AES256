
public class StringDemo2 {
	
	
	public static  String getParsedDataSource() {
		return "\\sivaleti:1433\\practice";
	}
	public static void main(String[] args) {
		
		String serverInstance = "";
        int    seperatorPos;
        int    colonPos;
        seperatorPos = getParsedDataSource().indexOf("\\");
        System.out.println("seperatorPos" + seperatorPos);
        
        if (seperatorPos > -1)
        {
            colonPos  = getParsedDataSource().indexOf(":", seperatorPos);
            System.out.println(colonPos);
            if (colonPos > seperatorPos)
            {
                System.out.println(getParsedDataSource().substring( seperatorPos +1, colonPos));
                System.out.println(getParsedDataSource().substring(0, seperatorPos) + getParsedDataSource().substring(colonPos));
            }
        } 
		
		
		
		
	}

}
