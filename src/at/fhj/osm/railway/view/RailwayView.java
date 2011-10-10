package at.fhj.osm.railway.view;

import java.awt.Dimension;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.text.AttributedCharacterIterator;
import java.util.Vector;

import javax.swing.JPanel;

import at.fhj.osm.railway.component.RailWay;
import at.fhj.osm.railway.component.RailwayStation;

public class RailwayView extends JPanel{

	/**
	 * 
	 */
	private static final long serialVersionUID = -384326067871796982L;
	
	int points[][] = {{473939,151553},{474139,152794},{474451,152921},{474546,153289}};
	int offset_lat=473700;
	int offset_lon=151050;
	int x,y;
	int x1,x2,y1,y2;
	int width=1300;
	int heigth = 500;
	
	Font myFont;
	
	Vector<RailwayStation> vrws = new Vector<RailwayStation>();
	
	Vector<RailWay> vRailway;
	
	public RailwayView(Vector<RailWay> vway,Vector<RailwayStation> vrws){
		this.vRailway = vway;
		this.vrws = vrws;
		this.setPreferredSize(new Dimension(width,heigth));
		myFont=new Font("Arial", Font.PLAIN, 10);
		
	}
	
	/**
	   * Hier wird das Fenster neu gezeichnet
	   */
	  public void paintComponent( Graphics g ) 
	  {    
		  Graphics2D g2d=(Graphics2D)g;
		  g2d.setFont(myFont);
			//Font erzeugen
		  for(int k=0;k<vrws.size();k++){
			  RailwayStation rs=vrws.elementAt(k);
			  x = (rs.lon-offset_lon)/2;
			  y = heigth-(rs.lat-offset_lat)/2;
			  g.drawRect(x-2, y-2, 4, 4);
			  g2d.drawString(rs.name, x-10, y-20);
		  }
	/*	  
		  for(int j=0;j<points.length;j++){
			  x = (points[j][1]-offset_lon)/2;
			  y = heigth-(points[j][0]-offset_lat)/2;
			  g.drawRect(x, y, 4, 4);
		  } */
		  
		  g2d.drawString("lat="+offset_lat, width/2, heigth -10);
		  g2d.drawString("lon="+offset_lon, 10, heigth/2);
		  
		  for (RailWay railWay : vRailway) {
			  x1 = (railWay.fromNode.lon-offset_lon)/2;
			  y1 = heigth-(railWay.fromNode.lat-offset_lat)/2;
			  x2 = (railWay.toNode.lon-offset_lon)/2;
			  y2 = heigth-(railWay.toNode.lat-offset_lat)/2;
			  g.drawLine(x1, y1, x2, y2);
		  }
		  
		 
		  
		 
		  
	  }
	
	

}
