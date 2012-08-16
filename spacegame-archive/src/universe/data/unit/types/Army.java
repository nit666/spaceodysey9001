package universe.data.unit.types;

import universe.data.Faction;
import universe.data.Location;
import universe.data.Unit;
import universe.data.unit.UnitType;
import universe.data.unit.units.ArmyUnit;

public class Army implements UnitType {

	public static final String NAME = "Army";
	
	public double getConstructionMineralsRequired() {
		return UnitTypeConstants.ARMY.getMinerals();
	}

	public long getConstructionTime() {
		return UnitTypeConstants.ARMY.getConstructionTime();
	}

	public String getLabel() {
		return "Army";
	}

	public Unit getNewInstance(Faction faction, Location location, long id) {
		return new ArmyUnit(faction, location, id);
	}

	public String getTypeId() {
		return NAME;
	}

}
