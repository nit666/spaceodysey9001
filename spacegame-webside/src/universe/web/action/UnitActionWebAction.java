package universe.web.action;

import javax.servlet.jsp.JspWriter;
import javax.servlet.jsp.PageContext;

import universe.ControllerContext;
import universe.data.Unit;
import universe.web.SessionConstants;

public class UnitActionWebAction extends AbstractWebAction {
	
	public static String NAME = "UnitActions";
	
	public void act(PageContext context, JspWriter out) {
		// get the unit to be viewed from the session
		String unitId = context.getRequest().getParameter("unit");
		
		try {
			ControllerContext ccontext = SessionConstants.getControllerContext(context.getSession());

			Unit theUnit = ccontext.getUnitDAO().getUnitById(Integer.valueOf(unitId));
			
			// put the unit to be viewed in the session constants
			SessionConstants.setCurrentSelectedUnit(context.getSession(), theUnit);
			
			// view details about the unit, eg: what minerals are in it, the location, owner etc..
			out.print("<p>What would you like this unit to do?</p>");
			
			// the action list will be determined by the config
			
		} catch (Exception e) {
			e.printStackTrace();
			throw new RuntimeException(e.getCause());
		}
	}

	public String getName() {
		return "UnitActions";
	}
}
