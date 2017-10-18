package pcook01;

import java.util.Scanner;

public class Passenger extends User{
	
	private Scanner sc;

	public Passenger(String firstName, String lastName, double balance,
			Location location) {
		super(firstName, lastName, balance, location);
		sc = new Scanner(System.in);
	}

	public void requestRide(Location destination) {
		Uber.requestRide(this, destination);
	}
	
	public boolean userConfirmRide(Ride ride) {
		char response;
		sc = new Scanner(System.in);
		
		System.out.print("USER: A ride has been found! Would you like to confirm (y/n)? ");
		
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
		
		System.out.print("Rate your Uber driver [1-5] or -1 to cancel: ");
		
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
