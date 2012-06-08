package universe.rule.unit;

import universe.action.ActionContext;
import universe.data.Unit;
import universe.data.unit.Hanger;
import universe.rule.ActionRule;

public class UnitIsHangerRule implements ActionRule {

	Unit theUnit;
	
	public UnitIsHangerRule(Unit unit) {
		theUnit = unit;
	}
	
	public boolean evaluate(ActionContext context) {
		if (theUnit instanceof Hanger) {
			return true;
		}
		return false;
	}

	public String getMessage() {
		return "Unit is not a hanger and has nowhere to store the new unit";
	}

}
