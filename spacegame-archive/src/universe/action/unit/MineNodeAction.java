package universe.action.unit;

import universe.action.DatabaseAction;
import universe.action.ActionContext;
import universe.action.ActionException;
import universe.dao.KnowsAboutDAO;
import universe.data.ActionData;
import universe.data.Faction;
import universe.data.Unit;
import universe.data.unit.Mine;
import universe.data.unit.MineralNode;
import universe.helper.TimeHelper;
import universe.rule.faction.FactionKnowsAboutRule;
import universe.rule.faction.FactionOwnsUnitAction;
import universe.rule.unit.UnitIsMineRule;
import universe.rule.unit.UnitIsMineralNodeRule;
import universe.rule.unit.UnitLocationsAreEqual;

public class MineNodeAction extends DatabaseAction {


	@Override
	public void initiateRules(ActionContext context) {
		Unit miningUnit = (Unit) context.getParameter("mining-unit", Unit.class);
		Unit miningNode = (Unit) context.getParameter("mining-node", Unit.class);
		
		addRule(new UnitIsMineRule(miningUnit));
		addRule(new UnitIsMineralNodeRule(miningNode));
		addRule(new FactionOwnsUnitAction(miningUnit));
		addRule(new FactionKnowsAboutRule(miningNode, KnowsAboutDAO.KnowsAboutTypes.MINERAL_NODE));
		addRule(new UnitLocationsAreEqual(miningUnit, miningNode));
	}
	
	@Override
	public ActionStatus doAction(ActionContext context) throws ActionException {
		Unit miningUnit = (Unit) context.getParameter("mining-unit", Unit.class);
		Unit miningNode = (Unit) context.getParameter("mining-node", Unit.class);
		check(miningNode.getLocation().equals(miningUnit.getLocation()), "The mining unit is not in the mineral nodes location");
		
		// do the mining
		return mine(context, (Mine)miningUnit, (MineralNode)miningNode, context.getLastActionTime(), context.getCurrentActionTime());
		
	}

	private DatabaseAction.ActionStatus mine(ActionContext context, Mine mine, MineralNode node, Long startTime, Long endTime) {
		double mineralsPerSecond = mine.getMineralsPerSecond();
		double seconds = (endTime - startTime) / TimeHelper.ONE_SECOND; 
		
		double minerals = mineralsPerSecond * seconds;
		
		if (node.getRemainingCargoOfType(mine.getMineType()) == 0.0) {
			// mine has run out of minerals so done
			context.getMessageDAO().sendMessageToFaction(context.getCurrentFaction(), "mineral-node-is-empty", node);
			return ActionStatus.COMPLETE;
		}
		
		// make sure the node has the minerals we need
		if (node.getRemainingCargoOfType(mine.getMineType()) < minerals) {
			minerals = node.getRemainingCargoOfType(mine.getMineType());
		}
		
		// make sure the mine has enough space to hold the minerals
		if (mine.getRemainingSpace() > minerals) {
			minerals = mine.getRemainingSpace();
		}
		
		// update the cargo holds
		node.removeCargoOfType(mine.getMineType(), minerals);
		mine.addCargoOfType(mine.getMineType(), minerals);
		
		// save everything
		context.getUnitDAO().saveUnit((Unit) mine);
		context.getPlanetDAO().saveMineralNode(node);
		
		return ActionStatus.IN_PROGRESS;
	}
	
	@Override
	public ActionData getActionData(Faction faction, Object... params) {
		ensureParamLength(2, params);
		ActionData data = new ActionData();
		data.setAction(this);
		data.setFaction(faction);
		data.addParameter("mining-unit", params[0], String.class);
		data.addParameter("mining-node", params[1], String.class);
		return data;
	}
}
