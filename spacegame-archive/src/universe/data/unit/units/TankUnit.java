package universe.data.unit.units;

import universe.data.Faction;
import universe.data.Location;
import universe.data.Unit;
import universe.data.unit.Combat;
import universe.data.unit.Moveable;
import universe.data.unit.UnitType;
import universe.data.unit.types.Tank;
import universe.data.unit.types.UnitTypeFactory;

public class TankUnit extends Unit implements Moveable, Combat {

	public TankUnit(Faction owner, Location location, long id) {
		super(owner, location, id);
	}

	@Override
	public UnitType getUnitType() {
		return UnitTypeFactory.getUnitTypeFromName(Tank.NAME);
	}

}
