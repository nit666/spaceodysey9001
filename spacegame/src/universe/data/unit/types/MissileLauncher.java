package universe.data.unit.types;

import universe.data.Faction;
import universe.data.Location;
import universe.data.Unit;
import universe.data.unit.UnitType;
import universe.data.unit.units.MissileLauncherUnit;

public class MissileLauncher implements UnitType {

	public final static String NAME = "MissileLauncher";
	
	public double getConstructionMineralsRequired() {
		return UnitTypeConstants.MISSILE_LAUNCHER.getMinerals();
	}

	public long getConstructionTime() {
		return UnitTypeConstants.MISSILE_LAUNCHER.getConstructionTime();
	}

	public String getLabel() {
		return "Missile Launcher";
	}

	public Unit getNewInstance(Faction faction, Location location, long id) {
		return new MissileLauncherUnit(faction, location, id);
	}

	public String getTypeId() {
		return NAME;
	}

}
