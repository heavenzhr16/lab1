package lab1;

public class Point {
	int x;
	int y;
	
	public Point(int x, int y){
		this.x = x;
		this.y = y;
	}
	
	public Point(){
		this.x = 0;
		this.y = 0;
	}

	public void setX(int x){
		this.x=x;
	}

	public void setY(int y){
		this.y=y;
	}

	public int getX(){
		return this.x;
	}

	public int getY(){
		return this.y;
	}

}
