package universe.helper;

import junit.framework.TestCase;

public class OrbitMathHelperTest extends TestCase {

	public void testCalculateFakeTravelDistance() {
		
		// should just do half a circle at the same distance
		double dist = OrbitMathHelper.calculateFakeTravelDistance(0, 180, 20, 20);
		assertEquals(dist, 62.83185307179586);
		
		// take an average of the distances
		dist = OrbitMathHelper.calculateFakeTravelDistance(0, 180, 30, 10);
		assertEquals(dist, 62.83185307179586);
		
		// should take the smaller angle, not 270 but 90
		dist = OrbitMathHelper.calculateFakeTravelDistance(0, 270, 30, 10);
		assertEquals(dist, 31.41592653589793);
		dist = OrbitMathHelper.calculateFakeTravelDistance(270, 0, 30, 10);
		assertEquals(dist, 31.41592653589793);
		
		// should be 90 degrees again
		dist = OrbitMathHelper.calculateFakeTravelDistance(150, 240, 30, 10);
		assertEquals(dist, 31.41592653589793);
		dist = OrbitMathHelper.calculateFakeTravelDistance(240, 150, 30, 10);
		assertEquals(dist, 31.41592653589793);		
	}
	
}
