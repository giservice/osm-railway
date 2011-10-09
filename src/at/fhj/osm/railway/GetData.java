package at.fhj.osm.railway;

// GetData.java

import java.io.File;

import javax.xml.parsers.*;
import javax.xml.stream.*;

import org.w3c.dom.*;

import at.fhj.osm.railway.view.MainFrame;

public class GetData
{

	static long startTime=0; 
	
   public static void main( String[] args ) throws XMLStreamException
   {
	   startTime = System.currentTimeMillis();
	  ParseOsmData pxd = new ParseOsmData();
	
	   if(pxd.initDom()){
	   		pxd.getRailway();
		   pxd.getStationsAndNodes();
		   pxd.newXml();
	   } 
	   
	  // new MainFrame();
      
   }
   public static String getTime(){
	   long diff = System.currentTimeMillis()-startTime;
	   String ret = diff/1000+"sec#";
	   return ret;
   }
}