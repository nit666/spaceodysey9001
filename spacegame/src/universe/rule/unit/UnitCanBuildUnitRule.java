package universe.rule.unit;

import universe.action.ActionContext;
import universe.data.Unit;
import universe.data.unit.Constructable;
import universe.data.unit.Factory;
import universe.data.unit.Hanger;
import universe.data.unit.UnitType;
import universe.rule.ActionRule;

public class UnitCanBuildUnitRule implements ActionRule {

	Unit builder;
	UnitType built;
	String message;
	
	public UnitCanBuildUnitRule(Unit builder, UnitType built) {
		this.builder = builder;
		this.built = built;
	}
	
	public boolean evaluate(ActionContext context) {
		if (builder == null) {
			message = "The building unit has not been set";
			return false;
		}
		
		if (built ==  null) {
			message = "The unit to be built has not been set";
			return false;
		}
		
		// assumes that this has been checked already
		Factory factory = (Factory) builder;
		if (!factory.canBuild(built)) {
			message = "The unit type of " + built + " cannot be built by factory";
			return false;
		}
		
		// assumes that this has already been checked
		Hanger hanger = (Hanger) builder;
		if (hanger.getRemainingHangerSpace() < 1) {
			message = "The building unit doesn't have enough space to build a new unit";
			return false;
		}
		
		if (!(built instanceof Constructable)) {
			message = "unit type to be built is not a buildable unit";
			return false;
		}
		
		return true;
	}

	public String getMessage() {
		return message;
	}

}
