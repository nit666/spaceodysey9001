package universe.data.unit;

import universe.data.Cargo;
import universe.data.cargo.MineralType;

public interface CargoHold {

	double getRemainingCargoOfType(Cargo type);
	double getRemainingCargoOfType(MineralType type);
	double removeCargoOfType(Cargo type, double amount);
	double removeCargoOfType(MineralType type, double amount);
	void addCargoOfType(Cargo type, double amount);
	void addCargoOfType(MineralType type, double amount);
	double getRemainingSpace();
}
