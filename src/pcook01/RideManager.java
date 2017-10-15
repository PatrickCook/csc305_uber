package pcook01;

import java.util.Scanner;
import java.util.Timer;
import java.util.TimerTask;

public class RideManager implements Runnable {
	private Ride ride;
	
	public RideManager(Ride ride) {
		this.ride = ride;
	}

	@Override
	public void run() {
		startRide();
	}	
	
	public void startRide() {
		Timer timer = new Timer();
		timer.schedule(new TimerTask() {

			public void run() {
				System.out.printf("%s has picked up %s at %s\n", ride.getDriver().toString(), 
						ride.getPassenger().toString(), ride.getPickup().toString());
			}

		}, 2000);
		
		timer.schedule(new TimerTask() {

			public void run() {
				System.out.printf("%s has dropped off %s at %s\n", ride.getDriver().toString(), 
						ride.getPassenger().toString(), ride.getDestination().toString());
				timer.cancel();
				collectRatings();
				System.out.println(ride.toString());
			}

		}, 4000);
	}
	
	public void collectRatings() {
		int driverRating, passengerRating;
		Driver driver = ride.getDriver();
		Passenger passenger  = ride.getPassenger();
		
		passengerRating = driver.collectRating();
		driverRating = passenger.collectRating();
		
		if (passengerRating != -1) {
			passenger.addRating(passengerRating);
		}
		
		if (driverRating != -1) {
			driver.addRating(driverRating);
		}
		
		System.out.printf("%s: Rating - %.2f, Balance - %.2f\n", 
				driver.toString(), driver.getRating(), driver.getBalance());
		
		System.out.printf("%s: Rating - %.2f, Balance - %.2f\n", 
				passenger.toString(), passenger.getRating(), passenger.getBalance());
	}
}
