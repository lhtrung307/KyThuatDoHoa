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

	// public void ellipseMid(Point P, int a, int b) {
	// int x, y, fx, fy, a2, b2, p;
	// x = 0;
	// y = b;
	// a2 = a * a;
	// b2 = b * b;
	// fx = 0;
	// fy = 2 * a2 * y;
	// Doixung(P, x, y);
	// p = (int) Math.round(b2 - (a2 * b) + (0.25 * a));
	// while (fx < fy) {
	// x++;
	// fx += 2 * b2;
	// if (p < 0) {
	// p += b2 * (2 * x + 3);
	// } else {
	// y--;
	// p += b2 * (2 * x + 3) + a2 * (-2 * y + 2);
	// fy -= 2 * a2;
	// }
	// Doixung(P, x, y);
	//
	// }
	// p = (int) Math.round(b2*(x + 0.5)*(x+0.5)+a2*(y-1)-a2*b2);
	// while(y>0) {
	// y--;
	// fy -= 2*a2;
	// if(p>=0) {
	// p+= a2*(3-2*y);
	// }else {
	// x++;
	// fx += 2*b2;
	// p+= b2*(2*x+2)+a2*(-2*y +3);
	// }
	// Doixung(P, x, y);
	// }
	//
	// }

	public void ellipseBre() {
		int x, y, dx, dy, rx, ry, p;
		rx = bankinhNho * bankinhNho;
		ry = bankinhLon * bankinhLon;
		x = 0;
		y = bankinhLon;
		dx = 0;
		dy = (rx << 1) * y;
		Doixung(tam, x, y);
		p = (int) Math.round(ry - (rx * bankinhLon) + (0.25 * rx));
		while (dx < dy) {
			x++;
			dx += ry << 1;
			if (p < 0) {
				p += dx + ry;

			} else {
				y--;
				dy -= rx << 1;
				p += ry + dx - dy;
			}
			Doixung(tam, x, y);
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
			Doixung(tam, x, y);
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
}
