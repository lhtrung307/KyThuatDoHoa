package kythuatdohoa;

import java.awt.image.BufferedImage;

import kythuatdohoa.Point;

public class DuongTron {
	private BufferedImage image;
	private Point tam = new Point();
	private int R;

	public DuongTron(BufferedImage image, Point tam, int r) {
		this.image = image;
		this.tam = tam;
		R = r;
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

	
	public void duongtronMid() {
		int x, y, d;
		x = 0;
		y = R;
		d = 1 - R;
		while (x <= y) {
			Doixung(tam, x, y);
			if (d < 0)
				d += 2 * x + 3;
			else {
				d += 2 * (x - y) + 5;
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
