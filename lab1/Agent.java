package lab1;

import java.util.Map;
import java.util.Random;

public class Agent {
	//fields
	private Point location;
	private int energy;
	private String identity;
	private Map<String, Double> strategy;

	//constructor
	public Agent(Map<String, Double> strategy) {
		this.location = new Point(0,0);
		this.energy=10;
		this.identity = "A";
		this.strategy = strategy;
	}//constructor
	
	public void setX(int x) {
		this.location.setX(x);
	}
	
	public int getX() {
		return this.location.getX();
	}

	public int getY() {
		return this.location.getY();
	}

	public void setY(int y) {
		this.location.setY(y);
	}

	public Point getLocation(){
		return this.location;
	}

	public void setLocation(Point newSpot){
		this.location = newSpot;
	}

	public int getEnergy() {
		return energy;
	}

	public void setEnergy(int energy) {
		this.energy = energy;
	}

	public String getIdentity() {
		return this.identity;
	}
	
	// Modifications: added getter and setter for strategy
	// Modifications: added decideAction that use the agent's strategy probabilities to select an action.
    public Map<String, Double> getStrategy() {
        return strategy;
    }

    public void setStrategy(Map<String, Double> strategy) {
        this.strategy = strategy;
    }


	public String decideAction() {
		double rand = Math.random(); // Random number between 0.0 and 1.0
		double cumulativeProbability = 0.0;
		for (Map.Entry<String, Double> entry : strategy.entrySet()) {
			cumulativeProbability += entry.getValue();
			if (rand <= cumulativeProbability) {
				return entry.getKey();
			}
		}
		// In case of rounding errors, return a default action
		return "UP";
	}
	

	public void up() {
		this.setY(this.getY()-1);
	}
	
	public void down() {
		this.setY(this.getY()+1);
	}
	
	public void right() {
		this.setX(this.getX()+1);
	}
	
	public void left() {
		this.setX(this.getX()-1);
	}

	//we need to check if occupied before each move and have a plan of what to do if it is occupied
	//also the replacement case for food bc it'll replace food in the cell
	public void move(Display d, Point p) {
		if(!this.occupied(d, p)){ 
			String action = decideAction();
			switch(action) {
			case "UP":
				up();
				break;
			case "DOWN":
				down();
				break;
			case "RIGHT":
				right();
				break;
			case "LEFT":
				left();
				break;
			default://stay put
				setX(getX());
				setY(getY());
				break;
			}
		}
	}
	



	
	/*occupied(Point p)
	Point p is a point on the gameboard. This method will return true if it's occupied by another agent, 
	an obstacle O, food F, or the goal. It will not return true if the point is the origin. All necessary
	updates will happen in this method: if it's occupied with food, the agent querying will receive a food boost.
	If it's occupied with an obstacle, the agent will lose a life, etc. NOT DPNE
	*/

	public boolean occupied(Display d, Point p) {
		if(d.getDisplay().containsKey(p)){//if it's in the display
			//update agent accordingly: food or obstacle
			String cellCount = d.getDisplay().get(p);

			switch(cellCount){
				case "A": //it's hitting another agent, so nothing is happening
				return true;

				case "F":
				Food f = new Food();
				f.giveEnergy(this);
				//give it energy
				//remove F from hashmap? or keep it?
				return false; //technically, it is occupied, but agents can occupy the same space as food/take the space

				case "O":// it hits an obstacle
				//take energy away
				Obs o = new Obs();
				o.takeEnergy(this);
				return true;

				default: //it didn't hit an agent, an obstacle, or food, so the space was empty, the goal, or the initialState
				return false; //bc the space wasn't occupied
				//working right here, do the identities
			}
		} else {
			System.out.println("Point is not in display- what is going on!");
			return false;
		}
	}
}
