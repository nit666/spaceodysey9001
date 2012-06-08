package universe.action.unit;

import java.util.Calendar;

import universe.ControllerContext;
import universe.action.ActionType;
import universe.action.UnitAction;
import universe.dao.UnitDAO;
import universe.data.Message;
import universe.data.Planet;
import universe.data.Unit;
import universe.data.cargo.MineralType;
import universe.data.unit.CargoHold;
import universe.data.unit.UnitType;

/**
 * Build a unit from a factory unit. This is the base build action.
 *  
 * @author Neil
 */
public class BuildUnitAction implements UnitAction {

//	@Override
//	public void initiateRules(ActionContext context) {
//		Unit buildingUnit = (Unit) context.getParameter("builder", Unit.class);
//		UnitType unitToBeBuilt = (UnitType) context.getParameter("unit-type", UnitType.class);
//		
//		addRule(new UnitExistsRule(buildingUnit));
//		addRule(new UnitIsFactoryRule(buildingUnit));
//		addRule(new UnitIsHangerRule(buildingUnit));
//		addRule(new UnitCanBuildUnitRule(buildingUnit, unitToBeBuilt));
//		addRule(new FactionKnowsAboutRule(unitToBeBuilt, KnowsAboutDAO.KnowsAboutTypes.UNIT_TYPE));
//		addRule(new FactionOwnsUnitAction(buildingUnit));
//	}
//	
//	@Override
//	public ActionStatus doAction(ActionContext context) throws ActionException {
//		
//		UnitDAO units = context.getUnitDAO();
//		UnitType unitToBeBuilt = (UnitType) context.getParameter("unit-type", UnitType.class);
//		Unit buildingUnit = (Unit) context.getParameter("builder", Unit.class);
//				
//		// if the unit is already being built then get it from the parameters otherwise create it
//		Unit unitBeingBuilt = (Unit) context.getParameter("unit", Unit.class);
//		if (unitBeingBuilt == null) {
//			unitBeingBuilt = unitToBeBuilt.getNewInstance(context.getCurrentFaction(), buildingUnit.getLocation());
//			unitBeingBuilt.setCurrentAction(ActionType.BEING_BUILT.getValue());
//			unitBeingBuilt.setCurrentTarget(buildingUnit.getUnitId());
//			((Hanger) buildingUnit).addUnitToHanger(unitBeingBuilt);
//			context.addParameter("unit", unitBeingBuilt, Unit.class);
//		} 
//		
//		// building will update the resources on the factory and the percent complete on the unit. 
//		build((Factory) buildingUnit, unitBeingBuilt, context.getLastActionTime(), context.getCurrentActionTime());
//		
//		// save the new factory and unit details
//		units.saveUnit(buildingUnit);
//		units.saveUnit(unitBeingBuilt);
//		
//		// update the build complete time for the unit, if the unit is finished then finish the action
//		if (unitBeingBuilt.isConstructionComplete()) {
//			context.getMessageDAO().sendMessageToFaction(context.getCurrentFaction(), "build-unit-complete", unitToBeBuilt.getTypeId(), unitBeingBuilt.getUnitId());
//			unitBeingBuilt.setCurrentAction(ActionType.WAITING.getValue());
//			return ActionStatus.COMPLETE;
//		}
//		
//		return ActionStatus.IN_PROGRESS;
//	}
//
//	/**
//	 * Do the actual building, seperated for cleanness
//	 * @param builder
//	 * @param unitToBeBuilt
//	 * @param startTime
//	 * @param endTime
//	 */
//	private void build(Factory builder, Unit unitToBeBuilt, Long startTime, Long endTime) {
//		// get the total minerals
////		double mineralsRequired = unitToBeBuilt.getUnitType().getConstructionMineralsRequired() - unitToBeBuilt.getMineralsContent();
////		double gasRequired = unitToBeBuilt.getUnitType().getConstructionGasRequired() - unitToBeBuilt.getGasContent();
////		double waterRequired = unitToBeBuilt.getUnitType().getConstructionWaterRequried() - unitToBeBuilt.getWaterContent();
////		double rareRequired = unitToBeBuilt.getUnitType().getConstructionRareReqired() - unitToBeBuilt.getRareContent();
//		
//		// get the rate at which to build
//		double buildRate = unitToBeBuilt.getUnitType().getConstructionMineralsPerSecond();
//				
//		// get the amount of time that we are building for
//		double timeToBuild = (endTime - startTime) / TimeHelper.ONE_SECOND; 
//
//		// work out the minerals to take for this tick
//		double minerals = timeToBuild * buildRate;
////		if (mineralsRequired <= minerals) {
//			minerals = mineralsRequired;
//		}
//		if (builder.getRemainingCargoOfType(Mineral.getMineralOfType(MineralType.ORE)) < minerals) {
//			return;
//		}
//		double gas = timeToBuild * buildRate;
//		if (gasRequired <= gas) {
//			gas = gasRequired;
//		}
//		if (builder.getRemainingCargoOfType(Mineral.getMineralOfType(MineralType.GAS)) < gas) {
//			return;
//		}
//		double water = timeToBuild * buildRate;
//		if (waterRequired <= water) {
//			water = waterRequired;
//		}
//		if (builder.getRemainingCargoOfType(Mineral.getMineralOfType(MineralType.WATER)) < water) {
//			return;
//		}
//		double rare = timeToBuild * buildRate;
//		if (rareRequired <= rare) {
//			rare = rareRequired;
//		}
//		if (builder.getRemainingCargoOfType(Mineral.getMineralOfType(MineralType.RARE)) < rare) {
//			return;
//		}
//		
//		// now do the actual building
//		builder.removeCargoOfType(Mineral.getMineralOfType(MineralType.ORE), minerals);
//		unitToBeBuilt.setMineralsContent(unitToBeBuilt.getMineralsContent() + minerals);
//		builder.removeCargoOfType(Mineral.getMineralOfType(MineralType.GAS), gas);
//		unitToBeBuilt.setGasContent(unitToBeBuilt.getGasContent() + gas);
//		builder.removeCargoOfType(Mineral.getMineralOfType(MineralType.WATER), water);
//		unitToBeBuilt.setWaterContent(unitToBeBuilt.getWaterContent() + water);		
//		builder.removeCargoOfType(Mineral.getMineralOfType(MineralType.RARE), rare);
//		unitToBeBuilt.setRareContent(unitToBeBuilt.getRareContent() + rare);		
//		
//	}
//	
//	@Override
//	public ActionData getActionData(Faction faction, Object... params) {
//		ensureParamLengths(new int[]{2,3}, params);
//		ActionData data = new ActionData();
//		data.setAction(this);
//		data.setFaction(faction);
//		data.addParameter("builder", params[0], Unit.class);
//		data.addParameter("unit-type", params[1], UnitType.class);
//		if (params.length > 2) {
//			data.addParameter("unit", params[2], Unit.class);
//		}
//		return data;
//	}

	
	
	public void completeAction(Unit u, ControllerContext cc) {
		
		UnitDAO unitdao = cc.getUnitDAO();
		// get the target unit that was being built
		Unit target = unitdao.getUnitById(u.getCurrentTarget());
		
		target.setActionETA(null);
		target.setCurrentTarget(0);
		target.setCurrentAction(ActionType.WAITING.getValue());
		unitdao.saveUnit(target);
		
		u.setActionETA(null);
		u.setCurrentTarget(0);
		u.setCurrentAction(ActionType.WAITING.getValue());
		unitdao.saveUnit(u);
		
		Planet p = cc.getPlanetDAO().getPlanetById(target.getParent().getLocationId());
		
		cc.getMessageDAO().sendMessageToFaction(u.getOwner(), Message.CONSTRUCTION_COMPLETE, target.getUnitType().getTypeId(), p.getName());
	}

	public void startAction(Unit u, ControllerContext cc, Object... params) {
		
		UnitDAO unitdao = cc.getUnitDAO();
		
		UnitType type = (UnitType) params[0]; // the type of unit to create
		
		int minerals = (int) type.getConstructionMineralsRequired();
		Calendar eta = Calendar.getInstance();
		eta.setTimeInMillis(eta.getTimeInMillis() + type.getConstructionTime());
		
		// assume that the unit is a hold and has enough cargo
		CargoHold hold = (CargoHold) u;
		hold.removeCargoOfType(MineralType.ORE, minerals);
		
		Unit newUnit = type.getNewInstance(u.getOwner(), u.getParent(), cc.getNextId());
		newUnit.setCurrentAction(ActionType.BEING_BUILT.getValue());
		newUnit.setOwner(u.getOwner());
		newUnit.setActionETA(eta.getTime());
		newUnit.setParent(u.getParent());
		unitdao.insertUnit(newUnit);
		
		u.setCurrentAction(ActionType.BUILDING.getValue());
		u.setCurrentTarget(newUnit.getUnitId());
		u.setActionETA(eta.getTime());
		unitdao.saveUnit(u);
	}
}
