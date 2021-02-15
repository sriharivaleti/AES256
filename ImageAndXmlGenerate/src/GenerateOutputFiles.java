import java.io.File;
import java.io.IOException;
import java.util.Calendar;
import java.util.Date;

import javax.imageio.ImageIO;

import java.awt.image.BufferedImage;

public class GenerateOutputFiles {

	 
	public static void main(String[] args) throws Exception{
		File outputDirFile = new File("C:\\Temp");
		
		String directoryName ="C:\\Images\\";		
		String generatedFileDir ="C:\\DestinationFolder\\";
		File folder = new File(directoryName);
		if(!folder.exists()) {
			System.out.println("Source Folder " + directoryName +" Not Exists. Terminating Program");
			return;
		}
		
		if(!outputDirFile.exists())
		{
			System.out.println("Temp Folder " + outputDirFile  +" Not Exists. Terminating Program");
			return;
		}
		
		if(!new File(generatedFileDir).exists()) {
			System.out.println("Destination Folder " + generatedFileDir  +" Not Exists. Terminating Program");
			return;
		}
			
		//
		System.out.println("Started Creating Receipts");
		String customerNumber = "N001";
		String employeeNumber = "00011111";
		String documentType = String.valueOf(2);
        // should never occur if DOCUMENT_TYPE_LENGTH is set properly regarding DOCUMENT_TYPE variable
        if (documentType.length() >1)
        {
            documentType = documentType.substring(documentType.length() - 1, documentType.length());
        }
        
        String calendarYear = (new Integer((Calendar.getInstance()).get(Calendar.YEAR) - 2000)).toString();
        
        int seqnumber = 134;// Considering Sequenece Number min 3 length max 4 length
        
        //int count =0;
       //
        
		File[] listOfFiles = folder.listFiles();
		System.out.println("Start :" + new Date());
		for (int i = 0; i < listOfFiles.length; i++) {
		try {
		String fileExtension = getFileExtension(listOfFiles[i]);
		if(fileExtension.length() > 3 || fileExtension.equalsIgnoreCase("svg"))
			continue;
		String documentNumber = null;
		Integer seqno1 = new Integer(seqnumber);
		String seqno = seqno1.toString();
		if(seqno.length() == 4)
			documentNumber =  customerNumber + employeeNumber + documentType + calendarYear + seqno;
		else
			documentNumber =  customerNumber + employeeNumber + documentType + calendarYear +"0"+seqno;
		seqnumber++;
		//System.out.println(documentNumber);
		String barcode = documentNumber;
		File outputTiffFile = File.createTempFile(documentNumber+ '_', "."+fileExtension,outputDirFile);
		String tifFilename = outputTiffFile.getName();
		String rootFilename = tifFilename.substring(0, tifFilename.length() - 4);
		outputTiffFile.delete();
		//System.out.println(rootFilename);
		//System.out.println(listOfFiles[i].getName());
		
		File imagefile = new File(listOfFiles[i].getAbsolutePath());
		
		BufferedImage image = null;
		
		 image = ImageIO.read(imagefile);
		 
		ImageIO.write(image, fileExtension,new File(generatedFileDir+tifFilename));
		
		String xmlFilename = rootFilename + ".xml";
		File xmlFile = new File(generatedFileDir, xmlFilename);
		
		generateXml(xmlFile, documentNumber, barcode);		
		new Thread().wait(100);		
		}
		catch(Exception e) {
			//System.out.println("failed for image "+ listOfFiles[i].getAbsolutePath() );
		}
        
		}
		System.out.println("End :" + new Date());
		//System.out.println(count);

		System.out.println("Program Execution completed ");
	}
	
	private static String getFileExtension(File file) {
        String fileName = file.getName();
        if(fileName.lastIndexOf(".") != -1 && fileName.lastIndexOf(".") != 0)
        return fileName.substring(fileName.lastIndexOf(".")+1);
        else return "";
    }
	
	private static void generateXml(File xmlFile, String documentNumber, String barcode) {
		
		String drawer = "CyberShift Test";
		String username = "cybershifttest";
		try {
		KwikTagUpload.createXmlFile(xmlFile, documentNumber, barcode, drawer,
				username, null);
		}
		catch(Exception e) {
			
			System.out.println("Exception while generating "+ xmlFile);
		}
		
	}

}
