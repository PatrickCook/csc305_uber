package pcook01;

public class UberMap {
	private static final int MAPSIZE_X = 300;
	private static final int MAPSIZE_Y = 300;
	private static int[][] map = new int[MAPSIZE_X][MAPSIZE_Y];
	
	
	public static double getDistance(Location pickup, Location dropoff) {
		int dx, dy;
		
		if (!containsLocation(pickup) && !containsLocation(dropoff)) {
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
}
