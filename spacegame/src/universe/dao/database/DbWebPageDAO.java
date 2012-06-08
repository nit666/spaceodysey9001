package universe.dao.database;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import universe.dao.WebPageDAO;
import universe.data.WebPageLink;

public class DbWebPageDAO implements WebPageDAO {

	ConnectionFactory cf;
	
	public DbWebPageDAO(ConnectionFactory cf) {
		this.cf = cf;
	}	

	
	public List<WebPageLink> getPageActions(String pageName) {

		String sql = "select page_name, link_action, link_order, link_text" +
				" from web_action_links " +
				" where page_name = ? " +
				" order by link_order";
		
		Connection conn = null;
		List<WebPageLink> linkActions = new ArrayList<WebPageLink>();
		try {
			conn = cf.getConnection();
			
			PreparedStatement st = conn.prepareStatement(sql);
			st.setString(1, pageName);
			
			ResultSet rs = st.executeQuery();
			
			// is id unique so will never get two rows
			while (rs.next()) {
				WebPageLink linkAction = new WebPageLink();
				linkAction.setName(rs.getString("page_name"));
				linkAction.setText(rs.getString("link_text"));
				linkAction.setOrder(rs.getInt("link_order"));
				linkAction.setParent(rs.getString("link_action"));
				linkActions.add(linkAction);
			}
			return linkActions;
			
		} catch (SQLException e) {
			e.printStackTrace();
			throw new IllegalStateException(e.getCause());
		} finally {
			cf.returnConnection(conn);
		}
	}

	public String getPageText(String pageName) {
		String sql = "select page_name, page_text " +
				"from web_action " +
				"where page_name = ?";
		
		Connection conn = null;

		try {
			conn = cf.getConnection();
			
			PreparedStatement st = conn.prepareStatement(sql);
			st.setString(0, pageName);
			
			ResultSet rs = st.executeQuery();
			
			// id is unique so will never get two rows
			if (rs.next()) {
				return rs.getString(1);
			}
			return null;
			
		} catch (SQLException e) {
			e.printStackTrace();
			throw new IllegalStateException(e.getCause());
		} finally {
			cf.returnConnection(conn);
		}
	}
}
