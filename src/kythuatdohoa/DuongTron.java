package kythuatdohoa;

import java.awt.image.BufferedImage;
import java.util.ArrayList;

import kythuatdohoa.Point;

public class DuongTron extends Shape{
	private Point tam = new Point();
	private int R;

	public DuongTron(Point tam, int r) {
		this.tam = tam;
		R = r;
	}

<<<<<<< HEAD
	public int getR() {
		return R;
	}

	public void setR(int r) {
		R = r;
	}

	public ArrayList<Point> getPoints() {
		return points;
	}

	public void setCircle(ArrayList<Point> points) {
		this.points = points;
	}

	public Point getTam() {
		return tam;
	}

	public void setTam(Point tam) {
		this.tam = tam;
	}
	
=======
>>>>>>> 2043c6c5f79b7cfffa16c17740c948e1d584376b
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
		D = new Point(tam.getX() + y, tam.getY() + x);
		if (D.getX() > 0 && D.getX() < 640 && D.getY() > 0 && D.getY() < 480) {
			points.add(D);
		}
		D = new Point(tam.getX() - y, tam.getY() + x);
		if (D.getX() > 0 && D.getX() < 640 && D.getY() > 0 && D.getY() < 480) {
			points.add(D);
		}
		D = new Point(tam.getX() + y, tam.getY() - x);
		if (D.getX() > 0 && D.getX() < 640 && D.getY() > 0 && D.getY() < 480) {
			points.add(D);
		}
		D = new Point(tam.getX() - y, tam.getY() - x);
		if (D.getX() > 0 && D.getX() < 640 && D.getY() > 0 && D.getY() < 480) {
			points.add(D);
		}

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
	
	public int getR() {
		return R;
	}

	public void setR(int r) {
		R = r;
	}


	public void setCircle(ArrayList<Point> circle) {
		this.circle = circle;
	}

	public Point getTam() {
		return tam;
	}

	public void setTam(Point tam) {
		this.tam = tam;
	}
	
	

	

<<<<<<< HEAD
=======
	public BufferedImage getImage() {
		return image;
	}

	public void setImage(BufferedImage image) {
		this.image = image;
	}
	
	public ArrayList<Point> getCircle(){
		return circle;
	}

>>>>>>> 2043c6c5f79b7cfffa16c17740c948e1d584376b
	public void doiXung(Point p) {
		int trx1, try1;
		trx1 = (tam.getX() - p.getX());
		try1 = (tam.getY() - p.getY());

		p.setX(p.getX() -  trx1);
		p.setY(p.getY() -  try1);

		DuongTron dt = new DuongTron(p, R);
		dt.duongtronMid();
	}

	@Override
	public void drawShape(BufferedImage image) {
		this.duongtronMid();
		for(Point point: points) {
			Main.drawPoint(point, image);
		}
	}

}
