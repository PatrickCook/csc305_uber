package test;

import static org.junit.Assert.*;

import java.util.Iterator;
import java.util.PriorityQueue;

import org.junit.Test;

import pcook01.Driver;
import pcook01.Location;
import pcook01.Passenger;
import pcook01.Ride;
import pcook01.Uber;

public class UberTest {

	@Test
	public void testGetClosestDriversNoTie() {
		Passenger p = new Passenger("Patrick", "Cook", 100, new Location(1,1));
		Driver t1 = new Driver("T1", "Test", 0, new Location(0,0));
		Driver t2 = new Driver("T2", "Test", 0, new Location(5,5));
		Driver t3 = new Driver("T3", "Test", 0, new Location(10,10));
		
		Uber.addUberDriver(t1);
		Uber.addUberDriver(t2);
		Uber.addUberDriver(t3);

		PriorityQueue<Ride> pqueue = Uber.getClosestDrivers(p, new Location(20,20));

		assertEquals(pqueue.poll().getDriver().toString(), "T1 Test");
		assertEquals(pqueue.poll().getDriver().toString(), "T2 Test");
		assertEquals(pqueue.poll().getDriver().toString(), "T3 Test");
		
		Uber.removeUberDriver(t1);
		Uber.removeUberDriver(t2);
		Uber.removeUberDriver(t3);
	}
	
	@Test
	public void testGetClosestDriversWithTie() {
		Passenger p = new Passenger("Patrick", "Cook", 100, new Location(1,1));
		Driver t1 = new Driver("T1", "Test", 0, new Location(0,0));
		Driver t2 = new Driver("T2", "Test", 0, new Location(0,0));
		Driver t3 = new Driver("T3", "Test", 0, new Location(10,10));
		t1.addRating(3);
		t2.addRating(5);
		
		Uber.addUberDriver(t1);
		Uber.addUberDriver(t2);
		Uber.addUberDriver(t3);
		
		PriorityQueue<Ride> pqueue = Uber.getClosestDrivers(p, new Location(20,20));

		assertEquals(pqueue.poll().getDriver().toString(), "T2 Test");
		assertEquals(pqueue.poll().getDriver().toString(), "T1 Test");
		assertEquals(pqueue.poll().getDriver().toString(), "T3 Test");
		
		Uber.removeUberDriver(t1);
		Uber.removeUberDriver(t2);
		Uber.removeUberDriver(t3);
	}
	
	@Test
	public void testGetClosestDriversWithNoDrivers() {
		Passenger p = new Passenger("Patrick", "Cook", 100, new Location(1,1));
		
		PriorityQueue<Ride> pqueue = Uber.getClosestDrivers(p, new Location(20,20));

		assertEquals(pqueue.size(), 0);

	}

}
