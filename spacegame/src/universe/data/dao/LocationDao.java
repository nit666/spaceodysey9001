package universe.data.dao;

import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;

import universe.data.PathElement;
import universe.data.Planet;
import universe.datatypes.Location;
import universe.helper.TimeHelper;

public class LocationDao {

	protected LocationDao() {
		// so this class can't be constructed
		
		// a default planet for starting
		createPlanet("Callista", "Callista");
	}
	
	Map<String, Location> locationMap = new HashMap<String, Location>();
	static LocationDao instance;
	
	public static LocationDao getInstance() {
		if (instance == null) {
			instance = new LocationDao();
		}
		return instance;
	}
	
	protected void createPlanet(String id, String name) {
		Planet p = new Planet(id, name);
		if (!locationMap.containsKey(id)) {
			locationMap.put(id, p);
		} else {
			throw new IllegalStateException("Duplicate location key: " + id);
		}
	}
	
	protected Planet getPlanetById(String id) {
		Location p = locationMap.get(id);
		if (p instanceof Planet) {
			return (Planet) p;
		} else {
			throw new IllegalStateException("The id " + id + " is not for a planet");
		}
	}
	
	/** 
	 * Get the location by ID, useful for getting the location that a player ship is at and then 
	 * finding out where they can go from there. For example, if they are on a planet, you can get the
	 * moons and wormholes that are available from that planet. 
	 */
	public Location getLocationById(String id) {
		if (locationMap.containsKey(id)) {
			return locationMap.get(id);
		} else {
			throw new IllegalStateException("No such location with id = " + id);
		}
	}
	
	/**
	 * Find the shortest path to a destination and return a list of path elements that can be used for the
	 * ship to travel to that path. 
	 * 
	 * @param currentLocation the current location for the ship
	 * @param destination where they want to path to
	 * @param shipSpeed the speed they can travel in normal space
	 * @return
	 */
	public List<PathElement> findPathFromTo(Location currentLocation, Location destination, double shipSpeed) {
		return currentLocation.createPathTo(currentLocation, destination, TimeHelper.getCurrentTime(), shipSpeed, new HashSet<Location>());
	}
	
	/**
	 * A default location so we can create new players here, this will be expanded.
	 * @return the default location
	 */
	public Location getStartingLocation() {
		return getLocationById("Callista");
	}
}
