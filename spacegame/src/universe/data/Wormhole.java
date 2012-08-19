package universe.data;

import java.util.LinkedList;
import java.util.List;
import java.util.Set;

import universe.datatypes.Location;
import universe.datatypes.Orbitable;

public class Wormhole extends Orbitable {

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
	
	/**
	 * Assumes that the startLocation is this wormhole, and will go to the next planet to look for a path.
	 */
	public List<PathElement> createPathTo(Location startLocation, Location newLocation, long startTime, double shipSpeed, Set<Location> hits) {
		if (!startLocation.getId().equals(getId())) {
			throw new IllegalStateException("Can't create paths from places that aren't myself");
		}
		if (hits.contains(this)) {
			return new LinkedList<PathElement>();
		}
		hits.add(this);
		PathElement destinationPath = calculatePathTime(startTime);
		List<PathElement> path = getDestination().getParent().createPathTo(getDestination(), newLocation, destinationPath.getArrivalTime(), shipSpeed, hits);
		if (path.size() > 0) {
			path.add(0, destinationPath);
		}
		return path;
	}
	
	/** 
	 * calculate the path including arrival time for a wormhole.
	 * @param wh
	 * @param pathTime
	 * @return
	 */
	public PathElement calculatePathTime(long pathTime) {
		long arrivalTime = pathTime + getTravelTime();
		return new PathElement(getDestination(), arrivalTime);
	}

	/**
	 * A type of orbitable object that can be used to represent the end of a wormhole for the purposes of path caculations.
	 */
	public static class WormholeDestinationOrbit extends Orbitable {

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
