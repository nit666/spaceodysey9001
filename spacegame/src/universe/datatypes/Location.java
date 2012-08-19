package universe.datatypes;

import java.util.List;
import java.util.Set;

import universe.data.PathElement;

public interface Location {
	
	/**
	 * Each location has an ID so that it can be easily located
	 */
	String getId();
	
	/**
	 * Each location knows how to find the path to its parent or its children. 
	 * If the location requested isn't the parent or child then a search is done. 
	 * @param newLocation the location that a path is being created for. 
	 * @return A list of locations that need to be followed to reach the new location.
	 */
	List<PathElement> createPathTo(Location startLocation, Location newLocation, long startTime, double shipSpeed, Set<Location> hits);
}
