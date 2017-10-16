package pcook01;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.Iterator;
import java.util.PriorityQueue;

public final class Uber {
	private static int completed = 0;
	private static int canceled = 0;
	private static ArrayList<Transaction> transactions = new ArrayList<>();
	private static ArrayList<Passenger> users = new ArrayList<>();
	private static ArrayList<Driver> drivers = new ArrayList<>();
	private static ArrayList<Ride> rides = new ArrayList<>();

	public static void requestRide(Passenger passenger, Location dropoff) {
		Ride newRide;
		Driver driver;
		Transaction transaction;
		PriorityQueue<Ride> available;
		
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
			driver = available.peek().getDriver();
			newRide = new Ride(driver, passenger, dropoff);

			if (rideConfirmation(newRide, driver, passenger)) {
				transaction = new Transaction(passenger, driver, newRide.getCost());
				transactions.add(transaction);
				
				PaymentSystem.processTransaction(transaction);
				/* Ride has begun */
				System.out.println("RideManager: Pickup location has been confirmed");
				/* Threading for each setup ride */
				rides.add(newRide);
				(new Thread(new RideManager(newRide))).start();
				completed++;
				return;
			} else {
				/* Remove driver if he declines ride */
				available.remove(driver);
			}
		}
		canceled++;
		System.out.println("RideManager: User/driver failed to confirm ride. "
				+ "Ride request canceled.");
	}

	public static boolean rideConfirmation(Ride newRide, Driver driver, Passenger passenger) {
		if (driver.confirmRide(newRide)) {
			if (passenger.confirmRide(newRide)) {
				System.out.println("The user and driver have confirmed");

				if (passenger.getBalance() > newRide.getCost()) {
					driver.setAvailable(false);
					return true;
				} else {
					System.out.println("RideManager: Insufficient funds to request ride.");
				}
			}
		}
		return false;
	}

	public static PriorityQueue<Ride> getClosestDrivers(Passenger passenger, Location dropoff) {
		Ride ride;
		
		Comparator<Ride> comparator = new Comparator<Ride>() {
	        @Override
	        public int compare(Ride a, Ride b) {
	        	if (a.getDriverDistance() < b.getDriverDistance()) {
	        		return -1;
	        	} else if (a.getDriverDistance() > b.getDriverDistance()) {
	        		return 1;
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

		for (Iterator i = drivers.iterator(); i.hasNext(); ) {
			Driver driver = (Driver)i.next();
			if (driver.isAvailable()) {
				ride = new Ride(driver, passenger, dropoff);
				pqueue.add(ride);
			}
		}

		return pqueue;
	}
	
	public static void outputUberHistory() {
		int spot;
		int sizeX = UberMap.MAPSIZE_X, sizeY = UberMap.MAPSIZE_Y;
		
		System.out.printf("%d completed, %d canceled\n", completed, canceled);
		
		for (Passenger p : users) {
			UberMap.setLocation(p.getLocation(), 1);
			System.out.println(p.toStringWB());
		}
		for (Driver d : drivers) {
			UberMap.setLocation(d.getLocation(), 2);
			System.out.println(d.toStringWB());
		}
		
		System.out.println("D - driver, P - passenger");
		
		for (int row = 0; row < sizeX; row++) {
			for (int col = 0; col < sizeY; col++) {
				spot = UberMap.getLocation(new Location(row, col));
				if (spot == 0) {
					System.out.print("- ");
				} else if (spot == 1) {
					System.out.print("U ");
				} else if (spot == 2) {
					System.out.print("D ");
				}
			}
			System.out.println();
		}
	}
	
	/* Setters and Getters */
	public static void addUberDriver(Driver driver) {
		drivers.add(driver);
	}
	
	public static void addUberUser(Passenger passenger) {
		users.add(passenger);
	}
	
	public static void removeUberDriver(Driver driver) {
		drivers.remove(driver);
	}
	
	public static void removeUberUser(Passenger passenger) {
		users.remove(passenger);
	}
}
