package universe.action.unit;

import universe.ControllerContext;
import universe.action.UnitAction;
import universe.data.Cargo;
import universe.data.Unit;
import universe.data.unit.CargoHold;


public class MoveCargoToUnitAction implements UnitAction {

	public void completeAction(Unit u, ControllerContext cc) {
		// this is an immediate action, so nothing to complete
	}

	public void startAction(Unit u, ControllerContext cc, Object... params) {
		// get the unit that we will be taking the cargo from
		Unit sourceUnit = (Unit) params[0];
		// get the type of cargo to take
		Cargo cargoType = (Cargo) params[1];
		// get the amount of cargo to move
		Double amount = (Double) params[2];
		
		CargoHold destination = (CargoHold) u;
		CargoHold source = (CargoHold) sourceUnit;
		
		// if the amount of cargo is larger than the space in u then trim it down
		if (destination.getRemainingSpace() < amount) {
			amount = destination.getRemainingSpace();
		}
		
		// if the amount of cargo is larger than the amount that is available in the source, then trim it down
		if (source.getRemainingCargoOfType(cargoType) < amount) {
			amount = source.getRemainingCargoOfType(cargoType);
		}
		
		// take the cargo from the source
		source.removeCargoOfType(cargoType, amount);
		
		// add the cargo to the destination
		destination.addCargoOfType(cargoType, amount);
		
		// save the source
		cc.getUnitDAO().saveUnit(sourceUnit);
		
		// save the destination
		cc.getUnitDAO().saveUnit(u);
	}
	
	
//
//	@Override
//	public void initiateRules(ActionContext context) {
//		// TODO Auto-generated method stub
//		Unit sendingUnit = (Unit) context.getParameter("sender", Unit.class);
//		Unit recievingUnit = (Unit) context.getParameter("reciever", Unit.class);
//		
//		addRule(new UnitIsCargoHoldRule(sendingUnit));
//		addRule(new UnitIsCargoHoldRule(recievingUnit));
//		addRule(new FactionOwnsUnitAction(sendingUnit));
//		addRule(new UnitLocationsAreEqual(sendingUnit, recievingUnit));
//	}
//	
//	@Override
//	public ActionStatus doAction(ActionContext context) throws ActionException {
//
//		UnitDAO units = context.getUnitDAO();
//		MessageDAO messages = context.getMessageDAO();
//		
//		Unit sendingUnit = (Unit) context.getParameter("sender", Unit.class);
//		Unit recievingUnit = (Unit) context.getParameter("reciever", Unit.class);
//		Cargo cargoTypeID = (Cargo) context.getParameter("cargo_type_id", Cargo.class);
//		check (cargoTypeID != null, "The cargo type is not set");
//		Double cargoVolume = null;
//		try {
//			cargoVolume = (Double) context.getParameter("cargo_amount", Double.class);
//		} catch (NumberFormatException e) {
//		}
//		check (cargoVolume != null, "The cargo volume has not been set or is in the wrong format");
//		
//		// check that the sender has the proper amount of cargo
//		CargoHold sendingHold = (CargoHold) sendingUnit;
//		CargoHold recievingHold = (CargoHold) recievingUnit;
//		
//		if (sendingHold.getRemainingCargoOfType(cargoTypeID) < cargoVolume) {
//			messages.sendMessageToFaction(context.getCurrentFaction(), "cargo-hold-not-enough-to-send-error", sendingUnit, recievingUnit, cargoTypeID);
//			return ActionStatus.FAILED;
//		}
//		
//		// check that the reciever has room for the cargo
//		if (recievingHold.getRemainingSpace() < cargoVolume) {
//			messages.sendMessageToFaction(context.getCurrentFaction(), "cargo-hold-not-enough-space-to-recieve-error", sendingUnit, recievingUnit, cargoTypeID);
//			return ActionStatus.FAILED;
//		}
//		
//		// move the cargo
//		sendingHold.removeCargoOfType(cargoTypeID, cargoVolume);
//		recievingHold.addCargoOfType(cargoTypeID, cargoVolume);
//		
//		// save the sender and reciever
//		units.saveUnit(sendingUnit);
//		units.saveUnit(recievingUnit);
//		
//		return ActionStatus.COMPLETE;
//	}
//
//	@Override
//	public ActionData getActionData(Faction factionId, Object... params) {
//		ensureParamLength(4, params);
//		ActionData data = new ActionData();
//		data.setAction(this);
//		data.setFaction(factionId);
//		data.addParameter("sender", params[0], Unit.class);
//		data.addParameter("reciever", params[1], Unit.class);
//		data.addParameter("cargo_type_id", params[2], Cargo.class);
//		data.addParameter("cargo_amount", params[3], Double.class);
//		return data;
//	}

}
