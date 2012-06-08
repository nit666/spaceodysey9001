package universe.helper;

import universe.data.Location;

public final class LocationHelper {

	public static boolean areSame(Location l1, Location l2) {
		if (l1 == null && l2 == null) {
			return true;
		} else if (l1 == null || l2 == null){
			return false;
		} else if (l1.getParent() == null && l2.getParent() == null) {
			return l1.getX() == l2.getX() &&
			l1.getY() == l2.getY() &&
			l2.getZ() == l2.getZ();

		} else {
			return areSame(l1.getParent(), l2.getParent()) &&
			l1.getX() == l2.getX() &&
			l1.getY() == l2.getY() &&
			l1.getZ() == l2.getZ();
		}
	}
	
}
