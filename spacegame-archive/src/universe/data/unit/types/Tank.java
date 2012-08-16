package universe.data.unit.types;

import universe.data.Faction;
import universe.data.Location;
import universe.data.Unit;
import universe.data.unit.UnitType;

public class Tank implements UnitType {

	public static final String NAME = "Tank";
	
	public double getConstructionMineralsRequired() {
		return UnitTypeConstants.TANK.getMinerals();
	}

	public long getConstructionTime() {
		return UnitTypeConstants.TANK.getConstructionTime();
	}

	public String getLabel() {
		return "Tank";
	}

	public Unit getNewInstance(Faction faction, Location location, long id) {
		return null;
	}

	public String getTypeId() {
		return NAME;
	}

}
