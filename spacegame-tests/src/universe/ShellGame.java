package universe;

import java.util.Scanner;
import java.util.StringTokenizer;

import universe.data.Location;
import universe.data.Moon;
import universe.data.Planet;
import universe.data.Wormhole;
import universe.data.dao.LocationDao;

public class ShellGame {

	public ShellGame() {}
	
	public void start() {
		LocationDao dao = new LocationDao();
		
		System.out.print("Command? ");
		Scanner scan = new Scanner(System.in);
		String line = scan.nextLine();
		while (!"end".equals(line)) {
			StringTokenizer st = new StringTokenizer(line, " ");
			if (st.countTokens() == 2) {
				String command = st.nextToken();
				String param = st.nextToken();
				
				if ("info".equals(command)) {
					try {
						Location l = dao.getLocationById(param);
						printPossiblePaths(l); 
					} catch (Exception e) {
						System.out.println(e.getMessage());
					}
				}
			}

			System.out.print("Command? ");
			line = scan.nextLine();
		}

	}
	
	private void printPossiblePaths(Location l) {
		if (l instanceof Planet) {
			printPlanet((Planet) l);
		} else if (l instanceof Moon) {
			printMoon((Moon) l);
		} else if (l instanceof Wormhole) {
			printWormhole((Wormhole) l);
		}
	}
	
	private void printPlanet(Planet p) {
		System.out.println("Location " + p.getId() + " is a planet");
		System.out.println();
		System.out.println("Possible Destinations: ");
		if (p.getMoons().size() > 0) {
			System.out.println("    Moons: ");
			for (Moon m : p.getMoons()) {
				System.out.println("        " + m.getName());
			}
		}
		if (p.getWormholes().size() > 0) {
			System.out.println("    Wormholes: ");
			for (Wormhole w : p.getWormholes()) {
				System.out.println("        " + w.getEndPlanet().getName() + " via wormhole " + w.getId());
			}
		}
	}
	
	private void printMoon(Moon moon) {
		System.out.println("Location " + moon.getId() + " is a moon");
		System.out.println();
		System.out.println("Possible Destinations: ");
		Planet p = moon.getParent();
		if (p.getMoons().size() > 1) {
			System.out.println("    Moons: ");
			for (Moon m : p.getMoons()) {
				if (!m.getId().equals(moon.getId())) {
					System.out.println("        " + m.getName());
				}
			}
		}
		if (p.getWormholes().size() > 0) {
			System.out.println("    Wormholes: ");
			for (Wormhole w : p.getWormholes()) {
				System.out.println("        " + w.getEndPlanet().getName() + " via wormhole " + w.getId());
			}
		}
	}
	
	private void printWormhole(Wormhole w) {
		System.out.println("Location " + w.getId() + " is a wormhole with Desination = " + w.getEndPlanet().getName());
		System.out.println();
	}
	
	/**
	 * @param args
	 */
	public static void main(String[] args) {
		new ShellGame().start();
	}

}
