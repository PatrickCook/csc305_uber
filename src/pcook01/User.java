package pcook01;

import java.util.ArrayList;

/**
 * Abstract class used to create the building blocks of Drivers and Passengers
 * @author patrickcook
 *
 */
public abstract class User {
	private String firstName;
	private String lastName;
	private double balance;
	private Location location;
	private double sumRatings = 0;
	private double numRatings = 0;
	private final double ACCEPT_RATE = 0.80;
	private final int MAX_RATING = 5;
	
	/**
	 * Default Constructor for User
	 * @param firstName - First name of user
	 * @param lastName - Last name of user
	 * @param balance - Initial balance of user
	 * @param location - Initial location of user
	 */
	public User (String firstName, String lastName, double balance, Location location) {
		this.firstName = firstName;
		this.lastName = lastName;
		this.balance = balance >= 0 ? balance : 0;
		this.location = UberMap.containsLocation(location) ? location : new Location(0,0);
	}
	
	/**
	 * Random rating generator
	 * @return - Number between 1-5
	 */
	public int collectRating() {
		return (int) (Math.random() * MAX_RATING) + 1;
	}
	
	/**
	 * Randomly confirms a ride
	 * @param ride
	 * @return
	 */
	public boolean randomConfirmRide(Ride ride) {
		return Math.random() <= ACCEPT_RATE;
	}
	
	/**
	 * Updates the rating
	 * @param rating
	 */
	public void addRating(int rating) {
		if (0 < rating && rating <= 5) {
			numRatings++;
			sumRatings += rating;
		}		
	}
	
	/**
	 * Calcuate user rating using sum of ratings and num of ratings
	 * @return Rating of a user
	 */
	public double getRating() {
		if (numRatings == 0) {
			return 0.0;
		}
		return sumRatings / numRatings;
	}
	
	/**
	 * Getter for user balance
	 * @return Remaining user balance
	 */
	public double getBalance() {
		return balance;
	}

	/**
	 * Set user balance
	 * @param balance New balance
	 */
	public void setBalance(double balance) {
		this.balance = balance >= 0 ? balance : 0;
	}
	
	/**
	 * Add more funds to user balance
	 * @param balance Amount to add
	 */
	public void addBalance(double balance) {
		if (balance < 0) return;
		
		this.balance += balance;
	}

	/**
	 * Remove funds from user balance
	 * @param balance Amount to remove
	 */
	public void removeBalance(double balance) {
		if (this.balance - balance < 0) {
			this.balance = 0;
		} else {
			this.balance -= balance;
		}
	}

	/**
	 * Getter for user location 
	 * @return Location of user
	 */
	public Location getLocation() {
		return location;
	}

	/**
	 * Set location of user 
	 * @param currentLocation
	 */
	public void setLocation(Location currentLocation) {
		this.location = currentLocation;
	}
	
	/**
	 * Returns a string containing first and last name
	 */
	public String toString() {
		return firstName + " " + lastName;
	}
	
	/**
	 * Returns string w/ first and last name, rating and current location
	 * @return String with first/last name, rating and current location
	 */
	public String toStringFull() {
		return String.format("%s %s: \n\tRating: %.2f\n\tLocation: (%d, %d)",
				firstName, lastName, getRating(), location.getxCord(), location.getyCord());
	}
} 
