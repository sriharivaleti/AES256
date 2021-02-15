package com.hari.main.demo;

import java.io.FileInputStream;

import javax.xml.XMLConstants;
import javax.xml.stream.XMLInputFactory;
import javax.xml.stream.XMLStreamConstants;
import javax.xml.stream.XMLStreamReader;

public class XMlReaderMain {
	
	public static void main(String[] args) {
		
		String fileName = "C:\\Users\\sivaleti\\Documents\\GitHub\\J2SEPractice\\XMLReadingTest\\src\\Test.xml";
		try {
			XMLInputFactory xmlif = null ;
            xmlif = XMLInputFactory.newInstance();
            xmlif.setProperty(XMLInputFactory.IS_REPLACING_ENTITY_REFERENCES,Boolean.TRUE);
            xmlif.setProperty(XMLInputFactory.IS_SUPPORTING_EXTERNAL_ENTITIES,Boolean.FALSE);
            xmlif.setProperty(XMLInputFactory.IS_COALESCING , Boolean.FALSE);
            
            FileInputStream fis = null;
            fis = new FileInputStream(fileName);
            XMLStreamReader xmlReader = xmlif.createXMLStreamReader(fis);
            
            if (!validateDocumentHeader(xmlReader)) {
				System.out.println("XMl is invalid");
				return;
			}
            int i =0;
            while(xmlReader.hasNext())
            {
            	i++;
               	xmlReader.next();
               	if(xmlReader.isStartElement())
               	{
               		System.out.println(i+"th iteration Start tag:"+xmlReader.getLocalName());
               		System.out.println(xmlReader.getAttributeLocalName(0));
               		System.out.println(xmlReader.getAttributeValue(0));
               	}
                if(xmlReader.hasText())
                {
                	String value = xmlReader.getText();
                	System.out.println(i+"th iteration Text:"+value);
                	
                }
                if(xmlReader.isEndElement())
               	{
               		System.out.println(i+"th iteration End:"+xmlReader.getLocalName());
               	}
            }
            
           /* while(xmlReader.hasNext()) {
            	int event = xmlReader.next();try {
            		
            		if(xmlReader.hasText())
            		System.out.println(xmlReader.getElementText());
            	}
            	catch(Exception e) {
            		e.printStackTrace();
            	}    		
            	
            }*/
			
		}
		catch(Exception e) {
			System.out.println(e);
		}
	}
	
	 private static boolean validateDocumentHeader(XMLStreamReader xmlr){
	        if(XMLStreamReader.START_DOCUMENT != xmlr.getEventType()){
	            System.out.println("Error processing XML file: No document start tag found. ");
	            return false;
	        }
	        return true;
	    }
	

}
