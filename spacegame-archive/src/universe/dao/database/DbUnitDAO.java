package universe.dao.database;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

import universe.ControllerContext;
import universe.dao.UnitDAO;
import universe.data.Faction;
import universe.data.Location;
import universe.data.Planet;
import universe.data.Unit;
import universe.data.cargo.MineralType;
import universe.data.unit.CargoHold;
import universe.data.unit.UnitType;
import universe.data.unit.types.UnitTypeFactory;
import universe.helper.DatabaseHelper;

public class DbUnitDAO implements UnitDAO {

	ConnectionFactory cf;
	ControllerContext context;
	
	public DbUnitDAO(ConnectionFactory cf, ControllerContext context) {
		this.cf = cf;
		this.context = context;
	}	
	
	private Unit popluateUnit(ResultSet rs, Location l) throws SQLException {
		String unitType = rs.getString("unit_type");

		Faction faction = context.getFactionDAO().getFactionByID(rs.getInt("faction_id"));
		
		
		Unit u = UnitTypeFactory.getUnitFromTypeName(unitType, faction, l, rs.getInt("unit_id"));

		// now get the values from the database
		u.setOwner(faction);
		u.setLocation(l);
//		
//		if (u instanceof CargoHold) {
//			CargoHold c = (CargoHold) u;
//			c.addCargoOfType(MineralType.GAS, rs.getInt("gas"));
//			c.addCargoOfType(MineralType.WATER, rs.getInt("water"));
//			c.addCargoOfType(MineralType.ORE, rs.getInt("minerals"));
//		}
		
		// details of the action that the unit is performing
		u.setCurrentAction(rs.getInt("current_action"));
		u.setCurrentTarget(rs.getInt("current_target"));
		u.setActionETA(rs.getTimestamp("action_eta"));
		
		// a list of statuses are defined in the unit class
		u.setStatus(rs.getInt("status"));
		
		return u;
	}
	
	public List<Unit> getAllUnits() {
		return new LinkedList<Unit>();
	}

	public List<Unit> getAllUnitsForPlanetByFaction(Faction f, Planet p) {

		String sql = "select * from unit " +
			" where parent = ? " +
			" and faction_id = ? " +
			" and faction_id in ( " +
			"		select faction_id from unit " +
			"		where parent = ? " +
			" ) ";
		
		Connection conn = null;
		List<Unit> units = new ArrayList<Unit>();
		try {
			conn = cf.getConnection();
			
			PreparedStatement st = conn.prepareStatement(sql);
			st.setLong(1, p.getPlanetId());
			st.setLong(2, f.getFactionId());
			st.setLong(3, p.getPlanetId());
			
			ResultSet rs = st.executeQuery();
			
			// is id unique so will never get two rows
			while (rs.next()) {
				units.add(popluateUnit(rs, p));
			}
			return units;
			
		} catch (SQLException e) {
			e.printStackTrace();
			throw new IllegalStateException(e.getCause());
		} finally {
			cf.returnConnection(conn);
		}	
	}

	public Unit getUnitById(long id) {
		String sql = "select * from unit where unit_id = ?";

		Connection conn = null;
		Unit unit = null;
		try {
			conn = cf.getConnection();
			
			PreparedStatement st = conn.prepareStatement(sql);
			st.setLong(1, id);
			
			ResultSet rs = st.executeQuery();
			
			// is id unique so will never get two rows
			if (rs.next()) {
				Planet p = context.getPlanetDAO().getPlanetById(rs.getLong("parent"));
				unit = popluateUnit(rs, p);
			}
			
		} catch (SQLException e) {
			e.printStackTrace();
			throw new IllegalStateException(e.getCause());
		} finally {
			cf.returnConnection(conn);
		}	
		return unit;
	}

	public void saveUnit(Unit theUnitToSave) {
		String sql = "update unit set faction_id = ?, xloc = ?, yloc = ?, parent = ?, " +
				"current_action = ?, current_target = ?, ";
//		if (theUnitToSave instanceof CargoHold) {
//			sql  +="minerals = ?, gas = ?, water = ?, ";
//		}
		sql +=	"action_eta = ?, status = ? " +
				" where unit_id = ?";
		
		Connection conn = null;

		try {
			conn = cf.getConnection();
			
			int i = 1;
			PreparedStatement st = conn.prepareStatement(sql);
			st.setLong(i++, theUnitToSave.getOwner().getFactionId());
			st.setInt(i++, (int) theUnitToSave.getX());
			st.setInt(i++, (int) theUnitToSave.getY());
			st.setLong(i++, theUnitToSave.getParent().getLocationId());
			st.setInt(i++, theUnitToSave.getCurrentAction());
			st.setLong(i++, theUnitToSave.getCurrentTarget());
//			if (theUnitToSave instanceof CargoHold) {
//				CargoHold hold = (CargoHold) theUnitToSave;
//				
//				// save the minerals
//				st.setInt(i++, (int) hold.getRemainingCargoOfType(MineralType.ORE));
//				st.setInt(i++, (int) hold.getRemainingCargoOfType(MineralType.GAS));
//				st.setInt(i++, (int) hold.getRemainingCargoOfType(MineralType.WATER));
//			}
			st.setTimestamp(i++, DatabaseHelper.toTimeStamp(theUnitToSave.getActionETA()));
			st.setInt(i++, theUnitToSave.getStatus());
			st.setLong(i++, theUnitToSave.getUnitId());
			
			int result = st.executeUpdate();
			if (result != 1) {
				throw new IllegalStateException("trying to update a unit that doesn't exist? id = " + theUnitToSave.getUnitId());
			}
			
		} catch (SQLException e) {
			e.printStackTrace();
			throw new IllegalStateException(e.getCause());
		} finally {
			cf.returnConnection(conn);
		}
	}

	public List<Unit> getAllUnitsWithExpiredActions() {
		String sql = "select * from unit " +
				" where action_eta is not null " +
				" and action_eta < CURRENT_TIMESTAMP " +
				" order by action_eta desc";
		
		Connection conn = null;
		List<Unit> units = new ArrayList<Unit>();
		try {
			conn = cf.getConnection();
			
			PreparedStatement st = conn.prepareStatement(sql);
			
			ResultSet rs = st.executeQuery();
			
			// is id unique so will never get two rows
			while (rs.next()) {
				Planet p = context.getPlanetDAO().getPlanetById(rs.getInt("parent"));
				units.add(popluateUnit(rs, p));
			}
			return units;
			
		} catch (SQLException e) {
			e.printStackTrace();
			throw new IllegalStateException(e.getCause());
		} finally {
			cf.returnConnection(conn);
		}	
	}

	public void insertUnit(Unit theUnitToSave) {
		// insert stuff here
		String sql = "insert into unit (unit_id, unit_type, faction_id, xloc, yloc, parent, current_action, current_target, ";
		if (theUnitToSave instanceof CargoHold) {
			sql  += "minerals, gas, water, ";
		}
		sql +=	"action_eta, status) " +
				" values (?,?,?,?,?,?,?,?,";
		if (theUnitToSave instanceof CargoHold) {
			sql  += "?,?,?,";
		}		
		sql += "?,?)";
		
		Connection conn = null;
		
		try {
			conn = cf.getConnection();
			
			int i = 1;
			PreparedStatement st = conn.prepareStatement(sql);
			st.setLong(i++, theUnitToSave.getUnitId());
			st.setString(i++, theUnitToSave.getUnitType().getTypeId());
			st.setLong(i++, theUnitToSave.getOwner().getFactionId());
			st.setInt(i++, (int) theUnitToSave.getX());
			st.setInt(i++, (int) theUnitToSave.getY());
			st.setInt(i++, (int) theUnitToSave.getParent().getLocationId());
			st.setInt(i++, theUnitToSave.getCurrentAction());
			st.setLong(i++, theUnitToSave.getCurrentTarget());
			if (theUnitToSave instanceof CargoHold) {
				CargoHold hold = (CargoHold) theUnitToSave;
				
				// save the minerals
				st.setInt(i++, (int) hold.getRemainingCargoOfType(MineralType.ORE));
				st.setInt(i++, (int) hold.getRemainingCargoOfType(MineralType.GAS));
				st.setInt(i++, (int) hold.getRemainingCargoOfType(MineralType.WATER));
			}
			st.setTimestamp(i++, DatabaseHelper.toTimeStamp(theUnitToSave.getActionETA()));
			st.setInt(i++, theUnitToSave.getStatus());
			
			int result = st.executeUpdate();
			if (result != 1) {
				throw new IllegalStateException("can't insert unit with id = " + theUnitToSave.getUnitId());
			}
			
		} catch (SQLException e) {
			e.printStackTrace();
			throw new IllegalStateException(e.getCause());
		} finally {
			cf.returnConnection(conn);
		}		
	}

	public List<Unit> getAllUnitsForPlanet(Planet p) {

		String sql = "select * from unit " +
			" where parent = ? ";
		
		Connection conn = null;
		List<Unit> units = new ArrayList<Unit>();
		try {
			conn = cf.getConnection();
			
			PreparedStatement st = conn.prepareStatement(sql);
			st.setLong(1, p.getPlanetId());
			
			ResultSet rs = st.executeQuery();
			
			// is id unique so will never get two rows
			while (rs.next()) {
				units.add(popluateUnit(rs, p));
			}
			return units;
			
		} catch (SQLException e) {
			e.printStackTrace();
			throw new IllegalStateException(e.getCause());
		} finally {
			cf.returnConnection(conn);
		}	
	}

	public List<Unit> getAllUnitsOfType(UnitType type) {
		String sql = "select * from unit " +
		" where unit_type = ? ";
	
		Connection conn = null;
		List<Unit> units = new ArrayList<Unit>();
		try {
			conn = cf.getConnection();
			
			PreparedStatement st = conn.prepareStatement(sql);
			st.setString(1, type.getTypeId());
			
			ResultSet rs = st.executeQuery();
			
			// is id unique so will never get two rows
			while (rs.next()) {
				Planet p = context.getPlanetDAO().getPlanetById(rs.getLong("parent"));
				units.add(popluateUnit(rs, p));
			}
			return units;
			
		} catch (SQLException e) {
			e.printStackTrace();
			throw new IllegalStateException(e.getCause());
		} finally {
			cf.returnConnection(conn);
		}
	}
}
