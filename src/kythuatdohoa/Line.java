package kythuatdohoa;

import java.awt.image.BufferedImage;

public class Line {
	private Point d1 = new Point();
	private Point d2 = new Point();
	private Point dv = new Point();
	private BufferedImage image;

	public Line(BufferedImage image, Point d1, Point d2) {
		this.image = image;
		this.d1 = d1;
		this.d2 = d2;
	}

	public void DDA() {
		int dx, dy, steps;
		float x_inc = 0, y_inc = 0, x = 0, y = 0;

		dx = d2.getX() - d1.getX();
		dy = d2.getY() - d1.getY();
		if (Math.abs(dx) > Math.abs(dy)) {
			steps = Math.abs(dx);

		}
		else {
			steps = Math.abs(dy);

		}

		x_inc = (float) dx / steps;
		y_inc = (float) dy / steps;
		x = d1.getX();
		y = d1.getY();
		Main.drawPoint(d1, image);

		for (int k = 1; k <= steps; k++) {
			x = x + x_inc;
			y = y + y_inc;
			dv = new Point(Math.round(x), Math.round(y));
			Main.drawPoint(dv, image);

		}

	}

	public void DDAxoa() {

		int dx, dy, steps;
		float x_inc = 0, y_inc = 0, x = 0, y = 0;

		dx = d2.getX() - d1.getX();
		dy = d2.getY() - d1.getY();
		if (Math.abs(dx) > Math.abs(dy)) {
			steps = Math.abs(dx);

		}

		else {
			steps = Math.abs(dy);

		}

		x_inc = (float) dx / steps;
		y_inc = (float) dy / steps;
		x = d1.getX();
		y = d1.getY();
		Main.drawPoint(d1, image);

		for (int k = 1; k <= steps; k++) {
			x = x + x_inc;
			y = y + y_inc;
			dv = new Point(Math.round(x), Math.round(y));
			Main.drawPoint(dv, image);

		}

	}

	public BufferedImage getImage() {
		return image;
	}

	void MidpointLine() {

		int Dx = Math.abs(d2.getX() - d1.getX());
		int Dy = Math.abs(d2.getY() - d1.getY());
		
		if (Dx > Dy) {

			if (d1.getX() > d2.getX()) {
				dv = d1;
				d1 = d2;
				d2 = dv;
			}
			int x = d1.getX();
			int y = d1.getY();
			Main.drawPoint(d1, image);
			float D = Dy - (Dx / 2); 
			while (x <= d2.getX()) {
				x++;
				if (D < 0) {
					D = D + Dy;
				} else {
					if (d1.getY() > d2.getY()) {
						y = y - 1;
						D = D + (Dy - Dx);
					} else {
						y = y + 1;
						D = D + (Dy - Dx);
					}

				}
				dv = new Point(x, y);
				Main.drawPoint(dv, image);
			}
		} else {
			
			if (d1.getY() > d2.getY()) {
				dv = d1;
				d1 = d2;
				d2 = dv;
			}
			
			int x = d1.getX();
			int y = d1.getY();
			Main.drawPoint(d1, image);
			float D = Dx - (Dy / 2); // ~ float D = Dy - Dx/2;
			while (y <= d2.getY()) {
				y++;
				if (D < 0) {
					D = D + Dx;
				} else {
					if (d1.getX() > d2.getX()) x = x - 1;
					else x = x + 1;
						D = D + (Dx - Dy);
				}
				dv = new Point(x, y);
				Main.drawPoint(dv, image);
			}
		}
	}

	void XoaMidpointLine() {

		int Dx = Math.abs(d2.getX() - d1.getX());
		int Dy = Math.abs(d2.getY() - d1.getY());
		if (Dx > Dy) {

			if (d1.getX() > d2.getX()) {
				dv = d1;
				d1 = d2;
				d2 = dv;
			}
			int x = d1.getX();
			int y = d1.getY();
			Main.drawPoint(d1, image);
			float D = Dy - (Dx / 2); // ~ float D = Dy - Dx/2;
			while (x <= d2.getX()) {
				x++;
				if (D < 0) {
					D = D + Dy;
				} else {
					if (d1.getY() > d2.getY()) {
						y = y - 1;
						D = D + (Dy - Dx);
					} else {
						y = y + 1;
						D = D + (Dy - Dx);
					}

				}
				dv = new Point(x, y);
				Main.drawPoint(dv, image);
			}
		} else {
			if (d1.getY() > d2.getY()) {
				dv = d1;
				d1 = d2;
				d2 = dv;
			}
			int x = d1.getX();
			int y = d1.getY();
			Main.drawPoint(d1, image);
			float D = Dx - (Dy /2 ); // ~ float D = Dy - Dx/2;
			while (y <= d2.getY()) {
				y++;
				if (D < 0) {
					D = D + Dx;
				} else {
					if (d1.getX() > d2.getX()) x = x - 1;
					else x = x + 1;
						D = D + (Dx - Dy);
					

				}
				dv = new Point(x, y);
				Main.drawPoint(dv, image);
			}
		}
	} 

	public void Bres_Line() {
		int dx, dy, x, y, P, const1, const2;
		dx = Math.abs(d2.getX() - d1.getX());
		dy = Math.abs(d2.getY() - d1.getY());
		if (dx > dy) 
			{
			if (d1.getX() > d2.getX()) {
				Point dz = d1;
				d1 = d2;
				d2 = dz;
			}
			x = d1.getX();
			y = d1.getY();
			P = 2 *dy - dx; // p0
			const1 = 2 * dy;//p<0             p(i+1)= pi + 2dy - 2dx(y(i+1)-yi)
			const2 = 2 * (dy - dx);//p>0
			dv = new Point(x, y);
			Main.drawPoint(dv, image);
			while (x <= d2.getX()) {
				x++;
				if (P < 0) {
					P = P + const1;
				} else {
					if (d1.getY() > d2.getY())
						y = y - 1;
					else
						y = y + 1;
					P = P + const2;
				}
				dv = new Point(x, y);
				Main.drawPoint(dv, image);
			}

		} else {
			if (d1.getY() > d2.getY()) {
				Point dz = d1;
				d1 = d2;
				d2 = dz;
			}
			int da = dx;
			dx = dy;
			dy = da;
				
			x = d1.getX();
			y = d1.getY();
			P = 2 * dy - dx;
			const1 = 2 * dy;
			const2 = 2 * (dy - dx);
			dv = new Point(x, y);
			Main.drawPoint(dv, image);
			while (y <= d2.getY()) {
				y++;
				if (P < 0) {
					P = P + const1;
				} else {
					if (d1.getX() > d2.getX())
						x = x - 1;
					else
						x = x + 1;
					P = P + const2;
				}
				dv = new Point(x, y);
				Main.drawPoint(dv, image);
			}
		}
	}

	public void XoaBres_Line() {
		int dx, dy, x, y, P, const1, const2;
		dx = Math.abs(d2.getX() - d1.getX());
		dy = Math.abs(d2.getY() - d1.getY());
		if (dx > dy) {
			if (d1.getX() > d2.getX()) {
				Point dz = d1;
				d1 = d2;
				d2 = dz;
			}
			x = d1.getX();
			y = d1.getY();
			P = 2 * dy - dx;
			const1 = 2 * dy;
			const2 = 2 * (dy - dx);
			dv = new Point(x, y);
			Main.drawPoint(dv, image);
			while (x <= d2.getX()) {
				x++;
				if (P < 0) {
					P = P + const1;
				} else {
					if (d1.getY() > d2.getY())
						y = y - 1;
					else
						y = y + 1;
					P = P + const2;
				}
				dv = new Point(x, y);
				Main.drawPoint(dv, image);
			}

		} else {
			if (d1.getY() > d2.getY()) {
				Point dz = d1;
				d1 = d2;
				d2 = dz;
			}
				int da = dx;
				dx = dy;
				dy = da;
			

			x = d1.getX();
			y = d1.getY();
			P = 2 *dy - dx;
			const1 = 2 * dy;
			const2 = 2 * (dy - dx);
			dv = new Point(x, y);
			Main.drawPoint(dv, image);
			while (y <= d2.getY()) {
				y++;
				if (P < 0) {
					P = P + const1;
				} else {
					if (d1.getX() > d2.getX())
						x = x - 1;
					else
						x = x + 1;
					P = P + const2;
				}
				dv = new Point(x, y);
				Main.drawPoint(dv, image);
			}
		}

	}

}
