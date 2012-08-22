package universe.data;

public class ShipComponent {

	public enum ComponentType {
		CHASSIS,
		ENGINE,
		ENERGY_STORAGE,
		NAVIGATION_COMPUTUER,
		MAIN_COMPUTER,
		LIFE_SUPPORT,
		CARGO_HOLD,
		CABIN
	}
	
	ComponentType type;
	String name;
	Rarity rarity = Rarity.EVERYWHERE;
	
	// energy usage
	double idleEnergyCost = 0; // the energy usage when the ship is docked or idle.
	double usingEnergyCost = 0; // the energy usage when the ship is moving.
	double standbyEnergyCost = 0; // the energy usage when the component is in standby mode.
	
	double weight = 0; // weight in kilos
	
	// durability determines how likely this component is to fail
	int maxDurability = 0;
	int currentDurability = 0;
	
	// The cost to buy the component (determines repair cost as well)
	double baseCost = 0; // how much the component costs at the very minimum
	double rarityCostMin = 0; // min adder for rarity
	double rarityCostMax = 0; // max adder for rarity
	
	public ShipComponent(ComponentType type, String name) {
		this.type = type;
		this.name = name;
	}
	
	public ComponentType getType() {
		return type;
	}
	
	public String getName() {
		return name;
	}

	public double getIdleEnergyCost() {
		return idleEnergyCost;
	}



	public double getUsingEnergyCost() {
		return usingEnergyCost;
	}

	public double getStandbyEnergyCost() {
		return standbyEnergyCost;
	}

	public int getMaxDurability() {
		return maxDurability;
	}
	
	public ShipComponent setUsingEnergyCost(double usingEnergyCost) {
		this.usingEnergyCost = usingEnergyCost;
		return this;
	}

	public ShipComponent setIdleEnergyCost(double idleEnergyCost) {
		this.idleEnergyCost = idleEnergyCost;
		return this;
	}

	public ShipComponent setStandbyEnergyCost(double standbyEnergyCost) {
		this.standbyEnergyCost = standbyEnergyCost;
		return this;
	}


	public ShipComponent setMaxDurability(int maxDurability) {
		this.maxDurability = maxDurability;
		return this;
	}

	public int getCurrentDurability() {
		return currentDurability;
	}

	public ShipComponent setCurrentDurability(int currentDurability) {
		this.currentDurability = currentDurability;
		return this;
	}

	public double getBaseCost() {
		return baseCost;
	}

	public ShipComponent setBaseCost(double baseCost) {
		this.baseCost = baseCost;
		return this;
	}

	public double getRarityCostMin() {
		return rarityCostMin;
	}

	public ShipComponent setRarityCostMin(double rarityCostMin) {
		this.rarityCostMin = rarityCostMin;
		return this;
	}

	public double getRarityCostMax() {
		return rarityCostMax;
	}

	public ShipComponent setRarityCostMax(double rarityCostMax) {
		this.rarityCostMax = rarityCostMax;
		return this;
	}	

	public double getWeight() {
		return weight;
	}

	public ShipComponent setWeight(double weight) {
		this.weight = weight;
		return this;
	}
}
