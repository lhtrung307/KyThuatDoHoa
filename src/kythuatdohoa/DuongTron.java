package kythuatdohoa;

import java.awt.image.BufferedImage;

import kythuatdohoa.Point;

public class DuongTron {
	private BufferedImage image;

	public DuongTron(BufferedImage image) {
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
		D = new Point(P.getX() + y, P.getY() + x);
		if (D.getX() > 0 && D.getX() < 640 && D.getY() > 0 && D.getY() < 480)
			Main.drawPoint(D, image);
		D = new Point(P.getX() - y, P.getY() + x);
		if (D.getX() > 0 && D.getX() < 640 && D.getY() > 0 && D.getY() < 480)
			Main.drawPoint(D, image);
		D = new Point(P.getX() + y, P.getY() - x);
		if (D.getX() > 0 && D.getX() < 640 && D.getY() > 0 && D.getY() < 480)
			Main.drawPoint(D, image);
		D = new Point(P.getX() - y, P.getY() - x);
		if (D.getX() > 0 && D.getX() < 640 && D.getY() > 0 && D.getY() < 480)
			Main.drawPoint(D, image);

	}

	public void veduongtron(Point P, int R) {
		int x;
		int y;
		for (x = 0; x <= Math.round(R * Math.sqrt(2) / 2); x++) {
			y = (int) Math.round(Math.sqrt(R * R - x * x));
			Doixung(P, x, y);
		}
	}
	public void duongtronBre(Point P, int R) {
		int x, y, p;
		x = 0;
		y = R;
		p = 3-2*R;
		while (x<=y) {
			Doixung(P, x, y);
			if (p < 0)
				p = p + 4 * x + 6;
			else {
				p = p + 4 * (x - y) + 10;
				y = y - 1;
			}
			x = x + 1;
		}
	}

	public BufferedImage getImage() {
		return image;
	}

	public void setImage(BufferedImage image) {
		this.image = image;
	}
	
	
}
