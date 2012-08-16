package universe.data.unit.types;

import universe.data.Faction;
import universe.data.Location;
import universe.data.Unit;
import universe.data.unit.UnitType;
import universe.data.unit.units.CruiserUnit;

public class Cruiser implements UnitType {

	public final static String NAME = "Crusier";
	
	public double getConstructionMineralsRequired() {
		return UnitTypeConstants.CRUISER.getMinerals();
	}

	public long getConstructionTime() {
		return UnitTypeConstants.CRUISER.getConstructionTime();
	}

	public String getLabel() {
		return "Cruiser";
	}

	public Unit getNewInstance(Faction faction, Location location, long id) {
		return new CruiserUnit(faction, location, id);
	}

	public String getTypeId() {
		return NAME;
	}

}
