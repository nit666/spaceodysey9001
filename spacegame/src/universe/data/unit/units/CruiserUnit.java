package universe.data.unit.units;

import universe.data.Faction;
import universe.data.Location;
import universe.data.Unit;
import universe.data.unit.Combat;
import universe.data.unit.MoveableInHyperspace;
import universe.data.unit.UnitType;
import universe.data.unit.types.Cruiser;
import universe.data.unit.types.UnitTypeFactory;

public class CruiserUnit extends Unit implements MoveableInHyperspace, Combat {

	public CruiserUnit(Faction owner, Location location, long id) {
		super(owner, location, id);
	}

	@Override
	public UnitType getUnitType() {
		return UnitTypeFactory.getUnitTypeFromName(Cruiser.NAME);
	}

}
