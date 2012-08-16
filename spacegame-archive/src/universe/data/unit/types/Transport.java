package universe.data.unit.types;

import universe.data.Faction;
import universe.data.Location;
import universe.data.Unit;
import universe.data.unit.UnitType;
import universe.data.unit.units.TransportUnit;

public class Transport implements UnitType {

	public static final String NAME = "Transport";
	
	private static Transport instance;
	
	public Unit getNewInstance(Faction faction, Location location, long id) {
		return new TransportUnit(faction, location, id);
	}

	public String getTypeId() {
		return "Transport";
	}

	public double getConstructionMineralsRequired() {
		return UnitTypeConstants.TRANSPORT.getMinerals();
	}
	
	public static Transport getInstance() {
		if (instance == null) {
			instance = new Transport();
		}
		return instance;
	}

	public long getConstructionTime() {
		return UnitTypeConstants.TRANSPORT.getConstructionTime();
	}

	public String getLabel() {
		return "Transport";
	}
}
