package universe.helper;

import java.math.BigDecimal;
import java.util.Calendar;

import universe.data.Location;
import universe.data.Orbitable;
import universe.data.Planet;

public class OrbitMathHelper {

	public static final double GRAVITATIONAL_CONSTANT = Math.pow(6.67259, -11);
	
	static BigDecimal earthGM = new BigDecimal(398600500000000.0);
	
	/**
	 * Calculates the distance from the parent body, based off the period of rotation.
	 */
	public static double getOrbitDistanceFromParent(double period) {
		double sp = Math.pow(period, 2.0);
		return Math.pow(sp, 1.0/3.0); 
	}
	
	/**
	 * Calulates the current angle from zero at the current time. 
	 * @param p The planet in question
	 * @param startTime The time that the universe was started from
	 * @return The angle in percentage
	 */
	public static double getCurrentOrbitAngle(double p, long startTime) {
		Calendar c = Calendar.getInstance();
		return getCurrentOrbitAngle(p, startTime, c.getTimeInMillis());
	}
	
	public static double getCurrentOrbitAngle(double p, long startTime, long currentTime) {
		long timeSinceStartTime = currentTime - startTime;
		long effectivePeriod = timeSinceStartTime % (long) p;
		return effectivePeriod / p * 100.0;
	}
	
	/**
	 * helper method that does the same thing as calculateFakeTravelDistance but only needs period values
	 * 
	 * @param sourcePeriod
	 * @param destPeriod
	 * @return
	 */
	public static double calculateFakeTravelDistance(double sourcePeriod, double destPeriod) {
		return calculateFakeTravelDistance(
				getCurrentOrbitAngle(sourcePeriod,TimeHelper.getGameStartTime()), 
				getCurrentOrbitAngle(destPeriod, TimeHelper.getGameStartTime()),
				getOrbitDistanceFromParent(sourcePeriod),
				getOrbitDistanceFromParent(destPeriod));
	}
	
	/**
	 * This function will tell a workable travel distance between two orbiting objects (like a moon and wormhole)
	 * It works out an average arc distance between the two objects. 
	 * 
	 * @param sourceAngle this is in degrees
	 * @param destAngle this is in degrees
	 * @param sourceDist the distance from the planet (orbit radius)
	 * @param destDistance the distance from the planet (orbit radius)
	 * @return the travel distance to the destination
	 */
	public static double calculateFakeTravelDistance(double sourceAngle, double destAngle, double sourceDist, double destDist) {
		
		// the travel distance is the angular distance between two orbiting objects and the difference in distance from the planet
		double distanceDiff = sourceDist;
		if (sourceDist > destDist) {
			distanceDiff = sourceDist - destDist;
		} else if (destDist > sourceDist) {
			distanceDiff = destDist - sourceDist;
		}
		
		double angleDiff = Math.abs(sourceAngle - destAngle);
		if (angleDiff > 180) {
			angleDiff = 360 - angleDiff;
		}
		
		// figure out how far the ship needs to travel in an arc
		// formula for this is: length = diameter X PI X arc/360
		// use the largest diameter - the distance difference
		return distanceDiff * 2 * Math.PI * angleDiff/360;
	}
	
	/**
	 * Calculates about how long it will take to travel a certain distance in time and then returns
	 * the arrival time. 
	 * 
	 * @param startTime the time that the ship departs the current location
	 * @param distance how long to travel
	 * @param speed the speed that the ship will be travelling at
	 * @return the arrival time
	 */
	public static long calculateArrivalTime(long startTime, double distance, double speed) {
		double travelTime = distance / speed;
		return startTime + (long) travelTime;
	}
	
	/**
	 * Determines if two objects are in the same system or not.
	 */
	public static boolean inSameSystem(Location l1, Location l2) {
		String system1 = "";
		String system2 = "";
		if (l1 instanceof Planet) {
			system1 = l1.getId();
		} else {
			system1 = ((Orbitable) l1).getParent().getId();
		}
		if (l2 instanceof Planet) {
			system2 = l2.getId();
		} else {
			system2 = ((Orbitable) l2).getParent().getId();
		}
	
		return system1.equals(system2);
	}
}
