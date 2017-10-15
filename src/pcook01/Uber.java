package pcook01;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.Iterator;
import java.util.PriorityQueue;

public final class Uber {
	private static ArrayList<Driver> drivers = new ArrayList<>();
	private static ArrayList<Ride> rides = new ArrayList<>();

	public static void requestRide(Passenger passenger, Location dropoff) {
		Ride newRide;
		Driver driver;
		Transaction transaction;
		PriorityQueue<Driver> available;

		/* Check if dropoff location is supported by map */
		if (!UberMap.containsLocation(dropoff)) {
			System.out.println("RideManager: Location is not supported by Uber");
			return;
		}
		/* Make sure at least one driver was selected */
		if ((available = getClosestDrivers(passenger)).size() == 0) {
			System.out.println("RideManager: Failed to request ride. "
					+ "No drivers available");
			return;
		}
		
		/* 
		 * Wait for user and driver to confirm ride. 
		 * Step through queue until driver is chosen
		 */
		while (!available.isEmpty()) {
			driver = available.peek();
			newRide = new Ride(driver, passenger, dropoff);
			
			if (rideConfirmation(newRide, driver, passenger)) {
				transaction = new Transaction(passenger, driver, newRide.getCost());
				PaymentSystem.processTransaction(transaction);
				/* Ride has begun */
				System.out.println("RideManager: Pickup location has been confirmed");
				/* Threading for each setup ride */
				rides.add(newRide);
				(new Thread(new RideManager(newRide))).start();
				return;
			} else {
				/* Remove driver if he declines ride */
				available.remove(driver);
			}
		}
		
		System.out.println("RideManager: User/driver failed to confirm ride. "
				+ "Ride request canceled.");
	}
	
	public static boolean rideConfirmation(Ride newRide, Driver driver, Passenger passenger) {
		if (driver.confirmRide(newRide)) {
			if (passenger.confirmRide(newRide)) {
				System.out.println("The user and driver have confirmed");
				
				if (passenger.getBalance() > newRide.getCost()) {
					return true;
				} else {
					System.out.println("RideManager: Insufficient funds to request ride.");
				}
			}
		}
		return false;
	}
	
	public static PriorityQueue<Driver> getClosestDrivers(Passenger passenger) {
		double minDist = Double.MAX_VALUE;
		Comparator<Driver> comparator = new Comparator<Driver>() {
	        @Override
	        public int compare(Driver a, Driver b) {
	            if (a.getRating() < b.getRating())
	                return -1;
	            else if (a.getRating() > b.getRating())
	                return 1;
	            return 0;
	        }
	    };
		
		PriorityQueue<Driver> pqueue = new PriorityQueue<Driver>(comparator);
		
		for (Iterator i = drivers.iterator(); i.hasNext(); ) {
			Driver driver = (Driver)i.next();
			if (driver.isAvailable()) {
				double dist = UberMap.getDistance(driver.getLocation(), 
						passenger.getLocation());
				
				if (dist < minDist) {
	    		  minDist = dist;
				}
			}
		}
		
		for (Iterator i = drivers.iterator(); i.hasNext(); ) {
			Driver driver = (Driver)i.next();
			if (driver.isAvailable()) {
				double dist = UberMap.getDistance(driver.getLocation(), 
						passenger.getLocation());
				
				if (dist == minDist) {
					pqueue.add(driver);
				}
			}
		}
		
		return pqueue;
	}
	
	public static void addUberDriver(Driver driver) {
		drivers.add(driver);
	}
}
