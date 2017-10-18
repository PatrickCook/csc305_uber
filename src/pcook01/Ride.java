package pcook01;

/**
 * Represents a single Uber Ride
 * @author patrickcook
 *
 */
public class Ride {
	private Location pickup;
	private Location dropoff;
	private double rideDistance;
	private double driverDistance;
	private Driver driver;
	private Passenger passenger;
	
	/**
	 * Constructor for an Uber Ride
	 * @param driver Uber ride driver
	 * @param passenger Uber ride passenger
	 * @param dropoff Dropoff location for ride
	 */
	public Ride(Driver driver, Passenger passenger, Location dropoff) {
		this.pickup = passenger.getLocation();
		this.dropoff = dropoff;
		this.driver = driver;
		this.passenger = passenger;
		this.driverDistance = UberMap.getDistance(driver.getLocation(), passenger.getLocation());
		this.rideDistance = UberMap.getDistance(passenger.getLocation(), dropoff);
	}

	/**
	 * Getter for cost
	 * @return Cost is calculated based on total distance and payment rate 
	 */
	public double getCost() {
		return PaymentSystem.RIDE_RATE * getTotalDistance();
	}

	/**
	 * Getter for driver
	 * @return Driver for Uber ride
	 */
	public Driver getDriver() {
		return driver;
	}
	
	/**
	 * Calculates the total ride distance
	 * @return Returns the distance of the drive to 
	 * the passenger plus the ride distance
	 */
	public double getTotalDistance () {
		return (rideDistance + driverDistance);
	}
	
	/**
	 * Getter for distance from driver to passenger
	 * @return Distance from driver to passenger
	 */
	public double getDriverDistance() {
		return driverDistance;
	}
	
	/**
	 * Getter for distance from pickup location to dropoff location
	 * @return Distance from pickup to dropoff location
	 */
	public double getRideDistance() {
		return rideDistance;
	}
	
	/**
	 * Uses Uber constant to estimate the wait time for a user
	 * @return Calculates and returns wait time until driver arrives
	 */
	public int getEstimatedWaitTime() {
		return new Double(getDriverDistance() * Uber.TIME_PER_UNIT_DIST_MS).intValue();
	}
	
	/**
	 * Users Uber constant to estimate ride duration
	 * @return Calculates the estimated ride duration
	 */
	public int getEstimatedRideTime() {
		return new Double(getRideDistance() * Uber.TIME_PER_UNIT_DIST_MS).intValue();
	}
	
	/**
	 * Getter for passenger 
	 * @return Passenger for the ride
	 */
	public Passenger getPassenger() {
		return passenger;
	}
	
	/**
	 * Getter for destination location
	 * @return Destination Location
	 */
	public Location getDestination() {
		return dropoff;
	}
	
	/**
	 * Getter for pickup location
	 * @return Pickup location
	 */
	public Location getPickup() {
		return pickup;
	}
	
	/**
	 * Creates a string to represent the ride. This string 
	 * is logged when Uber app exits
	 * @return String representing the uber ride
	 */
	public String toString() {
		return String.format("Ride Details: \n\tDriver: %s \n\tPassenger: %s\n\tCost: $%.2f \n\tDistance: %.2f\n\t",
				this.driver.toString(), this.passenger.toString(), getCost(), getRideDistance());
	}
}

