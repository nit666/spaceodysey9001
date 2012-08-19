package universe.data;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

import universe.datatypes.Location;
import universe.datatypes.Orbitable;
import universe.helper.TimeHelper;

public class PlayerShip extends Orbitable {
	Player player;
	Location currentLocation;
	
	// used for travel
	List<PathElement> currentPath = null;
	double percentOfPathCompleted = 0;
	
	double speed = 0.77;
	
	public Player getPlayer() {
		return player;
	}
	public Location getCurrentLocation() {
		return currentLocation;
	}
	public List<PathElement> getCurrentPath() {
		return currentPath;
	}
	
	public void setPlayer(Player player) {
		this.player = player;
	}
	public void setCurrentLocation(Location currentLocation) {
		this.currentLocation = currentLocation;
	}
	public void setCurrentPath(List<PathElement> currentPath) {
		this.currentPath = currentPath;
	}
	public double getPercentOfPathCompleted() {
		return percentOfPathCompleted;
	}
	public void setPercentOfPathCompleted(double percentOfPathCompleted) {
		this.percentOfPathCompleted = percentOfPathCompleted;
	}
	public double getSpeed() {
		return speed;
	}
	public void setSpeed(double speed) {
		this.speed = speed;
	}
	public Location getParent() {
		return getCurrentLocation();
	}
	public double getPeriod() {
		return getCurrentLocation() instanceof Orbitable? ((Orbitable) getCurrentLocation()).getPeriod() : 0;
	}
	public String getId() {
		return getPlayer().toString();
	}
	@Override
	public List<PathElement> createPathTo(Location startLocation,Location newLocation, long startTime, double shipSpeed, Set<Location> hits) {
		return getCurrentLocation().createPathTo(this.getCurrentLocation(), newLocation, startTime, shipSpeed, hits);
	}
	
	public List<PathElement> pathToNow(Location newLocation) {
		return createPathTo(this, newLocation, TimeHelper.getCurrentTime(), getSpeed(), new HashSet<Location>());
	}
	
}
