package com.hari.test;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.InputStream;
import java.util.Properties;

import javax.imageio.ImageIO;

public class AddImagesToReports {
	
	public void addImages(String arg) throws Exception {
		
		InputStream inputStream = null ;
		
		String configFileName = arg;
		Properties prop = new Properties();
		//configFileName = "config.properties";
		
		inputStream = getClass().getClassLoader().getResourceAsStream(configFileName);

		
		if (inputStream != null) {
			prop.load(inputStream);
		} else {
			throw new FileNotFoundException("property file '" + configFileName + "' not found in the classpath");
		}

		String documentNumbers = prop.getProperty("DocumentNumbers");
		String directoryName = prop.getProperty("ReceiptsRootFolder");
		String generatedFileDir = prop.getProperty("ReceiptsDestFolder");
		File outputDirFile = new File("Tmp");
		
		if (!new File(directoryName).exists()) {
			System.out.println("Source Folder " + directoryName + " Not Exists. Terminating Program");
			return;
		}

		if (!outputDirFile.exists()) {
			outputDirFile.mkdir();
		}
		System.out.println(generatedFileDir);
		if (!new File(generatedFileDir).exists()) {
			System.out.println("Destination Folder " + generatedFileDir + " Not Exists. Terminating Program");
			return;
		}
		
		String[] documentNumArray = documentNumbers.split(",");
		File folder = new File(directoryName);
		
		File[] listOfFiles = folder.listFiles();
		int reportidindex =0;
		
		for (int i = 0; i < listOfFiles.length; i++) {
			try {
				String fileExtension = getFileExtension(listOfFiles[i]);
				if (fileExtension.length() > 3 || fileExtension.equalsIgnoreCase("svg"))
					continue;
				String documentNumber = documentNumArray[reportidindex++];
				//System.out.println(documentNumber);
				if(reportidindex == documentNumArray.length)
					reportidindex =0;				
				String barcode = documentNumber;
				File outputTiffFile = File.createTempFile(documentNumber + '_', "." + fileExtension, outputDirFile);
				String tifFilename = outputTiffFile.getName();
				String rootFilename = tifFilename.substring(0, tifFilename.length() - 4);
				outputTiffFile.delete();
				// System.out.println(rootFilename);
				// System.out.println(listOfFiles[i].getName());

				File imagefile = new File(listOfFiles[i].getAbsolutePath());

				BufferedImage image = null;

				image = ImageIO.read(imagefile);

				ImageIO.write(image, fileExtension, new File(generatedFileDir+"/" + tifFilename));

				String xmlFilename = rootFilename + ".xml";
				File xmlFile = new File(generatedFileDir, xmlFilename);

				generateXml(xmlFile, documentNumber, barcode);
				
			} catch (Exception e) {
				// System.out.println("failed for image "+ listOfFiles[i].getAbsolutePath() );
				//e.printStackTrace();
				//System.out.println(e);
			}

		}

		
		inputStream.close();
	}
	
	public static void main(String[] args) {
		try {
		System.out.println("Program Started to add receipts to given document numbers");
		new AddImagesToReports().addImages("config.properties");
		System.out.println("Program ended");
		}
		catch(Exception e) {
			e.printStackTrace();
			System.out.println(e);
		}
	}
	
	
	private static String getFileExtension(File file) {
		String fileName = file.getName();
		if (fileName.lastIndexOf(".") != -1 && fileName.lastIndexOf(".") != 0)
			return fileName.substring(fileName.lastIndexOf(".") + 1);
		else
			return "";
	}

	private static void generateXml(File xmlFile, String documentNumber, String barcode) {

		String drawer = "Cybershift Test";
		String username = "cybershifttest";
		try {
			KwikTagUpload.createXmlFile(xmlFile, documentNumber, barcode, drawer, username, null);
		} catch (Exception e) {

			System.out.println("Exception while generating " + xmlFile);
		}

	}


}
