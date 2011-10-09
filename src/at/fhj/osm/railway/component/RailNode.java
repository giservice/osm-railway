package at.fhj.osm.railway.component;

public class RailNode {
	public int id;
	public int lon;
	public int lat;
	
	public RailNode(int id,String stlat,String stlon){
		this.id=id;
		float flon = Float.valueOf(stlon);
		float flat = Float.valueOf(stlat);
		lon=(int) (flon*10000);
		lat=(int) (flat*10000);
		
	}
	

}
