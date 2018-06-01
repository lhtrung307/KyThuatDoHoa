package kythuatdohoa;

import java.awt.image.BufferedImage;

public class Ellipse extends Shape{
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

	}
	
	public Point getPoint() {
		return tam;
	}
	
	public void doiXung(Point p) {
		int trx1, try1;
		trx1 = (tam.getX() - p.getX());
		try1 = (tam.getY() - p.getY());
		p.setX(p.getX() -  trx1);
		p.setY(p.getY() -  try1);
		Ellipse e = new Ellipse(p, bankinhNho, bankinhLon);
		e.ellipseBre();
	}
	
	private void generateSquare() {
		Point d1 = new Point(tam.getX() + bankinhNho, tam.getY() + bankinhLon);
		d1.translateRealToCoordinate();
		d1 = PhepBienDoi.scaling(d1, 0.705069124, 0.705069124);
		d1.translateCoordinateToReal();
		Point d2 = new Point(tam.getX() - bankinhNho, tam.getY() + bankinhLon);
		d2.translateRealToCoordinate();
		d2 = PhepBienDoi.scaling(d2, 0.705069124, 0.705069124);
		d2.translateCoordinateToReal();
		Point d3 = new Point(tam.getX() + bankinhNho, tam.getY() - bankinhLon);
		d3.translateRealToCoordinate();
		d3 = PhepBienDoi.scaling(d3, 0.705069124, 0.705069124);
		d3.translateCoordinateToReal();
		Point d4 = new Point(tam.getX() - bankinhNho, tam.getY() - bankinhLon);
		d4.translateRealToCoordinate();
		d4 = PhepBienDoi.scaling(d4, 0.705069124, 0.705069124);
		d4.translateCoordinateToReal();
		BresenhamLine line1 = new BresenhamLine(d1, d2);
		BresenhamLine line2 = new BresenhamLine(d1, d3);
		BresenhamLine line3 = new BresenhamLine(d2, d4);
		BresenhamLine line4 = new BresenhamLine(d3, d4);
		line1.drawLine();
		points.addAll(line1.getPoints());
		line2.drawLine();
		points.addAll(line2.getPoints());
		line3.drawLine();
		points.addAll(line3.getPoints());
		line4.drawLine();
		points.addAll(line4.getPoints());
	}

	@Override
	public void drawShape(BufferedImage image) {
		this.ellipseBre();
		generateSquare();
		for(Point point: points) {
			Main.drawPoint(point, image);
		}
	}

	@Override
	public void scale(double sx, double sy) {
		bankinhLon = Math.round(bankinhLon * (float)sx);
		bankinhNho = Math.round(bankinhNho * (float)sy);
		points.clear();
	}

	@Override
	public void rotation(double theta, Point p) {
		tam.translateRealToCoordinate();
		this.setTam(PhepBienDoi.rotation(tam, p, theta));
		tam.translateCoordinateToReal();
		points.clear();
	}

	public void setTam(Point tam) {
		this.tam = tam;
	}
}
