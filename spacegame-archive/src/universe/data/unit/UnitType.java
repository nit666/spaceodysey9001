package universe.data.unit;

import universe.data.Faction;
import universe.data.Location;
import universe.data.Unit;

public interface UnitType {
	
	// Create a new instance of the unit type
	Unit getNewInstance(Faction faction, Location location, long id);
	
	// A descriptive name of the unit type that also counts as an ID.
	String getTypeId();
	
	// A web friendly label for the type
	String getLabel();
	
	// get the amount of materials required to build the unit
	double getConstructionMineralsRequired();

	// get the time in milliseconds that is required to build the unit
	long getConstructionTime();
}
