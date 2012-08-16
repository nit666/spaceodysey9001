package universe.data.unit;

import universe.data.Unit;

public interface Hanger {

	void addUnitToHanger(Unit theUnit);
	
	void removeUnitFromHanger(Unit theUnit);
	
	boolean unitIsInHanger(Unit theUnit);
	
	int getRemainingHangerSpace();
}
