package universe.data.unit.types;

import universe.data.Faction;
import universe.data.Location;
import universe.data.Unit;
import universe.data.unit.UnitType;

public class Bomber implements UnitType {

	public final static String NAME = "Bomber";
	
	public double getConstructionMineralsRequired() {
		return UnitTypeConstants.BOMBER.getMinerals();
	}

	public long getConstructionTime() {
		return UnitTypeConstants.BOMBER.getConstructionTime();
	}

	public String getLabel() {
		return "Bomber";
	}

	public Unit getNewInstance(Faction faction, Location location, long id) {
		// TODO Auto-generated method stub
		return null;
	}

	public String getTypeId() {
		return NAME;
	}

}
