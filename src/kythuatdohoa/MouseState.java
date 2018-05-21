package kythuatdohoa;

import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

public class MouseState implements MouseListener{

	public MouseState()
	
	@Override
	public void mouseClicked(MouseEvent arg0) {
		if (arg0.getButton() == MouseEvent.BUTTON1 && status == POINT) {
			point.setX(arg0.getX());
			point.setY(arg0.getY());
			drawPoint(point, image);
			refreshDrawPlace(image);
			status = 0;
		}
	}

	@Override
	public void mouseReleased(MouseEvent e) {
		if (e.getButton() == MouseEvent.BUTTON1 && status == LINE) {
			BresenhamLine breLine = new BresenhamLine(image, point, new Point(e.getX(), e.getY()));
			breLine.BresenhamLine();
			image = breLine.getImage();
			refreshDrawPlace(image);
			System.out.println("released");
			status = 0;
		}
		if (e.getButton() == MouseEvent.BUTTON1 && status == RECTANGLE) {
			Rectangle rect = new Rectangle(image, point, new Point(e.getX(), e.getY()));
			rect.paint();
			image = rect.getImage();
			refreshDrawPlace(image);
			System.out.println("released");
			status = 0;
		}
		if (e.getButton() == MouseEvent.BUTTON1 && status == SQUARE) {
			Square sq = new Square(image, point, new Point(e.getX(), e.getY()));
			sq.paint();
			image = sq.getImage();
			refreshDrawPlace(image);
			System.out.println("released");
			status = 0;
		}
	}

	@Override
	public void mousePressed(MouseEvent arg0) {
		if (arg0.getButton() == MouseEvent.BUTTON1) {
			if (status == LINE) {
				point.setX(arg0.getX());
				point.setY(arg0.getY());
			}
			if (status == RECTANGLE) {
				point.setX(arg0.getX());
				point.setY(arg0.getY());
			}
			if (status == SQUARE) {
				point.setX(arg0.getX());
				point.setY(arg0.getY());
			}

			if (status == DUONG_TRON) {
				point.setX(arg0.getX());
				point.setY(arg0.getY());
			}
			if (status == ELLIPSE) {
				point.setX(arg0.getX());
				point.setY(arg0.getY());
			}

		}
	}
}
