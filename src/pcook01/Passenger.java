package pcook01;

public class Passenger extends User{
	
	public void requestRide(Location destination) {
		Ride requestedRide = RideManager.requestRide(this, destination);
		addPreviousRide(requestedRide);
	}
}
