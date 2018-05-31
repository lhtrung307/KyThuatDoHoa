package kythuatdohoa;

import java.awt.image.BufferedImage;

public class Line extends Shape{
	private Point d1 = new Point();
	private Point d2 = new Point();
	private Point dv = new Point();

	public Line(Point d1, Point d2) {
		this.d1 = d1;
		this.d2 = d2;
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
//			Main.drawPoint(d1, image);
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
//				Main.drawPoint(dv, image);
			}
		} else {
			
			if (d1.getY() > d2.getY()) {
				dv = d1;
				d1 = d2;
				d2 = dv;
			}
			
			int x = d1.getX();
			int y = d1.getY();
//			Main.drawPoint(d1, image);
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
//				Main.drawPoint(dv, image);
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
//			Main.drawPoint(d1, image);
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
//				Main.drawPoint(dv, image);
			}
		} else {
			if (d1.getY() > d2.getY()) {
				dv = d1;
				d1 = d2;
				d2 = dv;
			}
			int x = d1.getX();
			int y = d1.getY();
//			Main.drawPoint(d1, image);
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
//				Main.drawPoint(dv, image);
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
			points.add(dv);
//			Main.drawPoint(dv, image);
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
				points.add(dv);
//				Main.drawPoint(dv, image);
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
			points.add(dv);
//			Main.drawPoint(dv, image);
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
				points.add(dv);
//				Main.drawPoint(dv, image);
			}
		}
	}
	
	public void BresLineForCoor() {
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
//			Main.drawPoint(dv, image);
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
//				Main.drawPoint(dv, image);
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
//			Main.drawPoint(dv, image);
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
//				Main.drawPoint(dv, image);
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
//			Main.drawPoint(dv, image);
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
//				Main.drawPoint(dv, image);
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
//			Main.drawPoint(dv, image);
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
//				Main.drawPoint(dv, image);
			}
		}

	}

	@Override
	public void drawShape(BufferedImage image) {
		this.Bres_Line();
		for (Point point : points) {
			Main.drawPoint(point, image);
		}
		
	}


	@Override
	public void scale(double sx, double sy) {
		// TODO Auto-generated method stub
		
	}

}
