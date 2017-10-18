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
	private final double ACCEPT_RATE = 0.60;
	private final int MAX_RATING = 5;
	
	public User (String firstName, String lastName, double balance, Location location) {
		this.firstName = firstName;
		this.lastName = lastName;
		this.balance = balance >= 0 ? balance : 0;
		this.location = UberMap.containsLocation(location) ? location : new Location(0,0);
	}
	
	public int collectRating() {
		return (int) (Math.random() * MAX_RATING);
	}
	
	public boolean randomConfirmRide(Ride ride) {
		return Math.random() <= ACCEPT_RATE;
	}
	
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
		this.balance = balance >= 0 ? balance : 0;
	}
	
	public void addBalance(double balance) {
		if (balance < 0) return;
		
		this.balance += balance;
	}

	public void removeBalance(double balance) {
		if (this.balance - balance < 0) {
			this.balance = 0;
		} else {
			this.balance -= balance;
		}
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
