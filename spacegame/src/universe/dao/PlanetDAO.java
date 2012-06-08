package universe.dao;

import java.util.List;

import universe.data.Faction;
import universe.data.Planet;
import universe.data.PlanetLink;
import universe.data.unit.MineralNode;

public interface PlanetDAO {

	List<Planet> getPlanetsForFaction(Faction f);
	
	List<Planet> getAllPlanets();
	
	Planet getPlanetById(long id);
	
	void savePlanet(Planet p);
	
	void saveMineralNode(MineralNode node);
	
	List<PlanetLink> getLinkedPlanets(Planet p);
	
	PlanetLink getLinkedPlanetByLinkId(long linkId);
}
