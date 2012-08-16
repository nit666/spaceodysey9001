package universe.data;

public class Wormhole implements Orbitable {

	String id;
	Planet startPlanet;
	double startPeriod;
	Planet endPlanet;
	double endPeriod;
	long travelTime;
	
	public Wormhole(String id, Planet start, double sPeriod, Planet end, double ePeriod, long travelTime) {
		this(id, sPeriod, end, ePeriod, travelTime);
		this.startPlanet = start;
	}
	
	public Wormhole(String id, double sPeriod, Planet end, double ePeriod, long travelTime) {
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

	public double getPeriod() {
		return getStartPeriod();
	}

	public Planet getParent() {
		return getStartPlanet();
	}

	public long getTravelTime() {
		return travelTime;
	}
	
	public Orbitable getDestination() {
		return new WormholeDestinationOrbit(this);
	}
	
	public static class WormholeDestinationOrbit implements Orbitable {

		Wormhole wh;
		
		public WormholeDestinationOrbit(Wormhole wh) {
			this.wh = wh;
		}
		
		public Planet getParent() {
			return wh.getEndPlanet();
		}
	
		public double getPeriod() {
			return wh.getEndPeriod();
		}
	
		public String getId() {
			return wh.getId();
		}
	}
}
