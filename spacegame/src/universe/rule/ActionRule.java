package universe.rule;

import universe.action.ActionContext;

public interface ActionRule {

	boolean evaluate(ActionContext context);
	
	String getMessage();
}
