package at.fhj.osm.railway.component;

public class RailwayStation {
	public int lat;
	public int lon;
	public String name;
	
	
	public RailwayStation(String n,String stlat,String stlon){
		name = n;
		float flon = Float.valueOf(stlon);
		float flat = Float.valueOf(stlat);
		lon=(int) (flon*10000);
		lat=(int) (flat*10000);
		
	}
	


}
