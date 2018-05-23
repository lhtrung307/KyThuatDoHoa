package kythuatdohoa;

import java.awt.image.BufferedImage;

public class Square extends Shape{
	private Point d1 = new Point();
	private Point d2 = new Point();
	private Point d3, d4;

	public Square(Point d1, Point d2) {
		this.d1 = d1;
		this.d2 = d2;
	}

	public Point getDiemTren() {
		return d3;
	}
	
	public Point getDiemDuoi() {
		return d4;
	}

	public void paint() {
		int dx, dy;

		dx = d2.getX() - d1.getX();
		dy = d2.getY() - d1.getY();

		int min = Math.min(Math.abs(dx), Math.abs(dy));
		if(dx<0) {
			d3 = new Point(d1.getX() - min, d1.getY());
			d4 = new Point(d1.getX(), d1.getY() + min);
			if(dy<0) {
				d4 = new Point(d1.getX(), d1.getY()-min);
			}
		}else {
			d3 = new Point(d1.getX() + min, d1.getY());
			d4 = new Point(d1.getX(), d1.getY() + min);
			if(dy<0) {
				d4 = new Point(d1.getX(), d1.getY()-min);
			}
		}

		d2.setX(d3.getX());
		d2.setY(d4.getY());

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
	
	public void doiXung(Point p) {
		int trx1, trx2, try1, try2;
		trx1 = (p.getX() - d1.getX());
		try1 = (p.getY() - d1.getY());
		trx2 = (p.getX() - d2.getX());
		try2 = (p.getY() - d2.getY());

		d1.setX(d1.getX() + 2 * trx1);
		d1.setY(d1.getY() + 2 * try1);
		d2.setX(d2.getX() + 2 * trx2);
		d2.setY(d2.getY() + 2 * try2);

		paint();
	}

	@Override
	public void drawShape(BufferedImage image) {
		this.paint();
		for (Point point : points) {
			Main.drawPoint(point, image);
		}
	}
}

