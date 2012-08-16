package universe.data.unit.units;

import universe.data.Faction;
import universe.data.Location;
import universe.data.Unit;
import universe.data.unit.Combat;
import universe.data.unit.MoveableInSpace;
import universe.data.unit.UnitType;
import universe.data.unit.types.Missile;
import universe.data.unit.types.UnitTypeFactory;

public class MissileUnit extends Unit implements MoveableInSpace, Combat {

	public MissileUnit(Faction owner, Location location, long id) {
		super(owner, location, id);
	}

	@Override
	public UnitType getUnitType() {
		return UnitTypeFactory.getUnitTypeFromName(Missile.NAME);
	}

}
