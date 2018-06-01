package kythuatdohoa;

import java.awt.image.BufferedImage;


public class Rectangle extends Shape{
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
		this.setD1(PhepBienDoi.scaling(d1, sx, sy));
		this.setD2(PhepBienDoi.scaling(d2, sx, sy));
		points.clear();
	}
	
	@Override
	public void rotation(double theta, Point p) {
		d1.translateRealToCoordinate();
		this.setD1(PhepBienDoi.rotation(d1, new Point(0, 0), theta));
		d1.translateCoordinateToReal();
		d2.translateRealToCoordinate();
		this.setD2(PhepBienDoi.rotation(d2, new Point(0, 0), theta));
		d2.translateCoordinateToReal();
		d3.translateRealToCoordinate();
		this.setD3(PhepBienDoi.rotation(d3, new Point(0, 0), theta));
		d3.translateCoordinateToReal();
		System.out.println(d4);
		d4.translateRealToCoordinate();
		this.setD4(PhepBienDoi.rotation(d4, new Point(0, 0), theta));
		d4.translateCoordinateToReal();
		System.out.println(d4);
		points.clear();
	}
}