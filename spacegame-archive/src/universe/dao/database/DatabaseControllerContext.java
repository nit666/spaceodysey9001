package universe.dao.database;

import java.util.Calendar;
import java.util.Date;

import universe.ControllerContext;
import universe.PreferenceCodes;
import universe.dao.ActionDAO;
import universe.dao.FactionDAO;
import universe.dao.KnowsAboutDAO;
import universe.dao.MessageDAO;
import universe.dao.PlanetDAO;
import universe.dao.UnitDAO;
import universe.dao.WebPageDAO;

public class DatabaseControllerContext implements ControllerContext {

	ActionDAO actionDAO;
	FactionDAO factionDAO;
	PlanetDAO planetDAO;
	UnitDAO unitDAO;
	WebPageDAO webpageDAO;
	MessageDAO messageDAO;
	
	public DatabaseControllerContext(ConnectionFactory conFactory) {
		
		actionDAO = new DbActionDAO(conFactory);
		factionDAO = new DbFactionDAO(conFactory);
		planetDAO = new DbPlanetDAO(conFactory, this);
		unitDAO = new DbUnitDAO(conFactory, this);
		webpageDAO = new DbWebPageDAO(conFactory);
		messageDAO = new DbMessageDAO(conFactory, this);
	}
	
	public ActionDAO getActionDAO() {
		return actionDAO;
	}

	public FactionDAO getFactionDAO() {
		return factionDAO;
	}

	public KnowsAboutDAO getKnowsAboutDAO() {
		// TODO Auto-generated method stub
		return null;
	}

	public MessageDAO getMessageDAO() {
		return messageDAO;
	}

	public PlanetDAO getPlanetDAO() {
		return planetDAO;
	}

	public UnitDAO getUnitDAO() {
		return unitDAO;
	}

	public WebPageDAO getWebPageDAO() {
		return webpageDAO;
	}

	public long getNextId() {
		return Calendar.getInstance().getTimeInMillis();
	}

	static Date lastCheckTime = Calendar.getInstance().getTime();
	
	public Object getPreference(PreferenceCodes code) {
		if (code ==  PreferenceCodes.LAST_CHECKED_TIME) {
			Date temptime = lastCheckTime;
			lastCheckTime = Calendar.getInstance().getTime();
			return temptime;
		}
		return null;
	}
}
