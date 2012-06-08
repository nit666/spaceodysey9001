package universe.web.tag;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.naming.ConfigurationException;
import javax.servlet.jsp.JspException;
import javax.servlet.jsp.JspWriter;
import javax.servlet.jsp.tagext.TagSupport;

import universe.data.WebPageLink;
import universe.web.SessionConstants;
import universe.web.action.BuildWebAction;
import universe.web.action.MoveCargoToUnitWebAction;
import universe.web.action.MoveUnitAction;
import universe.web.action.UnitActionWebAction;
import universe.web.action.ViewPlanetWebAction;
import universe.web.action.ViewPlanetsWebAction;
import universe.web.action.WebAction;

public class WebActionTag extends TagSupport {

	static Map<String, WebAction> actions = new HashMap<String, WebAction>();
	
	static void addAction(WebAction theAction) {
		actions.put(theAction.getName(), theAction);
	}
	
	static {
		addAction(new ViewPlanetsWebAction());
		addAction(new ViewPlanetWebAction());
		addAction(new BuildWebAction());
		addAction(new UnitActionWebAction());
		addAction(new MoveUnitAction());
		addAction(new MoveCargoToUnitWebAction());
		// etc... for all actions
	}
	
	@Override
	public int doStartTag() throws JspException {
		
		// get the parameter for the next action
		String actionName = pageContext.getRequest().getParameter("action");
		// get the action!
		WebAction action = actions.get(actionName);
		if (action == null) {
			// TODO do default action here
			action = actions.get("ViewAllPlanets");
		}
		
		// check that it can be done
		if (!action.isAvailable(pageContext)) {
			throw new JspException("Security violation! Action " + actionName + " is not allowed");
		}
		
		// act
		action.act(pageContext, pageContext.getOut());
		
		// get all linking actions
		try {
			List<WebPageLink> linkingActions = SessionConstants.getControllerContext(pageContext.getSession()).getWebPageDAO().getPageActions(actionName);
			
			JspWriter out = pageContext.getOut();
			
			out.print("<div id=\"action-links\">");
			// draw each one that can be done
			for (WebPageLink linkAction : linkingActions) {
				WebAction a = actions.get(linkAction.getParent());
				if (a == null) {
					throw new ConfigurationException("Action " + linkAction + " is specified for page " + actionName + " but doesn't exist");
				}
				if (a.isAvailable(pageContext)) {
					out.print("<p>");
					a.render(out, linkAction.getText());
					out.print("</p>");
				}
			}
			out.print("</div>");

		} catch (Exception e) {
			e.printStackTrace();
			throw new JspException(e);
		} 
		
		return SKIP_BODY;
	}
}
