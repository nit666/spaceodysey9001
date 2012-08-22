package universe.data.shipcomponents;

import universe.data.ShipComponent;

public class NavigationShipComponent extends ShipComponent {

	double range; // in kilometers
	
	public NavigationShipComponent(String name) {
		super(ComponentType.NAVIGATION_COMPUTUER, name);
	}

	public double getRange() {
		return range;
	}

	public NavigationShipComponent setRange(double range) {
		this.range = range;
		return this;
	}
}
