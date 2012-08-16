package universe.data;

public class Faction implements DataObject {

	long factionId;
	String name;
	String password;
	String email;
	
	Integer gas;
	Integer water;
	Integer minerals;

	public Integer getGas() {
		return gas;
	}

	public void setGas(Integer gas) {
		this.gas = gas;
	}

	public Integer getWater() {
		return water;
	}

	public void setWater(Integer water) {
		this.water = water;
	}

	public Integer getMinerals() {
		return minerals;
	}

	public void setMinerals(Integer minerals) {
		this.minerals = minerals;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public long getFactionId() {
		return factionId;
	}

	public void setFactionId(long factionId) {
		this.factionId = factionId;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Object getKey() {
		return factionId;
	}

	@Override
	public boolean equals(Object obj) {
		if (obj instanceof Faction) {
			return factionId == ((Faction) obj).getFactionId();
		}
		return super.equals(obj);
	}

	@Override
	public int hashCode() {
		return new Long(factionId).hashCode();
	}
	
	
}
