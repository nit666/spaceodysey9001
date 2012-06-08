package universe.dao.database;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Date;

import universe.ControllerContext;
import universe.dao.MessageDAO;
import universe.data.Faction;
import universe.data.Message;
import universe.helper.BooleanHelper;

public class DbMessageDAO implements MessageDAO {

	ConnectionFactory cf;
	ControllerContext ccontext;
	
	public DbMessageDAO(ConnectionFactory cf, ControllerContext context) {
		this.cf = cf;
		this.ccontext = context;
	}
	
	private Message getAutoMessage(String name) {
		String sql = "select * from auto_message where name = ?";
		
		Connection conn = null;
		try {
			conn = cf.getConnection();
			
			PreparedStatement st = conn.prepareStatement(sql);
			st.setString(1, name);
			
			ResultSet rs = st.executeQuery();
			
			// is id unique so will never get two rows
			if (rs.next()) {
				Message m = new Message();
				m.setMessageId(ccontext.getNextId());
				m.setTitle(rs.getString("title"));
				m.setMessage(rs.getString("message"));
				m.setRead(false);
				m.setSentDate(new Date());
				return m;
			}
			return null;
			
		} catch (SQLException e) {
			e.printStackTrace();
			throw new IllegalStateException(e.getCause());
		} finally {
			cf.returnConnection(conn);
		}
	}
	
	public void sendMessageToFaction(Faction factionID, String messageName, Object... params) {
		String sql = "insert into message(message_id, sender, reciever, title, message, date_sent, read) " +
				"select ?, ?, ?, ?, ? , CURRENT_TIMESTAMP, 'F'";
		
		Message m = getAutoMessage(messageName);
		
		if (m == null) {
			throw new IllegalStateException("The message with " + messageName + " doesn't exist in the auto_message table");
		}
		
		Connection conn = null;
		
		try {
			conn = cf.getConnection();
			
			PreparedStatement st = conn.prepareStatement(sql);
	
			st.setLong(1, m.getMessageId());
			st.setLong(2, 0);
			st.setLong(3, factionID.getFactionId());
			st.setString(4, m.getTitle());
			st.setString(5, String.format(m.getMessage(), params));
			
			int result = st.executeUpdate();
			if (result != 1) {
				throw new IllegalStateException("can't insert auto message with name = " + messageName);
			}
			
		} catch (SQLException e) {
			e.printStackTrace();
			throw new IllegalStateException(e.getCause());
		} finally {
			cf.returnConnection(conn);
		}	

	}

	public void sendMessageToFaction(Faction sender, Faction reciever,
			String message) {
		// TODO Auto-generated method stub

	}

	public Message getNextMessageForFaction(Faction faction) {
		String sql = "select * from message where reciever = ? and sender = 0 order by date_sent desc";
		
		Connection conn = null;
		Message message = null;
		try {
			conn = cf.getConnection();
			
			PreparedStatement st = conn.prepareStatement(sql);
			st.setLong(1, faction.getFactionId());
			
			ResultSet rs = st.executeQuery();
			
			// is id unique so will never get two rows
			if (rs.next()) {
				message = populateMessage(rs);
			}
			
		} catch (SQLException e) {
			e.printStackTrace();
			throw new IllegalStateException(e.getCause());
		} finally {
			cf.returnConnection(conn);
		}	
		return message;
	}
	
	private Message populateMessage(ResultSet rs) throws SQLException {
		Message m = new Message();
		Faction sender = ccontext.getFactionDAO().getFactionByID(rs.getLong("sender"));
		Faction reciever = ccontext.getFactionDAO().getFactionByID(rs.getLong("reciever"));
		
		m.setMessageId(rs.getLong("message_id"));
		m.setSender(sender);
		m.setReciever(reciever);
		m.setTitle(rs.getString("title"));
		m.setMessage(rs.getString("message"));
		m.setSentDate(rs.getDate("sent_date"));
		m.setRead(BooleanHelper.fromDatabaseString(rs.getString("read")));
		
		return m;
	}

}
