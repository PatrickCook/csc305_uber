package pcook01;

import java.util.ArrayList;

public abstract class User {
	private String firstName;
	private String lastName;
	private double balance;
	private Location location;
	private ArrayList<Ride> prevRides;
	private ArrayList<Rating> ratings;
	
	public void addRating(Rating rating) {
		ratings.add(rating);
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
} 
