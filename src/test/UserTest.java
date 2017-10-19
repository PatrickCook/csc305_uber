package test;

import static org.junit.Assert.*;

import org.junit.Test;

import pcook01.Driver;
import pcook01.Location;
import pcook01.User;

public class UserTest {

	@Test
	public void testAddRating() {
		User u1 = new Driver("Patrick", "Cook", 0, new Location(0,0));
		u1.addRating(5);
		u1.addRating(0);
		
		assertEquals(u1.getRating(), 5.0, .001);
		u1.addRating(1);
		
		assertEquals(u1.getRating(), 3.0, .001);
	}
	
	@Test
	public void testAddNegativeRating() {
		User u1 = new Driver("Patrick", "Cook", 0, new Location(0,0));
		u1.addRating(5);
		u1.addRating(-5);
		
		assertEquals(u1.getRating(), 5.0, .001);
		u1.addRating(1);
		
		assertEquals(u1.getRating(), 3.0, .001);
	}
	
	@Test
	public void testAddBalance() {
		User u1 = new Driver("Patrick", "Cook", 0, new Location(0,0));
		u1.addBalance(-5);

		
		assertEquals(u1.getBalance(), 0, .001);
		u1.addBalance(1);
		
		assertEquals(u1.getBalance(), 1.0, .001);
	}
	
	@Test
	public void testAddNegativeBalance() {
		User u1 = new Driver("Patrick", "Cook", 0, new Location(0,0));
		u1.addRating(5);
		u1.addBalance(-5);
		
		assertEquals(u1.getRating(), 5.0, .001);
	}

}
