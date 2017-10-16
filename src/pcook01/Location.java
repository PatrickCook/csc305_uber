package pcook01;

public class Location {
	private int xCord, yCord;
	
	public Location(int xCord, int yCord) {
		this.xCord = xCord;
		this.yCord = yCord;
	}

	public boolean equals(Location other) {
		return this.xCord == other.xCord && this.yCord == other.yCord;
	}
	public int getxCord() {
		return xCord;
	}

	public void setxCord(int xCord) {
		this.xCord = xCord;
	}

	public int getyCord() {
		return yCord;
	}

	public void setyCord(int yCord) {
		this.yCord = yCord;
	}
	
	public String toString() {
		return "(" + xCord + ", " + yCord + ")";
	}
}
