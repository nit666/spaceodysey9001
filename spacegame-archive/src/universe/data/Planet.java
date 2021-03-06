package universe.data;

import java.io.Serializable;

public class Planet implements Ownable, Serializable {

	long planetId;
	Faction faction;
	double period;
	double radiusFromParent;
	int xloc;
	int yloc;
	Location parent;
	String type;
	int water;
	int gas;
	int minerals;
	String name;
	
	
	
	public double getPeriod() {
		return period;
	}

	public void setPeriod(double period) {
		this.period = period;
	}

	public double getRadiusFromParent() {
		return radiusFromParent;
	}

	public void setRadiusFromParent(double radiusFromParent) {
		this.radiusFromParent = radiusFromParent;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Faction getOwner() {
		return faction;
	}

	public void addMessage(Message message) {
		
	}

	public long getPlanetId() {
		return planetId;
	}

	public void setPlanetId(long planetId) {
		this.planetId = planetId;
	}

	public Faction getFaction() {
		return faction;
	}

	public void setFaction(Faction faction) {
		this.faction = faction;
	}

	public void setXloc(int xloc) {
		this.xloc = xloc;
	}

	public void setYloc(int yloc) {
		this.yloc = yloc;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public int getWater() {
		return water;
	}

	public void setWater(int water) {
		this.water = water;
	}

	public int getGas() {
		return gas;
	}

	public void setGas(int gas) {
		this.gas = gas;
	}

	public int getMinerals() {
		return minerals;
	}

	public void setMinerals(int minerals) {
		this.minerals = minerals;
	}

	public void setParent(Location parent) {
		this.parent = parent;
	}

	public Location getLocation() {
		return this;
	}

	public void run() {
		
	}

	public Object getKey() {
		return planetId;
	}

	public long getLocationId() {
		return planetId;
	}

	public Location getParent() {
		return null;
	}

	public double getX() {
		return xloc;
	}

	public double getY() {
		return yloc;
	}

	public double getZ() {
		return 0;
	}

}
