package universe.rule.unit;

import universe.action.ActionContext;
import universe.data.Unit;
import universe.helper.LocationHelper;
import universe.rule.ActionRule;

public class UnitLocationsAreEqual implements ActionRule {

	Unit unita;
	Unit unitb;
	
	public UnitLocationsAreEqual(Unit a, Unit b) {
		unita = a;
		unitb = b;
	}
	
	public boolean evaluate(ActionContext context) {
		return LocationHelper.areSame(unita.getLocation(), unitb.getLocation());
	}

	public String getMessage() {
		return "The unit locations are not the same, but need to be";
	}

}
