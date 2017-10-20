package pcook01;

import java.io.FileWriter;

import java.io.IOException;
import java.util.Timer;
import java.util.TimerTask;
import java.util.concurrent.CountDownLatch;

import org.json.simple.JSONObject;

/**
 * RideManager is used to keep track and update all objects involved 
 * in an Uber ride. Once a ride is requested a RideManager is created
 * for that ride and the Uber experience begins
 * @author Patrick Cook
 */
public class RideManager {
	private Ride ride;

	/**
	 * Default constructor for creating a RideManager
	 * @param ride - ride that is specific to this manager
	 */
	public RideManager(Ride ride) {
		this.ride = ride;
	}

	/**
	 * The starting point for every Uber ride. Responsible for
	 * simulating driver pickup, ride duration and ride completion
	 * using timers. All user attributes are updated once the ride
	 * has been completed
	 */
	public void startRide() {
		Timer timer = new Timer();
		Driver driver = ride.getDriver();
		Passenger user = ride.getPassenger();
		CountDownLatch latch = new CountDownLatch(2);
		Transaction transaction = new Transaction(user, driver, ride.getCost());
		
		/* Let user know driver is on his way */
		System.out.printf("RideManager: %s is on his way. Estimated wait time is %d ms.\n",
				driver.toString(), ride.getEstimatedWaitTime());

		/* Alert user that driver has arrived */
		timer.schedule(new TimerTask() {

			public void run() {
				System.out.printf("RideManager: %s has arrived\n", 
						driver.toString());
				latch.countDown();
			}

		}, ride.getEstimatedWaitTime());

		/* Alert user that they have been dropped off */
		timer.schedule(new TimerTask() {

			public void run() {
				System.out.printf("RideManager: %s has dropped off %s\n", driver.toString(),
						user.toString());
				
				PaymentSystem.processTransaction(transaction);
				collectRatings();
				
				/* Update driver and user information */
				driver.setAvailable(true);
				driver.setLocation(ride.getDestination());
				user.setLocation(ride.getDestination());
				
				latch.countDown();
			}

		}, ride.getEstimatedWaitTime() + ride.getEstimatedRideTime());
		
		/* Wait for timers to end */
		try {
			latch.await();
		} catch (InterruptedException e) {
			
		}
		
		timer.cancel();
	}
	
	/**
	 * Used to collect user ratings. Currently rating collection
	 * defaults to a randomized function. However userCollectRating()
	 * can be swapped in to allow for user input
	 */
	public void collectRatings() {
		int driverRating, passengerRating;
		Driver driver = ride.getDriver();
		Passenger passenger  = ride.getPassenger();

		/* Swap with userCollectRating() for user input version */
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
