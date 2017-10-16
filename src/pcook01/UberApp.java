package pcook01;

public class UberApp {

	public static void main(String[] args) {
		Passenger patrick = new Passenger("Patrick", "Cook", 200.00, new Location(0,0));
		Passenger casey = new Passenger("Patrick", "Cook", 200.00, new Location(15,15));

		Driver driver = new Driver("Uber", "Driver", 100.00, new Location(10,10));
		Driver driver2 = new Driver("Lyft", "Driver", 100.00, new Location(30,10));
		
		Uber.addUberUser(patrick);
		Uber.addUberUser(casey);
		Uber.addUberDriver(driver);
		Uber.addUberDriver(driver2);
		
		patrick.requestRide(new Location(5,5));
		
		Uber.outputUberHistory();
	}

}
