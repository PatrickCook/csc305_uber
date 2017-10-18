package pcook01;

import java.util.Scanner;

public class Driver extends User {
	private Scanner sc;
	private String carTitle;
	private boolean available;

	public Driver(String firstName, String lastName, double balance,
			Location location) {
		super(firstName, lastName, balance, location);
		this.sc = new Scanner(System.in);
		this.available = true;
	}
	
	public void setAvailable(boolean avail) {
		this.available = avail;
	}
	
	public boolean isAvailable() {
		return available;
	}
	
	public boolean userConfirmRide(Ride ride) {
		char response;
		
		System.out.printf("DRIVER: A user has requested a ride to %s. Would you like to accept (y/n)? ", 
				ride.getDestination().toString());
		
		while (true) {
			response = sc.next(".").charAt(0);

			//Make sure user actually pressed y
			if (response == 'y') {
				return true;
			} else if (response == 'n') {
				return false;
			}
			System.out.println("Please enter y/n...");
		}
	}
	
	public int userCollectRating() {
		int rating;
		
		System.out.print("Rate your passenger [1-5] or -1 to skip: ");
		
		while (true) {
			rating = sc.nextInt();
			if (1 <= rating && rating <= 5) {
				return rating;
			} else if (rating == -1) {
				return -1;
			}
			System.out.println("Please enter number between [1-5]: ");
		}
	}
}
