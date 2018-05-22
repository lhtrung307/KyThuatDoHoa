package kythuatdohoa;

import java.awt.image.BufferedImage;
import java.util.ArrayList;

public class BresenhamLine {
	private Point d1 = new Point();
	private Point d2 = new Point();
	private BufferedImage image;
	private ArrayList<Point> line;

	public BresenhamLine(BufferedImage image, Point d1, Point d2) {
		this.image = image;
		this.d1 = d1;
		this.d2 = d2;
		line = new ArrayList<>();
	}
	
	public BresenhamLine(Point d1, Point d2) {
		this.d1 = d1;
		this.d2 = d2;
	}

	public BufferedImage getImage() {
		return image;
	}

	public void BresenhamLine() {
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
				Point dv = new Point(x, y);
				line.add(dv);
				Main.drawPoint(dv, image);
			}
		}else {
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
			
			while (y <= d2.getY()) {
				y++;
				if (p < 0) {
					p = p + 2*dy;
				} else {
					if (d1.getX() > d2.getX())
						x = x - 1;
					else
						x = x + 1;
					p = p + 2*(dy-dx);
				}
				Point dv = new Point(x, y);
				line.add(dv);
				Main.drawPoint(dv, image);
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

			while (x != d2.getX()) {
				x++; k++;
				if (p < 0) {
					p = p + 2 * dy;
				} else {
					p = p + 2 * (dy - dx);
					if (d1.getY() > d2.getY())
						y--;
					else
						y++;
				}
				if(k <= 5) {
					Point dv = new Point(x, y);
					line.add(dv);
					Main.drawPoint(dv, image);
				}else {
					Point dv = new Point(x, y);
					line.add(dv);
				}
				if(k == 10)
					k=0;
				
			}
		}else {
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
			
			while (y <= d2.getY()) {
				y++; k++;
				if (p < 0) {
					p = p + 2*dy;
				} else {
					if (d1.getX() > d2.getX())
						x = x - 1;
					else
						x = x + 1;
					p = p + 2*(dy-dx);
				}
				if(k <= 5) {
					Point dv = new Point(x, y);
					line.add(dv);
					Main.drawPoint(dv, image);
				}else {
					Point dv = new Point(x, y);
					line.add(dv);
				}
				if(k == 10)
					k=0;
				
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
		return line;
	}

	public void setLine(ArrayList<Point> line) {
		this.line = line;
	}
	
	
}
