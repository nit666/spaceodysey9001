package universe;

import universe.dao.ActionDAO;
import universe.dao.FactionDAO;
import universe.dao.KnowsAboutDAO;
import universe.dao.MessageDAO;
import universe.dao.PlanetDAO;
import universe.dao.UnitDAO;
import universe.dao.WebPageDAO;

public interface ControllerContext {
	// get some handy data access objects
	FactionDAO getFactionDAO();
	UnitDAO getUnitDAO();
	ActionDAO getActionDAO();
	MessageDAO getMessageDAO();
	KnowsAboutDAO getKnowsAboutDAO();
	PlanetDAO getPlanetDAO();
	WebPageDAO getWebPageDAO();
	
	long getNextId();
	Object getPreference(PreferenceCodes name);
}
