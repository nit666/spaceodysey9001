package universe.data.shipcomponents;

import java.util.HashMap;
import java.util.Map;
import java.util.Set;

import universe.data.Cargo;
import universe.data.ShipComponent;

public class CargoHoldShipComponent extends ShipComponent {

	double maxSpace; // in kilogrammes
	double currentSpace;
	Map<String, Cargo> cargo = new HashMap<String, Cargo>(); 
	
	public CargoHoldShipComponent(String name, double maxSpace) {
		super(ComponentType.CARGO_HOLD, name);
		this.maxSpace = maxSpace;
		this.currentSpace = maxSpace;
	}
	
	/**
	 * Get a list of all the cargo types that are currently in the cargo hold
	 * @return
	 */
	public Set<String> getAvailableCargo() {
		return cargo.keySet();
	}
	
	public void addCargoOfType(Cargo type) {
		if (getRemainingSpace() < type.getWeight()) {
			throw new IllegalStateException("not enough space to hold cargo");
		}
		Cargo cargoType = cargo.get(type.getId());
		if (cargoType == null) {
			cargo.put(type.getId(), type);
		} else {
			// already exists, this shouldn't happen for quest packages, but some cargos are scalable, like resources
			cargoType.setWeight(cargoType.getWeight() + type.getWeight());
			cargo.put(type.getId(), cargoType);
		}
		currentSpace -= type.getWeight();
	}

	public double removeCargoOfType(Cargo type) {
		double actualAmountTaken = 0;
		Cargo existingCargo = cargo.get(type.getId());
		if (existingCargo == null) {
			return 0;
		} else if (type.getWeight() >= existingCargo.getWeight()){
			actualAmountTaken = existingCargo.getWeight();
			cargo.remove(type.getId());
		} else {
			actualAmountTaken = type.getWeight();
			existingCargo.setWeight(existingCargo.getWeight() - type.getWeight());
			cargo.put(type.getId(), existingCargo);
		}
		currentSpace += actualAmountTaken;
		if (currentSpace > maxSpace) {
			currentSpace = maxSpace;
		}
		return actualAmountTaken;
	}
	
	public double getRemainingCargoOfType(Cargo type) {
		Cargo existingCargo = cargo.get(type.getId());
		if (existingCargo == null) {
			return 0;
		}
		return existingCargo.getWeight();
	}

	public boolean doesCargoTypeExist(Cargo type) {
		return cargo.containsKey(type.getId());
	}
	
	public double getRemainingSpace() {
		return currentSpace;
	}
}
