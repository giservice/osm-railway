package at.fhj.osm.railway.view;

import java.awt.Dimension;
import java.awt.event.AdjustmentEvent;
import java.awt.event.AdjustmentListener;
import java.util.Vector;

import javax.swing.JFrame;
import javax.swing.JScrollPane;

import at.fhj.osm.railway.component.RailWay;
import at.fhj.osm.railway.component.RailwayStation;


public class MainFrame extends JFrame implements AdjustmentListener {

	/**
	 * 
	 */
	private static final long serialVersionUID = 4993534351268084775L;
	
	RailwayView rwv;
	
	JScrollPane sp;
	
	public MainFrame(Vector<RailWay> vRailway,Vector<RailwayStation> vRailstations){
		this.setTitle("Railways");
		this.setDefaultCloseOperation( JFrame.EXIT_ON_CLOSE ); 
		rwv=new RailwayView(vRailway,vRailstations);
		sp=new JScrollPane(rwv);
		
		this.setPreferredSize(new Dimension(640, 480));
		sp.getHorizontalScrollBar().addAdjustmentListener(this);
		sp.getVerticalScrollBar().addAdjustmentListener(this);
		
		this.setContentPane(sp);
		this.pack();
		this.setVisible(true);
	}

	@Override
	public void adjustmentValueChanged(AdjustmentEvent e) {
		// TODO Auto-generated method stub
		
		if(sp!=null){
			//rwv.repaint();
			sp.repaint();
		}
		
	}

}
