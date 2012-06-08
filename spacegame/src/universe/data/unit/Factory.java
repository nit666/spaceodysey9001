package universe.data.unit;


public interface Factory extends CargoHold {
	
	boolean canBuild(UnitType type);
}
