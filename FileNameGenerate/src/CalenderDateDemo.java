import java.util.*;

import com.braju.format.Format;
public class CalenderDateDemo {
	public static void main(String[] arg) {
		String calendarYear = (new Integer((Calendar.getInstance()).get(Calendar.YEAR) - 2000)).toString();
		System.out.println(calendarYear);
        if (2 - calendarYear.length() != 0)
        {
            Object args[] = { new Integer(2- calendarYear.length()), new Integer(0), calendarYear };
            calendarYear = Format.sprintf("%0*d%s", args);
        }
        System.out.println(calendarYear);
        Integer i = new Integer(135);
        String sequenceNumber = i.toString();
        System.out.println(sequenceNumber);
        if (4 - sequenceNumber.length() != 0)
        {
            Object args[] = { new Integer(4 - sequenceNumber.length()), new Integer(0), sequenceNumber };
            sequenceNumber = Format.sprintf("%0*d%s", args);
        }
        System.out.println(sequenceNumber);

	}

}
