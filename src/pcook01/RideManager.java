package pcook01;

import java.io.FileWriter;
import java.io.IOException;
import java.util.Timer;
import java.util.TimerTask;
import java.util.concurrent.CountDownLatch;

import org.json.simple.JSONObject;

public class RideManager {
	private Ride ride;

	public RideManager(Ride ride) {
		this.ride = ride;
	}


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
				logRide();
				
				/* Update driver and user information */
				UberMap.unsetLocation(user.getLocation());
				UberMap.unsetLocation(driver.getLocation());
				
				driver.setAvailable(true);
				driver.setLocation(ride.getDestination());
				user.setLocation(ride.getDestination());
				
				UberMap.setLocation(user.getLocation(), 1);
				UberMap.setLocation(driver.getLocation(), 2);
				
				latch.countDown();
			}

		}, ride.getEstimatedWaitTime() + ride.getEstimatedRideTime());
		
		try {
			latch.await();
		} catch (InterruptedException e) {
			
		}
		timer.cancel();
		
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
	
	@SuppressWarnings("unchecked")
	public void logRide() {
		
		JSONObject log = new JSONObject();
		JSONObject user = new JSONObject();
		JSONObject driver = new JSONObject();
		JSONObject locs = new JSONObject();
		
		user.put("name", ride.getPassenger().toString());
		user.put("balance", ride.getPassenger().getBalance());
		
		driver.put("name", ride.getDriver().toString());
		driver.put("balance", ride.getDriver().getBalance());
		
		locs.put("start", ride.getPickup().toString());
		locs.put("end", ride.getDestination().toString());		
		
		log.put("driver", driver);
		log.put("user", user);
		log.put("location", locs);
		log.put("cost", ride.getCost());
		
		
		/* write to log file */
        try (FileWriter file = new FileWriter("uber_logs.json", true)) {

            file.write(log.toString() + "\n");
            file.flush();

        } catch (IOException e) {
            e.printStackTrace();
        }
	}
}
