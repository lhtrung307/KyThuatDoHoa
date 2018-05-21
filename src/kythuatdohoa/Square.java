package kythuatdohoa;

import java.awt.image.BufferedImage;

public class Square {
	private Point d1 = new Point();
	private Point d2 = new Point();
	private BufferedImage image;

	public Square(BufferedImage image, Point d1, Point d2) {
		this.image = image;
		this.d1 = d1;
		this.d2 = d2;
	}

	public BufferedImage getImage() {
		return image;
	}

	public void paint() {
		Point d3, d4;
		int dx, dy;

		dx = d2.getX() - d1.getX();
		dy = d2.getY() - d1.getY();

		int min = Math.min(Math.abs(dx), Math.abs(dy));
		if (dx < 0) {
			d3 = new Point(d1.getX() - min, d1.getY());
			d4 = new Point(d1.getX(), d1.getY() + min);
			if (dy < 0) {
				d4 = new Point(d1.getX(), d1.getY() - min);
			}
		} else {
			d3 = new Point(d1.getX() + min, d1.getY());
			d4 = new Point(d1.getX(), d1.getY() + min);
			if (dy < 0) {
				d4 = new Point(d1.getX(), d1.getY() - min);
			}
		}
		d2.setX(d3.getX());
		d2.setY(d4.getY());

		BresenhamLine line1 = new BresenhamLine(image, d1, d3);
		BresenhamLine line2 = new BresenhamLine(image, d1, d4);
		BresenhamLine line3 = new BresenhamLine(image, d2, d3);
		BresenhamLine line4 = new BresenhamLine(image, d2, d4);

		line1.BresenhamLine();
		line2.BresenhamLine();
		line3.BresenhamLine();
		line4.BresenhamLine();
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

		Square sq = new Square(image, d1, d2);
		sq.paint();
	}
}
