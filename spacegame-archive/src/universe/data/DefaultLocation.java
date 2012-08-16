package universe.data;

public class DefaultLocation implements Location {

	long id;
	Location parent;
	int x;
	int y;
	int z;
	
	public DefaultLocation(long id, Location parent, int x, int y, int z) {
		this.id = id;
		this.parent = parent;
		this.x = x;
		this.y = y;
		this.z = z;
	}
	
	public long getLocationId() {
		return id;
	}

	public Location getParent() {
		return (parent == null)? BaseLocation.getInstance() : parent;
	}

	public double getX() {
		return x;
	}

	public double getY() {
		return y;
	}

	public double getZ() {
		return z;
	}

	public Object getKey() {
		return getLocationId();
	}

}
