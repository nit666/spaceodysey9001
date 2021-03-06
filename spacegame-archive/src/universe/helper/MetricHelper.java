package universe.helper;


public final class MetricHelper {

	public static double getKilosForTonne(double tonnes) {
		return tonnes * 1000;
	}
	
	public static double percentToDegrees(double percent) {
		return percent/100 * 360;
	}
	
	public static double degreesToPercent(double degrees) {
		return degrees/360 * 100;
	}
	
}
