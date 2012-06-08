package universe.data;

public interface Entity extends DataObject, Location {
		
	Location getLocation();
	
	void addMessage(Message message);
	
	void run();
}
