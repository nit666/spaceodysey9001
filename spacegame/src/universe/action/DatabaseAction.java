package universe.action;

import java.util.ArrayList;
import java.util.List;

import universe.data.ActionData;
import universe.data.Faction;
import universe.rule.ActionRule;

public abstract class DatabaseAction {

	public enum ErrorStatus {
		ERROR,
		WARNING,
		INFO,
		DEBUG
	}
	
	public enum ActionStatus {
		IN_PROGRESS, // 0
		COMPLETE, // 1
		FAILED, // 2
		WAIT // 3
	}
	
	private List<ActionRule> rules = new ArrayList<ActionRule>();
	
	protected void addRule(ActionRule rule) {
		rules.add(rule);
	}
	
	public abstract void initiateRules(ActionContext context);
	
	public String evaluateRules(ActionContext context) {
		
		initiateRules(context);
		
		for (ActionRule rule : rules) {
			if (!rule.evaluate(context)) {
				return rule.getMessage();
			}
		}
		
		return null;
	}
	
	public abstract ActionStatus doAction(ActionContext context) throws ActionException;
	
	public abstract ActionData getActionData(Faction factionId, Object... params); 
	
	protected void check(boolean condition, String messageOnFail) throws ActionException {
		if (!condition) {
			throw new ActionException(messageOnFail);
		}
	}
	
	protected void ensureParamLength(int requiredLength, Object[] params) {
		if (params.length != requiredLength) {
			throw new IllegalArgumentException("invalid number of parameters");
		}
	}
	
	protected void ensureParamLengths(int[] lengthsAllowed, Object[] params) {
		for (int length : lengthsAllowed) {
			if (params.length == length) {
				return;
			}
		}
		throw new IllegalArgumentException("invalid number of parameters");
	}
}
