package universe.rule.faction;

import universe.action.ActionContext;
import universe.data.Unit;
import universe.rule.ActionRule;

public class FactionOwnsUnitAction implements ActionRule {

	Unit unit;
	
	public FactionOwnsUnitAction(Unit unit) {
		this.unit = unit;
	}
	
	public boolean evaluate(ActionContext context) {
		if (unit.getOwner().equals(context.getCurrentFaction())) {
			return true;
		}
		return false;
	}

	public String getMessage() {
		return "The action cannot be performed on the current unit becuase the faction doesn't own it";
	}

}
