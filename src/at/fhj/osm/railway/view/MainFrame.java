package at.fhj.osm.railway.view;

import java.util.Vector;

import javax.swing.JFrame;

import at.fhj.osm.railway.component.RailWay;


public class MainFrame extends JFrame {

	/**
	 * 
	 */
	private static final long serialVersionUID = 4993534351268084775L;
	
	public MainFrame(Vector<RailWay> vRailway){
		this.setTitle("Railways");
		this.setDefaultCloseOperation( JFrame.EXIT_ON_CLOSE ); 
		RailwayView rwv=new RailwayView(vRailway);
		this.setContentPane(rwv);
		this.pack();
		this.setVisible(true);
	}

}
