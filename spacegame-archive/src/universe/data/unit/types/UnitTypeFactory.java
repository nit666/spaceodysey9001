package universe.data.unit.types;

import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

import universe.data.Faction;
import universe.data.Location;
import universe.data.Unit;
import universe.data.unit.UnitType;

public class UnitTypeFactory {

	static Map<String, UnitType> types = new HashMap<String, UnitType>();
	
	static void addUnitType(UnitType type) {
		types.put(type.getTypeId(), type);
	}
	
	static {
		addUnitType(new Army());
		addUnitType(new Bomber());
		addUnitType(new ColonyShip());
		addUnitType(new Cruiser());
		addUnitType(new Destroyer());
		addUnitType(new Fighter());
		addUnitType(new MiningColony());
		addUnitType(new Missile());
		addUnitType(new MissileLauncher());
		addUnitType(new Probe());
		addUnitType(new Tank());
		addUnitType(new Transport());
		
		addUnitType(new Missile());
	}
	
	public static Unit getUnitFromTypeName(String name, Faction faction, Location location, int id) {
		UnitType type = types.get(name);
		return type.getNewInstance(faction, location, id);
	}
	
	public static UnitType getUnitTypeFromName(String name) {
		return types.get(name);
	}
	
	public static Collection<UnitType> getAllUnitTypes() {
		return types.values();
	}
}
