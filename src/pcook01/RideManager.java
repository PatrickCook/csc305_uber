package pcook01;

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
				System.out.printf("%s has picked up %s at %s", ride.getDriver().toString(), 
						ride.getPassenger().toString(), ride.getPickup().toString());
			}

		}, 2000);
		
		timer.schedule(new TimerTask() {

			public void run() {
				System.out.printf("%s has dropped off %s at %s", ride.getDriver().toString(), 
						ride.getPassenger().toString(), ride.getDestination().toString());
				timer.cancel();
			}

		}, 4000);
		
		collectRatings();
	}
	
	public void collectRatings() {
		
	}
}
