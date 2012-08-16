package universe.helper;

public class Compare {

	private Compare(){}

	public static boolean areSame(Object o1, Object o2) {
		if (o1 == null) return false;
		if (o2 == null) return false;
		return o1.equals(o2);
	}
	
	public static boolean isEmpty(String s) {
		if (s == null) return true;
		if ("".equals(s.trim())) return true;
		return false;
	}
}
