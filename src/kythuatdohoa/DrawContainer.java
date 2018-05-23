package kythuatdohoa;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;
import java.awt.image.BufferedImage;
import java.math.BigInteger;
import java.util.Queue;
import java.util.Timer;
import java.util.concurrent.TimeUnit;

import javax.swing.JPanel;

public class DrawContainer extends JPanel implements MouseMotionListener, MouseListener {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	public static int POINT = 1;
	public static int LINE = 2;
	public static int DUONG_TRON = 3;
	public static int ELLIPSE = 4;
	public static int RECTANGLE = 5;
	public static int SQUARE = 6;
	public static int SCALE = 7;
	public static int COLORING = 8;
	private int status;
	private Point point;
	// private BufferedImage image;
	private DrawPlace drawPlace;
	private BufferedImage imageClone;
	public static int size = 20;
	public static int wCell = 20;
	public static int hCell = 10;

	private Ellipse ellipse;

	public DrawContainer() {
		status = 0;
		drawPlace = new DrawPlace();
		drawPlace.setBounds(0, 0, Main.SCR_HEIGHT, Main.SCR_WIDTH);
		this.add(drawPlace);
		// image = drawPlace.getImage();
		point = new Point();
		imageClone = new BufferedImage(drawPlace.getImage().getWidth(), drawPlace.getImage().getHeight(),
				drawPlace.getImage().getType());
		this.addMouseListener(this);
		this.addMouseMotionListener(this);

	}

	@Override
	public void mouseClicked(MouseEvent e) {
		// TODO Auto-generated method stub
		if (e.getButton() == MouseEvent.BUTTON1 && status == POINT) {
			System.out.println("click");
			Point p = new Point(e.getX(), e.getY());
			Main.drawPoint(p, drawPlace.getImage());
			drawPlace.refreshDrawPlace(drawPlace.getImage());
			status = 0;
		}
		// while(true) {
		// for(int i = 1; i < 360; i++) {
		// Cube3D cube = new Cube3D();
		// cube.rotateY3D(i);
		// cube.rotateX3D(i);
		// imageClone.setData(drawPlace.getImage().getRaster());
		// cube.setImage(imageClone);
		// cube.drawCube();
		// drawPlace.refreshDrawPlace(cube.getImage());
		// try {
		// Thread.sleep(1000);
		// } catch (InterruptedException e1) {
		// System.out.println(e1);
		// }
		// System.out.println("wow");
		// }
		// }
	}

	@Override
	public void mouseEntered(MouseEvent e) {
		// TODO Auto-generated method stub

	}

	@Override
	public void mouseExited(MouseEvent e) {
		// TODO Auto-generated method stub

	}

	@Override
	public void mousePressed(MouseEvent e) {
		// TODO Auto-generated method stub
		if (e.getButton() == MouseEvent.BUTTON1) {
			Point p = new Point(e.getX(), e.getY());
			// convertToCoordinatePoints(p);
			if (status == LINE) {
				point = p;
			}
			if (status == RECTANGLE) {
				point = p;
			}
			if (status == SQUARE) {
				point = p;
			}

			if (status == DUONG_TRON) {
				point = p;
			}
			if (status == ELLIPSE) {
				point = p;
			}
			point = p;

		}
	}

	@Override
	public void mouseReleased(MouseEvent e) {
		if (e.getButton() == MouseEvent.BUTTON1) {
			Point p = new Point(e.getX(), e.getY());
			DrawContainer.convertToCoordinatePoints(p);
			if (status == LINE) {
				Line line = new Line(point, p);
				line.drawShape(drawPlace.getImage());
				status = 0;
			}
			if (status == RECTANGLE) {
				Rectangle rect = new Rectangle(drawPlace.getImage(), point, p);
				rect.drawShape(drawPlace.getImage());
				status = 0;
				rect.doiXung(point);
			}
			if (status == SQUARE) {
				Square sq = new Square(drawPlace.getImage(), point, p);
				sq.paint();
				drawPlace.refreshDrawPlace(drawPlace.getImage());
				System.out.println("released");
				status = 0;
			}
			if (status == DUONG_TRON) {
				int R = Point.distance(point, p);
				DuongTron dTron = new DuongTron(point, R);
				dTron.drawShape(drawPlace.getImage());
				status = 0;
			}
			if (status == ELLIPSE) {
				int bankinhNho = Point.distance(point, new Point(p.getX(), point.getY()));
				int bankinhLon = Point.distance(point, new Point(point.getX(), p.getY()));
				Ellipse elip = new Ellipse(point, bankinhNho, bankinhLon);
				elip.drawShape(drawPlace.getImage());
				ellipse = elip;
				status = 0;
			}

			if (status == SCALE) {
				for (Point elipPoint : ellipse.getPoints()) {
					try {
						Point temp = PhepBienDoi.getPointFromMatrix(PhepBienDoi.scale(elipPoint, 2, 2));
						Main.drawPoint(temp, drawPlace.getImage());
					} catch (Exception exc) {
						System.out.println(exc);
					}
				}
				drawPlace.refreshDrawPlace(drawPlace.getImage());
				System.out.println("scale");
			}

			if (status == COLORING) {
				coloring(point.getX(), point.getY(), Color.RED);
				drawPlace.refreshDrawPlace(drawPlace.getImage());
			}
			drawPlace.refreshDrawPlace(drawPlace.getImage());
		}
	}

	@Override
	public void mouseDragged(MouseEvent e) {
		// TODO Auto-generated method stub
		if (e.getButton() == MouseEvent.NOBUTTON) {
			Point p = new Point(e.getX(), e.getY());
			imageClone.setData(drawPlace.getImage().getRaster());
			if (status == LINE) {
				Line line = new Line(point, p);
				line.drawShape(imageClone);
//				System.out.println("draw");
			}
			if (status == DUONG_TRON) {
				int R = Point.distance(point, p);
				DuongTron dTron = new DuongTron(point, R);
				dTron.drawShape(imageClone);
			}
			if (status == ELLIPSE) {
				int bankinhNho = Point.distance(point, new Point(p.getX(), point.getY()));
				int bankinhLon = Point.distance(point, new Point(point.getX(), p.getY()));
				Ellipse elip = new Ellipse(point, bankinhNho, bankinhLon);
				elip.drawShape(imageClone);
			}
			if (status == RECTANGLE) {
				Rectangle rect = new Rectangle(point, p);
				rect.drawShape(imageClone);
			}
			if (status == SQUARE) {
				Square sq = new Square(imageClone, point, p);
				sq.paint();
				drawPlace.refreshDrawPlace(sq.getImage());
			}
			System.out.println(p.toString());
			drawPlace.refreshDrawPlace(imageClone);
			// Cube3D cube = new Cube3D();
			// cube.rotateY3D(e.getX() - point.getX());
			// cube.rotateX3D(e.getY() - point.getY());
			// imageClone.setData(drawPlace.getImage().getRaster());
			// cube.setImage(imageClone);
			// cube.drawCube();
			// drawPlace.refreshDrawPlace(cube.getImage());
			//// try {
			//// Thread.sleep(100);
			//// } catch (InterruptedException e1) {
			//// // TODO Auto-generated catch block
			//// System.out.println(e);
			//// }
			// System.out.println("wow");
		}
	}

	@Override
	public void mouseMoved(MouseEvent e) {
		// TODO Auto-generated method stub

	}

	public int getStatus() {
		return status;
	}

	public void setStatus(int status) {
		this.status = status;
	}

	public void drawCoordinate2D(Color coorColor) {
		Color green = Color.BLUE;
		int fixW = (this.getWidth() - 2) % size == 0 ? (this.getWidth() - 2)
				: (this.getWidth() - 2) - (this.getWidth() - 2) % size;
		int fixH = (this.getHeight() - 2) % size == 0 ? (this.getHeight() - 2)
				: (this.getHeight() - 2) - (this.getHeight() - 2) % size;
		for (int w = 0; w <= fixW; w++) {
			if (w % size == 0) {
				Main.color = green;
				Point top = new Point(w, 0);
				Point bot = new Point(w, fixH);
				Line line = new Line(top, bot);
				line.BresLineForCoor();
			}
			if (w == fixW / 2) {
				Main.color = coorColor;
				Point top = new Point(w, 0);
				Point bot = new Point(w, fixH);
				Line line = new Line(top, bot);
				line.BresLineForCoor();
				Main.color = coorColor;
			}
		}
		for (int h = 0; h <= fixH; h++) {
			if (h % size == 0) {
				Main.color = green;
				Point left = new Point(0, h);
				Point right = new Point(fixW, h);
				Line line = new Line(left, right);
				line.BresLineForCoor();
			}
			if (h == fixH / 2) {
				Main.color = coorColor;
				Point left = new Point(0, h);
				Point right = new Point(fixW, h);
				Line line = new Line(left, right);
				line.BresLineForCoor();
			}
		}
		Main.color = coorColor;
		// g.setColor(Color.red);
		// g.drawString("O", fixW / 2 + size / 3, fixH / 2 + 2 * size / 3);
		// g.drawString("Y", fixW / 2 + size / 3, 15);
		// g.drawString("X", fixW - 15, fixH / 2 - 5);
	}

	public void drawCoordinate3D(Color coorColor) {
		Color green = Color.BLUE;
		int fixW = this.getWidth() % size == 0 ? this.getWidth() : this.getWidth() - this.getWidth() % size;
		int fixH = this.getHeight() % size == 0 ? this.getHeight() : this.getHeight() - this.getHeight() % size;
		for (int w = 0; w <= fixW; w++) {
			if (w % size == 0) {
				Main.color = green;
				Point top = new Point(w, 0);
				Point bot = new Point(w, fixH);
				Line line = new Line(top, bot);
				line.BresLineForCoor();
			}
			if (w == fixW / 2) {
				Main.color = coorColor;
				Point top = new Point(w, 0);
				Point bot = new Point(w, fixH / 2);
				Line line = new Line(top, bot);
				line.BresLineForCoor();

				int ww = fixW / 2;
				int hh = fixH / 2;
				for (int h = hh; h > 0; h--, ww--) {
					Main.drawPoint(new Point(ww, fixH - h), drawPlace.getImage());
				}
				Main.color = coorColor;
			}
		}
		for (int h = 0; h <= fixH; h++) {
			if (h % size == 0) {
				Main.color = green;
				Point left = new Point(0, h);
				Point right = new Point(fixW, h);
				Line line = new Line(left, right);
				line.BresLineForCoor();
			}
			if (h == fixH / 2) {
				Main.color = coorColor;
				Point left = new Point(fixW / 2, h);
				Point right = new Point(fixW, h);
				Line line = new Line(left, right);
				line.BresLineForCoor();
				Main.color = green;
			}
		}
		Main.color = coorColor;
		// g.setColor(Color.red);
		//
		// g.drawString("O", fixW / 2 + size / 3, fixH / 2 + 2 * size / 3);
		// g.drawString("Z", fixW / 2 + size / 3, 15);
		// g.drawString("X", fixW - 15, fixH / 2 - 5);
		// g.drawString("Y", fixW / 4, fixH - 15);
	}

	public static void convertToCoordinatePoints(Point p) {
		if (p.getX() % size > size / 4 || p.getY() % size > size / 4) {
			p.setX(p.getX() - p.getX() % size + size);
			p.setY(p.getY() - p.getY() % size + size);
		} else {
			p.setX(p.getX() - p.getX() % size);
			p.setY(p.getY() - p.getY() % size);
		}
		System.out.println(p.toString());
	}

	public static int convertOnePoint(int v) {
		return v - v % size;
	}

	public void coloring(int x, int y, Color color) {
		int clr = drawPlace.getImage().getRGB(x, y);
		int bgClr = DrawPlace.BGColor.getRGB();
		if (clr == bgClr && clr != color.getRGB()) {
			System.out.println(x + " - " + y);
			Main.color = color;
			drawPlace.getImage().setRGB(x, y, color.getRGB());
			coloring(x + 1, y, color);
			coloring(x - 1, y, color);
			coloring(x, y + 1, color);
			coloring(x, y - 1, color);
			// coloring(x - 1, y + 1, color);
			// coloring(x + 1, y + 1, color);
			// coloring(x + 1, y - 1, color);
			// coloring(x - 1, y - 1, color);
		}
		// Main.color = Color.BLACK;
	}
}
