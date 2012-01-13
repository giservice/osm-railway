package at.fhj.osm.railway.view;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.text.AttributedCharacterIterator;
import java.util.Vector;

import javax.swing.JPanel;

import at.fhj.osm.railway.component.RailWay;
import at.fhj.osm.railway.component.RailwayStation;
import at.fhj.osm.railway.component.StreetWay;
import at.fhj.osm.railway.component.WaterNode;
import at.fhj.osm.railway.component.WaterWay;

public class RailwayView extends JPanel{

	/**
	 * 
	 */
	private static final long serialVersionUID = -384326067871796982L;
	
	int offset_lat=473700;
	int offset_lon=151050;
	int x,y;
	int x1,x2,y1,y2;
//	int width=1300;
//	int heigth = 500;
	int width=2600;
	int heigth = 1000;
	
	int max_lat,lat;
	int max_lon,lon;
	
	int div=2;
	
	Font myFont;
	
	Vector<RailwayStation> vrws;
	
	Vector<RailWay> vRailway;
	
	Vector<WaterWay> vWaterway;
	
	Vector<StreetWay> vStreetway;
	
	public RailwayView(Vector<RailWay> vway,Vector<RailwayStation> vrws,Vector<WaterWay> vww,Vector<StreetWay> vsw){
		this.vRailway = vway;
		this.vrws = vrws;
		this.vWaterway = vww;
		this.vStreetway = vsw;
		this.setPreferredSize(new Dimension(width,heigth));
		myFont=new Font("Arial", Font.PLAIN, 10);
		max_lat=offset_lat + heigth * div;
		max_lon=offset_lon + width * div;
	}
	
	/**
	   * Hier wird das Fenster neu gezeichnet
	   */
	  public void paintComponent( Graphics g ) 
	  {    
		  Graphics2D g2d=(Graphics2D)g;
		  g2d.setFont(myFont);
		  g.setColor(Color.BLACK);
			//Font erzeugen
		  for(int k=0;k<vrws.size();k++){
			  RailwayStation rs=vrws.elementAt(k);
			  if(rs.lon<max_lon && rs.lon>offset_lon && rs.lat<max_lat && rs.lat>offset_lat){
				  x = (rs.lon-offset_lon)/div;
				  y = heigth-(rs.lat-offset_lat)/div;
			  
				  g.drawRect(x-2, y-2, 4, 4);
				  g2d.drawString(rs.name, x-10, y-20);
			  }
		  }
	
		  
		  g2d.drawString("lat="+offset_lat, width/2, heigth -10);
		  g2d.drawString("lon="+offset_lon, 10, heigth/2);
		  
		  g2d.drawString("lat="+max_lat, width/2, 10);
		  g2d.drawString("lon="+max_lon, width-100, heigth/2);
		  
		  for (RailWay railWay : vRailway) {
			  if(railWay.fromNode.lon<max_lon && railWay.fromNode.lon>offset_lon && railWay.fromNode.lat<max_lat && railWay.fromNode.lat>offset_lat){
				  if(railWay.toNode.lon<max_lon && railWay.toNode.lon>offset_lon && railWay.toNode.lat<max_lat && railWay.toNode.lat>offset_lat){
							
					  x1 = (railWay.fromNode.lon-offset_lon)/div;
					  y1 = heigth-(railWay.fromNode.lat-offset_lat)/div;
					  x2 = (railWay.toNode.lon-offset_lon)/div;
					  y2 = heigth-(railWay.toNode.lat-offset_lat)/div;
					  g.drawLine(x1, y1, x2, y2);
				  }
			 }
		  }
		  g.setColor(Color.BLUE);
		  for (WaterWay waterWay : vWaterway) {
			  if(waterWay.fromNode.lon<max_lon && waterWay.fromNode.lon>offset_lon && waterWay.fromNode.lat<max_lat && waterWay.fromNode.lat>offset_lat){
				  if(waterWay.toNode.lon<max_lon && waterWay.toNode.lon>offset_lon && waterWay.toNode.lat<max_lat && waterWay.toNode.lat>offset_lat){
							
					  x1 = (waterWay.fromNode.lon-offset_lon)/div;
					  y1 = heigth-(waterWay.fromNode.lat-offset_lat)/div;
					  x2 = (waterWay.toNode.lon-offset_lon)/div;
					  y2 = heigth-(waterWay.toNode.lat-offset_lat)/div;
					  g.drawLine(x1, y1, x2, y2);
				  }
			 }
		  }
		  
		  g.setColor(Color.RED);
		  for (StreetWay streetWay : vStreetway) {
			  if(streetWay.fromNode.lon<max_lon && streetWay.fromNode.lon>offset_lon && streetWay.fromNode.lat<max_lat && streetWay.fromNode.lat>offset_lat){
				  if(streetWay.toNode.lon<max_lon && streetWay.toNode.lon>offset_lon && streetWay.toNode.lat<max_lat && streetWay.toNode.lat>offset_lat){
							
					  x1 = (streetWay.fromNode.lon-offset_lon)/div;
					  y1 = heigth-(streetWay.fromNode.lat-offset_lat)/div;
					  x2 = (streetWay.toNode.lon-offset_lon)/div;
					  y2 = heigth-(streetWay.toNode.lat-offset_lat)/div;
					  g.drawLine(x1, y1, x2, y2);
				  }
			 }
		  }
		  
		 
		  
		 
		  
	  }
	
	

}
