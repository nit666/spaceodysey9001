package universe.action.unit;

import java.util.Calendar;
import java.util.LinkedList;
import java.util.List;

import universe.ControllerContext;
import universe.action.ActionType;
import universe.action.UnitAction;
import universe.data.BaseLocation;
import universe.data.Message;
import universe.data.PlanetLink;
import universe.data.Unit;
import universe.data.cargo.MineralType;
import universe.data.unit.CargoHold;
import universe.data.unit.events.EventOnMoveTo;
import universe.data.unit.units.MiningColonyUnit;
import universe.helper.TimeHelper;


/**
 * move a a unit from one location to another. This is the base class for movement which
 * will move between bodies in space but if different types of movement are needed then
 * override the getTotalTravelTime and getNewLocation methods.
 *  
 * @author Neil
 */
public class MoveUnitToAction implements UnitAction {
	
	@SuppressWarnings("unchecked")
	public void startAction(Unit u, ControllerContext cc, Object... params) {
		PlanetLink link = (PlanetLink) params[0];
		List<Unit> units = (List<Unit>) params[1];
		
		
		List<Unit> cargoholds = new LinkedList<Unit>();
		double totalGas = 0; 
		for (Unit unit: units) {
			if ((unit instanceof CargoHold) && unit.getOwner().getFactionId() == u.getOwner().getFactionId()) {
				if (unit instanceof MiningColonyUnit) {
					cargoholds.add(0, unit);
				} else {
					cargoholds.add(unit);
				}
				totalGas += ((CargoHold) unit).getRemainingCargoOfType(MineralType.GAS);
			}
		}
		
		if (cargoholds.size() == 0) {
			throw new IllegalArgumentException("There are no ships or colonies that can carry gas on this planet, and you need gas to move");
		}
		
		if (totalGas < link.getTravelCost()) {
			throw new IllegalArgumentException("There is not enough stored gas on this planet to power your ship");
		}
		
		// save the cargo hold
		double remaining = link.getTravelCost();
		for (Unit unit : cargoholds) {
			remaining -= ((CargoHold) unit).removeCargoOfType(MineralType.GAS, remaining);
		    cc.getUnitDAO().saveUnit(unit);
		    if (remaining >= 0) {
		    	break;
		    }
		}
		
		// set the action in the unit
		u.setCurrentAction(ActionType.MOVING.getValue());
		u.setCurrentTarget(link.getId());
		
		// work out the ETA
		Calendar eta = Calendar.getInstance();
		eta.setTimeInMillis(eta.getTimeInMillis() + TimeHelper.SecondsToMilliSeconds(link.getTravelTime()));
		u.setActionETA(eta.getTime());
		// unit is no longer at a planet
		u.setLocation(BaseLocation.getInstance());
		
		cc.getUnitDAO().saveUnit(u);
	}
	
	public void completeAction(Unit u, ControllerContext cc) {
		long linkid = u.getCurrentTarget();
		
		PlanetLink link = cc.getPlanetDAO().getLinkedPlanetByLinkId(linkid);
		
		u.setLocation(link.getDestination());
		u.setActionETA(null);
		u.setCurrentAction(ActionType.WAITING.getValue());
		u.setCurrentTarget(0);
		cc.getUnitDAO().saveUnit(u);

		// if the unit has events then do them now
		if (u instanceof EventOnMoveTo) {
			((EventOnMoveTo) u).moveToEvent(cc, link.getDestination());
		}
		
		cc.getMessageDAO().sendMessageToFaction(u.getOwner(), Message.MOVING_COMPLETE, u.getUnitType().getLabel(), link.getDestination().getName());
	}
}
