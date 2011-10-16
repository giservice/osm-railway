package at.fhj.osm.railway.component;

public class OSMWay {
	public int from=0;
	public int to=0;
	
	public RailNode fromNode = null;
	public RailNode toNode = null;
	
	public OSMWay(int a,int b){
		from = a;
		to = b;
	}

}

