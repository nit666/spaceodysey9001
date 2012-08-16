package universe.dao.database;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import universe.ControllerContext;
import universe.dao.PlanetDAO;
import universe.data.BaseLocation;
import universe.data.Faction;
import universe.data.Planet;
import universe.data.PlanetLink;
import universe.data.unit.MineralNode;

public class DbPlanetDAO implements PlanetDAO {

	ConnectionFactory cf;
	ControllerContext context;
	
	public DbPlanetDAO(ConnectionFactory cf, ControllerContext context) {
		this.cf = cf;
		this.context = context;
	}	
	
	private Planet populatePlanet(ResultSet rs) throws SQLException {
		Planet p = new Planet();
		Faction faction = context.getFactionDAO().getFactionByID(rs.getInt("faction_id"));
		p.setFaction(faction);
		p.setGas(rs.getInt("gas"));
		p.setWater(rs.getInt("water"));
		p.setMinerals(rs.getInt("minerals"));
		
		// the parent must be the base for the moment
		if (rs.getLong("parent") != BaseLocation.getInstance().getLocationId()) {
			throw new UnsupportedOperationException("Planets must have a the base as a parent for the moment");
		}
		p.setParent(BaseLocation.getInstance());
		p.setPlanetId(rs.getInt("planet_id"));
		p.setType(rs.getString("type"));
		p.setXloc(rs.getInt("xloc"));
		p.setYloc(rs.getInt("yloc"));
		p.setName(rs.getString("name"));
		return p;
	}
	
	public List<Planet> getAllPlanets() {
		
		String sql = "select * from planet";
		
		Connection conn = null;
		List<Planet> planets = new ArrayList<Planet>();
		try {
			conn = cf.getConnection();
			
			PreparedStatement st = conn.prepareStatement(sql);
			
			ResultSet rs = st.executeQuery();
			
			// is id unique so will never get two rows
			if (rs.next()) {
				planets.add(populatePlanet(rs));
			}
			return planets;
			
		} catch (SQLException e) {
			e.printStackTrace();
			throw new IllegalStateException(e.getCause());
		} finally {
			cf.returnConnection(conn);
		}
	}

	public List<Planet> getPlanetsForFaction(Faction f) {
		String sql = "select p.*" +
			" from planet p where planet_id <> 0"; // +
			//" join knows_about k on p.planet_id = k.obj_id " +
			//" where (k.obj_type = 'P' " +
			//" and k.faction_id = ?) ";
		
		Connection conn = null;
		List<Planet> planets = new ArrayList<Planet>();
		try {
			conn = cf.getConnection();
			
			PreparedStatement st = conn.prepareStatement(sql);
			//st.setLong(1, f.getFactionId());
			
			ResultSet rs = st.executeQuery();
			
			// is id unique so will never get two rows
			while (rs.next()) {
				planets.add(populatePlanet(rs));
			}
			return planets;
			
		} catch (SQLException e) {
			e.printStackTrace();
			throw new IllegalStateException(e.getCause());
		} finally {
			cf.returnConnection(conn);
		}	
	}

	public void saveMineralNode(MineralNode node) {
		// TODO Auto-generated method stub

	}

	public void savePlanet(Planet p) {
		// TODO Auto-generated method stub

	}

	public Planet getPlanetById(long planetId) {
		String sql = "select * from planet where planet_id = ?";
		
		Connection conn = null;
		try {
			conn = cf.getConnection();
			
			PreparedStatement st = conn.prepareStatement(sql);
			st.setLong(1, planetId);
			
			ResultSet rs = st.executeQuery();
			
			// is id unique so will never get two rows
			if (rs.next()) {
				return populatePlanet(rs);
			}
			return null;
			
		} catch (SQLException e) {
			e.printStackTrace();
			throw new IllegalStateException(e.getCause());
		} finally {
			cf.returnConnection(conn);
		}	
	}

	public List<PlanetLink> getLinkedPlanets(Planet p) {

		String sql = "select *" +
		" from planet_link " +
		" where source = ? ";
	
		Connection conn = null;
		List<PlanetLink> links = new ArrayList<PlanetLink>();
		try {
			conn = cf.getConnection();
			
			PreparedStatement st = conn.prepareStatement(sql);
			st.setLong(1, p.getPlanetId());
			
			ResultSet rs = st.executeQuery();
			
			// is id unique so will never get two rows
			while (rs.next()) {
				Planet source = context.getPlanetDAO().getPlanetById(rs.getLong("source"));
				Planet dest = context.getPlanetDAO().getPlanetById(rs.getLong("destination"));
				PlanetLink link = new PlanetLink(rs.getLong("link_id"), source, dest, rs.getInt("travel_time"), rs.getInt("travel_cost"));
				links.add(link);
			}
			return links;
			
		} catch (SQLException e) {
			e.printStackTrace();
			throw new IllegalStateException(e.getCause());
		} finally {
			cf.returnConnection(conn);
		}	
	}

	public PlanetLink getLinkedPlanetByLinkId(long linkId) {
		String sql = "select * from planet_link where link_id = ?";
		
		Connection conn = null;
		try {
			conn = cf.getConnection();
			
			PreparedStatement st = conn.prepareStatement(sql);
			st.setLong(1, linkId);
			
			ResultSet rs = st.executeQuery();
			
			// is id unique so will never get two rows
			if (rs.next()) {
				Planet source = context.getPlanetDAO().getPlanetById(rs.getLong("source"));
				Planet dest = context.getPlanetDAO().getPlanetById(rs.getLong("destination"));
				return new PlanetLink(rs.getLong("link_id"), source, dest, rs.getInt("travel_time"), rs.getInt("travel_cost"));
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
