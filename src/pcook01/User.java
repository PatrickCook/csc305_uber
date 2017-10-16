package pcook01;

import java.util.ArrayList;

public abstract class User {
	private String firstName;
	private String lastName;
	private double balance;
	private Location location;
	private ArrayList<Ride> prevRides;
	private int sumRatings = 0;
	private int numRatings = 0;
	
	public User (String firstName, String lastName, double balance, Location location) {
		this.firstName = firstName;
		this.lastName = lastName;
		this.balance = balance;
		this.location = location;
	}
	
	
	public abstract boolean confirmRide(Ride ride);
	public abstract int collectRating();
	
	public void addRating(int rating) {
		numRatings++;
		sumRatings += rating;
	}
	
	public double getRating() {
		if (numRatings == 0) {
			return 0.0;
		}
		
		return sumRatings / numRatings;
	}
	
	public void addPreviousRide(Ride ride) {
		prevRides.add(ride);
	}

	public double getBalance() {
		return balance;
	}

	public void setBalance(double balance) {
		this.balance = balance;
	}
	
	public void addBalance(double balance) {
		this.balance += balance;
	}

	public void removeBalance(double balance) {
		this.balance -= balance;
	}

	public Location getLocation() {
		return location;
	}

	public void setLocation(Location currentLocation) {
		this.location = currentLocation;
	}
	
	public String toString() {
		return firstName + " " + lastName;
	}
	
	public String toStringWB() {
		return firstName + " " + lastName + ": Rating " + balance;
	}
} 
