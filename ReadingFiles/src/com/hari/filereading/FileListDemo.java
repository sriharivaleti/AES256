package com.hari.filereading;

import java.io.*;

public class FileListDemo {

	
	public static void main(String[] args) {
		
		String directoryName ="C:\\Users\\sivaleti\\Desktop\\Sreehari\\ExpenseBuilds\\devbuild\\LocalFTPRoot";
		File folder = new File(directoryName);
		
		
		File[] listOfFiles = folder.listFiles();

		for (int i = 0; i < listOfFiles.length; i++) {
		  if (listOfFiles[i].isFile()) {
			  System.out.println(listOfFiles[i].getAbsoluteFile());
		  String fileName =listOfFiles[i].getName();
		  System.out.println(fileName);
		  
		  fileName = fileName.substring(0, fileName.lastIndexOf("."));
		  System.out.println("File Name :" + fileName );
		  String tmp = directoryName+"\\"+fileName+".xml";
		  boolean xmlFileExists =  new File(tmp).exists();
		  System.out.println(fileName+".xml" +" Exists.");
		  } 
		}
	}

	
}
