package universe.data.dao;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

import universe.data.ShipComponent;
import universe.data.shipcomponents.CargoHoldShipComponent;
import universe.data.shipcomponents.ChassisComponentType;
import universe.data.shipcomponents.ComputerShipComponent;
import universe.data.shipcomponents.EnergyStoreComponentType;
import universe.data.shipcomponents.EngineComponentType;
import universe.data.shipcomponents.LifeSupportComponentType;
import universe.data.shipcomponents.NavigationShipComponent;

public class ShipComponentDao {

	static List<ShipComponent> startingComponents = null;
	
	// create some ship components
	public ShipComponentDao() {
		// ships are made up of different components, the first being the chassis, which is the main part of the ship
		ChassisComponentType chassis = new ChassisComponentType("Dart Model Chassis Unit");
		chassis
			.setWeight(2000)
		  	.setBaseCost(10000)
		  	.setRarityCostMin(1000)
		  	.setRarityCostMax(3000)
		  	.setMaxDurability(1500)
		  	.setCurrentDurability(1500);
		addComponent(chassis);
		
		// engines have all the normal components, plus a speed factor
		EngineComponentType engine = new EngineComponentType("Light Ion Engine");
		engine
		  	.setSpeed(1.4)
		  	.setWeight(500)
		  	.setBaseCost(1300)
		  	.setRarityCostMin(200)
		  	.setRarityCostMax(500)
		  	.setMaxDurability(200)
		  	.setCurrentDurability(200)
		  	.setUsingEnergyCost(300);
		
		// An energy unit that stores 1000 energy, or with the above engine about 3 hours of use before refueling
		EnergyStoreComponentType battery = new EnergyStoreComponentType("1000 Energy Battery Unit");
		battery
		  	.setCapacity(1000)
		  	.setWeight(150)
		  	.setBaseCost(700)
		  	.setRarityCostMin(50)
		  	.setRarityCostMax(150)
		  	.setMaxDurability(400)
		  	.setCurrentDurability(400);
		addComponent(battery);
		
		ComputerShipComponent computer = new ComputerShipComponent("Basic Flight Computer");
		computer
			.setWeight(5)
			.setBaseCost(350)
			.setRarityCostMin(15)
			.setRarityCostMax(50)
			.setMaxDurability(1000)
			.setCurrentDurability(1000)
			.setUsingEnergyCost(20)
			.setIdleEnergyCost(5)
			.setStandbyEnergyCost(2);
		addComponent(computer);
		
		LifeSupportComponentType lifeSupport = new LifeSupportComponentType("Two Person Cabin Life Support");
		lifeSupport
			.setWeight(25)
			.setBaseCost(650)
			.setRarityCostMin(20)
			.setRarityCostMax(50)
			.setMaxDurability(300)
			.setCurrentDurability(300)
			.setUsingEnergyCost(35)
			.setIdleEnergyCost(0)
			.setStandbyEnergyCost(0);
		addComponent(lifeSupport);
		
		NavigationShipComponent navcom = new NavigationShipComponent("Short Range Radar");
		navcom
			.setRange(30)
			.setWeight(3)
			.setBaseCost(250)
			.setRarityCostMin(10)
			.setRarityCostMax(40)
			.setMaxDurability(700)
			.setCurrentDurability(700)
			.setUsingEnergyCost(10)
			.setIdleEnergyCost(0)
			.setStandbyEnergyCost(1);
		addComponent(navcom);
		
		CargoHoldShipComponent cargo = new CargoHoldShipComponent("50kg Storage Unit", 50);
		cargo
			.setWeight(2)
			.setBaseCost(40)
			.setRarityCostMin(5)
			.setRarityCostMax(15)
			.setMaxDurability(3000)
			.setCurrentDurability(3000);
		
		
		if (startingComponents == null) {
			startingComponents = new LinkedList<ShipComponent>();
			startingComponents.add(chassis);
			startingComponents.add(engine);
			startingComponents.add(battery);
			startingComponents.add(computer);
			startingComponents.add(lifeSupport);
			startingComponents.add(navcom);
			startingComponents.add(cargo);
		}
	}
	
	Map<String, ShipComponent> components = new HashMap<String, ShipComponent>();
	
	public void addComponent(ShipComponent component) {
		if (!components.containsKey(component.getName())) {
			components.put(component.getName(), component);
		} else {
			throw new IllegalStateException("A component with the name " + component.getName() + " already exists");
		}
	}
	
	public ShipComponent getComponent(String name) {
		if (components.containsKey(name)) {
			return components.get(name);
		} else {
			throw new IllegalStateException("No such component with name " + name + " exists");
		}
	}
	
	public List<ShipComponent> getStartingComponents() {
		return startingComponents;
	}
}
