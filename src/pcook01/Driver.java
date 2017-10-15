package pcook01;

import java.util.Scanner;

public class Driver extends User {
	private String carTitle;
	private boolean available;
	
	public Driver(String firstName, String lastName, double balance,
			Location location) {
		super(firstName, lastName, balance, location);
		
		this.available = true;
	}
	
	public void setAvailable(boolean avail) {
		this.available = avail;
	}
	
	public boolean isAvailable() {
		return available;
	}
	
	public boolean confirmRide(Ride ride) {
		char response;
		Scanner sc = new Scanner(System.in);
		
		System.out.printf("%s (Rating %.2f): A user has requested a ride to %s. Would you like to accept (y/n)?", 
				this.toString(), this.getRating(), ride.getDestination().toString());
		
		response = sc.next(".").charAt(0);

		//Make sure user actually pressed y
		if (response == 'y') {
			System.out.println("Driver has accepted the ride.");
			return true;
		}  else if (response == 'n') {
			System.out.println("Driver declined ride");
		}
		
		return false;
	}
}
