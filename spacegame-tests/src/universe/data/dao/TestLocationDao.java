package universe.data.dao;

import universe.data.Moon;
import universe.data.Wormhole;
import universe.helper.TimeHelper;

public class TestLocationDao extends LocationDao {

	/** 
	 * create a planet map, with moons and wormholes. Then all we need is the id where the player ship 
	 * currently is and can get the rest of the information that is needed.
	 * 
	 * The planets contain links to their moons and wormholes that leave from that planet. 
	 */
	public TestLocationDao() {
		
		// TODO fill in names for the planets
		// TODO fill in orbital period for moons and wormholes
		createPlanet("P1", "P1");
		createPlanet("P2", "P2");
		createPlanet("P3", "P3");
		createPlanet("P4", "P4");
		createPlanet("P5", "P5");
		createPlanet("P6", "P6");
		createPlanet("P7", "Mare");
		createPlanet("P8", "P8");
		createPlanet("P9", "P9");
		createPlanet("P10", "P10");
		createPlanet("P11", "P11");
		createPlanet("P12", "P12");
		createPlanet("P13", "P13");
		createPlanet("P14", "P14");
		createPlanet("P15", "P15");
		createPlanet("P16", "P16");
		createPlanet("P17", "P17");
		createPlanet("P18", "P18");
		createPlanet("P19", "P19");
		createPlanet("P20", "P20");
		
		getPlanetById("P1")
			.addMoon(new Moon("M1P1", "M1P1", TimeHelper.HoursToMilliSeconds(24)), locationMap)
			.addMoon(new Moon("M2P1", "M2P1", TimeHelper.HoursToMilliSeconds(35)), locationMap)
			.addMoon(new Moon("M3P1", "M3P1", TimeHelper.HoursToMilliSeconds(40)), locationMap)
			.addMoon(new Moon("M4P1", "M4P4", TimeHelper.HoursToMilliSeconds(64)), locationMap)
			.addWormhole(new Wormhole("W10", TimeHelper.HoursToMilliSeconds(70), getPlanetById("P10"), TimeHelper.HoursToMilliSeconds(153),
					TimeHelper.HoursToMilliSeconds(4)), locationMap)
			.addWormhole(new Wormhole("W11", TimeHelper.HoursToMilliSeconds(59), getPlanetById("P18"), TimeHelper.HoursToMilliSeconds(32), 
					TimeHelper.HoursToMilliSeconds(4)), locationMap);

		
		getPlanetById("P2")
			.addMoon(new Moon("M1P2", "M1P2", TimeHelper.HoursToMilliSeconds(300)), locationMap)
			.addWormhole(new Wormhole("W1", TimeHelper.HoursToMilliSeconds(76), getPlanetById("P1"), TimeHelper.HoursToMilliSeconds(134),
					TimeHelper.HoursToMilliSeconds(2.5f)), locationMap)
			.addWormhole(new Wormhole("W2", TimeHelper.HoursToMilliSeconds(89), getPlanetById("P4"), TimeHelper.HoursToMilliSeconds(45),
					TimeHelper.HoursToMilliSeconds(2)), locationMap)
			.addWormhole(new Wormhole("W3", TimeHelper.HoursToMilliSeconds(250), getPlanetById("P3"), TimeHelper.HoursToMilliSeconds(468),
					TimeHelper.HoursToMilliSeconds(2)), locationMap)
			.addWormhole(new Wormhole("W4", TimeHelper.HoursToMilliSeconds(24), getPlanetById("P10"), TimeHelper.HoursToMilliSeconds(39),
					TimeHelper.HoursToMilliSeconds(3)), locationMap);
		
		getPlanetById("P3")
			.addWormhole(new Wormhole("W3a", 1, getPlanetById("P2"), 1,
					TimeHelper.HoursToMilliSeconds(2)), locationMap);
		
		getPlanetById("P4")
			.addWormhole(new Wormhole("W2a", 1, getPlanetById("P2"), 1,
					TimeHelper.HoursToMilliSeconds(2)), locationMap);
		
		getPlanetById("P5")
			.addMoon(new Moon("M1P5", "M1P5", 1), locationMap)
			.addMoon(new Moon("M2P5", "M2P5", 1), locationMap)
			.addMoon(new Moon("M3P5", "M3P5", 1), locationMap)
			.addWormhole(new Wormhole("W4a", 1, getPlanetById("P2"), 1,
					TimeHelper.HoursToMilliSeconds(3)), locationMap)
			.addWormhole(new Wormhole("W5", 1, getPlanetById("P6"), 1,
					TimeHelper.HoursToMilliSeconds(2.5f)), locationMap);
		
		getPlanetById("P6")
			.addWormhole(new Wormhole("W5a", 1, getPlanetById("P5"), 1,
					TimeHelper.HoursToMilliSeconds(2.5f)), locationMap)
			.addWormhole(new Wormhole("W8", 1, getPlanetById("P9"), 1, 
					TimeHelper.HoursToMilliSeconds(2)), locationMap)
			.addWormhole(new Wormhole("W7", 1, getPlanetById("P8"), 1,
					TimeHelper.HoursToMilliSeconds(1)), locationMap)
			.addWormhole(new Wormhole("W6", 1, getPlanetById("P7"), 1,
					TimeHelper.HoursToMilliSeconds(1.5f)), locationMap);
		
		getPlanetById("P7")
			.addMoon(new Moon("M1P7", "Mirror Mare", 1), locationMap)
			.addWormhole(new Wormhole("W6a", 1, getPlanetById("P6"), 1, 
					TimeHelper.HoursToMilliSeconds(1.5f)), locationMap);
		
		getPlanetById("P8")
			.addWormhole(new Wormhole("W7a", 1, getPlanetById("P6"), 1,
					TimeHelper.HoursToMilliSeconds(1)), locationMap);
		
		getPlanetById("P9")
			.addMoon(new Moon("M1P9", "M1P9", 1), locationMap)
			.addWormhole(new Wormhole("W8a", 1, getPlanetById("P6"), 1,
					TimeHelper.HoursToMilliSeconds(2)), locationMap);
		
		getPlanetById("P10")
			.addWormhole(new Wormhole("W10a", 1, getPlanetById("P1"), 1,
					TimeHelper.HoursToMilliSeconds(4)), locationMap)
			.addWormhole(new Wormhole("W9", 1, getPlanetById("P5"), 1, 
					TimeHelper.HoursToMilliSeconds(2.5f)), locationMap);
		
		getPlanetById("P11")
			.addWormhole(new Wormhole("W14", 1, getPlanetById("P13"), 1,
					TimeHelper.HoursToMilliSeconds(3)), locationMap);
		
		getPlanetById("P12")
			.addMoon(new Moon("M1P12", "M1P12", 1), locationMap)
			.addWormhole(new Wormhole("W15", 1, getPlanetById("P13"), 1,
					TimeHelper.HoursToMilliSeconds(3)), locationMap);
		
		getPlanetById("P13")
			.addMoon(new Moon("M1P13", "M1P13", 1), locationMap)
			.addWormhole(new Wormhole("W13", 1, getPlanetById("P10"), 1,
					TimeHelper.HoursToMilliSeconds(3.5f)), locationMap)
			.addWormhole(new Wormhole("W14a", 1, getPlanetById("P11"), 1,
					TimeHelper.HoursToMilliSeconds(3)), locationMap)
			.addWormhole(new Wormhole("W15a", 1, getPlanetById("P12"), 1,
					TimeHelper.HoursToMilliSeconds(3)), locationMap)
			.addWormhole(new Wormhole("W16", 1, getPlanetById("P14"), 1,
					TimeHelper.HoursToMilliSeconds(2)), locationMap)
			.addWormhole(new Wormhole("W19", 1, getPlanetById("P17"), 1,
					TimeHelper.HoursToMilliSeconds(2.5f)), locationMap)
			.addWormhole(new Wormhole("W20", 1, getPlanetById("P18"), 1,
					TimeHelper.HoursToMilliSeconds(4)), locationMap);
		
		getPlanetById("P14")
			.addWormhole(new Wormhole("W16a", 1, getPlanetById("P13"), 1,
					TimeHelper.HoursToMilliSeconds(2)), locationMap)
			.addWormhole(new Wormhole("W17", 1, getPlanetById("P16"), 1,
					TimeHelper.HoursToMilliSeconds(1)), locationMap)
			.addWormhole(new Wormhole("W18", 1, getPlanetById("P15"), 1,
					TimeHelper.HoursToMilliSeconds(1.5f)), locationMap);
					
		getPlanetById("P15")
			.addWormhole(new Wormhole("W18a", 1, getPlanetById("P14"), 1,
					TimeHelper.HoursToMilliSeconds(1.5f)), locationMap);
		
		getPlanetById("P16")
			.addWormhole(new Wormhole("W17a", 1, getPlanetById("P14"), 1,
					TimeHelper.HoursToMilliSeconds(1)), locationMap);
		
		getPlanetById("P17")
			.addWormhole(new Wormhole("W21", 1, getPlanetById("P18"), 1,
					TimeHelper.HoursToMilliSeconds(2f)), locationMap);
		
		getPlanetById("P18")
			.addWormhole(new Wormhole("W12a", 1, getPlanetById("P10"), 1,
					TimeHelper.HoursToMilliSeconds(6)), locationMap)
			.addWormhole(new Wormhole("W20a", 1, getPlanetById("P13"), 1,
					TimeHelper.HoursToMilliSeconds(4)), locationMap)
			.addWormhole(new Wormhole("W22", 1, getPlanetById("P19"), 1,
					TimeHelper.HoursToMilliSeconds(1.5f)), locationMap)
			.addWormhole(new Wormhole("W23", 1, getPlanetById("P20"), 1,
					TimeHelper.HoursToMilliSeconds(1.5f)), locationMap);
		
		getPlanetById("P19")
			.addMoon(new Moon("M1P19", "M1P19", 1), locationMap)
			.addMoon(new Moon("M2P19", "M2P19", 1), locationMap)
			.addMoon(new Moon("M3P19", "M3P19", 1), locationMap)
			.addWormhole(new Wormhole("W22a", 1, getPlanetById("P18"), 1,
					TimeHelper.HoursToMilliSeconds(1.5f)), locationMap);
		
		getPlanetById("P20")
			.addWormhole(new Wormhole("W23a", 1, getPlanetById("P18"), 1,
					TimeHelper.HoursToMilliSeconds(1.5f)), locationMap);
	}
	
}
