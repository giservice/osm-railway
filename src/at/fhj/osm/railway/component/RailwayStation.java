package at.fhj.osm.railway.component;

public class RailwayStation {
	public int lat;
	public int lon;
	public String name;
	
	
	public RailwayStation(String n,int lat,int lon){
		name = n;
		this.lat= lat;
		this.lon= lon;
	}

}
