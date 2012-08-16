package universe.data.dao;

import java.util.List;

import junit.framework.TestCase;
import universe.data.Location;
import universe.data.Moon;
import universe.data.Planet;
import universe.data.Wormhole;

public class LocationDaoTest extends TestCase {

	public void testLocationMap() {
		LocationDao dao = new LocationDao();
		
		Location l = dao.getLocationById("P1");
		
		assertTrue(l instanceof Planet);
		
		Planet p = (Planet) l;
		List<Wormhole> wh = p.getWormholes();
		assertEquals(wh.size(), 2);
		
		List<Moon> moons = p.getMoons();
		assertEquals(moons.size(), 4);
		
		l = dao.getLocationById("W11");
		assertTrue(l instanceof Wormhole);
		
		Wormhole w = (Wormhole) l;
		assertEquals(w.getStartPlanet().getId(), "P1");
		
		l = dao.getLocationById("M3P1");
		assertTrue(l instanceof Moon);
		Moon m = (Moon) l;
		assertEquals(m.getParent().getId(), "P1");
	}
}
