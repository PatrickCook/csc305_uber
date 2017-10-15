package pcook01;

import java.util.Scanner;

public class Passenger extends User{
	
	public void requestRide(Location destination) {
		RideManager.requestRide(this, destination);
	}
	
	public boolean confirmRide(Ride ride) {
		char response;
		Scanner sc = new Scanner(System.in);
		
		System.out.printf("A ride has been found! The ride will cost %d. Would"
				+ " you like to confirm (y/n)?", ride.getCost());
		
		response = sc.next(".").charAt(0);
		sc.close();
		//Make sure user actually pressed y
		if (response == 'y') {
			System.out.println("User confirmed ride.");
			return true;
		}
		
		return false;
	}
}
