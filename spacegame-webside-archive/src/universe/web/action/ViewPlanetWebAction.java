package universe.web.action;

import java.util.List;

import javax.servlet.jsp.JspWriter;
import javax.servlet.jsp.PageContext;

import universe.ControllerContext;
import universe.data.Faction;
import universe.data.Planet;
import universe.data.Unit;
import universe.data.unit.units.MiningColonyUnit;
import universe.web.SessionConstants;
import universe.web.display.DisplayHelper;

public class ViewPlanetWebAction extends AbstractWebAction {

	public void act(PageContext context, JspWriter out) {
		
		try {
			ControllerContext cc = SessionConstants.getControllerContext(context.getSession());
		
			String planetId = context.getRequest().getParameter("plid");
			Planet p = null;
			if (planetId != null) {
				int plid = Integer.parseInt(planetId);
				
				p = cc.getPlanetDAO().getPlanetById(plid);
			} else {
				p = SessionConstants.getCurrentViewedPlanet(context.getSession());				
			}
			
			if (p == null) {
				throw new IllegalStateException("no planet id found!");
			}

			// the the planet as the currently viewed planet so we can come back to here.
			SessionConstants.setCurrentViewedPlanet(context.getSession(), p);
			
			List<Unit> units = cc.getUnitDAO().getAllUnitsForPlanet(p);
			
			SessionConstants.setCurrentViewedUnits(context.getSession(), units);
			
			Faction theFaction = SessionConstants.getCurrentFaction(context.getSession());
			
//			List<PlanetLink> planetLinks = cc.getPlanetDAO().getLinkedPlanets(p);
			
			// now do the drawing!
			out.print(DisplayHelper.showPlanetInfo(p, SessionConstants.getCurrentFaction(context.getSession())));

			// if this is the case, get the colony from the units and print out some details
			if (p.getFaction().equals(theFaction)) {
				for (Unit u : units) {
					if (u instanceof MiningColonyUnit) {
						MiningColonyUnit mu = (MiningColonyUnit) u;
						out.print(DisplayHelper.showColonyInfo(mu));
					}
				}
			}
			// show the units, first for the owner faction
			out.print(DisplayHelper.showUnitInfoForFaction(theFaction, units, true));
			
			// now for the other factions
			List<Faction> factions = cc.getFactionDAO().getKnownFactions(theFaction);
			
			for (Faction f : factions) {
				out.print("<p>Units for faction " + f.getName() + "</p>");
				out.print(DisplayHelper.showUnitInfoForFaction(f, units, false));
			}
			
		} catch (Exception e) {
			e.printStackTrace();
			throw new IllegalStateException(e);
		}
	}

	public String getName() {
		return "ViewPlanet";
	}
}
