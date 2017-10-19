package pcook01;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.Iterator;
import java.util.PriorityQueue;

/**
 * Central class for Uber Application
 * Responsible for handling ride requests, finding drivers
 * and outputting Uber logs to a file
 * @author Patrick Cook
 *
 */
public class Uber {
	private static int completed = 0;
	private static int canceled = 0;
	private static ArrayList<Passenger> users = new ArrayList<>();
	private static ArrayList<Driver> drivers = new ArrayList<>();
	private static ArrayList<Ride> rides = new ArrayList<>();
	static final int TIME_PER_UNIT_DIST_MS = 25;	 /* ms per unit of distance */

	/**
	 * Allows a user to request a ride. Driver is found and the ride is started
	 * @param passenger - user requesting ride
	 * @param dropoff - dropoff location for ride
	 */
	public static void requestRide(Passenger passenger, Location dropoff) {
		Ride newRide;
		Driver driver;
		RideManager rideManager;
		PriorityQueue<Ride> available;
		
		System.out.println("----------- New Uber ride has been requested ---------");
		
		/* Check if dropoff location is supported by map */
		if (!UberMap.containsLocation(dropoff)) {
			System.out.println("RideManager: Location is not supported by Uber");
			return;
		}
		
		/* Make sure at least one driver was selected */
		if ((available = getClosestDrivers(passenger, dropoff)).size() == 0) {
			System.out.println("RideManager: Failed to request ride. "
					+ "No drivers available");
			return;
		}

		/*
		 * Wait for user and driver to confirm ride.
		 * Step through queue until driver is chosen
		 */
		while (!available.isEmpty()) {
			driver = available.poll().getDriver();
			newRide = new Ride(driver, passenger, dropoff);
			
			/* Check if they can pay for ride */
			if (passenger.getBalance() < newRide.getCost()) {
				System.out.println("Insufficient funds to request ride.");
				break;
			}
			
			if (rideConfirmation(newRide, driver, passenger)) {
				System.out.println("RideManager: Pickup location has been confirmed");
				
				/* Threading for each setup ride */
				rides.add(newRide);
				rideManager = new RideManager(newRide);
				rideManager.startRide();

				completed++;
				
				System.out.println("------------------------------------------------------\n");
				return;
			}
		}
		canceled++;
		
		System.out.println("RideManager: Ride request canceled.");
		System.out.println("------------------------------------------------------\n");
	}

	/**
	 * Determines whether the driver and passenger agree to the ride
	 * @param newRide - holds all ride information
	 * @param driver - current selected driver
	 * @param passenger - user requesting a ride
	 * @return Returns true if both parties confirm
	 */
	public static boolean rideConfirmation(Ride newRide, Driver driver, Passenger passenger) {
		if (driver.randomConfirmRide(newRide)) {
			if (passenger.randomConfirmRide(newRide)) {
				System.out.println("The user and driver have confirmed");
				driver.setAvailable(false);
				
				return true;
			}
		}
		return false;
	}

	/**
	 * Algorithm for building a queue of drivers. Drivers who are closest to
	 * the passenger are given priority. If there is a tie the driver with 
	 * the highest rating receives priority
	 * @param passenger - user requesting the ride
	 * @param dropoff - dropoff location for the ride
	 * @return Returns a PriorityQueue of drivers to pick from 
	 */
	public static PriorityQueue<Ride> getClosestDrivers(Passenger passenger, Location dropoff) {
		Ride ride;
		
		/* Create a comparator to compare drivers */
		Comparator<Ride> comparator = new Comparator<Ride>() {
	        @Override
	        public int compare(Ride a, Ride b) {
	        	/* Chose closest driver */
	        	if (a.getDriverDistance() < b.getDriverDistance()) {
	        		return -1;
	        	} else if (a.getDriverDistance() > b.getDriverDistance()) {
	        		return 1;
	        	/* If there is a tie, use ratings to choose */	
	        	} else {
	        		if (a.getDriver().getRating() < b.getDriver().getRating())
		                return -1;
		            else if (a.getDriver().getRating() > b.getDriver().getRating())
		                return 1;
		            return 0;
	        	}
	        }
	    };

		PriorityQueue<Ride> pqueue = new PriorityQueue<Ride>(comparator);
		
		/* For each driver create a potential ride for use to choose from later */
		for (Iterator<Driver> i = drivers.iterator(); i.hasNext(); ) {
			Driver driver = (Driver)i.next();
			if (driver.isAvailable()) {
				ride = new Ride(driver, passenger, dropoff);
				pqueue.add(ride);
			}
		}

		return pqueue;
	}
	
	/**
	 * Used to output all trip history for the current run
	 */
	public static void outputUberHistory() {
		
		System.out.println("--------------- Uber Information ---------------");
		System.out.printf("%d completed, %d canceled, %d transactions\n", 
				completed, canceled, completed);
		
		/* Output passenger information */
		for (Passenger p : users) {
			System.out.println(p.toStringFull());
		}
		
		/* Output driver information */
		for (Driver d : drivers) {
			System.out.println(d.toStringFull());
		}
	}
	
	/**
	 * Add an uber driver to Uber
	 * @param driver - New driver to choose from
	 */
	public static void addUberDriver(Driver driver) {
		drivers.add(driver);
	}
	
	/**
	 * Add a new uber user
	 * @param passenger - New uber users (passenger)
	 */
	public static void addUberUser(Passenger passenger) {
		users.add(passenger);
	}
	
	/**
	 * Remove driver from list of Uber drivers
	 * @param driver - Driver to remove
	 */
	public static void removeUberDriver(Driver driver) {

		drivers.remove(driver);
	}
	
	/**
	 * Remove user from list of uber passengers
	 * @param passenger - Passenger to remove
	 */
	public static void removeUberUser(Passenger passenger) {
		users.remove(passenger);
	}
}
