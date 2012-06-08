package universe.rule.faction;

import universe.action.ActionContext;
import universe.data.Faction;
import universe.rule.ActionRule;

public class FactionExistsRule implements ActionRule {
	
	Faction theFaction;
	
	FactionExistsRule(int factionToCheck) {
		theFaction = new Faction();
		theFaction.setFactionId(factionToCheck);
	}
	
	FactionExistsRule(Faction factionToCheck) {
		theFaction = factionToCheck;	
	}
	
	public boolean evaluate(ActionContext context) {
		
		if (theFaction == null || theFaction.getFactionId() == 0) {
			return false;
		}
		
		if (context.getFactionDAO().getFactionByID(theFaction.getFactionId()) != null) {
			return true;
		}
		return false;
	}

	public String getMessage() {
		// TODO Auto-generated method stub
		return null;
	}

}
