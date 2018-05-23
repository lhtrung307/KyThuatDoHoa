package kythuatdohoa;

import java.awt.image.BufferedImage;
import java.util.ArrayList;

public abstract class Shape {
	protected ArrayList<Point> points;

	public Shape(ArrayList<Point> points) {
		super();
		this.points = points;
	}

	public Shape() {
		points = new ArrayList<>();
	}

	public ArrayList<Point> getPoints() {
		return points;
	}

	public void setPoints(ArrayList<Point> points) {
		this.points = points;
	}
	
	public abstract void drawShape(BufferedImage image);
}
