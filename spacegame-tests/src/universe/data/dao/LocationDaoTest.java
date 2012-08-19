package universe.data.dao;

import java.util.HashSet;
import java.util.List;

import junit.framework.TestCase;
import universe.data.Moon;
import universe.data.PathElement;
import universe.data.Planet;
import universe.data.Player;
import universe.data.PlayerShip;
import universe.data.Wormhole;
import universe.datatypes.Location;
import universe.helper.PathHelper;

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
	
	public void testPathCreation() {
		LocationDao dao = new LocationDao();
		
		Location p1 = dao.getLocationById("P1");
		PlayerShip s = new PlayerShip();
		s.setPlayer(new Player());
		
		s.setCurrentLocation(p1);
		
		// try moving to a mooon
		Location m1p1 = dao.getLocationById("M1P1");
		List<PathElement> p = s.createPathTo(s, m1p1 , 100000, s.getSpeed(), new HashSet<Location>());
		assertEquals(p.size(), 1);
		assertEquals(p.get(0).getDestination().getId(), "M1P1");
		PathHelper.printPath(p);
		
		// try moving to a planet from a moon
		s.setCurrentLocation(m1p1);
		p = s.createPathTo(s, p1, 100000, s.getSpeed(), new HashSet<Location>());
		assertEquals(p.size(), 1);
		assertEquals(p.get(0).getDestination().getId(), "P1");
		PathHelper.printPath(p);
		
		// try moving from a planet to another planet, single jump
		Location p2 = dao.getLocationById("P2");
		s.setCurrentLocation(p2);
		p = s.createPathTo(s, p1, 100000, s.getSpeed(), new HashSet<Location>());
		PathHelper.printPath(p);
		assertEquals(p.size(), 3);
		assertEquals(p.get(0).getDestination().getId(), "W1");
		assertEquals(p.get(1).getDestination().getId(), "W1");
		assertEquals(p.get(2).getDestination().getId(), "P1");
			

		// try moving from a planet to a moon, single jump
		Location M2P1 = dao.getLocationById("M2P1");
		s.setCurrentLocation(p2);
		p = s.createPathTo(s, M2P1, 100000, s.getSpeed(), new HashSet<Location>());
		assertEquals(p.size(), 3);
		assertEquals(p.get(0).getDestination().getId(), "W1");
		assertEquals(p.get(1).getDestination().getId(), "W1");
		assertEquals(p.get(2).getDestination().getId(), "M2P1");
		PathHelper.printPath(p);
		
		// try moving from a moon to a planet
		s.setCurrentLocation(M2P1);
		p = s.createPathTo(s, p2, 100000, s.getSpeed(), new HashSet<Location>());
		PathHelper.printPath(p);
		assertEquals(p.size(), 7);
//		assertEquals(p.get(0).getDestination().getId(), "W1");
//		assertEquals(p.get(1).getDestination().getId(), "W1");
//		assertEquals(p.get(2).getDestination().getId(), "M2P1");
		
		Location P16 = dao.getLocationById("P16");
		s.setCurrentLocation(p1);
		p = s.createPathTo(s, P16, 100000, s.getSpeed(), new HashSet<Location>());
		PathHelper.printPath(p);
		assertEquals(p.size(), 9);
	}
}
