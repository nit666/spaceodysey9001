package universe.data.location;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

import universe.data.Location;

public class LocationMap {

	Map<Long, Location> locations;
	Map<Long, Set<Location>> parentMap;
	Set<Location> rootLocations;
	
	public LocationMap() {
		locations = new HashMap<Long, Location>();
		rootLocations = new HashSet<Location>();
	}
	
	public void addLocation(Location loc) {
		if (loc.getParent() == null) {
			rootLocations.add(loc);
			locations.put(new Long(loc.getLocationId()), loc);
		} else {
			locations.put(new Long(loc.getLocationId()), loc);
			if (parentMap.containsKey(loc.getParent().getLocationId())) {
				Set<Location> children = parentMap.get(loc.getParent().getLocationId());
				children.add(loc);
			} else {
				Set<Location> children = new HashSet<Location>();
				children.add(loc);
				parentMap.put(new Long(loc.getParent().getLocationId()), children);
			}
		}
	}
	
	public Set<Location> getChildrenForParent(Location parent) {
		if (parent != null) {
			return parentMap.get(parent.getLocationId());
		} else {
			return rootLocations;
		}
	}
}
