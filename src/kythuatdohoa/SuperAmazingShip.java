package kythuatdohoa;

import java.awt.image.BufferedImage;

public class SuperAmazingShip extends Shape<SuperAmazingShip>{
	private BresenhamLine ong1;
	private BresenhamLine ong2;
	private BresenhamLine ong3;
	private BresenhamLine ong4;
	private Ellipse miengOng1;
	private Ellipse miengOng2;
	private Rectangle thatLung;
	private Rectangle tui1;
	private Rectangle tui2;
	public SuperAmazingShip() {
		super();
		ong1 = new BresenhamLine(new Point(96, 72), new Point(75, 175));
		ong1.drawLine();
		points.addAll(ong1.getPoints());
		ong2 = new BresenhamLine(new Point(208, 72), new Point(229, 175));
		ong2.drawLine();
		points.addAll(ong2.getPoints());
		ong3 = new BresenhamLine(new Point(152, 122), new Point(141, 175));
		ong3.drawLine();
		points.addAll(ong3.getPoints());
		ong4 = new BresenhamLine(new Point(152, 122), new Point(163, 175));
		ong4.drawLine();
		points.addAll(ong4.getPoints());
		
		miengOng1 = new Ellipse(new Point(108, 175), 33, 5);
		miengOng1.ellipseBre();
		points.addAll(miengOng1.getPoints());
		miengOng2 = new Ellipse(new Point(196, 175), 33, 5);
		miengOng2.ellipseBre();
		points.addAll(miengOng2.getPoints());
		
		thatLung = new Rectangle(new Point(96, 56), new Point(208, 72));
		thatLung.paint();
		points.addAll(thatLung.getPoints());
		
		tui1 = new Rectangle(new Point(165, 85), new Point(200, 110));
		tui1.paint();
		points.addAll(tui1.getPoints());
		
		tui2 = new Rectangle(new Point(168, 88), new Point(197, 107));
		tui2.paint();
		points.addAll(tui2.getPoints());
	}
	@Override
	public void drawShape(BufferedImage image) {
		for (Point point : points) {
			Main.drawPoint(point, image);
		}
	}
	@Override
	public void scale(double sx, double sy) {
		// TODO Auto-generated method stub
		
	}
	@Override
	public SuperAmazingShip rotation(double theta, Point p) {
		for (Point point : points) {
			point = point.rotation(theta, p);
//			points.add(temp);
		}
		return this;
	}
	
	
}
