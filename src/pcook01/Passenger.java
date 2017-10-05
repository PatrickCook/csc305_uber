package pcook01;

public class Passenger extends User{
	
	public void requestRide(Location destination) {
		RideManager.requestRide(this, destination);
	}
}
