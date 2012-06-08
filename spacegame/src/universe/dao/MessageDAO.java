package universe.dao;

import universe.data.Faction;
import universe.data.Message;

public interface MessageDAO {

	void sendMessageToFaction(Faction faction, String messageName, Object... params);
	
	void sendMessageToFaction(Faction sender, Faction reciever, String message);
	
	Message getNextMessageForFaction(Faction faction);
}
