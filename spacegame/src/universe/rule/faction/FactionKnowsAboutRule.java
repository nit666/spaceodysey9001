package universe.rule.faction;

import universe.action.ActionContext;
import universe.dao.KnowsAboutDAO;
import universe.rule.ActionRule;

public class FactionKnowsAboutRule implements ActionRule {

	
	Object theObject;
	KnowsAboutDAO.KnowsAboutTypes type;
	
	public FactionKnowsAboutRule(Object object, KnowsAboutDAO.KnowsAboutTypes type) { 
		this.theObject = object;
		this.type = type;
	}
	
	public boolean evaluate(ActionContext context) {
		// make sure that faction actually knows this type of unit
		if (context.getKnowsAboutDAO().getFactionKnowsAbout(context.getCurrentFaction(), theObject, type)) {
			return true;
		}
		return false;
			
	}

	public String getMessage() {
		return "The faction doesn't know about this type of object, or the specific object";
	}

}
