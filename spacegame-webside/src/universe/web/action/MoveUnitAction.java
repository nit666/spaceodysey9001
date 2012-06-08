package universe.web.action;

import javax.servlet.jsp.JspWriter;
import javax.servlet.jsp.PageContext;

import universe.ControllerContext;
import universe.action.unit.MoveUnitToAction;
import universe.data.Faction;
import universe.data.Planet;
import universe.data.PlanetLink;
import universe.data.Unit;
import universe.data.unit.MoveableInHyperspace;
import universe.web.SessionConstants;
import universe.web.display.DisplayHelper;

public class MoveUnitAction extends AbstractWebAction {

	public static String NAME = "MoveUnit";
	
	public void act(PageContext context, JspWriter out) {
		// get the unit to move from the session constants
		Unit theUnit = SessionConstants.getCurrentSelectedUnit(context.getSession());
		
		try {
			ControllerContext ccontext = SessionConstants.getControllerContext(context.getSession());
			
			Planet p = SessionConstants.getCurrentViewedPlanet(context.getSession());
			
			String linkId = context.getRequest().getParameter("link");
			
			if (linkId == null) {
				// if there is no planet id specified in the params then print a list of planets
				out.print(DisplayHelper.showLinkedPlanets(ccontext.getPlanetDAO().getLinkedPlanets(p), getName()));
				
			} else {
				// if there is a planet id, then start a move action to that planet and print confirmation
				MoveUnitToAction action = new MoveUnitToAction();
								
				PlanetLink link = ccontext.getPlanetDAO().getLinkedPlanetByLinkId(Long.valueOf(linkId));
				
				try {
					action.startAction(theUnit, SessionConstants.getControllerContext(context.getSession()), link, SessionConstants.getCurrentViewedUnits(context.getSession()));
					out.print("<p>ok, it's on the way!</p>");
				} catch (Exception e) {
					out.print("<p>" + e.getMessage() + "</p>");
				}
				
			}
		} catch (Exception e) {
			e.printStackTrace();
			throw new RuntimeException(e.getCause());
		}
	}

	public String getName() {
		return NAME;
	}

	@Override
	public boolean isAvailable(PageContext context) {
		
		Faction faction = SessionConstants.getCurrentFaction(context.getSession());
		
		// get the unit to be moved from the session constants
		Unit unit = SessionConstants.getCurrentSelectedUnit(context.getSession());
		
		// if the unit doesn't exist, then return false
		if (unit == null) return false;
		
		// if the unit doesn't belong to the current faction then return false
		if (unit.getOwner().getFactionId() != faction.getFactionId()) {
			return false;
		}
		
		// if the unit cannot move then return false
		if (!(unit instanceof MoveableInHyperspace)) {
			return false;
		}	
		
		return true;
	}

	
	
}
