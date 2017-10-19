package test;

import static org.junit.Assert.*;

import org.junit.Test;

import pcook01.Driver;
import pcook01.Location;
import pcook01.Passenger;
import pcook01.PaymentSystem;
import pcook01.Ride;
import pcook01.User;

public class RideTest {

	@Test
	public void testGetCost() {
		Driver t1 = new Driver("Test", "Test", 0, new Location(0,0));
		Passenger t2 = new Passenger("Test", "Test", 10, new Location(0,0));
		Ride r = new Ride(t1, t2, new Location(15,15));
		assertEquals(r.getCost(), 
				PaymentSystem.RIDE_RATE * r.getTotalDistance(), .001);
	}
	
	@Test
	public void testGetDriverAndTotalDistance() {
		Driver t1 = new Driver("Test", "Test", 0, new Location(0,0));
		Passenger t2 = new Passenger("Test", "Test", 10, new Location(10,15));
		Ride r = new Ride(t1, t2, new Location(15,15));
		
		assertEquals(r.getDriverDistance(), 18.02, .01);
		assertEquals(r.getTotalDistance(), 23.03, .01);
		
	}
	
	@Test
	public void testGetEstimatedTimes() {
		Driver t1 = new Driver("Test", "Test", 0, new Location(0,0));
		Passenger t2 = new Passenger("Test", "Test", 10, new Location(10,15));
		Ride r = new Ride(t1, t2, new Location(15,15));
		
		assertEquals(r.getEstimatedWaitTime(), 450, .01);
		assertEquals(r.getEstimatedRideTime(), 125, .01);
	}

}
