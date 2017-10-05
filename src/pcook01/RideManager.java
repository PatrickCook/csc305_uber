package pcook01;

import java.util.ArrayList;
import java.util.Iterator;

public final class RideManager {
	private static ArrayList<Driver> drivers;
	private static ArrayList<Ride> rides;

	
	public static Ride requestRide(Passenger passenger, Location dropoff) {
		Driver driver;
		
		if (!UberMap.containsLocation(dropoff)) {
			System.out.println("Location is not supported by Uber");
			return null;
		}
		
		if ((driver = getClosestDriver(passenger)) == null) {
			System.out.println("Failed to request ride. No drivers exist");
			return null;
		}
		
		return new Ride(driver, passenger, dropoff);
	}
	
	public static Driver getClosestDriver(Passenger passenger) {
		Driver closestDriver;
		double minDist = Double.MAX_VALUE;
		
		for (Iterator i = drivers.iterator(); i.hasNext(); ) {
			Driver driver = (Driver)i.next();
	      
			if (driver.isAvailable()) {
				double dist = UberMap.getDistance(driver.getLocation(), 
						passenger.getLocation());
				
				if (dist < minDist) {
	    		  minDist = dist;
	    		  closestDriver = driver;
				}
			}
		}
		
		return closestDriver;
	}
}
