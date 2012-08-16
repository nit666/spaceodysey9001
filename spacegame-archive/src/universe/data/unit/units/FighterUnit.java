package universe.data.unit.units;

import universe.data.Faction;
import universe.data.Location;
import universe.data.Unit;
import universe.data.unit.Combat;
import universe.data.unit.MoveableInHyperspace;
import universe.data.unit.UnitType;
import universe.data.unit.types.Fighter;
import universe.data.unit.types.UnitTypeFactory;

public class FighterUnit extends Unit implements MoveableInHyperspace, Combat {

	public FighterUnit(Faction owner, Location location, long id) {
		super(owner, location, id);
	}

	@Override
	public UnitType getUnitType() {
		return UnitTypeFactory.getUnitTypeFromName(Fighter.NAME);
	}

}
