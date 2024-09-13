package lab1;

public class Food {
	private Point location;
	
	public Food(Point c) {
		this.setLocation(c);
	}
	
	public Food() {
		this.setLocation(new Point(0, 0));
	}

	public Point getLocation() {
		return location;
	}

	public void setLocation(Point location) {
		this.location = location;
	}

	public void giveEnergy(Agent a){
		a.setEnergy(a.getEnergy()+1);
	}
	
	
}
