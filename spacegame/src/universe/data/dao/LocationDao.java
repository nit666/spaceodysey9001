package universe.data.dao;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

import sun.reflect.generics.reflectiveObjects.NotImplementedException;
import universe.data.Location;
import universe.data.Moon;
import universe.data.Orbitable;
import universe.data.PathElement;
import universe.data.Planet;
import universe.data.Wormhole;
import universe.helper.OrbitMathHelper;
import universe.helper.TimeHelper;

public class LocationDao {

	static Map<String, Location> locationMap = new HashMap<String, Location>();
	
	/** 
	 * create a planet map, with moons and wormholes. Then all we need is the id where the player ship 
	 * currently is and can get the rest of the information that is needed.
	 * 
	 * The planets contain links to their moons and wormholes that leave from that planet. 
	 */
	static {
		// TODO fill in names for the planets
		// TODO fill in orbital period for moons and wormholes
		createPlanet("P1", "P1");
		createPlanet("P2", "P2");
		createPlanet("P3", "P3");
		createPlanet("P4", "P4");
		createPlanet("P5", "P5");
		createPlanet("P6", "P6");
		createPlanet("P7", "Mare");
		createPlanet("P8", "P8");
		createPlanet("P9", "P9");
		createPlanet("P10", "P10");
		createPlanet("P11", "P11");
		createPlanet("P12", "P12");
		createPlanet("P13", "P13");
		createPlanet("P14", "P14");
		createPlanet("P15", "P15");
		createPlanet("P16", "P16");
		createPlanet("P17", "P17");
		createPlanet("P18", "P18");
		createPlanet("P19", "P19");
		createPlanet("P20", "P20");
		
		getPlanetById("P1")
			.addMoon(new Moon("M1P1", "M1P1", TimeHelper.HoursToMilliSeconds(24)), locationMap)
			.addMoon(new Moon("M2P1", "M2P1", TimeHelper.HoursToMilliSeconds(35)), locationMap)
			.addMoon(new Moon("M3P1", "M3P1", TimeHelper.HoursToMilliSeconds(40)), locationMap)
			.addMoon(new Moon("M4P1", "M4P4", TimeHelper.HoursToMilliSeconds(64)), locationMap)
			.addWormhole(new Wormhole("W11", TimeHelper.HoursToMilliSeconds(59), getPlanetById("P18"), TimeHelper.HoursToMilliSeconds(32), 
					TimeHelper.HoursToMilliSeconds(4)), locationMap)
			.addWormhole(new Wormhole("W10", TimeHelper.HoursToMilliSeconds(70), getPlanetById("P10"), TimeHelper.HoursToMilliSeconds(153),
					TimeHelper.HoursToMilliSeconds(4)), locationMap);
		
		getPlanetById("P2")
			.addMoon(new Moon("M1P2", "M1P2", TimeHelper.HoursToMilliSeconds(300)), locationMap)
			.addWormhole(new Wormhole("W1", TimeHelper.HoursToMilliSeconds(76), getPlanetById("P1"), TimeHelper.HoursToMilliSeconds(134),
					TimeHelper.HoursToMilliSeconds(2.5f)), locationMap)
			.addWormhole(new Wormhole("W2", TimeHelper.HoursToMilliSeconds(89), getPlanetById("P4"), TimeHelper.HoursToMilliSeconds(45),
					TimeHelper.HoursToMilliSeconds(2)), locationMap)
			.addWormhole(new Wormhole("W3", TimeHelper.HoursToMilliSeconds(250), getPlanetById("P3"), TimeHelper.HoursToMilliSeconds(468),
					TimeHelper.HoursToMilliSeconds(2)), locationMap)
			.addWormhole(new Wormhole("W4", TimeHelper.HoursToMilliSeconds(24), getPlanetById("P10"), TimeHelper.HoursToMilliSeconds(39),
					TimeHelper.HoursToMilliSeconds(3)), locationMap);
		
		getPlanetById("P3")
			.addWormhole(new Wormhole("W3a", 0, getPlanetById("P2"), 0,
					TimeHelper.HoursToMilliSeconds(2)), locationMap);
		
		getPlanetById("P4")
			.addWormhole(new Wormhole("W2a", 0, getPlanetById("P2"), 0,
					TimeHelper.HoursToMilliSeconds(2)), locationMap);
		
		getPlanetById("P5")
			.addMoon(new Moon("M1P5", "M1P5", 0), locationMap)
			.addMoon(new Moon("M2P5", "M2P5", 0), locationMap)
			.addMoon(new Moon("M3P5", "M3P5", 0), locationMap)
			.addWormhole(new Wormhole("W4a", 0, getPlanetById("P2"), 0,
					TimeHelper.HoursToMilliSeconds(3)), locationMap)
			.addWormhole(new Wormhole("W5", 0, getPlanetById("P6"), 0,
					TimeHelper.HoursToMilliSeconds(2.5f)), locationMap);
		
		getPlanetById("P6")
			.addWormhole(new Wormhole("W5a", 0, getPlanetById("P5"), 0,
					TimeHelper.HoursToMilliSeconds(2.5f)), locationMap)
			.addWormhole(new Wormhole("W8", 0, getPlanetById("P9"), 0, 
					TimeHelper.HoursToMilliSeconds(2)), locationMap)
			.addWormhole(new Wormhole("W7", 0, getPlanetById("P8"), 0,
					TimeHelper.HoursToMilliSeconds(1)), locationMap)
			.addWormhole(new Wormhole("W6", 0, getPlanetById("P7"), 0,
					TimeHelper.HoursToMilliSeconds(1.5f)), locationMap);
		
		getPlanetById("P7")
			.addMoon(new Moon("M1P7", "Mirror Mare", 0), locationMap)
			.addWormhole(new Wormhole("W6a", 0, getPlanetById("P6"), 0, 
					TimeHelper.HoursToMilliSeconds(1.5f)), locationMap);
		
		getPlanetById("P8")
			.addWormhole(new Wormhole("W7a", 0, getPlanetById("P6"), 0,
					TimeHelper.HoursToMilliSeconds(1)), locationMap);
		
		getPlanetById("P9")
			.addMoon(new Moon("M1P9", "M1P9", 0), locationMap)
			.addWormhole(new Wormhole("W8a", 0, getPlanetById("P6"), 0,
					TimeHelper.HoursToMilliSeconds(2)), locationMap);
		
		getPlanetById("P10")
			.addWormhole(new Wormhole("W10a", 0, getPlanetById("P1"), 0,
					TimeHelper.HoursToMilliSeconds(4)), locationMap)
			.addWormhole(new Wormhole("W9", 0, getPlanetById("P5"), 0, 
					TimeHelper.HoursToMilliSeconds(2.5f)), locationMap);
		
		getPlanetById("P11")
			.addWormhole(new Wormhole("W14", 0, getPlanetById("P13"), 0,
					TimeHelper.HoursToMilliSeconds(3)), locationMap);
		
		getPlanetById("P12")
			.addMoon(new Moon("M1P12", "M1P12", 0), locationMap)
			.addWormhole(new Wormhole("W15", 0, getPlanetById("P13"), 0,
					TimeHelper.HoursToMilliSeconds(3)), locationMap);
		
		getPlanetById("P13")
			.addMoon(new Moon("M1P13", "M1P13", 0), locationMap)
			.addWormhole(new Wormhole("W13", 0, getPlanetById("P10"), 0,
					TimeHelper.HoursToMilliSeconds(3.5f)), locationMap)
			.addWormhole(new Wormhole("W14a", 0, getPlanetById("P11"), 0,
					TimeHelper.HoursToMilliSeconds(3)), locationMap)
			.addWormhole(new Wormhole("W15a", 0, getPlanetById("P12"), 0,
					TimeHelper.HoursToMilliSeconds(3)), locationMap)
			.addWormhole(new Wormhole("W16", 0, getPlanetById("P14"), 0,
					TimeHelper.HoursToMilliSeconds(2)), locationMap)
			.addWormhole(new Wormhole("W19", 0, getPlanetById("P17"), 0,
					TimeHelper.HoursToMilliSeconds(2.5f)), locationMap)
			.addWormhole(new Wormhole("W20", 0, getPlanetById("P18"), 0,
					TimeHelper.HoursToMilliSeconds(4)), locationMap);
		
		getPlanetById("P14")
			.addWormhole(new Wormhole("W16a", 0, getPlanetById("P13"), 0,
					TimeHelper.HoursToMilliSeconds(2)), locationMap)
			.addWormhole(new Wormhole("W17", 0, getPlanetById("P16"), 0,
					TimeHelper.HoursToMilliSeconds(1)), locationMap)
			.addWormhole(new Wormhole("W18", 0, getPlanetById("P15"), 0,
					TimeHelper.HoursToMilliSeconds(1.5f)), locationMap);
					
		getPlanetById("P15")
			.addWormhole(new Wormhole("W18a", 0, getPlanetById("P14"), 0,
					TimeHelper.HoursToMilliSeconds(1.5f)), locationMap);
		
		getPlanetById("P16")
			.addWormhole(new Wormhole("W17a", 0, getPlanetById("P14"), 0,
					TimeHelper.HoursToMilliSeconds(1)), locationMap);
		
		getPlanetById("P17")
			.addWormhole(new Wormhole("W21", 0, getPlanetById("P18"), 0,
					TimeHelper.HoursToMilliSeconds(2f)), locationMap);
		
		getPlanetById("P18")
			.addWormhole(new Wormhole("W12a", 0, getPlanetById("P10"), 0,
					TimeHelper.HoursToMilliSeconds(6)), locationMap)
			.addWormhole(new Wormhole("W20a", 0, getPlanetById("P13"), 0,
					TimeHelper.HoursToMilliSeconds(4)), locationMap)
			.addWormhole(new Wormhole("W22", 0, getPlanetById("P19"), 0,
					TimeHelper.HoursToMilliSeconds(1.5f)), locationMap)
			.addWormhole(new Wormhole("W23", 0, getPlanetById("P20"), 0,
					TimeHelper.HoursToMilliSeconds(1.5f)), locationMap);
		
		getPlanetById("P19")
			.addMoon(new Moon("M1P19", "M1P19", 0), locationMap)
			.addMoon(new Moon("M2P19", "M2P19", 0), locationMap)
			.addMoon(new Moon("M3P19", "M3P19", 0), locationMap)
			.addWormhole(new Wormhole("W22a", 0, getPlanetById("P18"), 0,
					TimeHelper.HoursToMilliSeconds(1.5f)), locationMap);
		
		getPlanetById("P20")
			.addWormhole(new Wormhole("W23a", 0, getPlanetById("P18"), 0,
					TimeHelper.HoursToMilliSeconds(1.5f)), locationMap);
	}
	
	private static void createPlanet(String id, String name) {
		Planet p = new Planet(id, name);
		if (!locationMap.containsKey(id)) {
			locationMap.put(id, p);
		} else {
			throw new IllegalStateException("Duplicate location key: " + id);
		}
	}
	
	private static Planet getPlanetById(String id) {
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
		
		List<PathElement> path = new LinkedList<PathElement>();
		// TODO figure out how to do pathing between different planets etc ...
		// if planet and moon in same system then create path straight to it
		if (OrbitMathHelper.inSameSystem(currentLocation, destination)) {
			// if the destination is a wormhole then assume that we are going to the planet in the next system
			if (destination instanceof Wormhole) {
				Wormhole dwh = (Wormhole) destination;
				PathElement planetToWh = calculatePathTime(currentLocation, dwh, shipSpeed, TimeHelper.getCurrentTime());
				path.add(planetToWh);
				PathElement WhtoPlanetSystem = calculatePathTime(dwh, planetToWh.getArrivalTime());
				path.add(WhtoPlanetSystem);
				path.add(calculatePathTime(dwh.getDestination(), dwh.getEndPlanet(), shipSpeed, WhtoPlanetSystem.getArrivalTime()));
			} else {
				path.add(calculatePathTime(currentLocation, destination, shipSpeed, TimeHelper.getCurrentTime()));
			}
		} else {
			// if object and object in differnt system then need to do proper path
			throw new NotImplementedException();
		}
		
		return path;
	}
	
	/** 
	 * calculate the path including arrival time for a wormhole.
	 * @param wh
	 * @param pathTime
	 * @return
	 */
	public PathElement calculatePathTime(Wormhole wh, long pathTime) {
		long arrivalTime = pathTime + wh.getTravelTime();
		return new PathElement(wh, arrivalTime);
	}
	
	/**
	 * Calulate the arrival time of a single path element. The paths need to be in the same system. 
	 * 
	 * @param source the start planet or orbiting object
	 * @param dest the start planet or orbiting object
	 * @param shipSpeed the speed the ship can travel in free space
	 * @return the path element including travel time
	 */
	public PathElement calculatePathTime(Location source, Location dest, double shipSpeed, long pathTime) {
		if (source instanceof Planet || dest instanceof Planet) {
			return calculatePlanetPathTime(source, dest, shipSpeed, pathTime);
		}
		return calculateOrbitalPathTime(source, dest, shipSpeed, pathTime);
	}
	
	/**
	 * Calculate the arrival time for path parts between a planet and an orbiting object. This method only works for planets and orbiting objects. 
	 * 
	 * @param source either a planet or an or orbiting object
	 * @param dest either a planet or an or orbiting object
	 * @param shipSpeed the speed that the ship can travel in free space
	 * @return the path element including arrival time
	 */
	public PathElement calculatePlanetPathTime(Location source, Location dest, double shipSpeed, long pathTime) {
		Planet p = null;
		Orbitable o = null;
		if (source instanceof Planet && dest instanceof Orbitable) {
			p = (Planet) source;
			o = (Orbitable) dest;
		}
		else if (source instanceof Orbitable && dest instanceof Planet) {
			p = (Planet) dest;
			o = (Orbitable) source;			
		}
		else {
			throw new IllegalStateException("this method only deals with planet -> orbit paths");
		}
		
		if (!o.getParent().getId().equals(p.getId())) {
			throw new IllegalStateException("Can't figure out path time between objects in different systems");
		}
		double distanceToPlanet = OrbitMathHelper.getOrbitDistanceFromParent(o.getPeriod());
		long arrivalTime = OrbitMathHelper.calculateArrivalTime(pathTime, distanceToPlanet, shipSpeed);
	
		return new PathElement(dest, arrivalTime);
	}
	
	/**
	 * Calculate the path element for orbit -> orbit path elements
	 * @param source the starting orbital object
	 * @param dest the finishing orbital object
	 * @param shipSpeed the speed the ship can travel in free space
	 * @return the path element including arrival time
	 */
	public PathElement calculateOrbitalPathTime(Location source, Location dest, double shipSpeed, long pathTime) {
		if (!(source instanceof Orbitable && dest instanceof Orbitable)) {
			throw new IllegalStateException("this method only deals with orbit -> orbit paths");
		}
		Orbitable start = (Orbitable) source;
		Orbitable end = (Orbitable) dest;
		
		if (!start.getParent().getId().equals(end.getParent().getId())) {
			throw new IllegalStateException("can't figure out paths between objects in different systems");
		}
		
		double travelDistance = OrbitMathHelper.calculateFakeTravelDistance(start.getPeriod(), end.getPeriod());
			
		long arrivalTime = OrbitMathHelper.calculateArrivalTime(pathTime, travelDistance, shipSpeed);
		
		return new PathElement(dest, arrivalTime);
	}
}
