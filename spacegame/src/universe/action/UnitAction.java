package universe.action;

import universe.ControllerContext;
import universe.data.Unit;

public interface UnitAction {

	void startAction(Unit u, ControllerContext cc, Object ... params);
	
	void completeAction(Unit u, ControllerContext cc);
	
}
