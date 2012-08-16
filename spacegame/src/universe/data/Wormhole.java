package universe.data;

public class Wormhole implements Location {

	String id;
	Planet startPlanet;
	double startPeriod;
	Planet endPlanet;
	double endPeriod;
	double travelTime;
	
	public Wormhole(String id, Planet start, double sPeriod, Planet end, double ePeriod, double travelTime) {
		this(id, sPeriod, end, ePeriod, travelTime);
		this.startPlanet = start;
	}
	
	public Wormhole(String id, double sPeriod, Planet end, double ePeriod, double travelTime) {
		this.id = id;
		this.startPeriod = sPeriod;
		this.endPlanet = end;
		this.endPeriod = ePeriod;
		this.travelTime = travelTime;
	}
	
	public String getId() {
		return id;
	}

	public Planet getStartPlanet() {
		return startPlanet;
	}
	
	public void setStartPlanet(Planet p) {
		this.startPlanet = p;
	}

	public double getStartPeriod() {
		return startPeriod;
	}

	public Planet getEndPlanet() {
		return endPlanet;
	}

	public double getEndPeriod() {
		return endPeriod;
	}
	
}
