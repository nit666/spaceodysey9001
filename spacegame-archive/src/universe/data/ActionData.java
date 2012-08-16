package universe.data;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import universe.action.DatabaseAction;

public class ActionData {

	DatabaseAction action;
	Faction faction;
	Date lastUpdatedTime;
	Map<String, Object> parameters = new HashMap<String, Object>();
	
	public Faction getFaction() {
		return faction;
	}
	public void setFaction(Faction faction) {
		this.faction = faction;
	}
	public Map<String, Object> getParameters() {
		return parameters;
	}
	public void setParameters(Map<String, Object> parameters) {
		this.parameters = parameters;
	}
	@SuppressWarnings("unchecked")
	public void addParameter(String name, Object value, Class type) {
		value.getClass().asSubclass(type);
		parameters.put(name, value);
	}
	
	public DatabaseAction getAction() {
		return action;
	}
	public void setAction(DatabaseAction action) {
		this.action = action;
	}
	public Date getLastUpdatedTime() {
		return lastUpdatedTime;
	}
	public void setLastUpdatedTime(Date lastUpdatedTime) {
		this.lastUpdatedTime = lastUpdatedTime;
	}
	
}
