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
		rx = bankinhNho * bankinhNho;//x^2
		ry = bankinhLon * bankinhLon;// y^2
		x = 0;
		y = bankinhLon;
		dx = 0;
		dy = (rx << 1) * y;
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

	@Override
	public void drawShape(BufferedImage image) {
		this.ellipseBre();
		for(Point point: points) {
			Main.drawPoint(point, image);
		}
	}

	public void scale(double sx, double sy) {
		bankinhLon = Math.round(bankinhLon * (float)sx);
		bankinhNho = Math.round(bankinhNho * (float)sy);
		points.clear();
	
	}
	
	public void rotate(int theta) {
		
	}
}
