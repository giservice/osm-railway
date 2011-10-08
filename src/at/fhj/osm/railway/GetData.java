package at.fhj.osm.railway;

// GetData.java

import java.io.File;

import javax.xml.parsers.*;
import javax.xml.stream.*;

import org.w3c.dom.*;

public class GetData
{
   public static void main( String[] args ) throws XMLStreamException
   {
	   ParseXmlData pxd = new ParseXmlData();
	   if(pxd.initDom()){
		   pxd.getStations();
	   }
      
   }
}