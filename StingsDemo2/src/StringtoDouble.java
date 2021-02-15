import java.text.*;
public class StringtoDouble {
	
	public static void main(String[] args) {
		
		try {
			
			
			//DecimalFormat df = new DecimalFormat("######0.00");
		String s = "9,999,99.25";
		double l = DecimalFormat.getNumberInstance().parse(s).doubleValue();
		//double ll = Double.parseDouble(df.format("1,000.000"));
		System.out.println(l);
		
		String s1 ="<FieldList type=\"meta\"/>";
		System.out.println(s1);
		
		int versionumber =0;
		
		System.out.println(String.valueOf(versionumber));
		}
		catch(Exception e) {
			e.printStackTrace();
		}
	}

}
