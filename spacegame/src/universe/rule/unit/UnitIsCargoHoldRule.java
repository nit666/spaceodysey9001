package universe.rule.unit;

import universe.action.ActionContext;
import universe.data.Unit;
import universe.data.unit.CargoHold;
import universe.rule.ActionRule;

public class UnitIsCargoHoldRule implements ActionRule {

	Unit theUnit;
	
	public UnitIsCargoHoldRule(Unit unit) {
		theUnit = unit;
	}
	
	public boolean evaluate(ActionContext context) {	
		if (theUnit instanceof CargoHold) {
			return true;
		}
		return false;
	}

	public String getMessage() {
		return "Unit cannot hold cargo";
	}

}
