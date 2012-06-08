package universe.data;

public class PlanetLink {

	long id;
	Planet source;
	Planet destination;
	int travelTime; // (in seconds)
	int travelCost; // (gas cost)
	
	public PlanetLink(long id, Planet source, Planet dest, int travelTime, int travelCost) {
		this.id = id;
		this.source = source;
		this.destination = dest;
		this.travelTime = travelTime;
		this.travelCost = travelCost;
	}
	
	public long getId() {
		return id;
	}
	public Planet getSource() {
		return source;
	}
	public Planet getDestination() {
		return destination;
	}
	public int getTravelTime() {
		return travelTime;
	}
	public int getTravelCost() {
		return travelCost;
	}
}
