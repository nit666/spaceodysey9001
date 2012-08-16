package universe.helper;

public class BooleanHelper {

	private BooleanHelper(){}
	
	public static boolean fromDatabaseString(String s) {
		if ("T".equalsIgnoreCase(s)) {
			return true;
		} else {
			return false;
		}
	}
}
