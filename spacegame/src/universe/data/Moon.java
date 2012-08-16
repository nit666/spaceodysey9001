package universe.data;

public class Moon implements Orbitable {

	String name;
	String id;
	Planet parent;
	double period;
	
	public Moon(String id, String name, double period) {
		this.id = id;
		this.name = name;
		this.period = period;
	}
	
	public Moon(Planet parent, String id, String name, double period) {
		this(id, name, period);
		this.parent = parent;
	}
	
	public String getName() {
		return name;
	}

	public String getId() {
		return id;
	}

	public Planet getParent() {
		return parent;
	}
	
	public void setParent(Planet p) {
		this.parent = p;
	}

	public double getPeriod() {
		return period;
	}
}
