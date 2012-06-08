package universe.data.unit.units;

import universe.data.Cargo;
import universe.data.Faction;
import universe.data.Location;
import universe.data.Unit;
import universe.data.cargo.MineralType;
import universe.data.unit.Building;
import universe.data.unit.CargoHold;
import universe.data.unit.Constructable;
import universe.data.unit.Factory;
import universe.data.unit.Hanger;
import universe.data.unit.Mine;
import universe.data.unit.UnitType;
import universe.data.unit.defaults.DefaultCargoHold;
import universe.data.unit.types.MiningColony;
import universe.data.unit.types.UnitTypeFactory;

public class MiningColonyUnit extends Unit implements Building, Constructable,
		CargoHold, Hanger, Mine, Factory {

	DefaultCargoHold cargoHold = new DefaultCargoHold(300000);
	
	public MiningColonyUnit(Faction owner, Location location, long id) {
		super(owner, location, id);
	}

	@Override
	public UnitType getUnitType() {
		return UnitTypeFactory.getUnitTypeFromName(MiningColony.NAME);
	}

	public void addCargoOfType(Cargo type, double amount) {
		cargoHold.addCargoOfType(type, amount);
	}

	public double getRemainingCargoOfType(Cargo type) {
		return cargoHold.getRemainingCargoOfType(type);
	}

	public double getRemainingSpace() {
		return cargoHold.getRemainingSpace();
	}

	public double removeCargoOfType(Cargo type, double amount) {
		return cargoHold.removeCargoOfType(type, amount);
	}

	public void addUnitToHanger(Unit theUnit) {
		// TODO Auto-generated method stub

	}

	public int getRemainingHangerSpace() {
		// TODO Auto-generated method stub
		return 0;
	}

	public void removeUnitFromHanger(Unit theUnit) {
		// TODO Auto-generated method stub

	}

	public boolean unitIsInHanger(Unit theUnit) {
		// TODO Auto-generated method stub
		return false;
	}

	public Cargo getMineType() {
		// TODO Auto-generated method stub
		return null;
	}

	public double getMineralsPerSecond() {
		// TODO Auto-generated method stub
		return 0;
	}

	public boolean canBuild(UnitType type) {
		return true;
	}

	public void addCargoOfType(MineralType type, double amount) {
		cargoHold.addCargoOfType(type, amount);
	}

	public double getRemainingCargoOfType(MineralType type) {
		return cargoHold.getRemainingCargoOfType(type);
	}

	public double removeCargoOfType(MineralType type, double amount) {
		return cargoHold.removeCargoOfType(type, amount);
	}

}
