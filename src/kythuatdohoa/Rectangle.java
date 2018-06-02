package kythuatdohoa;

import java.awt.image.BufferedImage;


public class Rectangle extends Shape<Rectangle>{
	private Point d1 = new Point();
	private Point d2 = new Point();
	private Point d3;
	private Point d4;

	public Rectangle(Point d1, Point d2) {
		this.d1 = d1;
		this.d2 = d2;
		d3 = new Point(d2.getX(), d1.getY());
		d4 = new Point(d1.getX(), d2.getY());
	}


	public Rectangle(Point d1, Point d2, Point d3, Point d4) {
		super();
		this.d1 = d1;
		this.d2 = d2;
		this.d3 = d3;
		this.d4 = d4;
	}


	public Point getD1() {
		return d1;
	}


	public void setD1(Point d1) {
		this.d1 = d1;
	}


	public Point getD2() {
		return d2;
	}


	public void setD2(Point d2) {
		this.d2 = d2;
	}

	public Point getD3() {
		return d3;
	}

	public void setD3(Point d3) {
		this.d3 = d3;
	}

	public Point getD4() {
		return d4;
	}

	public void setD4(Point d4) {
		this.d4 = d4;
	}

	public void paint() {
		BresenhamLine line1 = new BresenhamLine(d1, d3);
		BresenhamLine line2 = new BresenhamLine(d1, d4);
		BresenhamLine line3 = new BresenhamLine(d2, d3);
		BresenhamLine line4 = new BresenhamLine(d2, d4);

		line1.drawLine();
		points.addAll(line1.getPoints());
		line2.drawLine();
		points.addAll(line2.getPoints());
		line3.drawLine();
		points.addAll(line3.getPoints());
		line4.drawLine();
		points.addAll(line4.getPoints());
	}

	@Override
	public void drawShape(BufferedImage image) {
		this.paint();
		for (Point point : points) {
			Main.drawPoint(point, image);
		}
		
	}


	@Override
	public void scale(double sx, double sy) {
		this.setD1(PhepBienDoi.scaling(d1, sx, sy, new Point(0, 0)));
		this.setD2(PhepBienDoi.scaling(d2, sx, sy, new Point(0, 0)));
		points.clear();
	}
	
	@Override
	public Rectangle rotation(double theta, Point p) {
		Point d1 = PhepBienDoi.rotation(this.d1, p, theta).clone();
		Point d2 = PhepBienDoi.rotation(this.d2, p, theta).clone();
		Point d3 = PhepBienDoi.rotation(this.d3, p, theta).clone();
		Point d4 = PhepBienDoi.rotation(this.d4, p, theta).clone();
		return new Rectangle(d1, d2, d3, d4);
	}
}