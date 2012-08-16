package universe.data.unit.units;

import universe.data.Faction;
import universe.data.Location;
import universe.data.Unit;
import universe.data.unit.Building;
import universe.data.unit.Combat;
import universe.data.unit.UnitType;
import universe.data.unit.types.AntiAirDefence;
import universe.data.unit.types.UnitTypeFactory;

public class AntiAirDefenseUnit extends Unit implements Building, Combat {

	public AntiAirDefenseUnit(Faction owner, Location location, long id) {
		super(owner, location, id);
	}

	@Override
	public UnitType getUnitType() {
		return UnitTypeFactory.getUnitTypeFromName(AntiAirDefence.NAME);
	}

}
