package universe.dao;

import universe.action.DatabaseAction;
import universe.data.ActionData;

public interface ActionDAO {
	
	ActionData getNextAction();
	void saveActionData(ActionData data);
	void deleteActionData(ActionData data);
	void writeActionLogMessage(DatabaseAction.ErrorStatus status, ActionData data, String message);
}
