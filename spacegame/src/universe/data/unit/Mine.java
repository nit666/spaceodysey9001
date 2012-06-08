package universe.data.unit;

import universe.data.Cargo;

public interface Mine extends CargoHold {

	double getMineralsPerSecond();
	
	Cargo getMineType();
}
