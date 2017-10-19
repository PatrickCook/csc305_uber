package test;

import static org.junit.Assert.*;

import org.junit.Test;

import pcook01.Location;

public class LocationTest {

	@Test
	public void testLocationEquals() {
		Location a = new Location(0,0);
		Location b = new Location(0,1);
		Location c = new Location(0,0);
		assertEquals(a.equals(b), false);
		assertEquals(a.equals(c), true);
	}
	@Test
	public void testSetXAndYCord() {
		Location a = new Location(0,0);
		a.setxCord(4);
		a.setyCord(5);
		assertEquals(a.getxCord(), 4);
		assertEquals(a.getyCord(), 5);
	}
	@Test
	public void testToString() {
		Location a = new Location(5, 15);
		assertEquals(a.toString(), "(5, 15)");		
	}
 
}
