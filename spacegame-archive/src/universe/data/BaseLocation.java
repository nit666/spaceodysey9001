package universe.data;

public class BaseLocation implements Location {

	public long getLocationId() {
		return 0;
	}

	public Location getParent() {
		return this;
	}

	public double getX() {
		return 0;
	}

	public double getY() {
		return 0;
	}

	public double getZ() {
		return 0;
	}

	public Object getKey() {
		return 0;
	}
	
	private static Location instance;
	
	public static Location getInstance() {
		if (instance == null) {
			instance = new BaseLocation();
		}
		return instance;
	}

}
