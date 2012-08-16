package universe.data;

import java.util.LinkedList;
import java.util.List;
import java.util.Map;

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
}
