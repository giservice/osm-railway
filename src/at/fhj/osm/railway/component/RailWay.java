package at.fhj.osm.railway.component;

public class RailWay {
	public int from=0;
	public int to=0;
	
	public RailNode fromNode = null;
	public RailNode toNode = null;
	
	public RailWay(int a,int b){
		from = a;
		to = b;
	}

}
