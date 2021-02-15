import java.text.DecimalFormat;
import java.util.Locale;

public class StringArrayToDoubleArray {
	
 public static void main(String[] args) {
	
	 String[] stringArray = {"1,00,000.00"};
	 double[] doubleArray = new double[stringArray.length];
	 
	  for( int i=0 ; i<stringArray.length ; i++ )
      {
      	if( !(stringArray[i].equals("")) )
      	{
      		try
      		{
      			//doubleArray[i] = DecimalFormat.getNumberInstance().parse(stringArray[i]).doubleValue();
      			 
      			doubleArray[i] = DecimalFormat.getNumberInstance(Locale.getDefault()).parse(stringArray[i]).doubleValue();
      			
      			System.out.println(doubleArray[i]);
      			
      		}
      		catch (NumberFormatException e)
				{
      		
      			System.out.println("Error");
				}
      		catch(Exception e) {
      			e.printStackTrace();
      		}
      	}
      	
      }
	 
	  
	  
	 
	 
}

}
