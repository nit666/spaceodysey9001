package universe.data.unit.events;

import universe.ControllerContext;
import universe.data.Planet;

public interface EventOnMoveTo {

	void moveToEvent(ControllerContext cc, Planet dest);
	
}
