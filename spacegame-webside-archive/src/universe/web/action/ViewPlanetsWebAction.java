package universe.web.action;

import java.util.List;

import javax.servlet.jsp.JspWriter;
import javax.servlet.jsp.PageContext;

import universe.ControllerContext;
import universe.data.Planet;
import universe.web.SessionConstants;
import universe.web.display.HTMLElement;

/**
 * View all planets that are available to a faction.
 */
public class ViewPlanetsWebAction extends AbstractWebAction {

	public void act(PageContext context, JspWriter out) {
		try {
			ControllerContext cc = SessionConstants.getControllerContext(context.getSession());
			List<Planet> planets = cc.getPlanetDAO().getPlanetsForFaction(SessionConstants.getCurrentFaction(context.getSession()));
			
			out.print("<p>Below is a list of all known planets</p>");
			
			HTMLElement el = new HTMLElement("DIV");
			el.addAttribute("id", "body-text");
			for (Planet p : planets) {
				el.addChild("DIV").addAttribute("class", "link")
					.addChild("A")
					.addAttribute("href", "main.jsp?action=ViewPlanet&plid=" + p.getPlanetId())
					.addChild("SPAN")
					    .addAttribute("class", "planet")
					    .addValue(p.getName());
			}
			out.print(el.draw());
			
			out.print("<p>Selecting a link will view details about that planet.");
			
		} catch (Exception e) {
			e.printStackTrace();
			throw new IllegalStateException(e);
		}
	}

	public String getName() {
		return "ViewAllPlanets";
	}

}
