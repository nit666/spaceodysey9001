package universe.data.unit.types;

import universe.data.Faction;
import universe.data.Location;
import universe.data.Unit;
import universe.data.unit.UnitType;
import universe.data.unit.units.ColonyShipUnit;

public class ColonyShip implements UnitType {

	public double getConstructionMineralsRequired() {
		return UnitTypeConstants.COLONY_SHIP.getMinerals();
	}

	public long getConstructionTime() {
		return UnitTypeConstants.COLONY_SHIP.getConstructionTime();
	}

	public String getLabel() {
		return "Colony Ship";
	}

	public Unit getNewInstance(Faction faction, Location location, long id) {
		return new ColonyShipUnit(faction, location, id);
	}

	public String getTypeId() {
		return "ColonyShip";
	}

}
