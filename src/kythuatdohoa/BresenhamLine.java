package kythuatdohoa;

import java.awt.image.BufferedImage;
import java.util.ArrayList;

public class BresenhamLine extends Shape<BresenhamLine> {
	private Point d1 = new Point();
	private Point d2 = new Point();

	public BresenhamLine(Point d1, Point d2) {
		this.d1 = d1;
		this.d2 = d2;
	}

	public void drawLine() {
		int x, y, dx, dy, p;
		dx = Math.abs(d1.getX() - d2.getX());
		dy = Math.abs(d1.getY() - d2.getY());
		if (dx > dy) {
			if (d1.getX() > d2.getX()) {
				Point temp = d1;
				d1 = d2;
				d2 = temp;
			}
			x = d1.getX();
			y = d1.getY();
			p = 2 * dy - dx;
			Point dv = new Point(x, y);
			points.add(dv);
			while (x != d2.getX()) {
				x++;
				if (p < 0) {
					p = p + 2 * dy;
				} else {
					p = p + 2 * (dy - dx);
					if (d1.getY() > d2.getY())
						y--;
					else
						y++;
				}
				dv = new Point(x, y);
				points.add(dv);
			}
		} else {
			if (d1.getY() > d2.getY()) {
				Point temp = d1;
				d1 = d2;
				d2 = temp;
			}
			int tmp = dx;
			dx = dy;
			dy = tmp;
			x = d1.getX();
			y = d1.getY();
			p = 2 * dy - dx;
			Point dv = new Point(x, y);
			points.add(dv);
			while (y < d2.getY()) {
				y++;
				if (p < 0) {
					p = p + 2 * dy;
				} else {
					if (d1.getX() > d2.getX())
						x = x - 1;
					else
						x = x + 1;
					p = p + 2 * (dy - dx);
				}
				dv = new Point(x, y);
				points.add(dv);
			}
		}
	}

	public void duongDutNet() {
		int x, y, dx, dy, p;
		int k = 0;
		dx = Math.abs(d1.getX() - d2.getX());
		dy = Math.abs(d1.getY() - d2.getY());
		if (dx > dy) {
			if (d1.getX() > d2.getX()) {
				Point temp = d1;
				d1 = d2;
				d2 = temp;
			}
			x = d1.getX();
			y = d1.getY();
			p = 2 * dy - dx;
			Point dv = new Point(x, y);
			points.add(dv);
			while (x != d2.getX()) {
				x++;
				k++;
				if (p < 0) {
					p = p + 2 * dy;
				} else {
					p = p + 2 * (dy - dx);
					if (d1.getY() > d2.getY())
						y--;
					else
						y++;
				}

				if (k <= 5) {
					dv = new Point(x, y);
					points.add(dv);
				} else {

					dv = new Point(x, y);
					points.add(dv);
				}
				if (k == 10)
					k = 0;
			}
		} else {
			if (d1.getY() > d2.getY()) {
				Point temp = d1;
				d1 = d2;
				d2 = temp;
			}
			int tmp = dx;
			dx = dy;
			dy = tmp;
			x = d1.getX();
			y = d1.getY();
			p = 2 * dy - dx;
			Point dv = new Point(x, y);
			points.add(dv);
			while (y < d2.getY()) {

				y++;
				k++;

				if (p < 0) {
					p = p + 2 * dy;
				} else {
					if (d1.getX() > d2.getX())
						x = x - 1;
					else
						x = x + 1;
					p = p + 2 * (dy - dx);
				}

				if (k <= 5) {
					dv = new Point(x, y);
					points.add(dv);
				} else {

					dv = new Point(x, y);
					points.add(dv);
				}
				if (k == 10)
					k = 0;
			}
		}
	}

	public Point getD1() {
		return d1;
	}

	public void setD1(Point d1) {
		this.d1 = d1;
	}

	public Point getD2() {
		return d2;
	}

	public void setD2(Point d2) {
		this.d2 = d2;
	}

	public ArrayList<Point> getLine() {
		return points;
	}

	public void setLine(ArrayList<Point> line) {
		this.points = line;
	}

	@Override
	public void drawShape(BufferedImage image) {
		this.drawLine();
		for (Point point : points) {
			Main.drawPoint(point, image);
		}
	}

	@Override
	public void scale(double sx, double sy) {
		d1 = PhepBienDoi.scaling(d1, sx, sy);
		d2 = PhepBienDoi.scaling(d2, sx, sy);
		points.clear();
	}

	@Override
	public BresenhamLine rotation(double theta, Point p) {
		Point d1 = PhepBienDoi.rotation(this.d1, p, theta).clone();
		Point d2 = PhepBienDoi.rotation(this.d2, p, theta).clone();
		return new BresenhamLine(d1, d2);
	}

}