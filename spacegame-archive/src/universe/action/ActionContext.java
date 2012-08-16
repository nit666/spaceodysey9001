package universe.action;

import universe.dao.ActionDAO;
import universe.dao.FactionDAO;
import universe.dao.KnowsAboutDAO;
import universe.dao.MessageDAO;
import universe.dao.PlanetDAO;
import universe.dao.UnitDAO;
import universe.data.Faction;

public interface ActionContext {

	// get parameters and so forth
	String getParameter(String name);
	@SuppressWarnings("unchecked")
	Object getParameter(String name, Class type);
	@SuppressWarnings("unchecked")
	void addParameter(String name, Object value, Class type);
	
	// other session details
	Faction getCurrentFaction();
	Long getLastActionTime();
	Long getCurrentActionTime();
	
	// get some handy data access objects
	FactionDAO getFactionDAO();
	UnitDAO getUnitDAO();
	ActionDAO getActionDAO();
	MessageDAO getMessageDAO();
	KnowsAboutDAO getKnowsAboutDAO();
	PlanetDAO getPlanetDAO();
}
