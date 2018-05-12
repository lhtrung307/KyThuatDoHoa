package kythuatdohoa;

public class Point {
	private int x;
	private int y;
	
	public Point(int x, int y) {
		setX(x);
		setY(y);
	}
	
	public Point() {
		x = 0;
		y = 0;
	}

	public int getX() {
		return x;
	}

	public void setX(int x) {
		if(x < 0){
			this.x = 0;
		}
		else if(x > Main.SCR_WIDTH - 1){
			this.x = Main.SCR_WIDTH - 1;
		}
		this.x = x;
	}

	public int getY() {
		return y;
	}

	public void setY(int y) {
		if(y < 0){
			this.y = 0;
		}
		else if(y > Main.SCR_HEIGHT - 1){
			this.y = Main.SCR_HEIGHT - 1;
		}
		this.y = y;
	}
	
	public String toString(){
		return x + " " + y;
	}
	
}
