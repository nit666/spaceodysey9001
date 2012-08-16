package universe.dao.database;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import universe.dao.FactionDAO;
import universe.data.Faction;

public class DbFactionDAO implements FactionDAO {

	ConnectionFactory cf;
	
	public DbFactionDAO(ConnectionFactory cf) {
		this.cf = cf;
	}

	private Faction populateFaction(ResultSet rs) throws SQLException {
		Faction f = new Faction();
		f.setFactionId(rs.getLong("faction_id"));
		f.setName(rs.getString("name"));
		f.setPassword(rs.getString("password"));
		f.setEmail(rs.getString("email"));
		f.setWater(rs.getInt("water"));
		f.setMinerals(rs.getInt("minerals"));
		f.setGas(rs.getInt("gas"));
						
		return f;
	}
	
	public Faction getFactionByID(long factionId) {
		String sql = "select * from faction where faction_id = ?";
		
		Connection conn = null;
		try {
			conn = cf.getConnection();
			
			PreparedStatement st = conn.prepareStatement(sql);
			st.setLong(1, factionId);
			
			ResultSet rs = st.executeQuery();
			
			// is id unique so will never get two rows
			if (rs.next()) {
				return populateFaction(rs);
			}
			return null;
			
		} catch (SQLException e) {
			e.printStackTrace();
			throw new IllegalStateException(e.getCause());
		} finally {
			cf.returnConnection(conn);
		}
	}

	public Faction getFactionByName(String name) {
		
		String sql = "select * from faction where name = ?";
		
		Connection conn = null;
		try {
			conn = cf.getConnection();
			
			PreparedStatement st = conn.prepareStatement(sql);
			st.setString(1, name);
			
			ResultSet rs = st.executeQuery();
			
			// is id unique so will never get two rows
			if (rs.next()) {
				System.out.println("Found faction with name of " + name);
				return populateFaction(rs);
			}
			System.out.println("No faction with name of " + name + " returning nothing");
			return null;
			
		} catch (SQLException e) {
			e.printStackTrace();
			throw new IllegalStateException(e.getCause());
		} finally {
			cf.returnConnection(conn);
		}
	}

	public List<Faction> getKnownFactions(Faction f) {
		String sql = "select f.* " +
			"from faction f where faction_id <> ? ";// +
			//"join knows_about k on f.faction_id = k.obj_id " +
			//"where k.obj_type = 'F' " +
			//"and k.faction_id = ? ";

		Connection conn = null;
		List<Faction> factions = new ArrayList<Faction>();
		try {
			conn = cf.getConnection();
			
			PreparedStatement st = conn.prepareStatement(sql);
			st.setLong(1, f.getFactionId());
			
			ResultSet rs = st.executeQuery();
			
			// is id unique so will never get two rows
			if (rs.next()) {
				factions.add(populateFaction(rs));
			}
			return factions;
			
		} catch (SQLException e) {
			e.printStackTrace();
			throw new IllegalStateException(e.getCause());
		} finally {
			cf.returnConnection(conn);
		}
	}

	public void saveFaction(Faction theFactionToSave) {
		String sql = "update faction set minerals = ?, gas = ?, water = ? where faction_id = ?";
		
		Connection conn = null;

		try {
			conn = cf.getConnection();
			
			int i = 1;
			PreparedStatement st = conn.prepareStatement(sql);
			st.setInt(i++, theFactionToSave.getMinerals());
			st.setInt(i++, theFactionToSave.getGas());
			st.setInt(i++, theFactionToSave.getWater());
			st.setLong(i++, theFactionToSave.getFactionId());
			
			int result = st.executeUpdate();
			if (result != 1) {
				throw new IllegalStateException("trying to update a faction that doesn't exist? id = " + theFactionToSave.getFactionId());
			}
			
		} catch (SQLException e) {
			e.printStackTrace();
			throw new IllegalStateException(e.getCause());
		} finally {
			cf.returnConnection(conn);
		}
	}
}
