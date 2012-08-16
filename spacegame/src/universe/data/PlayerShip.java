package universe.data;

import java.util.List;

public class PlayerShip {
	Player player;
	Location currentLocation;
	
	// used for travel
	List<PathElement> currentPath = null;
	double percentOfPathCompleted = 0;
	
	double speed = 3.799;
	
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
}
