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

	public void Doixung(Point tam, int x, int y) {
		Point D;

		D = new Point(tam.getX() + x, tam.getY() + y);
		if (D.getX() > 0 && D.getX() < 640 && D.getY() > 0 && D.getY() < 480)
			Main.drawPoint(D, image);
		D = new Point(tam.getX() - x, tam.getY() + y);
		if (D.getX() > 0 && D.getX() < 640 && D.getY() > 0 && D.getY() < 480)
			Main.drawPoint(D, image);
		D = new Point(tam.getX() + x, tam.getY() - y);
		if (D.getX() > 0 && D.getX() < 640 && D.getY() > 0 && D.getY() < 480)
			Main.drawPoint(D, image);
		D = new Point(tam.getX() - x, tam.getY() - y);
		if (D.getX() > 0 && D.getX() < 640 && D.getY() > 0 && D.getY() < 480)
			Main.drawPoint(D, image);
		D = new Point(tam.getX() + y, tam.getY() + x);
		if (D.getX() > 0 && D.getX() < 640 && D.getY() > 0 && D.getY() < 480)
			Main.drawPoint(D, image);
		D = new Point(tam.getX() - y, tam.getY() + x);
		if (D.getX() > 0 && D.getX() < 640 && D.getY() > 0 && D.getY() < 480)
			Main.drawPoint(D, image);
		D = new Point(tam.getX() + y, tam.getY() - x);
		if (D.getX() > 0 && D.getX() < 640 && D.getY() > 0 && D.getY() < 480)
			Main.drawPoint(D, image);
		D = new Point(tam.getX() - y, tam.getY() - x);
		if (D.getX() > 0 && D.getX() < 640 && D.getY() > 0 && D.getY() < 480)
			Main.drawPoint(D, image);

	}

	// public void veduongtron(Point tam, int R) {
	// int x;
	// int y;
	// for (x = 0; x <= Math.round(R * Math.sqrt(2) / 2); x++) {
	// y = (int) Math.round(Math.sqrt(R * R - x * x));
	// Doixung(tam, x, y);
	// }
	// }
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
	
	public void doiXung(Point tam) {
		int trx1, try1;
		trx1 = (tam.getX() - tam.getX());
		try1 = (tam.getY() - tam.getY());

		tam.setX(tam.getX() - 2 * trx1);
		tam.setY(tam.getY() - 2 * try1);

		DuongTron dt = new DuongTron(image, tam, R);
		dt.duongtronMid();
	}

}
