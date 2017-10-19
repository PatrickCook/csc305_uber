package test;

import static org.junit.Assert.*;

import org.junit.Test;

import pcook01.Location;
import pcook01.UberMap;

public class UberMapTest {

	@Test
	public void testLocationDistance() {
		Location l1 = new Location(0,0);
		Location l2 = new Location(6,8);
		Location l3 = new Location(5,0);
		
		assertEquals(UberMap.getDistance(l1, l2), 10, .001);
		assertEquals(UberMap.getDistance(l1, l3), 5, .001);

	}
	
	@Test
	public void testNegativeLocationDistance() {
		Location l1 = new Location(0,0);
		Location l4 = new Location(-6,8);

		assertEquals(UberMap.getDistance(l1, l4), -1, .001);
	}
	
	@Test
	public void testContainsGoodLocation() {
		Location l1 = new Location(0,0);
		
		assertEquals(UberMap.containsLocation(l1), true);
		
	}
	
	@Test
	public void testContainsBadLocation() {
		Location l1 = new Location(-6,8);
		
		assertEquals(UberMap.containsLocation(l1), false);
	}
	
	@Test
	public void testContainsBoundsLocation() {
		Location l1 = new Location(UberMap.MAPSIZE_X,UberMap.MAPSIZE_Y);
		
		assertEquals(UberMap.containsLocation(l1), false);
	}

}
