package pcook01;

import java.util.Scanner;

public class Driver extends User {
	private String carTitle;
	private boolean available;
	
	public boolean isAvailable() {
		return available;
	}
	
	public boolean confirmRide(Ride ride) {
		char response;
		Scanner sc = new Scanner(System.in);
		
		System.out.printf("A user has requested a ride to " 
				+ ride.getDestination().toString()
				+ ". Would you like to accept (y/n)?", ride.getCost());
		
		response = sc.next(".").charAt(0);
		sc.close();
		//Make sure user actually pressed y
		if (response == 'y') {
			System.out.println("Driver has accepted the ride.");
			return true;
		}
		
		return false;
	}
}
