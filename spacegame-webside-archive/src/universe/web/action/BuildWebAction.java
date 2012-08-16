package universe.web.action;

import java.io.IOException;
import java.util.List;

import javax.naming.NamingException;
import javax.servlet.ServletException;
import javax.servlet.jsp.JspWriter;
import javax.servlet.jsp.PageContext;

import universe.action.unit.BuildUnitAction;
import universe.data.Faction;
import universe.data.Unit;
import universe.data.cargo.MineralType;
import universe.data.unit.CargoHold;
import universe.data.unit.Factory;
import universe.data.unit.UnitType;
import universe.data.unit.types.MiningColony;
import universe.data.unit.types.UnitTypeFactory;
import universe.web.SessionConstants;
import universe.web.display.HTMLElement;

public class BuildWebAction extends AbstractWebAction {

	final double MIN_MINERALS_TO_BUILD = 10.0;
	
	public void act(PageContext context, JspWriter out) {
		try {
			// first check if we have something that can be built on the request
			String unit = context.getRequest().getParameter("buildunit");
			
			if (unit != null) {
				// if so then start a build action
				List<Unit> units = SessionConstants.getCurrentViewedUnits(context.getSession());
				Faction theFaction = SessionConstants.getCurrentFaction(context.getSession());
				
				UnitType typeToBuild = UnitTypeFactory.getUnitTypeFromName(unit);
				
				BuildUnitAction action = new BuildUnitAction();
				
				for (Unit u : units) {	
					if (u.getOwner().getFactionId() == theFaction.getFactionId()) {
						if (u instanceof Factory) {
							action.startAction(u, SessionConstants.getControllerContext(context.getSession()), typeToBuild);
						}
					}
				}

				out.print("A " + typeToBuild.getTypeId() + " is now being built");
				
			} else {
				// if not then generate a list of things that can be built
				for (UnitType type : UnitTypeFactory.getAllUnitTypes()) {

					// can't build a colony because only a colony ship can do that!
					if (type.getTypeId().equals(MiningColony.NAME)) {
						continue;
					}
					
					HTMLElement el = new HTMLElement("A")
						.addAttribute("class", "action-link")
						.addAttribute("id", getName())
						.addAttribute("href", "main.jsp?action=" + getName() + "&buildunit=" + type.getTypeId())
						.addValue(type.getLabel());
					out.print(el.draw());
					out.print("<BR />");
				}
				
			}
		
		} catch (IOException e) {
			e.printStackTrace();
			throw new IllegalStateException(e);
		} catch (ServletException e) {
			e.printStackTrace();
			throw new IllegalStateException(e);
		} catch (NamingException e) {
			e.printStackTrace();
			throw new IllegalStateException(e);
		}
	}

	public String getName() {
		return "Build";
	}

	@Override
	public boolean isAvailable(PageContext pContext) {
		boolean available = false;
		
		List<Unit> units = SessionConstants.getCurrentViewedUnits(pContext.getSession());
		Faction theFaction = SessionConstants.getCurrentFaction(pContext.getSession());
		
		// check if there is enough minerals to build something (min of 10)
		double totalMinerals = 0.0;
		for (Unit u : units) {
			if (u.getOwner().getFactionId() == theFaction.getFactionId()) {
				if (u instanceof CargoHold) {
					CargoHold ch = (CargoHold) u;
					totalMinerals += ch.getRemainingCargoOfType(MineralType.ORE);
				}
			}
		}
		
		if (totalMinerals < MIN_MINERALS_TO_BUILD) {
			return false;
		}
		
		// next check is that there is a colony or some such unit that can build, and that the faction can build it!
		for (Unit u : units) {
			if (u.getOwner().getFactionId() == theFaction.getFactionId()) {
				if (u instanceof Factory) {
					available = true;
					break;
				}
			}
			available = false;
		}
		
		return available;
	}

	
}
