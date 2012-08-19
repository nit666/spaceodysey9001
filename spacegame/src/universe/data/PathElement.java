package universe.data;

import universe.datatypes.Location;

public class PathElement {
	Location destination;
	long arrivalTime;
	
	public PathElement(Location destination, long arrivalTime) {
		this.destination = destination;
		this.arrivalTime = arrivalTime;
	}

	public long getArrivalTime() {
		return arrivalTime;
	}

	public void setArrivalTime(long arrivalTime) {
		this.arrivalTime = arrivalTime;
	}

	public Location getDestination() {
		return destination;
	}
}
