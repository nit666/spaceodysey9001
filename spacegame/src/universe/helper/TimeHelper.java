package universe.helper;

import java.util.Calendar;

public final class TimeHelper {

	private TimeHelper() {
		
	}
	
	public static final double ONE_SECOND = 1000.0;
	
	public static long getGameStartTime() {
		return 100000;
	}
	
	public static long getCurrentTime() {
		return Calendar.getInstance().getTimeInMillis();
	}
	
	public static long SecondsToMilliSeconds(long seconds) {
		return seconds * 1000;
	}
	
	public static long MilliSecondsToSeconds(long millis) {
		return millis / 1000;
	}
	
	public static long HoursToMilliSeconds(float hours) {
		long seconds = (long)(hours * 60 * 60); // get seconds
		return SecondsToMilliSeconds(seconds);
	}
	
}
