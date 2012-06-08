package universe.rule.unit;

import universe.action.ActionContext;
import universe.data.Unit;
import universe.data.unit.MineralNode;
import universe.rule.ActionRule;

public class UnitIsMineralNodeRule implements ActionRule {

	Unit theUnit;
	
	public UnitIsMineralNodeRule(Unit unit) {
		theUnit = unit;
	}
	
	public boolean evaluate(ActionContext context) {
		if (theUnit instanceof MineralNode) {
			return true;
		}
		return false;
	}

	public String getMessage() {
		return "Attempt to mine non mineral node";
	}

}
