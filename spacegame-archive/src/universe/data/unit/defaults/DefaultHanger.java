package universe.data.unit.defaults;

import java.util.HashSet;
import java.util.Set;

import universe.data.Unit;
import universe.data.unit.Hanger;

public class DefaultHanger implements Hanger {

	int maxSpace;
	Set<Unit> units = new HashSet<Unit>();
	
	public DefaultHanger(int space) {
		maxSpace = space;
	}
	
	public void addUnitToHanger(Unit theUnit) {
		if (units.size() >= maxSpace) {
			throw new IllegalStateException("can't fit any more units");
		}
		units.add(theUnit);
	}

	public int getRemainingHangerSpace() {
		return maxSpace - units.size();
	}

	public void removeUnitFromHanger(Unit theUnit) {
		units.remove(theUnit);
	}

	public boolean unitIsInHanger(Unit theUnit) {
		return units.contains(theUnit);
	}

}
