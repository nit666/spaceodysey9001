package universe.dao;

import java.util.List;

import universe.data.Faction;
import universe.data.Planet;
import universe.data.Unit;
import universe.data.unit.UnitType;

public interface UnitDAO {

	Unit getUnitById(long id);
	
	List<Unit> getAllUnits();
	
	List<Unit> getAllUnitsForPlanetByFaction(Faction f, Planet p);
	
	List<Unit> getAllUnitsForPlanet(Planet p);
	
	List<Unit> getAllUnitsWithExpiredActions();
	
	List<Unit> getAllUnitsOfType(UnitType type);
	
	void saveUnit(Unit theUnitToSave);
	
	void insertUnit(Unit theUnitToSave);
}
