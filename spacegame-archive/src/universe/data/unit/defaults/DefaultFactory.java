package universe.data.unit.defaults;

import universe.data.Cargo;
import universe.data.cargo.MineralType;
import universe.data.unit.CargoHold;
import universe.data.unit.Factory;
import universe.data.unit.UnitType;

public class DefaultFactory implements Factory {

	UnitType[] buildTypes;
	CargoHold hold;
	boolean underConstruction;
	
	public DefaultFactory(UnitType[] buildTypes, CargoHold hold) {
		this.buildTypes = buildTypes;
		this.hold = hold;
	}

	public boolean canBuild(UnitType type) {
		for (UnitType buildType : buildTypes) {
			if (buildType.getTypeId().equals(type.getTypeId())) {
				return true;
			}
		}
		return false;
	}

	public void addCargoOfType(Cargo type, double amount) {
		hold.addCargoOfType(type, amount);
	}

	public double getRemainingCargoOfType(Cargo type) {
		return hold.getRemainingCargoOfType(type);
	}

	public double getRemainingSpace() {
		return hold.getRemainingSpace();
	}

	public double removeCargoOfType(Cargo type, double amount) {
		return hold.removeCargoOfType(type, amount);
	}

	public boolean isUnderConstruction() {
		return underConstruction;
	}

	public void setUnderConstruction(boolean underConstruction) {
		this.underConstruction = underConstruction;
	}

	public void addCargoOfType(MineralType type, double amount) {
		hold.addCargoOfType(type, amount);
	}

	public double getRemainingCargoOfType(MineralType type) {
		return hold.getRemainingCargoOfType(type);
	}

	public double removeCargoOfType(MineralType type, double amount) {
		return hold.removeCargoOfType(type, amount);
	}

}
