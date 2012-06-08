package universe.web;

import java.util.List;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpSession;
import javax.sql.DataSource;

import universe.ControllerContext;
import universe.dao.database.DatabaseControllerContext;
import universe.dao.database.DatasourceConnectionFactory;
import universe.data.Faction;
import universe.data.Planet;
import universe.data.Unit;

public class SessionConstants {

	public static String CONTROLLER_CONTEXT_SESSION_KEY = "action-context";
	
	public static String CURRENT_FACTION_SESSION_KEY = "current-faction";
	
	public static String CURRENT_VIEWED_PLANET = "current-planet";
	
	public static String CURRENT_VIEWED_UNITS = "current-units";

	public static String CURRENT_SELECTED_UNIT = "current-selected-unit";
	
	public static String USER_AUTH_STATUS_SESSION_KEY = "authenticated";
	
	public static enum LOGIN_RESULTS {
		AUTHENTICATED,
		USERNAME_NOT_FOUND,
		PASSWORD_INCORRECT,
		USERNAME_LOCKED,
		USERNAME_MISSING,
		PASSWORD_MISSING;
	
	};
	
	public static Faction getCurrentFaction(HttpSession session) {
		return (Faction) session.getAttribute(CURRENT_FACTION_SESSION_KEY);
	}
	
	public static void setCurrentViewedPlanet(HttpSession session, Planet planet) {
		session.setAttribute(CURRENT_VIEWED_PLANET, planet);
	}
	
	public static Planet getCurrentViewedPlanet(HttpSession session) {
		return (Planet) session.getAttribute(CURRENT_VIEWED_PLANET);
	}

	public static void setCurrentViewedUnits(HttpSession session, List<Unit> units) {
		session.setAttribute(CURRENT_VIEWED_UNITS, units);
	}
	
	@SuppressWarnings("unchecked")
	public static List<Unit> getCurrentViewedUnits(HttpSession session) {
		return (List<Unit>) session.getAttribute(CURRENT_VIEWED_UNITS);
	}
	
	public static void setCurrentSelectedUnit(HttpSession session, Unit theUnit) {
		session.setAttribute(CURRENT_SELECTED_UNIT, theUnit);
	}
	
	public static Unit getCurrentSelectedUnit(HttpSession session) {
		return (Unit) session.getAttribute(CURRENT_SELECTED_UNIT);
	}
	
	public static ControllerContext getControllerContext(HttpSession session) throws ServletException, NamingException {
		String dsString = "java:/comp/env/jdbc/univ";
		Context ic = null;
		
		try {
			if (session.getAttribute(SessionConstants.CONTROLLER_CONTEXT_SESSION_KEY) == null) {
				ic = new InitialContext();
	     		DataSource ds = (DataSource) ic.lookup(dsString);
		    
	     		DatabaseControllerContext context = new DatabaseControllerContext(new DatasourceConnectionFactory(ds));
	     		session.setAttribute(SessionConstants.CONTROLLER_CONTEXT_SESSION_KEY, context);
	     		
	     		System.out.println("Datasource created for session");
			}
			return (ControllerContext) session.getAttribute(SessionConstants.CONTROLLER_CONTEXT_SESSION_KEY);
		
		} finally {
			if (ic != null) {
				ic.close();
			}
		}
	}
	
}
