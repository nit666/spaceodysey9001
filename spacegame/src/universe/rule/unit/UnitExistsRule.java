package universe.rule.unit;

import universe.action.ActionContext;
import universe.data.Unit;
import universe.rule.ActionRule;

public class UnitExistsRule implements ActionRule {

	Unit theUnit;
	
	public UnitExistsRule(Unit unit) {
		this.theUnit = unit;
	}
	
	public boolean evaluate(ActionContext context) {
		if (theUnit == null || theUnit.getUnitId() == 0) {
			return false;
		}
		
		if (context.getUnitDAO().getUnitById(theUnit.getUnitId()) == null) {
			return false;
		}
		return true;
	}

	public String getMessage() {
		return "Unit does not exist but is trying to build";
	}

}
