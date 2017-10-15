package pcook01;

import java.util.Scanner;

public class Passenger extends User{
	
	public Passenger(String firstName, String lastName, double balance,
			Location location) {
		super(firstName, lastName, balance, location);
	}

	public void requestRide(Location destination) {
		Uber.requestRide(this, destination);
	}
	
	public boolean confirmRide(Ride ride) {
		char response;
		Scanner sc = new Scanner(System.in);
		
		System.out.printf("%s (Rating %.2f): A ride has been found! The ride will cost $%.2f. Would you like to confirm (y/n)?", 
				this.toString(), this.getRating(), ride.getCost());
		
		response = sc.next(".").charAt(0);

		//Make sure user actually pressed y
		if (response == 'y') {
			System.out.println("User confirmed ride.");
			return true;
		} else if (response == 'n') {
			System.out.println("User declined ride");
		}
		
		
		return false;
	}

	@Override
	public int collectRating() {
		int rating;
		Scanner sc = new Scanner(System.in);
		
		System.out.print("Rate your Uber driver [0-5] or -1 to cancel: ");
		
		while (true) {
			rating = sc.nextInt();
			if (0 <= rating && rating <= 5) {
				return rating;
			} else if (rating == -1) {
				return -1;
			}
			System.out.println("Please enter number between [0-5]: ");
		}
	}
}
