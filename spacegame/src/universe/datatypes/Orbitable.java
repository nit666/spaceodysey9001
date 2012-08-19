package universe.datatypes;

import java.util.LinkedList;
import java.util.List;
import java.util.Set;

import universe.data.PathElement;


public abstract class Orbitable implements Location {
	public abstract String getId();
	public abstract double getPeriod();
	public abstract Location getParent();
	
	/**
	 * The path algorithm assumes that the parent can figure out the path, so just call the parent.
	 */
	public List<PathElement> createPathTo(Location startLocation, Location newLocation, long startTime, double shipSpeed, Set<Location> hits) {
		if (startLocation.getId().equals(getId())) {
			if (hits.contains(this)) {
				return new LinkedList<PathElement>();
			}
			hits.add(this);
			return getParent().createPathTo(startLocation, newLocation, startTime, shipSpeed, hits);
		} 
		throw new IllegalStateException("Can't figure out paths from locations that aren't myself");
	}
}
