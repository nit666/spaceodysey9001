package universe.data.unit.types;

import universe.helper.TimeHelper;

public enum UnitTypeConstants {
		ANTI_AIR_DEFENCE(100, 120),
		ARMY(80, 60),
		BOMBER(140, 120),
		COLONY_SHIP(400, 600),
		CRUISER(220, 300),
		DESTROYER(220, 300),
		FIGHTER(140, 120),
		MINING_COLONY(0, 300),
		MISSILE(20, 60),
		MISSILE_LAUNCHER(160, 120),
		PROBE(50, 90),
		TANK(100, 120),
		TRANSPORT(240, 300)
	;
	
	long minerals;
	long constructionTime;
	
	private UnitTypeConstants(long minerals, long constructionTime) {
		this.minerals = minerals;
		this.constructionTime = constructionTime;
	}

	public long getMinerals() {
		return minerals;
	}

	public long getConstructionTime() {
		return TimeHelper.SecondsToMilliSeconds(constructionTime);
	}
	
	
}
