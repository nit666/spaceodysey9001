package universe.data;

import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Set;

import universe.datatypes.Location;
import universe.helper.PathHelper;

public class Planet implements Location {

	String id;
	String name;
	
	List<Moon> moons = new LinkedList<Moon>();
	List<Wormhole> wormholes = new LinkedList<Wormhole>();
	
	public Planet(String id, String name) {
		this.id = id;
		this.name = name;
	}
	
	public Planet addMoon(Moon moon, Map<String, Location> locationMap) {
		moon.setParent(this);
		moons.add(moon);
		locationMap.put(moon.getId(), moon);
		return this;
	}
	
	// only add wormholes that start from this planet
	public Planet addWormhole(Wormhole wh, Map<String, Location> locationMap) {
		wh.setStartPlanet(this);
		wormholes.add(wh);
		locationMap.put(wh.getId(), wh);
		return this;
	}

	public String getId() {
		return id;
	}

	public String getName() {
		return name;
	}

	public List<Moon> getMoons() {
		return moons;
	}

	public List<Wormhole> getWormholes() {
		return wormholes;
	}

	public List<PathElement> createPathTo(Location startLocation, Location newLocation, long startTime, double shipSpeed, Set<Location> hits) {
		// if the start location is not the current location or a child of the current location throw an exception
		if (!PathHelper.inSameSystem(this, startLocation)) {
			throw new IllegalStateException("Trying to create an illegal path");
		}
		
		if (hits.contains(this)) {
			return new LinkedList<PathElement>();
		}
		hits.add(this);
		
		List<PathElement> path = new LinkedList<PathElement>();
		
		// if destination is this planet, or a child of this planet, then create path element and return
		if (newLocation.getId().equals(getId()) || isChildOf(newLocation)) {
			path.add(PathHelper.calculatePathTime(startLocation, newLocation, shipSpeed, startTime));
		}

		// if the destination is not in this planet system (but the start is) 
		// 1) ask the wormholes for a path
		// 2) get the fastest path 
		// 3) create a path from start to wormhole with fastest path and then add the wormhole path to it
		// 4) return
		else if (!PathHelper.inSameSystem(startLocation, newLocation)) {
			long shortestTime = 0;
			List<PathElement> shortestPath = new LinkedList<PathElement>();
			for (Wormhole w : getWormholes()) {
				PathElement wormHolePath = PathHelper.calculatePathTime(startLocation, w, shipSpeed, startTime);
				List<PathElement> testPath = w.createPathTo(w, newLocation, wormHolePath.getArrivalTime(), shipSpeed, hits);
				
				// if there is any path elements, we have found a way
				if (testPath.size() > 0) {
					testPath.add(0, wormHolePath);
					long testTime = PathHelper.getDestinationTime(testPath, wormHolePath.getArrivalTime());
					if (shortestTime == 0 || testTime < shortestTime) {
						shortestPath = testPath;
					}
				}
			}
			path = shortestPath;
		}
		return path;
	}
	
	public boolean isChildOf(Location child) {
		for (Moon m : getMoons()) {
			if (m.getId().equals(child.getId())) {
				return true;
			}
		}
		for (Wormhole w : getWormholes()) {
			if (w.getId().equals(child.getId())) {
				return true;
			}
		}
		return false;
	}
}
