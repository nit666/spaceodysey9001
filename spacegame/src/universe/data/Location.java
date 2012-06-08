package universe.data;

public interface Location extends DataObject {

	long getLocationId();
	Location getParent();
	double getX();
	double getY();
	double getZ();
}
