package pcook01;

public class UberMap {
	public static final int USER_SYM = 1;
	public static final int DRIVER_SYM = 2;
	public static final int MAPSIZE_X = 100;
	public static final int MAPSIZE_Y = 100;
	private static int[][] map = new int[MAPSIZE_X][MAPSIZE_Y];
	
	
	public static double getDistance(Location pickup, Location dropoff) {
		int dx, dy;
		
		if (!containsLocation(pickup) || !containsLocation(dropoff)) {
			return -1;
		}
		
		dx = Math.abs(dropoff.getxCord() - pickup.getxCord());
		dy = Math.abs(dropoff.getyCord() - pickup.getyCord());

				
		return Math.sqrt(Math.pow(dx, 2) + Math.pow(dy, 2));
	}
	
	public static boolean containsLocation(Location loc) {
		return (
				loc.getxCord() >= 0 && loc.getxCord() < MAPSIZE_X &&
				loc.getyCord() >= 0 && loc.getyCord() < MAPSIZE_Y
			   );
	}
	
	public static void unsetLocation(Location loc) {
		setLocation(loc, 0);
	}
	
	public static void setLocation(Location loc, int with) {
		map[loc.getxCord()][loc.getyCord()] = with;
	}
	
	public static int getLocation(Location loc) {
		return map[loc.getxCord()][loc.getyCord()];
	}
}
