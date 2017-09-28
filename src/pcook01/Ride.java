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
	
	public Ride(Location pickup, Location dropoff, Driver driver, Passenger passenger, double Distance) {
		this.pickup = pickup;
		this.dropoff = dropoff;
		this.driver = driver;
		this.passenger = passenger;
		this.distance = distance;
		this.departureTime = new Date();
	}
}
