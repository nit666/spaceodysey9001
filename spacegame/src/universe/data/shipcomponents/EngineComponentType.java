package universe.data.shipcomponents;

import universe.data.ShipComponent;

public class EngineComponentType extends ShipComponent {

	double speed = 1; // speed is an abstract unit to measure speed
	
	public EngineComponentType(String name) {
		super(ComponentType.ENGINE, name);
	}
	
	public double getSpeed() {
		return speed;
	}
	
	public EngineComponentType setSpeed(double speed) {
		this.speed = speed;
		return this;
	}
}
