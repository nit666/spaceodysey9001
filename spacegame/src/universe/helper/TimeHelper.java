package universe.helper;

public final class TimeHelper {

	private TimeHelper() {
		
	}
	
	public static final double ONE_SECOND = 1000.0;
	
	public static long SecondsToMilliSeconds(long seconds) {
		return seconds * 1000;
	}
	
	public static long MilliSecondsToSeconds(long millis) {
		return millis / 1000;
	}
	
}
