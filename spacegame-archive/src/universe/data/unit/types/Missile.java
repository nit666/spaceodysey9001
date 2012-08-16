package universe.data.unit.types;

import universe.data.Faction;
import universe.data.Location;
import universe.data.Unit;
import universe.data.unit.UnitType;
import universe.data.unit.units.MissileUnit;

public class Missile implements UnitType {

	public static final String NAME = "Missile";
	
	public double getConstructionMineralsRequired() {
		return UnitTypeConstants.MISSILE.getMinerals();
	}

	public long getConstructionTime() {
		return UnitTypeConstants.MISSILE.getConstructionTime();
	}

	public String getLabel() {
		return "Missile";
	}

	public Unit getNewInstance(Faction faction, Location location, long id) {
		return new MissileUnit(faction, location, id);
	}

	public String getTypeId() {
		return NAME;
	}

}
