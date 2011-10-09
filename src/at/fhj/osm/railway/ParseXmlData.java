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

public class ParseXmlData {
	
	private Document document,newdocument;
    private Node rootNode;
    private Vector<Node> xmlNote = new Vector<Node>();
    
    private int lastId,actId;
    
    private int nodeId[]=new int[10000];
	
	public ParseXmlData(){
		
	}
	// This method writes a DOM document to a file
	public static void writeXmlFile(Document doc, String filename) {
	    try {
	        // Prepare the DOM document for writing
	        Source source = new DOMSource(doc);

	        // Prepare the output file
	        File file = new File(filename);
	        Result result = new StreamResult(file);

	        // Write the DOM document to the file
	        Transformer xformer = TransformerFactory.newInstance().newTransformer();
	        xformer.transform(source, result);
	    } catch (TransformerConfigurationException e) {
	    } catch (TransformerException e) {
	    }
	}

	public boolean newXml(){
		try{
		// ---- Parse XML file ----
	      DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
	      // factory.setNamespaceAware( true );
	      DocumentBuilder builder  = factory.newDocumentBuilder();
	      newdocument = builder.newDocument();
	      Element root = newdocument.createElement("mado");
	        root.setAttribute("osm", "railway");
	        String text = "\n";
        	Node linebreak;
	        //newdocument.createE
	        
	     
	        for(int i=0;i<xmlNote.size();i++){
	        	Node n = xmlNote.elementAt(i);
	      
	        	linebreak = newdocument.createTextNode(text);
	        	root.appendChild(linebreak);
	        	
	        	root.appendChild(newdocument.importNode(n, true));
	        }
	        
	        newdocument.appendChild(root);
	        writeXmlFile(newdocument, "output/3.xml");
	        
	        
	  
	      return true;
		}catch(Exception e){
			e.printStackTrace();
		}
		return false;
		
		
	}
	public boolean initDom(){
		
		try{
		// ---- Parse XML file ----
	      DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
	      // factory.setNamespaceAware( true );
	      DocumentBuilder builder  = factory.newDocumentBuilder();
	      
	      document = builder.parse( new File( "res/proleb-kapfenberg.osm" ) );
	      rootNode = document.getDocumentElement();
	      return true;
		}catch(Exception e){
			
		}
		return false;
	}
	public void getRailway(){
		lastId=0;
		
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
	        actId=lastId;
	        
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
		          	 		nodeId[actId] =  Integer.parseInt(n.getNodeValue());
		          	 		actId++;
		          	 	
		          	 		
		          	 	}
			        }
	          }
	          
	          if( sNodeName.equals( "tag" ) )
	          {
	             NamedNodeMap nnm= nodeChild.getAttributes();
	            // System.out.println(nnm.toString() );
	             for( int k=0; k<nnm.getLength(); k++ )
		        {
          	 	Node n=nnm.item(k);
          	 
          	 		if(n.getNodeName().equals("k")&& n.getNodeValue().equals("railway")){
          	 	//	System.out.println(n.toString() );
          	 		step++;
          	 		if(step>1){
          	 			//System.out.println(GetData.getTime()+":RAIL:"+st );
          	 			//stations.add(nodeChild.cloneNode(false));
          	 			lastId=actId;
          	 			xmlNote.add(nodeMain);
          	 		}
          	 	
          	 	
          	 	}
          	 	
          	 	if(n.getNodeName().equals("v")&& n.getNodeValue().equals("rail")){
          	 	//	System.out.println(n.toString() );
          	 		step++;
          	 		if(step>1){
          	 		//	System.out.println(GetData.getTime()+":RAIL:"+st );
          	 			lastId=actId;
          	 			//stations.add(nodeChild.cloneNode(false));
          	 			xmlNote.add(nodeMain);
          	 			
          	 			
          	 		}
          	 		
          	 	}   
          	 	
		        }
	          }
	        }
	       
	      }
	      	System.out.println("Ref Nodes:"+lastId);
	      }

	
	public void getStationsAndNodes(){
		
		
		
		
		
		// ---- Get list of nodes to given tag ----
	      NodeList ndList = document.getElementsByTagName( "node" );
	      // System.out.println( "\nNode list at the beginning:" );
	      // printNodesFromList( ndList );
	      String st="";
	      // ---- Loop through the list of main nodes ----
	      for( int i=0; i<ndList.getLength(); i++ )
	      {
	        Node     nodeMain     = ndList.item( i );
	        Node     nodeChild    = null;
	        NamedNodeMap nnmMain= nodeMain.getAttributes();
            // System.out.println(nnm.toString() );
             for( int k=0; k<nnmMain.getLength(); k++ )
	        {
	      	 	Node n=nnmMain.item(k);
	      	 	if(n.getNodeName().equals("id")){
	      	 		int nid=Integer.parseInt(n.getNodeValue());
	      	 		for(int g=0;g<lastId+1;g++){
	      	 			if(nodeId[g]==nid){
	      	 				xmlNote.add(nodeMain);
	      	 				continue;
	      	 			}
	      	 			
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
	          int step=0;
	          
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
          	 		//System.out.println("NODE:"+n.toString() );
          	 		
          	 	}
          	 		if(n.getNodeName().equals("k")&& n.getNodeValue().equals("railway")){
          	 	//	System.out.println(n.toString() );
          	 		step++;
          	 		if(step>1){
          	 			System.out.println(GetData.getTime()+":STATION:"+st );
          	 			//stations.add(nodeChild.cloneNode(false));
          	 			xmlNote.add(nodeMain);
          	 		}
          	 	
          	 	
          	 	}
          	 	
          	 	if(n.getNodeName().equals("v")&& n.getNodeValue().equals("station")){
          	 	//	System.out.println(n.toString() );
          	 		step++;
          	 		if(step>1){
          	 			System.out.println(GetData.getTime()+":STATION:"+st );
          	 			//stations.add(nodeChild.cloneNode(false));
          	 			xmlNote.add(nodeMain);
          	 			
          	 			
          	 		}
          	 		
          	 	}   
          	 	
		        }
	          }
	        }
	      }
	}
	
	

}
