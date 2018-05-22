package kythuatdohoa;

import java.awt.image.BufferedImage;
import java.util.ArrayList;

public class Ellipse {
	private BufferedImage image;
	private Point tam = new Point();
	private int bankinhNho;
	private int bankinhLon;
	public ArrayList<Point> ellipse;

	public Ellipse(BufferedImage image, Point tam, int bankinhNho, int bankinhLon) {
	
		this.image = image;
		this.tam = tam;
		this.bankinhNho = bankinhNho;
		this.bankinhLon = bankinhLon;
		ellipse = new ArrayList<>();

	}


	public void Doixung(Point tam, int x, int y) {
		Point D;
		D = new Point(tam.getX() + x, tam.getY() + y);
		if (D.getX() > 0 && D.getX() < 640 && D.getY() > 0 && D.getY() < 480) {
			ellipse.add(D);
			Main.drawPoint(D, image);
		}
		D = new Point(tam.getX() - x, tam.getY() + y);
		if (D.getX() > 0 && D.getX() < 640 && D.getY() > 0 && D.getY() < 480) {
			ellipse.add(D);
			Main.drawPoint(D, image);
		}
		D = new Point(tam.getX() + x, tam.getY() - y);
		if (D.getX() > 0 && D.getX() < 640 && D.getY() > 0 && D.getY() < 480) {
			ellipse.add(D);
			Main.drawPoint(D, image);
		}
		D = new Point(tam.getX() - x, tam.getY() - y);
		if (D.getX() > 0 && D.getX() < 640 && D.getY() > 0 && D.getY() < 480) {
			ellipse.add(D);
			Main.drawPoint(D, image);
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

	public BufferedImage getImage() {
		return image;
	}

	public void setImage(BufferedImage image) {
		this.image = image;
	}
	
	public ArrayList<Point> getEllipse(){
		return ellipse;
	}
	public void doiXung(Point p) {
		int trx1, try1;
		trx1 = (tam.getX() - p.getX());
		try1 = (tam.getY() - p.getY());

		p.setX(p.getX() -  trx1);
		p.setY(p.getY() -  try1);

		Ellipse e = new Ellipse(image, p, bankinhNho, bankinhLon);
		e.ellipseBre();
	}
}
