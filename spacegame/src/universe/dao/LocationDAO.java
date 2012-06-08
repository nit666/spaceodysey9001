package universe.dao;

import universe.data.Faction;
import universe.data.Location;
import universe.data.location.LocationMap;

public interface LocationDAO {

	public LocationMap getAllLocations();
	
	/**
	 * Gets all of the locations that this faction has access to, including units, planets, solar systems etc.
	 * @param faction
	 * @return
	 */
	public LocationMap getAllKnownLocations(Faction faction);
	
	
	public LocationMap getChildrenOfLocation(Location parent);
	
	/**
	 * Gets the children of a particular location, isolates location getting to an area. 
	 * @param parent
	 * @return
	 */
	public LocationMap getKnownChildrenOfLocation(Location parent, Faction faction);
	
	/**
	 * same as the default but will only get for a particular depth. For example if the depth is 1 and null
	 * is the parent then only solar systems will be retrieved, not the planets within them. 
	 * @param parent
	 * @param faction
	 * @param depth
	 * @return
	 */
	public LocationMap getKnownChildrenOfLocation(Location parent, Faction faction, int depth);
}
