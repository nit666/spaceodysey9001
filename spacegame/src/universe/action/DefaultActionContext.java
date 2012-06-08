package universe.action;

import java.util.Date;

import universe.ControllerContext;
import universe.dao.ActionDAO;
import universe.dao.FactionDAO;
import universe.dao.KnowsAboutDAO;
import universe.dao.MessageDAO;
import universe.dao.PlanetDAO;
import universe.dao.UnitDAO;
import universe.data.ActionData;
import universe.data.Faction;

public class DefaultActionContext implements ActionContext {

	ControllerContext ccontext; 
	ActionData actionData;
	Date creationTime;
	
	public DefaultActionContext(ControllerContext ccontext) {
		this.ccontext = ccontext;
		creationTime = new Date();
	}
	
	public ActionDAO getActionDAO() {
		return ccontext.getActionDAO();
	}

	public Long getCurrentActionTime() {
		return creationTime.getTime();
	}

	public Faction getCurrentFaction() {
		return actionData.getFaction();
	}

	public FactionDAO getFactionDAO() {
		return ccontext.getFactionDAO();
	}

	public Long getLastActionTime() {
		if (actionData.getLastUpdatedTime() == null) {
			return new Date().getTime();
		}
		return actionData.getLastUpdatedTime().getTime();
	}

	public MessageDAO getMessageDAO() {
		return ccontext.getMessageDAO();
	}

	public String getParameter(String name) {
		return (String) actionData.getParameters().get(name);
	}

	@SuppressWarnings("unchecked")
	public Object getParameter(String name, Class type) {
		try {
			Object param = actionData.getParameters().get(name);
			if (param == null) {
				return null;
			}
			if (!(type.isAssignableFrom(param.getClass()))) {
				throw new IllegalStateException("class name mismatch");
			}
			return param;
		} catch (Exception e) {
			e.printStackTrace();
		} 
		
		return null;
	}

	public UnitDAO getUnitDAO() {
		return ccontext.getUnitDAO();
	}

	public ActionData getActionData() {
		return actionData;
	}

	public void setActionData(ActionData actionData) {
		this.actionData = actionData;
	}

	public KnowsAboutDAO getKnowsAboutDAO() {
		return ccontext.getKnowsAboutDAO();
	}

	@SuppressWarnings("unchecked")
	public void addParameter(String name, Object value, Class type) {
		actionData.addParameter(name, value, type);
		getActionDAO().saveActionData(actionData);
	}

	public PlanetDAO getPlanetDAO() {
		return ccontext.getPlanetDAO();
	}

}
