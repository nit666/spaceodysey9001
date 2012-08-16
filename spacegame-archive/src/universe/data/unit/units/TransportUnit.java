package universe.data.unit.units;

import universe.data.Cargo;
import universe.data.Faction;
import universe.data.Location;
import universe.data.Unit;
import universe.data.cargo.MineralType;
import universe.data.unit.CargoHold;
import universe.data.unit.Constructable;
import universe.data.unit.MoveableInHyperspace;
import universe.data.unit.UnitType;
import universe.data.unit.defaults.DefaultCargoHold;
import universe.data.unit.types.Transport;
import universe.data.unit.types.UnitTypeFactory;
import universe.helper.MetricHelper;

public class TransportUnit extends Unit implements CargoHold, Constructable, MoveableInHyperspace {

	CargoHold cargo = new DefaultCargoHold(MetricHelper.getKilosForTonne(1000.0));
	
	public TransportUnit(Faction owner, Location location, long id) {
		super(owner, location, id);
	}

	public void addCargoOfType(Cargo type, double amount) {
		cargo.addCargoOfType(type, amount);
	}

	public double getRemainingCargoOfType(Cargo type) {
		return cargo.getRemainingCargoOfType(type);
	}

	public double getRemainingSpace() {
		return cargo.getRemainingSpace();
	}

	public double removeCargoOfType(Cargo type, double amount) {
		return cargo.removeCargoOfType(type, amount);
	}

	@Override
	public UnitType getUnitType() {
		return UnitTypeFactory.getUnitTypeFromName(Transport.NAME);
	}

	public void addCargoOfType(MineralType type, double amount) {
		cargo.addCargoOfType(type, amount);
	}

	public double getRemainingCargoOfType(MineralType type) {
		return cargo.getRemainingCargoOfType(type);
	}

	public double removeCargoOfType(MineralType type, double amount) {
		return cargo.removeCargoOfType(type, amount);
	}

}
