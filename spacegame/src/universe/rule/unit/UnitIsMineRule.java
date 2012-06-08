package universe.rule.unit;

import universe.action.ActionContext;
import universe.data.Unit;
import universe.data.unit.Mine;
import universe.rule.ActionRule;

public class UnitIsMineRule implements ActionRule {

	Unit theUnit;
	
	public UnitIsMineRule(Unit unit) {
		theUnit = unit;
	}
	
	public boolean evaluate(ActionContext context) {
		if (theUnit instanceof Mine) {
			return true;
		}
		return false;
	}

	public String getMessage() {
		return "Attempt to mine using a non mining unit";
	}

}
