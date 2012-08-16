package universe;

import java.util.Date;
import java.util.List;
import java.util.Scanner;
import java.util.StringTokenizer;

import universe.data.Location;
import universe.data.Moon;
import universe.data.PathElement;
import universe.data.Planet;
import universe.data.Player;
import universe.data.PlayerShip;
import universe.data.Wormhole;
import universe.data.dao.LocationDao;
import universe.helper.TimeHelper;

public class ShellGame {

	public ShellGame() {}
	
	public void start() {
		LocationDao dao = new LocationDao();
		Player player = new Player();
		PlayerShip ship = new PlayerShip();
		ship.setPlayer(player);
		ship.setCurrentLocation(dao.getLocationById("P1"));
		
		System.out.print("Command? ");
		Scanner scan = new Scanner(System.in);
		String line = scan.nextLine();
		while (!"end".equals(line)) {
			StringTokenizer st = new StringTokenizer(line, " ");
			if (st.countTokens() == 1) {
				String command = st.nextToken();
				if ("info".equals(command)) {
					printPossiblePaths(ship.getCurrentLocation());
				} else if ("status".equals(command)) {
					doShipStatus(ship);
				}
			} else if (st.countTokens() == 2) {
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
				
				else if ("path".equals(command)) {
					if (ship.getCurrentPath() != null) {
						System.out.println("The ship is already moving");
					} else {
						try {
						    Location l = dao.getLocationById(param);
						    // find a path from the current location to a new location and set it on the ship
						    ship.setCurrentPath(dao.findPathFromTo(ship.getCurrentLocation(), l, ship.getSpeed()));
						    printPath(ship.getCurrentPath());
						} catch (Exception e) {
							System.out.println(e.getMessage());
						}
					}
				} 
				
				else if ("goto".equals(command)) {
					try {
					    Location l = dao.getLocationById(param);
					    ship.setCurrentLocation(l);
					    ship.setCurrentPath(null);
					    System.out.println("Ship location set to " + l.getId());
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
		System.out.println("    Parent Planet: " + p.getName());
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
	
	private void printPath(List<PathElement> elements) {
		System.out.println("Path: ");
		for (PathElement e : elements) {
			Date d = new Date(e.getArrivalTime());
			System.out.println("    " + e.getDestination().getId() + " arrives at " + d.toString());
		}
	}
	
	private Location getPath(List<PathElement> elements, long currentTime) {
		Location loc = elements.get(0).getDestination();
		if (currentTime >= elements.get(0).getArrivalTime()) {
			elements.remove(0);
			if (elements.size() > 0) {
				getPath(elements, currentTime);
			}
		} 
		return loc;
	}
	
	private void doShipStatus(PlayerShip ship) {
		// check status and update ship
		if (ship.getCurrentPath() != null) {
			long currentTime = TimeHelper.getCurrentTime();
			Location nextLocation =getPath(ship.getCurrentPath(), currentTime);
			ship.setCurrentLocation(nextLocation);
			if (ship.getCurrentPath().size() == 0) {
				ship.setCurrentPath(null);
			}
		}
		if (ship.getCurrentPath() != null) {
			System.out.println(getCurrentDestinationString(ship.getCurrentLocation()));
			printPath(ship.getCurrentPath());
		} else {
			System.out.println("Ship is located at " + ship.getCurrentLocation().getId());
		}
	}
	
	private String getCurrentDestinationString(Location l) {
		if (l instanceof Planet) {
			return "Your ship is currently in free space, destined for the Planet " + l.getId(); 
		} else if (l instanceof Wormhole.WormholeDestinationOrbit) {
			Wormhole.WormholeDestinationOrbit dest = (Wormhole.WormholeDestinationOrbit) l;
			return "Your ship is in hyperspace, heading for the " + dest.getParent().getName() + " system";
		} else if (l instanceof Wormhole) {
			Wormhole dest = (Wormhole) l;
			return "Your ship is in the " + dest.getParent().getName() + " system heading for the " + dest.getEndPlanet().getName() + " system via hyperspace";
		} else if (l instanceof Moon) {
			Moon m = (Moon) l;
			return "Your ship is in the " + m.getParent().getName() + " system heading for the moon " + m.getName();
		} else {
			return "I have no idea where you ship is!";
		}
	}
	
	/**
	 * @param args
	 */
	public static void main(String[] args) {
		new ShellGame().start();
	}

}
