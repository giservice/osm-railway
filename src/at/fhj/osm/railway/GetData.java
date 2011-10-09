package at.fhj.osm.railway;

// GetData.java

import java.io.File;
import java.util.Vector;

import javax.xml.parsers.*;
import javax.xml.stream.*;

import org.w3c.dom.*;

import at.fhj.osm.railway.component.RailNode;
import at.fhj.osm.railway.component.RailWay;
import at.fhj.osm.railway.component.RailwayStation;
import at.fhj.osm.railway.view.MainFrame;

public class GetData
{

	static long startTime=0; 
	
   public static void main( String[] args ) throws XMLStreamException
   {
	   startTime = System.currentTimeMillis();
	/*  ParseOsmData pxd = new ParseOsmData();
	
	   if(pxd.initDom()){
	   		pxd.getRailway();
		   pxd.getStationsAndNodes();
		   pxd.newXml();
	   }  */
	   
	   Vector<RailWay> vRailway = new Vector<RailWay>();
	    Vector<RailNode> vRailnode = new Vector<RailNode>();
	    Vector<RailwayStation> vRailstations = new Vector<RailwayStation>();
	   ParseRailwayXmlData pxd = new ParseRailwayXmlData();
	  if(pxd.initDom()){
	   		pxd.getRailway(vRailway);
		    pxd.getStationsAndNodes(vRailnode,vRailstations);
		    
		    for (RailWay railWay : vRailway) {
		    	boolean found = false;
		    	for (RailNode railNode : vRailnode) {
		    		if(railWay.from == railNode.id){
		    			railWay.fromNode = railNode;
		    		}
		    		if(railWay.to == railNode.id){
		    			railWay.toNode = railNode;
		    		}
					
				}
		    	
		    	
			}
		    System.out.println();
		    new MainFrame(vRailway,vRailstations);
		    System.out.println(GetData.getTime()+ "End");
	   } 
	   
	   
      
   }
   public static String getTime(){
	   long diff = System.currentTimeMillis()-startTime;
	   String ret = diff/1000+"sec#";
	   return ret;
   }
}