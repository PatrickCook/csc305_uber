package pcook01;

/**
 * Used to represent the imaginary Uber Map
 * 
 * @author Patrick Cook
 *
 */
public class UberMap {
	public static final int USER_SYM = 1;
	public static final int DRIVER_SYM = 2;
	public static final int MAPSIZE_X = 100;
	public static final int MAPSIZE_Y = 100;

	/**
	 * Gets the distance between two locations
	 * 
	 * @param pickup Pickup location
	 * @param dropoff Dropoff location
	 * @return Distance between pickup and dropoff location
	 */
	public static double getDistance(Location pickup, Location dropoff) {
		int dx, dy;

		if (!containsLocation(pickup) || !containsLocation(dropoff)) {
			return -1;
		}

		dx = Math.abs(dropoff.getxCord() - pickup.getxCord());
		dy = Math.abs(dropoff.getyCord() - pickup.getyCord());

		return Math.sqrt(Math.pow(dx, 2) + Math.pow(dy, 2));
	}

	/**
	 * Checks if a location is contained within the Uber Map
	 * @param loc - Given location to check
	 * @return - True if location is in Uber Map
	 */
	public static boolean containsLocation(Location loc) {
		return (loc.getxCord() >= 0 && loc.getxCord() < MAPSIZE_X
				&& loc.getyCord() >= 0 && loc.getyCord() < MAPSIZE_Y);
	}

}
