package kythuatdohoa;

import java.awt.image.BufferedImage;

public class Rectangle {
	private Point d1 = new Point();
	private Point d2 = new Point();
	private BufferedImage image;
	
	public Rectangle(BufferedImage image, Point d1, Point d2) {
		this.image = image;
		this.d1 = d1;
		this.d2 = d2;
	}
	
	public BufferedImage getImage() {
		return image;
	}

	public void paint() {
		Point d3 = new Point(d2.getX(), d1.getY());
		Point d4 = new Point(d1.getX(), d2.getY());
		
		BresenhamLine line1 = new BresenhamLine(image, d1, d3);
		BresenhamLine line2 = new BresenhamLine(image, d1, d4);
		BresenhamLine line3 = new BresenhamLine(image, d2, d3);
		BresenhamLine line4 = new BresenhamLine(image, d2, d4);
		
		line1.BresenhamLine();
		line2.BresenhamLine();
		line3.BresenhamLine();
		line4.BresenhamLine();
	}
}
