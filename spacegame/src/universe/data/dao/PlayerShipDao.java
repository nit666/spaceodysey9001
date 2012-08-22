package universe.data.dao;

import java.util.LinkedList;
import java.util.List;

import universe.data.Player;
import universe.data.PlayerShip;
import universe.data.ShipComponent;

public class PlayerShipDao {

	public PlayerShipDao() {
		 
	}	
	
	/**
	 * Get the default player ship, which is located at the default starting location and kitted out
	 * with the default starting components
	 * @param player
	 * @return the player ship that the player starts with.
	 */
	public PlayerShip getStartingPlayerShip(Player player) {
		PlayerShip ship = new PlayerShip();
		ship.setPlayer(player);
		
		ShipComponentDao scd = new ShipComponentDao();
		List<ShipComponent> components = scd.getStartingComponents();
		ship.setComponents(components);
		
		LocationDao ld = LocationDao.getInstance();
		ship.setCurrentLocation(ld.getStartingLocation());
		
		return ship;
	}
	
}
