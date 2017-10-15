package pcook01;

import java.util.Date;

public class Ride {
	private Location pickup;
	private Location dropoff;
	private double distance;
	private Date departureTime;
	private Date arrivalTime;
	private Driver driver;
	private Passenger passenger;
	
	public Ride(Driver driver, Passenger passenger, Location dropoff) {
		this.pickup = passenger.getLocation();
		this.dropoff = dropoff;
		this.driver = driver;
		this.passenger = passenger;
		this.distance = UberMap.getDistance(driver.getLocation(), passenger.getLocation()) +
				UberMap.getDistance(passenger.getLocation(), dropoff);
		this.departureTime = new Date();
	}

	public double getCost() {
		return PaymentSystem.RIDE_RATE * distance;
	}

	public Driver getDriver() {
		return driver;
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
}

