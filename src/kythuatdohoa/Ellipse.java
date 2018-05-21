package kythuatdohoa;

import java.awt.image.BufferedImage;

public class Ellipse {
	private BufferedImage image;

	public Ellipse(BufferedImage image) {
		this.image = image;
	}

	public void Doixung(Point P, int x, int y) {
		Point D;

		D = new Point(P.getX() + x, P.getY() + y);
		if (D.getX() > 0 && D.getX() < 640 && D.getY() > 0 && D.getY() < 480)
			Main.drawPoint(D, image);
		D = new Point(P.getX() - x, P.getY() + y);
		if (D.getX() > 0 && D.getX() < 640 && D.getY() > 0 && D.getY() < 480)
			Main.drawPoint(D, image);
		D = new Point(P.getX() + x, P.getY() - y);
		if (D.getX() > 0 && D.getX() < 640 && D.getY() > 0 && D.getY() < 480)
			Main.drawPoint(D, image);
		D = new Point(P.getX() - x, P.getY() - y);
		if (D.getX() > 0 && D.getX() < 640 && D.getY() > 0 && D.getY() < 480)
			Main.drawPoint(D, image);
	}
	public void ellipseBre(Point P, int a, int b) {
		int x, y, dx, dy, rx, ry, p;
		rx = a * a;
		ry = b * b;
		x = 0;
		y = b;
		dx = 0;
		dy = (rx << 1) * y;
		Doixung(P, x, y);
		p = (int) Math.round(ry - (rx * b) + (0.25 * rx));
		while (dx < dy) {
			x++;
			dx += ry << 1;
			if (p < 0)
				p += dx + ry;
			else {
				y--;
				dy -= rx << 1;
				p += ry + dx - dy;
			}
			Doixung(P, x, y);
		}
		p = (int) Math.round(ry * (x + 0.5) * (x + 0.5) + rx * (y - 1) * (y - 1) - rx * ry);
		while (y > 0) {
			y--;
			dy -= rx << 1;
			if (p > 0)
				p += rx - dy;
			else {
				x++;
				dx += ry << 1;
				p += dx + rx - dy;
			}
			Doixung(P, x, y);
		}

	}

	public BufferedImage getImage() {
		return image;
	}

	public void setImage(BufferedImage image) {
		this.image = image;
	}
	
	public void doiXung(Point p, Point P, int a, int b) {
		int trx1, try1;
		trx1 = (p.getX() - P.getX());
		try1 = (p.getY() - P.getY());

		P.setX(P.getX() - 2 * trx1);
		P.setY(P.getY() - 2 * try1);

		Ellipse e = new Ellipse(image);
		e.ellipseBre(P, a, b);
	}
}
