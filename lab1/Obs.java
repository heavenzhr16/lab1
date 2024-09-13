package lab1;

public class Obs {
	private Point location;
	private String identity = "O";

	public Obs () {
		this.setLocation(new Point());
	}
	
	public Obs(Point c) {
		this.setLocation(c);
	}

	public Point getLocation() {
		return location;
	}

	public void setLocation(Point location) {
		this.location = location;
	}

	public String getIdentity() {
		return this.identity;
	}

	public void takeEnergy(Agent a){
		a.setEnergy(a.getEnergy()-1);
	}
}
