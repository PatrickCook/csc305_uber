package test;

import static org.junit.Assert.*;

import org.junit.Test;

import pcook01.Driver;
import pcook01.Location;
import pcook01.Passenger;
import pcook01.Ride;
import pcook01.RideManager;

public class RideManagerTest {

	@Test
	public void testUpdatedLocations() {
		Driver t1 = new Driver("Test", "Test", 0, new Location(0,0));
		Passenger t2 = new Passenger("Test", "Test", 100, new Location(10,10));
		Ride r = new Ride(t1, t2, new Location(15,15));
		RideManager man = new RideManager(r);
		
		assertEquals(t1.getLocation().equals(new Location(0,0)), true);
		assertEquals(t2.getLocation().equals(new Location(10,10)), true);
		
		man.startRide();
		
		assertEquals(t1.getLocation().equals(new Location(15,15)), true);
		assertEquals(t2.getLocation().equals(new Location(15,15)), true);		
	}
	
	@Test
	public void testUpdatedBalance() {
		Driver t1 = new Driver("Test", "Test", 0, new Location(0,0));
		Passenger t2 = new Passenger("Test", "Test", 100, new Location(10,10));
		Ride r = new Ride(t1, t2, new Location(15,15));
		RideManager man = new RideManager(r);
		double initial = t2.getBalance();
		
		man.startRide();
		
		assertEquals(t2.getBalance(), initial - r.getCost(), .001);
		
	}

}
