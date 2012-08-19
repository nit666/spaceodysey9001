package universe.helper;

import java.util.Date;
import java.util.List;

import universe.data.PathElement;
import universe.data.Planet;
import universe.datatypes.Location;
import universe.datatypes.Orbitable;

public class PathHelper {

	/**
	 * Calulate the arrival time of a single path element. The paths need to be in the same system. 
	 * 
	 * @param source the start planet or orbiting object
	 * @param dest the start planet or orbiting object
	 * @param shipSpeed the speed the ship can travel in free space
	 * @return the path element including travel time
	 */
	public static PathElement calculatePathTime(Location source, Location dest, double shipSpeed, long pathTime) {
		if (source instanceof Planet || dest instanceof Planet) {
			return PathHelper.calculatePlanetPathTime(source, dest, shipSpeed, pathTime);
		}
		return PathHelper.calculateOrbitalPathTime(source, dest, shipSpeed, pathTime);
	}

	/**
	 * Calculate the arrival time for path parts between a planet and an orbiting object. This method only works for planets and orbiting objects. 
	 * 
	 * @param source either a planet or an or orbiting object
	 * @param dest either a planet or an or orbiting object
	 * @param shipSpeed the speed that the ship can travel in free space
	 * @return the path element including arrival time
	 */
	private static PathElement calculatePlanetPathTime(Location source, Location dest, double shipSpeed, long pathTime) {
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
	private static PathElement calculateOrbitalPathTime(Location source, Location dest, double shipSpeed, long pathTime) {
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

	/**
	 * Determines if two objects are in the same system or not.
	 */
	public static boolean inSameSystem(Location l1, Location l2) {
		String system1 = "";
		String system2 = "";
		if (l1 instanceof Planet) {
			system1 = l1.getId();
		} else {
			system1 = ((Orbitable) l1).getParent().getId();
		}
		if (l2 instanceof Planet) {
			system2 = l2.getId();
		} else {
			system2 = ((Orbitable) l2).getParent().getId();
		}
	
		return system1.equals(system2);
	}

	/**
	 * adds together all of the trip times for a path and returns the final arrival time.
	 * @param path
	 * @param startTime
	 * @return
	 */
	public static long getDestinationTime(List<PathElement> path, long startTime) {
		long tripTime = startTime;
		for (PathElement p : path) {
			tripTime += p.getArrivalTime() - tripTime;
		}
		return tripTime;
	}

	
	public static void printPath(List<PathElement> path) {
		System.out.println("Path Details:");
		for (PathElement p : path) {
			System.out.println(p.getDestination().getId() + ", at " + new Date(p.getArrivalTime()));
		}
		System.out.println();
	}
}
