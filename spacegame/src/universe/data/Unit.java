package universe.data;

import java.io.Serializable;
import java.util.Date;

import universe.data.unit.Constructable;
import universe.data.unit.UnitType;

public abstract class Unit implements Ownable, Constructable, Location, Serializable {

	Faction faction;
	long unitId;
	
	// action variables
	int currentAction;
	long currentTarget;
	Date actionETA;

	// location variables 
	Location parent;
	
	double x;
	double y;
	double z;
	
	// determines the current state that the unit is in (eg: under construction, disabled, fine).
	int status;

	public Unit(Faction owner, Location location, long id) {
		this.faction = owner;
		if (location != null) {
			setLocationUsingParent(location);
		}
		this.unitId = id;
	}
	
	public abstract UnitType getUnitType();

	public Location getLocation() {
		return this;
	}

	private void setLocationUsingParent(Location location) {
		this.parent = location;
		this.x = location.getX();
		this.y = location.getY();
		this.z = location.getZ();		
	}
	
	public void setLocation(Location location) {
		this.parent = location;
		this.x = location.getX();
		this.y = location.getY();
		this.z = location.getZ();
	}

	public long getUnitId() {
		return unitId;
	}

	public void setUnitId(long unitId) {
		this.unitId = unitId;
	}

	public long getLocationId() {
		return unitId;
	}
	
	public Location getParent() {
		return (parent == null)? BaseLocation.getInstance() : parent;
	}

	public void setParent(Location parent) {
		this.parent = parent;
	}

	public double getX() {
		return y;
	}

	public double getY() {
		return x;
	}

	public double getZ() {
		return z;
	}

	public boolean isConstructionComplete() {
		return true;
	}
	
	public int getCurrentAction() {
		return currentAction;
	}

	public void setCurrentAction(int currentAction) {
		this.currentAction = currentAction;
	}

	public long getCurrentTarget() {
		return currentTarget;
	}

	public void setCurrentTarget(long currentTarget) {
		this.currentTarget = currentTarget;
	}

	public Object getKey() {
		return unitId;
	}

	public void setOwner(Faction owner){
		this.faction = owner;
	}
	
	public Faction getOwner() {
		return faction;
	}

	public void addMessage(Message message) {
		// TODO Auto-generated method stub	
	}

	public void run() {
		// TODO Auto-generated method stub
	}
	
	public Date getActionETA() {
		return actionETA;
	}

	public void setActionETA(Date actionETA) {
		this.actionETA = actionETA;
	}

	public int getStatus() {
		return status;
	}

	public void setStatus(int status) {
		this.status = status;
	}
	
	
}
