package at.fhj.osm.railway.view;

import javax.swing.JFrame;


public class MainFrame extends JFrame {

	/**
	 * 
	 */
	private static final long serialVersionUID = 4993534351268084775L;
	
	public MainFrame(){
		this.setTitle("Railways");
		this.setDefaultCloseOperation( JFrame.EXIT_ON_CLOSE ); 
		RailwayView rwv=new RailwayView();
		this.setContentPane(rwv);
		this.pack();
		this.setVisible(true);
	}

}
