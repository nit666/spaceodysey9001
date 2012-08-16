package universe.dao.database;

import universe.action.DatabaseAction.ErrorStatus;
import universe.dao.ActionDAO;
import universe.data.ActionData;

public class DbActionDAO implements ActionDAO {

	ConnectionFactory cf;
	
	public DbActionDAO(ConnectionFactory cf) {
		this.cf = cf;
	}
	
	public void deleteActionData(ActionData data) {
		
	}

	public ActionData getNextAction() {
		return null;
	}

	public void saveActionData(ActionData data) {

	}

	public void writeActionLogMessage(ErrorStatus status, ActionData data, String message) {

	}

}
