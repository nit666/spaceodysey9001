package universe.data.unit.types;

import universe.data.Faction;
import universe.data.Location;
import universe.data.Unit;
import universe.data.unit.UnitType;
import universe.data.unit.units.MiningColonyUnit;

public class MiningColony implements UnitType {

	public static final String NAME = "Mining Colony";
	
	static final UnitType[] buildTypes = new UnitType[] {
		new Transport()
	};

	public String getTypeId() {
		return "Mining Colony";
	}

	public Unit getNewInstance(Faction faction, Location location, long id) {
		return new MiningColonyUnit(faction, location, id);
	}

	public double getConstructionMineralsRequired() {
		return UnitTypeConstants.MINING_COLONY.getMinerals();
	}

	public long getConstructionTime() {
		return UnitTypeConstants.MINING_COLONY.getConstructionTime();
	}

	public String getLabel() {
		return "Mining Colony";
	}
}
