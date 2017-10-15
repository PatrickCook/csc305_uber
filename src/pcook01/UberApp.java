package pcook01;

public class UberApp {

	public static void main(String[] args) {
		Passenger patrick = new Passenger("Patrick", "Cook", 200.00, new Location(0,0));
		Driver driver = new Driver("Uber", "Driver", 100.00, new Location(10,10));
		
		Uber.addUberDriver(driver);
		Uber.addUberDriver(driver);
		
		patrick.requestRide(new Location(5,5));
		

	}

}
