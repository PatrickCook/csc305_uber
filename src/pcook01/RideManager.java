package pcook01;

import java.io.FileWriter;
import java.io.IOException;
import java.util.Timer;
import java.util.TimerTask;

import org.json.simple.JSONObject;

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
		Driver driver = ride.getDriver();
		Passenger user = ride.getPassenger();
		Timer timer = new Timer();
		
		/* Let user know driver is on his way */
		timer.schedule(new TimerTask() {

			public void run() {
				System.out.printf("RideManager: %s is on his way. Estimated wait time is %d minutes.\n",
						driver.toString(), ride.getEstimatedWaitTime());
			}

		}, 2000);

		/* Alert user that driver has arrived */
		timer.schedule(new TimerTask() {

			public void run() {
				System.out.printf("RideManager: %s has arrived\n", 
						driver.toString());
			}

		}, 3000);

		/* Alert user that they have been dropped off */
		timer.schedule(new TimerTask() {

			public void run() {
				System.out.printf("RideManager: %s has dropped off %s\n", driver.toString(),
						user.toString());

				collectRatings();
				logRide();
				
				/* Update driver and user information */
				driver.setAvailable(true);
				driver.setLocation(ride.getDestination());
				user.setLocation(ride.getDestination());
				
				timer.cancel();
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

        System.out.println(log.toString());
	}
}
