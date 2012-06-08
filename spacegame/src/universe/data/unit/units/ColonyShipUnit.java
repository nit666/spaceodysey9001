package universe.data.unit.units;

import universe.ControllerContext;
import universe.data.Faction;
import universe.data.Location;
import universe.data.Planet;
import universe.data.Unit;
import universe.data.unit.Constructable;
import universe.data.unit.MoveableInHyperspace;
import universe.data.unit.UnitType;
import universe.data.unit.events.EventOnMoveTo;
import universe.data.unit.types.ColonyShip;

public class ColonyShipUnit extends Unit implements Constructable, MoveableInHyperspace, EventOnMoveTo {

	public ColonyShipUnit(Faction owner, Location location, long id) {
		super(owner, location, id);
	}

	@Override
	public UnitType getUnitType() {
		return new ColonyShip();
	}

	public void moveToEvent(ControllerContext cc, Planet dest) {
		// in this event, a new colony will be created, and the colony ship destroyed		
	}

}
