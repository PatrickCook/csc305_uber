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
		
	}

	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public double getBalance() {
		return balance;
	}

	public void setBalance(double balance) {
		this.balance = balance;
	}

	public Location getLocation() {
		return location;
	}

	public void setLocation(Location currentLocation) {
		this.location = currentLocation;
	}
	
} 
