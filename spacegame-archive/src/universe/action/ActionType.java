package universe.action;

public enum ActionType {
	BEING_BUILT("Under Construction", 1),
	WAITING("Waiting For Instructions", 0),
	BUILDING("Building...", 2),
	MOVING("In Transit", 3);
	
	private String name;
	private int value;
	
	private ActionType(String name, int value) {
		this.name = name;
		this.value = value;
	}
	
	public String getName() {
		return name;
	}
	
	public int getValue() {
		return value;
	}
}
