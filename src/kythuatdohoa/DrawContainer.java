package kythuatdohoa;

import java.awt.Color;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;
import java.awt.image.BufferedImage;

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
	public static int TRANSLATION = 7;
	public static int ROTATION = 8;
	public static int SCALING = 9;
	public static int REFLECTION = 10;
	public static int CUBE3D = 11;
	public static int COLORING = 12;
	public static int SCALE = 7;

	private int status;
	private Point point;
	private DrawPlace drawPlace;
	public static int size = 20;
	public static int wCell = 20;
	public static int hCell = 10;

	private Ellipse ellipse;
	private DuongTron circle;
	private Square square;
	private Rectangle rectangle;
	private Line line1;

	public DrawContainer() {
		status = 0;
		drawPlace = new DrawPlace();
		drawPlace.setBounds(0, 0, Main.SCR_HEIGHT, Main.SCR_WIDTH);
		this.add(drawPlace);
		// image = drawPlace.getImage();
		point = new Point();
		this.addMouseListener(this);
		this.addMouseMotionListener(this);

	}

	@Override
	public void mouseClicked(MouseEvent e) {
		if (e.getButton() == MouseEvent.BUTTON1 && status == POINT) {
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

	}

	@Override
	public void mouseExited(MouseEvent e) {

	}

	@Override
	public void mousePressed(MouseEvent e) {
		if (e.getButton() == MouseEvent.BUTTON1) {
			Point p = new Point(e.getX(), e.getY());
			// convertToCoordinatePoints(p);
			point = p;

		}
	}

	@Override
	public void mouseReleased(MouseEvent e) {
		if (e.getButton() == MouseEvent.BUTTON1) {
			Point p = new Point(e.getX(), e.getY());
			// DrawContainer.convertToCoordinatePoints(p);
			if (status == LINE) {
				Line line = new Line(point, p);

				line.drawShape(drawPlace.getImage());
				line1 = line;

				status = 0;
			}
			if (status == RECTANGLE) {
				Rectangle rect = new Rectangle(point, p);
				rect.drawShape(drawPlace.getImage());
				rectangle = rect;
				status = 0;
				// rect.doiXung(point);
			}
			if (status == SQUARE) {
				Square sq = new Square(point, p);
				sq.drawShape(drawPlace.getImage());
				square = sq;
				status = 0;
			}
			if (status == DUONG_TRON) {
				int R = Point.distance(point, p);
				DuongTron dTron = new DuongTron(point, R);
				dTron.drawShape(drawPlace.getImage());
				circle = dTron;
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

			// tinh tien
			if (status == TRANSLATION) {

				// ellipse
				// for (Point elipPoint : ellipse.getPoints()) {
				// try {
				// Point temp =
				// PhepBienDoi.getPointFromMatrix(PhepBienDoi.translation(elipPoint, -10, -10));
				// Main.drawPoint(temp, drawPlace.getImage());
				// } catch (Exception exc) {
				// System.out.println(exc);
				// }
				// }

				// circle
				// for (Point circlePoint : circle.getPoints()) {
				// try {
				// Point temp =
				// PhepBienDoi.getPointFromMatrix(PhepBienDoi.translation(circlePoint, -10,
				// -10));
				// Main.drawPoint(temp, drawPlace.getImage());
				// } catch (Exception exc) {
				// System.out.println(exc);
				// }
				// }

				// rectangle
				// for (Point rectanglePoint : rectangle.getPoints()) {
				// try {
				// Point temp =
				// PhepBienDoi.getPointFromMatrix(PhepBienDoi.translation(rectanglePoint, -10,
				// -10));
				// Main.drawPoint(temp, drawPlace.getImage());
				// } catch (Exception exc) {
				// System.out.println(exc);
				// }
				// }

				// square
				for (Point squarePoint : square.getPoints()) {
					try {
						Point temp = PhepBienDoi.getPointFromMatrix(PhepBienDoi.translation(squarePoint, -10, -10));
						Main.drawPoint(temp, drawPlace.getImage());
					} catch (Exception exc) {
						System.out.println(exc);
					}
				}

				// line
//				for (Point linePoint : line1.getPoints()) {
//					try {
//						Point temp = PhepBienDoi.getPointFromMatrix(PhepBienDoi.translation(linePoint, -10, -10));
//						Main.drawPoint(temp, drawPlace.getImage());
//					} catch (Exception exc) {
//						System.out.println(exc);
//					}
//				}

			}

			if (status == ROTATION) {
				for (Point elipPoint : ellipse.getPoints()) {
					try {
						Point temp = PhepBienDoi.getPointFromMatrix(PhepBienDoi.rotation(elipPoint, 20));
						Main.drawPoint(temp, drawPlace.getImage());
					} catch (Exception exc) {
						System.out.println(exc);
					}
				}

			}

			if (status == SCALING) {
				for (Point elipPoint : ellipse.getPoints()) {
					try {
						Point temp = PhepBienDoi.getPointFromMatrix(PhepBienDoi.scaling(elipPoint, 0.5, 0.5));
						Main.drawPoint(temp, drawPlace.getImage());
					} catch (Exception exc) {
						System.out.println(exc);
					}
				}

//				for (Point circlePoint : circle.getPoints()) {
//					try {
//						Point temp = PhepBienDoi.getPointFromMatrix(PhepBienDoi.scaling(circlePoint, 0.5, 0.5));
//						Main.drawPoint(temp, drawPlace.getImage());
//					} catch (Exception exc) {
//						System.out.println(exc);
//					}
//				}
//
//				for (Point RectanglePoint : rectangle.getPoints()) {
//					try {
//						Point temp = PhepBienDoi.getPointFromMatrix(PhepBienDoi.scaling(RectanglePoint, 2, 2));
//						Main.drawPoint(temp, drawPlace.getImage());
//					} catch (Exception exc) {
//						System.out.println(exc);
//					}
//				}
//
//				for (Point LinePoint : line1.getPoints()) {
//					try {
//						Point temp = PhepBienDoi.getPointFromMatrix(PhepBienDoi.scaling(LinePoint, 2, 2));
//						Main.drawPoint(temp, drawPlace.getImage());
//					} catch (Exception exc) {
//						System.out.println(exc);
//					}
//
//					drawPlace.refreshDrawPlace(drawPlace.getImage());
//					System.out.println("scale");
//				}

			}

			if (status == REFLECTION) {

			}

			if (status == COLORING) {
				coloring(point.getX(), point.getY(), Color.RED);
			}

			drawPlace.refreshDrawPlace(drawPlace.getImage());
		}

	}

	@Override
	public void mouseDragged(MouseEvent e) {
		if (e.getButton() == MouseEvent.NOBUTTON) {
			BufferedImage imageClone;
			imageClone = new BufferedImage(drawPlace.getImage().getWidth(), drawPlace.getImage().getHeight(),
					drawPlace.getImage().getType());
			Point p = new Point(e.getX(), e.getY());
			imageClone.setData(drawPlace.getImage().getRaster());
			if (status == LINE) {
				Line line = new Line(point, p);
				line.drawShape(imageClone);
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
				Square sq = new Square(point, p);
				sq.drawShape(imageClone);
			}

			if (status == CUBE3D) {
				Cube3D cube = new Cube3D();
				cube.rotateY3D(p.getX() - point.getX());
				cube.rotateX3D(p.getY() - point.getY());

				imageClone.setData(drawPlace.getImage().getRaster());
				cube.setImage(imageClone);
				cube.drawCube();
				drawPlace.refreshDrawPlace(cube.getImage());
				System.out.println("wow");
			}
			System.out.println(p.toString());
			// drawPlace.refreshDrawPlace(imageClone);
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
			//// System.out.println(e);
			//// }
			// System.out.println("wow");

			if (status == CUBE3D) {
				Cube3D cube = new Cube3D();
				cube.rotateY3D(p.getX() - point.getX());
				cube.rotateX3D(p.getY() - point.getY());
				cube.drawShape(imageClone);
			}
			System.out.println(p.toString());
			drawPlace.refreshDrawPlace(imageClone);

		}
	}

	@Override
	public void mouseMoved(MouseEvent e) {

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
			Point top = new Point(w, 0);
			Point bot = new Point(w, fixH);
			// if (w % size == 0) {
			// Main.color = green;
			// Line line = new Line(top, bot);
			// line.drawShape(drawPlace.getImage());
			// }
			if (w == fixW / 2) {
				Main.color = coorColor;
				Line line = new Line(top, bot);
				line.drawShape(drawPlace.getImage());
				Main.color = coorColor;
			}
			Main.color = coorColor;
		}
		for (int h = 0; h <= fixH; h++) {
			// if (h % size == 0) {
			// Main.color = green;
			// Point left = new Point(0, h);
			// Point right = new Point(fixW, h);
			// Line line = new Line(left, right);
			// line.drawShape(drawPlace.getImage());
			// }
			if (h == fixH / 2) {
				Main.color = coorColor;
				Point left = new Point(0, h);
				Point right = new Point(fixW, h);
				Line line = new Line(left, right);
				line.drawShape(drawPlace.getImage());
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