package pcook01;

/**
 * Used to represent a location within the UberMap
 * @author patrickcook
 *
 */
public class Location {
	private int xCord, yCord;
	
	/**
	 * Default constructor
	 * @param xCord X coordinate of location
	 * @param yCord Y coordinate of location
	 */
	public Location(int xCord, int yCord) {
		this.xCord = xCord;
		this.yCord = yCord;
	}

	/**
	 * Helper for checking if locations are equal
	 * @param other
	 * @return
	 */
	public boolean equals(Location other) {
		return this.xCord == other.xCord && this.yCord == other.yCord;
	}
	
	/**
	 * Getter for x coordinate
	 * @return int x coordinate
	 */
	public int getxCord() {
		return xCord;
	}

	/**
	 * Setter for x coordinate
	 * @param xCord X coordinate
	 */
	public void setxCord(int xCord) {
		this.xCord = xCord;
	}

	/**
	 * Getter for y coordinate
	 * @return int y coordinate
	 */
	public int getyCord() {
		return yCord;
	}
	
	/**
	 * Setter for y coordinate
	 * @param yCord y coordinate
	 */
	public void setyCord(int yCord) {
		this.yCord = yCord;
	}
	
	/**
	 * To string method used to represent a location
	 */
	public String toString() {
		return "(" + xCord + ", " + yCord + ")";
	}
}
