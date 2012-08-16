package universe.data.unit.types;

import universe.data.Faction;
import universe.data.Location;
import universe.data.Unit;
import universe.data.unit.UnitType;
import universe.data.unit.units.DestroyerUnit;

public class Destroyer implements UnitType {

	public final static String NAME = "Destroyer";
	
	public double getConstructionMineralsRequired() {
		return UnitTypeConstants.DESTROYER.getMinerals();
	}

	public long getConstructionTime() {
		return UnitTypeConstants.DESTROYER.getConstructionTime();
	}

	public String getLabel() {
		return "Destroyer";
	}

	public Unit getNewInstance(Faction faction, Location location, long id) {
		return new DestroyerUnit(faction, location, id);
	}

	public String getTypeId() {
		return NAME;
	}

}
