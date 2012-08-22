package universe.data.shipcomponents;

import universe.data.ShipComponent;

public class EnergyStoreComponentType extends ShipComponent {

	double capacity; // energy units are an abstract unit to measure energy
	
	public EnergyStoreComponentType(String name) {
		super(ComponentType.ENERGY_STORAGE, name);
	}

	public double getCapacity() {
		return capacity;
	}
	
	public EnergyStoreComponentType setCapacity(double capacity) {
		this.capacity = capacity;
		return this;
	}
}
