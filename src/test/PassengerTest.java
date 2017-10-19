package test;

import static org.junit.Assert.*;

import java.io.ByteArrayInputStream;

import org.junit.Test;

import pcook01.Location;
import pcook01.Passenger;

public class PassengerTest {

	@Test
	public void testUserConfirmRide() {
		Passenger p = new Passenger("Patrick", "Cook", 100, new Location(0,0));
		ByteArrayInputStream in = new ByteArrayInputStream("y".getBytes());
		System.setIn(in);
		assertEquals(p.userConfirmRide(), true);
		
		in = new ByteArrayInputStream("n".getBytes());
		System.setIn(in);
		assertEquals(p.userConfirmRide(), false);
		
	}
}
