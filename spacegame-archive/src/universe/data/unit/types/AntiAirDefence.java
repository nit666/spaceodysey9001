package universe.data.unit.types;

import universe.data.Faction;
import universe.data.Location;
import universe.data.Unit;
import universe.data.unit.UnitType;
import universe.data.unit.units.AntiAirDefenseUnit;

public class AntiAirDefence implements UnitType {

	public static final String NAME = "AntiAirDefence";
	
	public double getConstructionMineralsRequired() {
		return UnitTypeConstants.ANTI_AIR_DEFENCE.getMinerals();
	}

	public long getConstructionTime() {
		return UnitTypeConstants.ANTI_AIR_DEFENCE.getConstructionTime();
	}

	public String getLabel() {
		return "Anti Air Turrent";
	}

	public Unit getNewInstance(Faction faction, Location location, long id) {
		return new AntiAirDefenseUnit(faction, location, id);
	}

	public String getTypeId() {
		return NAME;
	}

}
