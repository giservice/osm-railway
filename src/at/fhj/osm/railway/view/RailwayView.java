package at.fhj.osm.railway.view;

import java.awt.Dimension;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.text.AttributedCharacterIterator;
import java.util.Vector;

import javax.swing.JPanel;

import at.fhj.osm.railway.component.RailwayStation;

public class RailwayView extends JPanel{

	/**
	 * 
	 */
	private static final long serialVersionUID = -384326067871796982L;
	
	int points[][] = {{473939,151553},{474139,152794},{474451,152921},{474546,153289}};
	int offset_lat=473800;
	int offset_lon=151500;
	int x,y;
	int width=960;
	int heigth = 480;
	
	Font myFont;
	
	Vector<RailwayStation> vrws = new Vector<RailwayStation>();
	
	public RailwayView(){
		this.setPreferredSize(new Dimension(width,heigth));
		myFont=new Font("Courier", Font.ITALIC|Font.PLAIN, 9);
		vrws.add(new RailwayStation("Niklasdorf", 473939,151553));
		vrws.add(new RailwayStation("Bruck/Mur", 474139,152794));
		vrws.add(new RailwayStation("Kapfenberg", 474451,152921));
		vrws.add(new RailwayStation("Kapfenberg FH", 474546,153289));
		
		
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
			  g.drawRect(x, y, 4, 4);
			  g2d.drawString(rs.name, x-10, y-10);
		  }
	/*	  
		  for(int j=0;j<points.length;j++){
			  x = (points[j][1]-offset_lon)/2;
			  y = heigth-(points[j][0]-offset_lat)/2;
			  g.drawRect(x, y, 4, 4);
		  } */
		  
		  g2d.drawString("lat="+offset_lat, width/2, heigth -10);
		  g2d.drawString("lon="+offset_lon, 10, heigth/2);
		  
		 
		  
		 
		  
	  }
	
	

}
