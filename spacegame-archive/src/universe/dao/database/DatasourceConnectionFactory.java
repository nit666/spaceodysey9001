package universe.dao.database;

import java.sql.Connection;
import java.sql.SQLException;

import javax.sql.DataSource;

public class DatasourceConnectionFactory implements ConnectionFactory {

	DataSource source;
	
	public DatasourceConnectionFactory(DataSource d) {
		this.source = d;
	}
	
	public Connection getConnection() throws SQLException {
		return source.getConnection();
	}

	public void returnConnection(Connection c) {
		if (c != null) {
			try {
				c.close();
			} catch (Exception e) {}
		}
	}

}
