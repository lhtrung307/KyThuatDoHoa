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

		dx = Math.abs(d2.getX() - d1.getX());
		dy = Math.abs(d2.getY() - d1.getY());

		int min = Math.min(dx, dy);
		d3 = new Point(d1.getX() + min, d1.getY());
		d4 = new Point(d1.getX(), d1.getY() + min);

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
}
