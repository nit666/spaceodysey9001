package universe.dao;


import java.util.List;

import universe.data.Faction;

public interface FactionDAO {

	Faction getFactionByID(long factionId);
	
	Faction getFactionByName(String name);
	
	List<Faction> getKnownFactions(Faction f);
	
	void saveFaction(Faction f);
}
