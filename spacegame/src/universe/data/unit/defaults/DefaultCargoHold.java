package universe.data.unit.defaults;

import java.util.HashMap;
import java.util.Map;

import universe.data.Cargo;
import universe.data.cargo.Mineral;
import universe.data.cargo.MineralType;
import universe.data.unit.CargoHold;

public class DefaultCargoHold implements CargoHold {

	double maxSpace; // in tonnes
	double currentSpace;
	Map<Cargo, Double> cargo = new HashMap<Cargo, Double>(); 
	boolean underConstruction;
	
	public DefaultCargoHold(double space) {
		this.maxSpace = space;
		this.currentSpace = maxSpace;
	}
	
	public void addCargoOfType(Cargo type, double amount) {
		if (getRemainingSpace() < amount) {
			throw new IllegalStateException("not enough space to hold cargo");
		}
		Double currentAmount = cargo.get(type);
		if (currentAmount == null) {
			cargo.put(type, new Double(amount));
		} else {
			currentAmount = currentAmount + amount;
			cargo.put(type, currentAmount);
		}
		currentSpace -= amount;
	}


	public double removeCargoOfType(Cargo type, double amount) {
		double actualAmountTaken = 0;
		Double currentAmount = cargo.get(type);
		if (currentAmount == null) {
			return 0;
		} else if (amount >= currentAmount){
			actualAmountTaken = currentAmount;
			cargo.remove(type);
		} else {
			actualAmountTaken = amount;
			cargo.put(type, new Double(currentAmount - amount));
		}
		currentSpace += actualAmountTaken;
		if (currentSpace > maxSpace) {
			currentSpace = maxSpace;
		}
		return actualAmountTaken;
	}
	
	public double getRemainingCargoOfType(Cargo type) {
		Double amount = cargo.get(type);
		if (amount == null) {
			return 0;
		}
		return amount;
	}

	public double getRemainingSpace() {
		return currentSpace;
	}

	public boolean isUnderConstruction() {
		return underConstruction;
	}

	public void setUnderConstruction(boolean underConstruction) {
		this.underConstruction = underConstruction;
	}

	public void addCargoOfType(MineralType type, double amount) {
		addCargoOfType(Mineral.getMineralOfType(type), amount);
	}

	public double getRemainingCargoOfType(MineralType type) {
		return getRemainingCargoOfType(Mineral.getMineralOfType(type));
	}

	public double removeCargoOfType(MineralType type, double amount) {
		return removeCargoOfType(Mineral.getMineralOfType(type), amount);
	}

}
