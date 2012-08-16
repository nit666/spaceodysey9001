package universe.dao;

import universe.data.Faction;

public interface KnowsAboutDAO {
	
	// these values are stored in the knows about table 
	// and this type determines which table to link to
	enum KnowsAboutTypes {
		FACTION("F"),
		UNIT("U"),
		UNIT_TYPE("T"),
		PLANET("P"),
		MINERAL_NODE("M"),
		WORMHOLE("W");
			
		String type;
		
		KnowsAboutTypes(String type) {
			this.type = type;
		}
		
		public String getType() {
			return type;
		}
	}
	
	boolean getFactionKnowsAbout(Faction faction, Object object, KnowsAboutTypes type);
	
	void addFactionKnowsAbout(String factionId, String id, KnowsAboutTypes type);
}
