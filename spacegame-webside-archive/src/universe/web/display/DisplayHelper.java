package universe.web.display;

import java.util.List;

import universe.action.ActionType;
import universe.data.Faction;
import universe.data.Planet;
import universe.data.PlanetLink;
import universe.data.Unit;
import universe.data.cargo.MineralType;
import universe.data.unit.units.MiningColonyUnit;
import universe.web.action.MoveUnitAction;
import universe.web.action.UnitActionWebAction;

public class DisplayHelper {

	
	private DisplayHelper() {}
	
	public static String showPlanetInfo(Planet planet, Faction faction) {
		
		StringBuilder info = new StringBuilder();
		info.append("<div id=\"planet-info\">");
		info.append("<p>Planet: ").append(planet.getName()).append("</p>");
		info.append("<p>Type: ").append(planet.getType()).append("</p>");
		info.append("<p>Owner: ").append(faction.getName()).append("</p>");
		info.append("</div>");
		
		return info.toString();
	}
	
	public static String showColonyInfo(MiningColonyUnit colony) {
		StringBuilder info = new StringBuilder();
		info.append("<div id=\"colony-info\">");
		info.append("<p>Colony: </p>");
		info.append("<p>Gas: <span class=\"gas\">").append(colony.getRemainingCargoOfType(MineralType.GAS)).append("</span></p>");
		info.append("<p>Water: <span class=\"water\">").append(colony.getRemainingCargoOfType(MineralType.WATER)).append("</span></p>");
		info.append("<p>Minerals: <span class=\"minerals\">").append(colony.getRemainingCargoOfType(MineralType.ORE)).append("</span></p>");
		info.append("</div>");
		
		return info.toString();
	}
	
	public static String showUnitInfoForFaction(Faction theFaction, List<Unit> units, boolean loggedInFaction) {
		StringBuilder info = new StringBuilder();
		
		if (units.size() == 0) {
			return "";
		}
		
		info.append("<div id=\"faction-unit-info\">");
		if (loggedInFaction) {
			info.append("<p>My Ships:</p>");
		}
		
		for (Unit u : units) {
			if (u.getOwner().getFactionId() == theFaction.getFactionId() && !(u instanceof MiningColonyUnit)) {
				;
				info.append("<p>");
				if (loggedInFaction && u.getCurrentAction() != ActionType.BEING_BUILT.getValue()) {
					info.append(new DefaultActionLink(UnitActionWebAction.NAME, u.getUnitType().getLabel(), "unit=" + u.getUnitId()).draw());
				} else if (loggedInFaction && u.getCurrentAction() == ActionType.BEING_BUILT.getValue()) {
					info.append(u.getUnitType().getLabel() + " (under construction)");
				} else {
					info.append(u.getUnitType().getLabel());
				}
				info.append("</p>");
			}
		}
		info.append("</div>");
		
		return info.toString();
	}
	
	public static String showLinkedPlanets(List<PlanetLink> links, String actionToUse) {
		StringBuilder out = new StringBuilder();
		
		out.append("<div id=\"planet-links\">");
		out.append("Nearby Planets");
		for (PlanetLink link : links) {
			out.append("<p>")
			.append(new DefaultActionLink(MoveUnitAction.NAME, link.getDestination().getName(), "link=" + link.getId()).draw())
			.append("</p>");
		}
		out.append("</div>");
		
		return out.toString();
	}
}
