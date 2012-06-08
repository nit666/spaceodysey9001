package universe.data.unit.types;

import universe.data.Faction;
import universe.data.Location;
import universe.data.Unit;
import universe.data.unit.UnitType;
import universe.data.unit.units.ProbeUnit;

public class Probe implements UnitType {

	public static final String NAME = "Probe";
	
	public double getConstructionMineralsRequired() {
		return UnitTypeConstants.PROBE.getMinerals();
	}

	public long getConstructionTime() {
		return UnitTypeConstants.PROBE.getConstructionTime();
	}

	public String getLabel() {
		return "Probe";
	}

	public Unit getNewInstance(Faction faction, Location location, long id) {
		return new ProbeUnit(faction, location, id);
	}

	public String getTypeId() {
		return NAME;
	}

}
