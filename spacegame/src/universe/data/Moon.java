package universe.data;

public class Moon implements Location {

	String name;
	String id;
	Planet parent;
	
	public Moon(String id, String name) {
		this.id = id;
		this.name = name;
	}
	
	public Moon(Planet parent, String id, String name) {
		this(id, name);
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
}
