package at.fhj.osm.railway;

import java.io.File;
import java.util.Vector;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.transform.Result;
import javax.xml.transform.Source;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerConfigurationException;
import javax.xml.transform.TransformerException;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.NamedNodeMap;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

import at.fhj.osm.railway.component.RailNode;
import at.fhj.osm.railway.component.RailWay;
import at.fhj.osm.railway.component.RailwayStation;
import at.fhj.osm.railway.component.WaterWay;

public class ParseRailwayXmlData {
	
	private Document document,newdocument;
    private Node rootNode;
 //   private Vector<Node> xmlNote = new Vector<Node>();
    
    
    
    private int lastId,actId;
    
//    private int lastIdWater,actIdWater;
    
    private int nodeId[]=new int[1000];
    
    private int pos =0;
    
    private int riverPos = 0;
    private int nodeIdRiver[]=new int[10000];
    private int railPos = 0;
    private int nodeIdRail[]=new int[10000];
    
    
    
    
	
	public ParseRailwayXmlData(){
		
	}


	public boolean initDom(){
		
		try{
		// ---- Parse XML file ----
	      DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
	      // factory.setNamespaceAware( true );
	      DocumentBuilder builder  = factory.newDocumentBuilder();
	      
	      document = builder.parse( new File( "output/osmfilter-pk-rail-river.xml" ) );
	      rootNode = document.getDocumentElement();
	      return true;
		}catch(Exception e){
			
		}
		return false;
	}
	public void getRailway(Vector<RailWay> vRailway,Vector<WaterWay> vWaterway){
		
		
		// ---- Get list of nodes to given tag ----
	      NodeList ndList = document.getElementsByTagName( "way" );
	      // System.out.println( "\nNode list at the beginning:" );
	      // printNodesFromList( ndList );
	      String st="";
	      // ---- Loop through the list of main nodes ----
	      for( int i=0; i<ndList.getLength(); i++ )
	      {
	        Node     nodeMain     = ndList.item( i );
	        Node     nodeChild    = null;
	        NodeList ndListChilds = nodeMain.getChildNodes();
	        pos =0;
	        lastId=0;
	        if( null == ndListChilds )  continue;
	        // Loop through the list of child nodes
	        for( int j=0; j<ndListChilds.getLength(); j++ )
	        {
	          nodeChild = ndListChilds.item( j );
	          if( null == nodeChild )  continue;
	          String sNodeName = nodeChild.getNodeName();
	          if( null == sNodeName )  continue;
	          int step=0;
	         
	          
	          boolean bName = false;
	          if( sNodeName.equals( "nd" ) ){
	        	  NamedNodeMap nnm= nodeChild.getAttributes();
		            // System.out.println(nnm.toString() );
		             for( int k=0; k<nnm.getLength(); k++ )
			        {
		          	 	Node n=nnm.item(k);
		          	 	if(n.getNodeName().equals("ref")){	
		          	 		int id=Integer.parseInt(n.getNodeValue());
		          	 		nodeId[pos] = id;
		          	 		pos++;
		          	 		nodeId[pos] =0;
		          	 	/*	if(lastId==0){
		          	 			lastId=id;
		          	 			
		          	 		}else{
		          	 			RailWay rw=new RailWay(lastId, id);
		          	 			lastId=id;
		          	 			vRailway.add(rw);
		          	 		}  */
		          	 		
		          	 		
		          	 	}
		          	 	
		          	 	
		          	 	
			        }
	          }
	          if( sNodeName.equals( "tag" ) ){
	        	  NamedNodeMap nnm= nodeChild.getAttributes();
		            // System.out.println(nnm.toString() );
		             for( int k=0; k<nnm.getLength(); k++ )
			        {
		          	 	Node n=nnm.item(k);
		          	 	if(n.getNodeName().equals("k")){	
		          	 		int s;
		          	 		if(n.getNodeValue().equals("railway")){
		          	 			
		          	 			
		          	 			for(s=0;s<pos-1;s++){
		          	 				RailWay rw=new RailWay(nodeId[s],nodeId[s+1] );
		          	 				nodeIdRail[riverPos++]=nodeId[s];
			          	 	//		lastId=id;
			          	 			vRailway.add(rw);
		          	 			}
		          	 			nodeIdRail[riverPos++]=nodeId[s];
		          	 		}
		          	 		if(n.getNodeValue().equals("waterway")){
		          	 			for(s=0;s<pos-2;s++){
		          	 				WaterWay ww=new WaterWay(nodeId[s],nodeId[s+1] );
		          	 				nodeIdRiver[riverPos++]=nodeId[s];
			          	 	//		lastId=id;
			          	 			vWaterway.add(ww);
		          	 			}
		          	 			nodeIdRiver[riverPos++]=nodeId[s];
		          	 		}
		          	 	/*	if(lastId==0){
		          	 			lastId=id;
		          	 			
		          	 		}else{
		          	 			RailWay rw=new RailWay(lastId, id);
		          	 			lastId=id;
		          	 			vRailway.add(rw);
		          	 		} */
		          	 		
		          	 		
		          	 	}
		          	 	
		          	 	
		          	 	
			        }
	          }
	          
	       
	        }
	       
	      }
	      	System.out.println(GetData.getTime()+ "Ref Ways:"+vRailway.size());
	      }

	
	public void getStationsAndNodes(Vector<RailNode> vRailnode,Vector<RailwayStation> vRailstations){
		String stLat="";
		String stLon="";
		int nid=0;
		int step=0;
		
		
		
		
		// ---- Get list of nodes to given tag ----
	      NodeList ndList = document.getElementsByTagName( "node" );
	      // System.out.println( "\nNode list at the beginning:" );
	      // printNodesFromList( ndList );
	      String st="";
	      boolean added=false;
	      // ---- Loop through the list of main nodes ----
	      for( int i=0; i<ndList.getLength(); i++ )
	      {
	        Node     nodeMain     = ndList.item( i );
	        Node     nodeChild    = null;
	        step=0;
	        NamedNodeMap nnmMain= nodeMain.getAttributes();
            // System.out.println(nnm.toString() );
             for( int k=0; k<nnmMain.getLength(); k++ )
	        {
	      	 	Node n=nnmMain.item(k);
	      	 	if(n.getNodeName().equals("id")){
	      	 		nid=Integer.parseInt(n.getNodeValue());      	 		
	      	 		step++;
	      	 		if(step>2){
		      	 		RailNode rn=new RailNode(nid, stLat, stLon);
		      	 		vRailnode.add(rn);
		      	 		
		      	 	}
	      	 	}
	      	 	if(n.getNodeName().equals("lat")){
	      	 		stLat=n.getNodeValue();      	 		
	      	 		step++;
	      	 		if(step>2){
		      	 		RailNode rn=new RailNode(nid, stLat, stLon);
		      	 		vRailnode.add(rn);
		      	 		
		      	 	}
	      	 	}
	      	 	if(n.getNodeName().equals("lon")){
	      	 		stLon=n.getNodeValue();        	 		
	      	 		step++;
	      	 		if(step>2){
		      	 		RailNode rn=new RailNode(nid, stLat, stLon);
		      	 		vRailnode.add(rn);
		      	 		
		      	 	}
	      	 	}
	      	 	
	      	 	
	        }
             
     	        
     	        NodeList ndListChilds = nodeMain.getChildNodes();
     	        
     	        if( null == ndListChilds )  continue;
     	        // Loop through the list of child nodes
     	        for( int j=0; j<ndListChilds.getLength(); j++ )
     	        {
     	          nodeChild = ndListChilds.item( j );
     	          if( null == nodeChild )  continue;
     	          String sNodeName = nodeChild.getNodeName();
     	          if( null == sNodeName )  continue;
     	          step=0;
     	          
     	          boolean bName = false;
     	          if( sNodeName.equals( "tag" ) )
     	          {
     	             NamedNodeMap nnm= nodeChild.getAttributes();
     	            // System.out.println(nnm.toString() );
     	             for( int k=0; k<nnm.getLength(); k++ )
     		        {
               	 	Node n=nnm.item(k);
               	 	if(n.getNodeName().equals("k")&& n.getNodeValue().equals("name")){
               	 	//	System.out.println("NAME:");
               	 		bName = true;
               	 		
               	 	}
               	 	
               	 	if(n.getNodeName().equals("v")&& bName){
               	 		bName = false;
               	 	//	System.out.println("VALUE:"+n.toString());
               	 		
               	 		st=n.getNodeValue();
               	 		RailwayStation rst=new RailwayStation(st, stLat, stLon);
               	 		vRailstations.add(rst);
               	 		//System.out.println("NODE:"+n.toString() );
               	 		
               	 	}
               	 	
               	 	
               	 	
               	 		
               	 	
               	 	
     		        }
     	          }
     	        }
     	        
	        
	        
	     	
	      }
	      System.out.println(GetData.getTime()+ "Ref Nodes:"+vRailnode.size());
	}
	
	

}
