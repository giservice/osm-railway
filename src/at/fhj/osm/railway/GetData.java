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
	   try {
		      // ---- Parse XML file ----
		      DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
		      // factory.setNamespaceAware( true );
		      DocumentBuilder builder  = factory.newDocumentBuilder();
		      Document        document = builder.parse( new File( "res/proleb-kapfenberg.osm" ) );
		      Node            rootNode = document.getDocumentElement();

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
		            	 			System.out.println("STATION:"+st );
		            	 		}
		            	 	
		            	 	
		            	 	}
		            	 	
		            	 	if(n.getNodeName().equals("v")&& n.getNodeValue().equals("station")){
		            	 	//	System.out.println(n.toString() );
		            	 		step++;
		            	 		if(step>1){
		            	 			System.out.println("STATION:"+st );
		            	 		}
		            	 		
		            	 	}
		            	 
		            	 	
		            	 	
				        }
		            
		            
		          }
		        }
		      }
	   }catch(Exception ex){
		   
		   
	   }

 /*     XMLInputFactory inputFactory = XMLInputFactory.newInstance();
      XMLStreamReader stax = inputFactory.createXMLStreamReader( new StreamSource( new File( "res/proleb-kapfenberg.osm" ) ) );
      while( stax.hasNext() ) {
         stax.next();
		 if(stax.hasName()){
			System.out.println(""+stax.getName());
			if(stax.getName().equals("node")){
				stax.ha
			if( stax.getAttributeCount() > 0 ) {
            for( int i = 0; i < stax.getAttributeCount(); i++ ) {				
               System.out.print( " " + stax.getAttributeLocalName(i) + "=\""
                                     + stax.getAttributeValue(i) + "\"" );
            }
         }
			
			}
		 }
      
      } */
      
   }
}