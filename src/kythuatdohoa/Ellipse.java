package kythuatdohoa;

import java.awt.image.BufferedImage;

public class Ellipse {
	private BufferedImage image;
	private Point tam = new Point();
	private int bankinhNho;
	private int bankinhLon;

	public Ellipse(BufferedImage image, Point tam, int bankinhNho, int bankinhLon) {
	
		this.image = image;
		this.tam = tam;
		this.bankinhNho = bankinhNho;
		this.bankinhLon = bankinhLon;
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
	}

	// public void ellipseMid(Point tam, int a, int b) {
	// int x, y, fx, fy, a2, b2, tam;
	// x = 0;
	// y = b;
	// a2 = a * a;
	// b2 = b * b;
	// fx = 0;
	// fy = 2 * a2 * y;
	// Doixung(tam, x, y);
	// tam = (int) Math.round(b2 - (a2 * b) + (0.25 * a));
	// while (fx < fy) {
	// x++;
	// fx += 2 * b2;
	// if (tam < 0) {
	// tam += b2 * (2 * x + 3);
	// } else {
	// y--;
	// tam += b2 * (2 * x + 3) + a2 * (-2 * y + 2);
	// fy -= 2 * a2;
	// }
	// Doixung(tam, x, y);
	//
	// }
	// tam = (int) Math.round(b2*(x + 0.5)*(x+0.5)+a2*(y-1)-a2*b2);
	// while(y>0) {
	// y--;
	// fy -= 2*a2;
	// if(tam>=0) {
	// tam+= a2*(3-2*y);
	// }else {
	// x++;
	// fx += 2*b2;
	// tam+= b2*(2*x+2)+a2*(-2*y +3);
	// }
	// Doixung(tam, x, y);
	// }
	//
	// }

	public void ellipseBre() {
		int x, y, dx, dy, rx, ry, tam;
		rx = bankinhNho * bankinhNho;
		ry = bankinhLon * bankinhLon;
		x = 0;
		y = bankinhLon;
		dx = 0;
		dy = (rx << 1) * y;
		Doixung(this.tam, x, y);
		tam = (int) Math.round(ry - (rx * bankinhLon) + (0.25 * rx));
		while (dx < dy) {
			x++;
			dx += ry << 1;
			if (tam < 0) {
				tam += dx + ry;

			} else {
				y--;
				dy -= rx << 1;
				tam += ry + dx - dy;
			}
			Doixung(this.tam, x, y);
		}
		tam = (int) Math.round(ry * (x + 0.5) * (x + 0.5) + rx * (y - 1) * (y - 1) - rx * ry);
		while (y > 0) {
			y--;
			dy -= rx << 1;
			if (tam > 0)
				tam += rx - dy;
			else {
				x++;
				dx += ry << 1;
				tam += dx + rx - dy;
			}
			Doixung(this.tam, x, y);
		}

	}
	
	public Point getPoint() {
		return tam;
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

		Ellipse e = new Ellipse(image, tam, bankinhNho, bankinhLon);
		e.ellipseBre();
	}
}
