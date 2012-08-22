package universe.data;

public class Cargo {

	String id;
	String name;
	double weight;
	
	public Cargo(String id, String name, double weight) {
		this.id = id;
		this.name = name;
		this.weight = weight;
	}
	
	// Some cargos have ID's, these are used for missions
	public String getId() {
		return id;
	}
	
	// The name is a user readable name like 'Package for Epsilon', or 'Food'
	public String getName() {
		return name;
	}
	
	// The weight determines how much can be held in a cargo hold
	public double getWeight() {
		return weight;
	}
	
	public void setWeight(double weight) {
		this.weight = weight;
	}
}
