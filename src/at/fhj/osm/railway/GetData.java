package at.fhj.osm.railway;

// GetData.java

import java.io.File;

import javax.xml.parsers.*;
import javax.xml.stream.*;

import org.w3c.dom.*;

public class GetData
{

	static long startTime=0; 
	
   public static void main( String[] args ) throws XMLStreamException
   {
	   startTime = System.currentTimeMillis();
	   ParseXmlData pxd = new ParseXmlData();
	 //  pxd.newXml();
	   if(pxd.initDom()){
		   pxd.getStations();
		   pxd.newXml();
	   } 
      
   }
   public static String getTime(){
	   long diff = System.currentTimeMillis()-startTime;
	   String ret = diff/1000+"sec#";
	   return ret;
   }
}