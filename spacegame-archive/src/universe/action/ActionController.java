package universe.action;

import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import universe.ControllerContext;
import universe.PreferenceCodes;
import universe.action.unit.BuildUnitAction;
import universe.action.unit.MoveUnitToAction;
import universe.dao.ActionDAO;
import universe.data.ActionData;
import universe.data.Planet;
import universe.data.Unit;
import universe.data.cargo.MineralType;
import universe.data.unit.types.MiningColony;
import universe.data.unit.types.UnitTypeFactory;
import universe.data.unit.units.MiningColonyUnit;

public class ActionController {

	public static final int BUILD_UNIT_ACTION = 0;
	
	private Map<Integer, UnitAction> actions = new HashMap<Integer, UnitAction>();
		
	{
		actions.put(ActionType.BUILDING.getValue(), new BuildUnitAction());	
		actions.put(ActionType.MOVING.getValue(), new MoveUnitToAction());
	}
	
	private void doUnitActions(ControllerContext context) {

		System.out.println("Checking for completed actions...");
		// need to get all units that have 'expired' actions.
		List<Unit> units = context.getUnitDAO().getAllUnitsWithExpiredActions();

		System.out.println("Found " + units.size() + " units with completed actions");
		
		// loop over all of the units
		for (Unit u : units) {
			int action = u.getCurrentAction();
			if (actions.containsKey(action)) {
				UnitAction actionClass = actions.get(action);
				try {
					System.out.println("Calling complete on action " + actionClass.getClass().getName());
					actionClass.completeAction(u, context);
				} catch (Throwable t) {
					t.printStackTrace();
				}
			} 
		}
	}
	
	private void doMessageActions(ControllerContext context) {
		
	}
	
	private void doMaintenanceActions(ControllerContext context) {
		// mining 
		
		try { 
			// get the last time that things were mined
			Date lastTime = (Date) context.getPreference(PreferenceCodes.LAST_CHECKED_TIME);
			Date currentTime = Calendar.getInstance().getTime();
			
			long timeSinceLastCheckInSeconds = (currentTime.getTime() - lastTime.getTime()) / 1000;
			
			// get all planets
			List<Unit> colonies = context.getUnitDAO().getAllUnitsOfType(UnitTypeFactory.getUnitTypeFromName(MiningColony.NAME));
			// if the planet has a colony on it then
			for (Unit u : colonies) {
				MiningColonyUnit colony = (MiningColonyUnit) u;
	
				Planet p = context.getPlanetDAO().getPlanetById(colony.getParent().getLocationId());
				
				// update mining for that planet
				colony.addCargoOfType(MineralType.GAS, p.getGas() / 10 * timeSinceLastCheckInSeconds);
				colony.addCargoOfType(MineralType.ORE, p.getMinerals() / 10 * timeSinceLastCheckInSeconds);
				colony.addCargoOfType(MineralType.WATER, p.getWater() / 10 * timeSinceLastCheckInSeconds);	
				
				context.getUnitDAO().saveUnit(colony);
			}
		} catch (Throwable t) {
			t.printStackTrace();
		}
	}
	
	public void doActions(ControllerContext context) {
		doUnitActions(context);
		doMessageActions(context);
		doMaintenanceActions(context);
	}
	
	public void doActionsVoid(ControllerContext context) {
		
		ActionDAO actions = context.getActionDAO();
		DefaultActionContext actionContext = new DefaultActionContext(context);
		while (true) {
			ActionData nextAction = actions.getNextAction();
			if (nextAction == null) {
				break;
			}
			actionContext.setActionData(nextAction);
			
			// process the action
			try {			
				DatabaseAction.ActionStatus result = null;
				String errorMessage = nextAction.getAction().evaluateRules(actionContext);
				if (errorMessage != null) {
					result = DatabaseAction.ActionStatus.FAILED;
				} else {
					result = nextAction.getAction().doAction(actionContext);
				}
				if (result == DatabaseAction.ActionStatus.COMPLETE) {
					// delete the action because it is done
					actions.deleteActionData(nextAction);
					actions.writeActionLogMessage(DatabaseAction.ErrorStatus.INFO, nextAction, "Action Complete");
				} else if (result == DatabaseAction.ActionStatus.IN_PROGRESS) {
					// action still running
					nextAction.setLastUpdatedTime(new Date());
					actions.saveActionData(nextAction);
					actions.writeActionLogMessage(DatabaseAction.ErrorStatus.INFO, nextAction, "Action Still Processing");
				} else if (result == DatabaseAction.ActionStatus.FAILED) {
					// write a message log here
					actions.deleteActionData(nextAction);
					actions.writeActionLogMessage(DatabaseAction.ErrorStatus.ERROR, nextAction, errorMessage);
				} else if (result == DatabaseAction.ActionStatus.WAIT) {
					// send to the end of the current queue
					nextAction.setLastUpdatedTime(new Date());
					actions.saveActionData(nextAction);
					actions.writeActionLogMessage(DatabaseAction.ErrorStatus.INFO, nextAction, "Action Waiting on Another Action");					
				}
				
			} catch (ActionException e) {
				e.printStackTrace();
			}
		}
	}
	
}
