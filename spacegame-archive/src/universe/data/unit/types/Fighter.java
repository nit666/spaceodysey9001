package universe.data.unit.types;

import universe.data.Faction;
import universe.data.Location;
import universe.data.Unit;
import universe.data.unit.UnitType;
import universe.data.unit.units.FighterUnit;

public class Fighter implements UnitType {

	public static final String NAME = "Fighter";
	
	public double getConstructionMineralsRequired() {
		return UnitTypeConstants.FIGHTER.getMinerals();
	}

	public long getConstructionTime() {
		return UnitTypeConstants.FIGHTER.getConstructionTime();
	}

	public String getLabel() {
		return "Fighter";
	}

	public Unit getNewInstance(Faction faction, Location location, long id) {
		return new FighterUnit(faction, location, id);
	}

	public String getTypeId() {
		return NAME;
	}

}
