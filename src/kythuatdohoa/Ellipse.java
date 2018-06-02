package kythuatdohoa;

import java.awt.image.BufferedImage;
import java.util.ArrayList;

public class Ellipse extends Shape<Ellipse> {
	private Point tam = new Point();
	private int bankinhNho;
	private int bankinhLon;

	public Ellipse(Point tam, int bankinhNho, int bankinhLon) {
		super();
		this.tam = tam;
		this.bankinhNho = bankinhNho;
		this.bankinhLon = bankinhLon;

	}

	public void Doixung(Point tam, int x, int y) {
		Point D;
		D = new Point(tam.getX() + x, tam.getY() + y);
		if (D.getX() > 0 && D.getX() < 640 && D.getY() > 0 && D.getY() < 480) {
			points.add(D);
		}
		D = new Point(tam.getX() - x, tam.getY() + y);
		if (D.getX() > 0 && D.getX() < 640 && D.getY() > 0 && D.getY() < 480) {
			points.add(D);
		}
		D = new Point(tam.getX() + x, tam.getY() - y);
		if (D.getX() > 0 && D.getX() < 640 && D.getY() > 0 && D.getY() < 480) {
			points.add(D);
		}
		D = new Point(tam.getX() - x, tam.getY() - y);
		if (D.getX() > 0 && D.getX() < 640 && D.getY() > 0 && D.getY() < 480) {
			points.add(D);
		}
	}

	public void ellipseBre() {
		int x, y, dx, dy, rx, ry, p;
		rx = bankinhNho * bankinhNho;
		ry = bankinhLon * bankinhLon;
		x = 0;
		y = bankinhLon;
		dx = 0;
		dy = (rx << 1) * y;
		Doixung(tam, x, y);
		Doixung(this.tam, x, y);
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

		// float p, a2, b2;
		// int x, y;// b ban kinh lon
		// a2 = bankinhNho * bankinhNho;
		// b2 = bankinhLon * bankinhLon;
		// x = 0;
		// y = bankinhLon;
		//
		// p = 2 * (b2 / a2) - (2 * bankinhLon) + 1;
		//
		// // ve nhanh thu 1 tu tren xuong
		//
		// while ((b2 / a2) * x <= y) {
		// Doixung(tam, x, y);
		// if (p < 0) {
		// p = p + 2 * (b2 / a2) * (2 * x + 3);
		// } else {
		// p = p - 4 * y + 2 * (b2 / a2) * (2 * x + 3);
		// y--;
		// }
		// x++;
		// }
		// // ve nhanh thu 2 tu duoi len
		// y = 0;
		// x = bankinhNho;
		// p = 2 * (a2 / b2) - 2 * bankinhNho + 1;
		// while ((a2 / b2) * y <= x) {
		// Doixung(tam, x, y);
		// if (p < 0) {
		// p = p + 2 * (a2 / b2) * (2 * y + 3);
		// } else {
		// p = p - 4 * x + 2 * ((float) a2 / b2) * (2 * y + 3);
		// x = x - 1;
		// }
		// y = y + 1;
		// }

	}

	public Point getPoint() {
		return tam;
	}

	public void doiXung(Point p) {
		int trx1, try1;
		trx1 = (tam.getX() - p.getX());
		try1 = (tam.getY() - p.getY());
		p.setX(p.getX() - trx1);
		p.setY(p.getY() - try1);
		Ellipse e = new Ellipse(p, bankinhNho, bankinhLon);
		e.ellipseBre();
	}

	private void generateRect() {
		Point d2 = new Point(tam.getX() + bankinhNho, tam.getY() + bankinhLon);
		Point d3 = new Point(tam.getX() - bankinhNho, tam.getY() + bankinhLon);
		Point d4 = new Point(tam.getX() + bankinhNho, tam.getY() - bankinhLon);
		Point d1 = new Point(tam.getX() - bankinhNho, tam.getY() - bankinhLon);
		tam.translateRealToCoordinate();
		d1 = PhepBienDoi.scaling(d1, 0.705069124, 0.705069124, tam);
		d2 = PhepBienDoi.scaling(d2, 0.705069124, 0.705069124, tam);
		d3 = PhepBienDoi.scaling(d3, 0.705069124, 0.705069124, tam);
		d4 = PhepBienDoi.scaling(d4, 0.705069124, 0.705069124, tam);
		tam.translateCoordinateToReal();
		Rectangle rect = new Rectangle(d1, d2, d3, d4);
		rect.paint();

		points.addAll(rect.getPoints());
	}

	@Override
	public void drawShape(BufferedImage image) {
		this.ellipseBre();
		for (Point point : points) {
			Main.drawPoint(point, image);
		}
	}

	@Override
	public void scale(double sx, double sy) {
		bankinhLon = Math.round(bankinhLon * (float) sx);
		bankinhNho = Math.round(bankinhNho * (float) sy);
		points.clear();
	}

	@Override
	public Ellipse rotation(double theta, Point p) {
		// Point tamClone = PhepBienDoi.rotation(this.tam, p, theta).clone();
		for (Point point : points) {
			point = point.rotation(theta, p);
//			points.add(temp);
		}
		return this;
	}

	public void setTam(Point tam) {
		this.tam = tam;
	}
}