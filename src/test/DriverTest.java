package test;

import static org.junit.Assert.*;

import org.junit.Test;

import pcook01.Driver;
import pcook01.Location;

public class DriverTest {

	@Test
	public void testSetAvailable() {
		Driver d = new Driver("Test", "Test", 0, new Location(0,0));
		assertEquals(d.isAvailable(), true);
		d.setAvailable(false);
		assertEquals(d.isAvailable(), false);
	}

}
