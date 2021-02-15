import java.io.*;
import java.util.*;

//import org.apache.commons.lang.StringEscapeUtils;

public class KwikTagUpload {

	public static final String EXPENSE_TYPE = "expenseType";
	public static final String SPENT_DATE = "spentDate";
	public static final String SPENT_AMOUNT = "spentAmount";

	private static String loggerName = KwikTagUpload.class.getName();

	private static final String xmlBeforeBarcode = "<?xml version=\"1.0\" encoding=\"utf-8\" ?>\n" + "<KwikTag>\n"
			+ "<DocumentList>\n" + "<Document barcode=\"";
	private static final String xmlBeforeDrawer = "\" drawer=\"";
	private static final String xmlBeforeUsername = "\" username=\"";
	private static final String xmlBeforeReportId = "\">\n" + "<FieldList type=\"meta\">\n"
			+ "<TagField name=\"ReportID\">";
	private static final String xmlBeforeSource = "</TagField>\n"
			+ "<TagField name=\"Source\">File Upload</TagField>\n";
	private static final String xmlBeforeExpenseType = "<TagField name=\"Expense Type\">";
	private static final String xmlBeforeSpentDate = "</TagField>\n" + "<TagField name=\"Spent Date\">";
	private static final String xmlBeforeSpentAmount = "</TagField>\n" + "<TagField name=\"Spent Amount\">";
	private static final String xmlEnd = "</TagField>\n" + "</FieldList>\n" + "</Document>\n" + "</DocumentList>\n"
			+ "</KwikTag>\n";

	/**
	 * Create an XML file to be sent to Kwiktag, in which the barcode element is set
	 * to the value of input parameter barcode and the custom field ReportID is set
	 * to the value of input parameter reportId.
	 * 
	 * @param xmlFile
	 *            XML file to be created.
	 * @param reportId
	 *            the value of custom field ReportID in the XML file. It can be the
	 *            expense report number that identifies an expense report.
	 * @param barcode
	 *            the value of barcode field in the XML file. It can be any unique
	 *            number that identifies the expense line to which the image will be
	 *            associated.
	 * @param drawer
	 *            the value of drawer field in the XML file
	 * @param username
	 *            the value of username filed in the XML file
	 * @param expenseInfo
	 *            hash map with expense info
	 * @throws IOException
	 */
	public static void createXmlFile(File xmlFile, String reportId, String barcode, String drawer, String username,
			Map<String, String> expenseInfo) throws IOException {
		FileWriter fw = null;
		try {
			fw = new FileWriter(xmlFile);
			fw.write(xmlBeforeBarcode);
			fw.write(barcode);
			fw.write(xmlBeforeDrawer);
			fw.write(drawer);
			fw.write(xmlBeforeUsername);
			fw.write(username);
			fw.write(xmlBeforeReportId);
			fw.write(reportId);
			fw.write(xmlBeforeSource);
			fw.write(xmlBeforeExpenseType);
			fw.write(getExpenseValue(expenseInfo, EXPENSE_TYPE));
			fw.write(xmlBeforeSpentDate);
			fw.write(getExpenseValue(expenseInfo, SPENT_DATE));
			fw.write(xmlBeforeSpentAmount);
			fw.write(getExpenseValue(expenseInfo, SPENT_AMOUNT));
			fw.write(xmlEnd);
		} finally {
			if (fw != null)
				fw.close();
		}
	}

	/**
	 * Read xml escaped key value from expense info hash map
	 * 
	 * @param expenseInfo
	 *            hash map with expense info
	 * @param key
	 *            key of the requested value
	 * @return xml escaped key value
	 */
	private static String getExpenseValue(Map<String, String> expenseInfo, String key) {
		String value = "-";
		if (null != expenseInfo) {
			String element = expenseInfo.get(key);
			if (null != element) {
				//value = StringEscapeUtils.escapeXml(element);
				if ((null == value) || ("".equals(value))) {
					value = "-";
				}
			}
		}
		return value;
	}

	/**
	 * Returns the full pathname of the shared directory used to pass files to the
	 * KwikTag image server. To prevent users from entering wrong directories, the
	 * user enters only the middle section of the path, and the rest is hard-coded.
	 * 
	 * @param fileShareLocation
	 *            The user-entered portion of the path
	 * @return
	 */
	public static String getFileSharePath(String fileShareLocation) {
		return fileShareLocation;
	}

	public static boolean fileShareLocationIsValid(String fileShareLocation) {
		String fileSharePath = getFileSharePath(fileShareLocation);
		File file = new File(fileSharePath);
		if (!file.exists()) {
			logWarning(fileSharePath, "");
			// It may not exist or it may not be readable or it may not be accessible
			// because a directory in the path may not be readable.
			return false;
		}
		if (!file.isDirectory()) {
			// It exists but is a file.
			return false;
		}
		if (!file.canWrite()) {
			// It is a directory but is not writable.
			return false;
		}
		return true;
	}

	private static void logWarning(String fileSharePath, String warning) {
		System.out.print("Invalid CyberShift hosting File Share Location " + '"' + fileSharePath + "\": " + warning);
	}
}
