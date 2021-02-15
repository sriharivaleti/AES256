import java.util.Vector;

public class StringDemo3 {
	
	
	
	public static String doMakeSingleQuote(String as_strList)
	{
        Vector vectorobj = null;
        String singleQuoteList = "";
        
        vectorobj = getTokens(as_strList, ",");
        if(vectorobj != null)
        {
            for(int index =0; index < vectorobj.size(); index++)
            {
                singleQuoteList += ("'" + fixQuotesForSqlUsage(vectorobj.elementAt(index).toString().trim()) + "'");
                if(index+1 < vectorobj.size())
                    singleQuoteList += ",";
            }
            vectorobj.removeAllElements();
        }
        
        return singleQuoteList;
	}
	
	public static Vector getTokens(String Str, String Token)
    {
        Vector av_tokens = null;
        int i = 0;
        int j = 0;
        String as_token;

        if (Str.length()!=0)
        {
            av_tokens = new Vector(); 

            if (countNumberOfTokens(Str, Token) == 1)
            {
                av_tokens.addElement(Str);
            }
            else
            {
                while((j = Str.indexOf(Token)) !=-1)
                {
                    as_token = Str.substring(i,j);
                    av_tokens.addElement(as_token);
                    Str = Str.substring(j+1);
                }
                av_tokens.addElement(Str);
            }
        }
        return(av_tokens);
    }
	
	 public static int countNumberOfTokens(String Str, String Token)
	    {
	        int j;
	        int TokenCount=0;
	        
	        if (Str.length()!=0)
	        {
	            TokenCount++;
	            
	            while((j = Str.indexOf(Token)) !=-1)
	            {
	                Str = Str.substring(j+1);
	                TokenCount++;
	            }
	        }
	        
	        return(TokenCount);
	    }
	
	 public static String fixQuotesForSqlUsage(String as_str)
	    {
	        String str = new String();
	        if (as_str == null) return(as_str);
	        if(as_str.indexOf("\'") == -1) return(as_str);
	        for (int i = 0; i < as_str.length(); i++)
	        {
	            if (as_str.charAt(i) == '\'')
	                str =  str + "'" + as_str.charAt(i);
	            else
	                str = str + as_str.charAt(i);
	        }
	        return(str);
	    }
	public static void main(String[] args) {
		
		System.out.println(doMakeSingleQuote("AMEX,VISA"));
		
	}

}
