package pcook01;

import java.util.Date;

public class Ride {
	private Location pickup;
	private Location dropoff;
	private double rideDistance;
	private double driverDistance;
	private Date departureTime;
	private Date arrivalTime;
	private Driver driver;
	private Passenger passenger;
	
	public Ride(Driver driver, Passenger passenger, Location dropoff) {
		this.pickup = passenger.getLocation();
		this.dropoff = dropoff;
		this.driver = driver;
		this.passenger = passenger;
		this.driverDistance = UberMap.getDistance(driver.getLocation(), passenger.getLocation());
		this.rideDistance = UberMap.getDistance(passenger.getLocation(), dropoff);
		this.departureTime = new Date();
	}

	public double getCost() {
		return PaymentSystem.RIDE_RATE * getTotalDistance();
	}

	public Driver getDriver() {
		return driver;
	}
	
	public double getTotalDistance () {
		return (rideDistance + driverDistance);
	}
	
	public double getDriverDistance() {
		return driverDistance;
	}
	
	public double getRideDistance() {
		return rideDistance;
	}
	
	public int getEstimatedWaitTime() {
		return new Double(getDriverDistance() / 10).intValue();
	}
	
	public Passenger getPassenger() {
		return passenger;
	}
	
	public Location getDestination() {
		return dropoff;
	}
	
	public Location getPickup() {
		return pickup;
	}
	
	public String toString() {
		return String.format("Ride Details: \n\tDriver: %s \n\tPassenger: %s\n\tCost: $%.2f \n\tDistance: %.2f\n\t",
				this.driver.toString(), this.passenger.toString(), getCost(), getRideDistance());
	}
}

