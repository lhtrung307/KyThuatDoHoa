package kythuatdohoa;

import java.awt.Color;
import java.awt.image.BufferedImage;

public class Point extends Shape{
	private int x;
	private int y;

	public Point(int x, int y) {
		setX(x);
		setY(y);
	}

	public Point() {
		x = 0;
		y = 0;
	}

	public int getX() {
		return x;
	}

	public void setX(int x) {
		if (x < 0) {
			this.x = 0;
		} else if (x > Main.SCR_WIDTH - 1) {
			this.x = Main.SCR_WIDTH - 1;
		}
		this.x = x;
	}

	public int getY() {
		return y;
	}

	public void setY(int y) {
		if (y < 0) {
			this.y = 0;
		} else if (y > Main.SCR_HEIGHT - 1) {
			this.y = Main.SCR_HEIGHT - 1;
		}
		this.y = y;
	}

	public String toString() {
		return x + " " + y;
	}

	public static int distance(Point p1, Point p2) {
		return (int) Math.sqrt(
				(p2.getX() - p1.getX()) * (p2.getX() - p1.getX()) + (p2.getY() - p1.getY()) * (p2.getY() - p1.getY()));
	}

	public void paint(BufferedImage iImage, String mau) {
		Color c;
		if (mau == "BIEN")
			c = new Color(230, 0, 0, 255);
		else if (mau == "BLACK")
			c = new Color(0, 0, 0, 255);
		else if (mau == "RED")
			c = new Color(255, 0, 0, 255);
		else if (mau == "OXY")
			c = new Color(1, 1, 1, 255);
		else if (mau == "GREEN")
			c = new Color(0, 255, 0, 255);
		else if (mau == "BLUE")
			c = new Color(0, 0, 255, 255);
		else
			c = new Color(255, 255, 255, 255);
		int k = c.getRGB();
		iImage.setRGB(x, y, k);
	}

	public String getPointRGB(BufferedImage iImage) {
		int pixel = iImage.getRGB(x, y);
		int alpha = (pixel >> 24) & 0xff;
		int r = (pixel >> 16) & 0xff;
		int g = (pixel >> 8) & 0xff;
		int b = (pixel) & 0xff;
		if (r == 255 && g == 0 & b == 0)
			return ("RED");
		if (r == 230 && g == 0 & b == 0)
			return ("BIEN");
		if (r == 0 && g == 0 & b == 0)
			return ("BLACK");
		if (r == 1 && g == 1 & b == 1)
			return ("OXY");
		if (r == 255 && g == 255 & b == 255)
			return ("WHITE");
		if (r == 0 && g == 255 & b == 0)
			return ("GREEN");
		if (r == 0 && g == 0 & b == 255)
			return ("BLUE");
		return "t";
	}
	
	public void translateCoordinateToReal() {
		if(this.getX()  > (Main.SCR_WIDTH - 2) / 2) {
			this.setX(Main.SCR_WIDTH - 2);
		}
		else if(this.getX() < -(Main.SCR_WIDTH - 2) / 2) {
			this.setX(0);
		}
		else
			this.setX(this.getX() + (Main.SCR_WIDTH - 2) / 2);
		if(this.getY() > (Main.SCR_HEIGHT - 2) / 2) {
			this.setY(Main.SCR_HEIGHT - 2);
		}else if(this.getY() < -(Main.SCR_HEIGHT - 2) / 2) {
			this.setY(0);
		}
		else
			this.setY(this.getY() + (Main.SCR_HEIGHT - 2) / 2);

	}
	

	public void translateRealToCoordinate() {
		this.setX(this.getX() - (Main.SCR_WIDTH - 2) / 2);
		this.setY(this.getY() - (Main.SCR_HEIGHT - 2) / 2);
	}

	@Override
	public void drawShape(BufferedImage image) {
		// TODO Auto-generated method stub
		
	}
}
