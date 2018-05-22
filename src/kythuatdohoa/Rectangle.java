package kythuatdohoa;

import java.awt.image.BufferedImage;

import javax.swing.JFrame;

public class Rectangle {
	private Point d1 = new Point();
	private Point d2 = new Point();
	private Point d3;
	private Point d4;
	private BufferedImage image;

	public Rectangle(BufferedImage image, Point d1, Point d2) {
		this.image = image;
		this.d1 = d1;
		this.d2 = d2;
	}

	public BufferedImage getImage() {
		return image;
	}

	public Point getDiemTren() {
		return d3;
	}
	
	public Point getDiemDuoi() {
		return d4;
	}
	
	public void paint() {
		d3 = new Point(d2.getX(), d1.getY());
		d4 = new Point(d1.getX(), d2.getY());

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

		paint();
	}

}
