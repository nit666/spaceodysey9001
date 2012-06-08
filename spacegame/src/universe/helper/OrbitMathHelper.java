package universe.helper;

import java.math.BigDecimal;
import java.util.Calendar;
import java.util.LinkedList;
import java.util.List;

import universe.data.Planet;


public class OrbitMathHelper {

	public static final double GRAVITATIONAL_CONSTANT = Math.pow(6.67259, -11);
	
	static BigDecimal earthGM = new BigDecimal(398600500000000.0);
	
	/* no longer used 
	public static double calculateOrbitalPeriod(BigDecimal GM, BigDecimal semiMajorAxis) {
		BigDecimal period = new BigDecimal(4.0 * Math.PI * Math.PI).multiply(semiMajorAxis.pow(3));
		return Math.sqrt(period.divide(GM).doubleValue());
	} */
	
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
	public static double getCurrentOrbitAngle(Planet p, long startTime) {
		Calendar c = Calendar.getInstance();
		long timeSinceStartTime = c.getTimeInMillis() - startTime;
		long effectivePeriod = timeSinceStartTime % (long) p.getPeriod();
		return effectivePeriod / p.getPeriod() * 100.0;
	}
	
	public static long calculateTimeToIntercept(Planet p1, Planet p2, long currentTime) {
		// figure out which is the closest to the parent
		//if (p1.getPeriod())
		
		// find out the angular speed for each planet
		
		// find out the current angle, and take them away to get the difference
		// if the closer planet has a larger number (eg: it is after the other planet already), then add 360 to the outer planet.	
		
		// t = D/(s1 - s2)
		
		// return current time + t
		
		return 0;
	}
	
	
	public static void main(String arg[]) {
		// a quick test
		List<Planet> planets = new LinkedList<Planet>();
		planets.add(createPlanet("p0", TimeHelper.SecondsToMilliSeconds(30)));
		planets.add(createPlanet("p1", TimeHelper.SecondsToMilliSeconds(60)));
		planets.add(createPlanet("p2", TimeHelper.SecondsToMilliSeconds(120)));
		planets.add(createPlanet("p3", TimeHelper.SecondsToMilliSeconds(240)));
		planets.add(createPlanet("p4", TimeHelper.SecondsToMilliSeconds(480)));
		planets.add(createPlanet("p5", TimeHelper.SecondsToMilliSeconds(920)));

		System.out.println("planets created");
		for (Planet p : planets) {
			System.out.println(p.getName() + ": " + p.getPeriod() + ", " + p.getRadiusFromParent());
		}
		
		long startTime = Calendar.getInstance().getTimeInMillis();
		
		int secondCount = 0;
		int minuteCount = 0;
		while (true) {
			if (secondCount >= 60) {
				secondCount = 0;
				minuteCount++;
			}
			secondCount++;
			try {
				Thread.sleep(1000);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
			
			System.out.println("Current location of planets at " + minuteCount + ":" + secondCount);
			
			for (Planet p : planets) {
				double angle = getCurrentOrbitAngle(p, startTime);
				System.out.println(p.getName() + ": " + MetricHelper.percentToDegrees(angle));				
			}
		}
	}
	
	private static Planet createPlanet(String name, double period) {
		Planet p = new Planet();
		p.setName(name);
		p.setPeriod(period);
		p.setRadiusFromParent(getOrbitDistanceFromParent(p.getPeriod()));
		return p;
	}
}
