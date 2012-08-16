package universe.data.unit.units;

import universe.data.Faction;
import universe.data.Location;
import universe.data.Unit;
import universe.data.unit.Building;
import universe.data.unit.Combat;
import universe.data.unit.UnitType;
import universe.data.unit.types.MissileLauncher;
import universe.data.unit.types.UnitTypeFactory;

public class MissileLauncherUnit extends Unit implements Combat, Building {

	public MissileLauncherUnit(Faction owner, Location location, long id) {
		super(owner, location, id);
	}

	@Override
	public UnitType getUnitType() {
		return UnitTypeFactory.getUnitTypeFromName(MissileLauncher.NAME);
	}

}
