package universe.dao.database;

import java.sql.Connection;
import java.sql.SQLException;

public interface ConnectionFactory {

	Connection getConnection() throws SQLException;
	
	void returnConnection(Connection c);
}
