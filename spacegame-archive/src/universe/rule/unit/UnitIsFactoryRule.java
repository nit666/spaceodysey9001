package universe.rule.unit;

import universe.action.ActionContext;
import universe.data.Unit;
import universe.data.unit.Factory;
import universe.rule.ActionRule;

public class UnitIsFactoryRule implements ActionRule {

	Unit theUnit;
	
	public UnitIsFactoryRule(Unit unit) {
		theUnit = unit;
	}
	
	public boolean evaluate(ActionContext context) {
		if (theUnit instanceof Factory) {
			return true;
		}
		return false;
	}

	public String getMessage() {
		return "Unit is not a factory but is trying to build something";
	}

}
