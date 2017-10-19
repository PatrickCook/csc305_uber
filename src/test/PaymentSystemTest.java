package test;

import static org.junit.Assert.*;

import org.junit.Test;

import pcook01.Driver;
import pcook01.Location;
import pcook01.Passenger;
import pcook01.PaymentSystem;
import pcook01.Transaction;
import pcook01.User;

public class PaymentSystemTest {

	@Test
	public void test() {
		User t1 = new Driver("Test", "Test", 0, new Location(0,0));
		User t2 = new Passenger("Test", "Test", 10, new Location(0,0));
		Transaction t = new Transaction(t2, t1, 5);
		
		PaymentSystem.processTransaction(t);
		assertEquals(t1.getBalance(), PaymentSystem.DRIVER_PERCENTAGE * 5, .001);
		assertEquals(t2.getBalance(), 5, .001);
	}

}
