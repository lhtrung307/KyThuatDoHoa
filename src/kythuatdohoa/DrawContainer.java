package kythuatdohoa;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;
import java.awt.image.BufferedImage;
import java.util.ArrayList;

import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.UIManager;

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
	public static int PYRAMID3D = 13;

	private int status;
	private int numb;
	private Point point;
	private DrawPlace drawPlace;
	private ArrayList<Shape> shapes;
	private Ellipse ellipse;
	private DuongTron circle;
	private Square square;
	private Rectangle rectangle;
	private Line line1;
	private Cube3D cube;
	private Pyramid3D pyramid;
		
	public DrawContainer() {
		status = 0;
		drawPlace = new DrawPlace();
		drawPlace.setBounds(0, 0, Main.SCR_WIDTH, Main.SCR_HEIGHT);
		this.add(drawPlace);
		shapes = new ArrayList<>();
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
				shapes.add(line);
				line1 = line;
			}
			if (status == RECTANGLE) {
				Rectangle rect = new Rectangle(point, p);
				rect.drawShape(drawPlace.getImage());
				rectangle = rect;
				shapes.add(rect);
				// rect.doiXung(point);
			}
			if (status == SQUARE) {
				Square sq = new Square(point, p);
				sq.drawShape(drawPlace.getImage());
				square = sq;
				shapes.add(sq);
			}
			if (status == DUONG_TRON) {
				int R = Point.distance(point, p);
				DuongTron dTron = new DuongTron(point, R);
				dTron.drawShape(drawPlace.getImage());
				circle = dTron;
				shapes.add(dTron);
			}
			if (status == ELLIPSE) {
				int bankinhNho = Point.distance(point, new Point(p.getX(), point.getY()));
				int bankinhLon = Point.distance(point, new Point(point.getX(), p.getY()));
				Ellipse elip = new Ellipse(point, bankinhNho, bankinhLon);
				elip.drawShape(drawPlace.getImage());
				ellipse = elip;
				shapes.add(elip);
			}

			// tinh tien
			if (status == TRANSLATION) {
				Point input = getTransInput();
				for (Shape shape : shapes) {
					for (Point elipPoint : shape.getPoints()) {
						elipPoint.translateRealToCoordinate();
						try {
							Point temp = PhepBienDoi.getPointFromMatrix(PhepBienDoi.translation(elipPoint, input.getX(), input.getY()));
							temp.translateCoordinateToReal();
							Main.drawPoint(temp, drawPlace.getImage());
							elipPoint.translateCoordinateToReal();
						} catch (Exception exc) {
							System.out.println(exc);
						}
					}
				}
				

				// line
				// for (Point linePoint : line1.getPoints()) {
				// try {
				// Point temp =
				// PhepBienDoi.getPointFromMatrix(PhepBienDoi.translation(linePoint, -10, -10));
				// Main.drawPoint(temp, drawPlace.getImage());
				// } catch (Exception exc) {
				// System.out.println(exc);
				// }
				// }

			}

			if (status == ROTATION) {
				String value = JOptionPane.showInputDialog("Enter angle", "");
				int theta = Integer.parseInt(value);
				for (Point elipPoint : ellipse.getPoints()) {
					elipPoint.translateRealToCoordinate();
					try {
						Point temp = PhepBienDoi.getPointFromMatrix(PhepBienDoi.rotation(elipPoint, theta));
						temp.translateCoordinateToReal();
						Main.drawPoint(temp, drawPlace.getImage());
					} catch (Exception exc) {
						System.out.println(exc);
					}
				}

			}

			if (status == SCALING) {
				String value = JOptionPane.showInputDialog("Enter scale", "");
				double scale = Double.parseDouble(value);
				for (Point elipPoint : ellipse.getPoints()) {
					elipPoint.translateRealToCoordinate();;
					try {
						Point temp = PhepBienDoi.getPointFromMatrix(PhepBienDoi.scaling(elipPoint, scale, scale));
						temp.translateCoordinateToReal();
						Main.drawPoint(temp, drawPlace.getImage());
					} catch (Exception exc) {
						System.out.println(exc);
					}
				}
				

				// for (Point circlePoint : circle.getPoints()) {
				// try {
				// Point temp = PhepBienDoi.getPointFromMatrix(PhepBienDoi.scaling(circlePoint,
				// 0.5, 0.5));
				// Main.drawPoint(temp, drawPlace.getImage());
				// } catch (Exception exc) {
				// System.out.println(exc);
				// }
				// }
				//
				// for (Point RectanglePoint : rectangle.getPoints()) {
				// try {
				// Point temp =
				// PhepBienDoi.getPointFromMatrix(PhepBienDoi.scaling(RectanglePoint, 2, 2));
				// Main.drawPoint(temp, drawPlace.getImage());
				// } catch (Exception exc) {
				// System.out.println(exc);
				// }
				// }
				//
				// for (Point LinePoint : line1.getPoints()) {
				// try {
				// Point temp = PhepBienDoi.getPointFromMatrix(PhepBienDoi.scaling(LinePoint, 2,
				// 2));
				// Main.drawPoint(temp, drawPlace.getImage());
				// } catch (Exception exc) {
				// System.out.println(exc);
				// }
				//
				// drawPlace.refreshDrawPlace(drawPlace.getImage());
				// System.out.println("scale");
				// }

			}

			if (status == REFLECTION) {
				if(numb == 1) {
					for(Point RectanglePoint : rectangle.getPoints()) {
						RectanglePoint.translateRealToCoordinate();
						try {
							Point temp = PhepBienDoi.getPointFromMatrix(PhepBienDoi.reflectionO(RectanglePoint));
							temp.translateCoordinateToReal();
							Main.drawPoint(temp, drawPlace.getImage());
						} catch (Exception exc) {
							System.out.println(exc);
						}
					}
					
					for(Point CirclePoint : circle.getPoints()) {
						CirclePoint.translateRealToCoordinate();
						try {
							Point temp = PhepBienDoi.getPointFromMatrix(PhepBienDoi.reflectionO(CirclePoint));
							temp.translateCoordinateToReal();
							Main.drawPoint(temp, drawPlace.getImage());
						} catch (Exception exc) {
							System.out.println(exc);
						}
					}
					
					for(Point SquarePoint : square.getPoints()) {
						SquarePoint.translateRealToCoordinate();
						try {
							Point temp = PhepBienDoi.getPointFromMatrix(PhepBienDoi.reflectionO(SquarePoint));
							temp.translateCoordinateToReal();
							Main.drawPoint(temp, drawPlace.getImage());
						} catch (Exception exc) {
							System.out.println(exc);
						}
					}
				}else if(numb == 2) {
					for(Point RectanglePoint : rectangle.getPoints()) {
						RectanglePoint.translateRealToCoordinate();
						try {
							Point temp = PhepBienDoi.getPointFromMatrix(PhepBienDoi.reflectionOx(RectanglePoint));
							temp.translateCoordinateToReal();
							Main.drawPoint(temp, drawPlace.getImage());
						} catch (Exception exc) {
							System.out.println(exc);
						}
					}
				}else {
					for(Point RectanglePoint : rectangle.getPoints()) {
						RectanglePoint.translateRealToCoordinate();
						try {
							Point temp = PhepBienDoi.getPointFromMatrix(PhepBienDoi.reflectionOy(RectanglePoint));
							temp.translateCoordinateToReal();
							Main.drawPoint(temp, drawPlace.getImage());
						} catch (Exception exc) {
							System.out.println(exc);
						}
					}
				}
			}

			if (status == COLORING) {
				coloring(point.getX(), point.getY(), Color.RED);
			}
			
			if (status == CUBE3D) {
				CubeInput cubeInput = new CubeInput();
				cubeInput.setBounds(0, 0, 150, 150);
				int result = showDialog(cubeInput);
				int x = Integer.parseInt(cubeInput.getxCoor().getText());
				int y = Integer.parseInt(cubeInput.getyCoor().getText());
				int z = Integer.parseInt(cubeInput.getzCoor().getText());
				int length = Integer.parseInt(cubeInput.getLength().getText());
				Cube3D cube = new Cube3D(x, y, z, length);
				cube.drawShape(drawPlace.getImage());
				this.cube = cube;
			}
			if (status == PYRAMID3D) {
				CubeInput cubeInput = new CubeInput();
				cubeInput.setBounds(0, 0, 150, 150);
				int result = showDialog(cubeInput);
				int x = Integer.parseInt(cubeInput.getxCoor().getText());
				int y = Integer.parseInt(cubeInput.getyCoor().getText());
				int z = Integer.parseInt(cubeInput.getzCoor().getText());
				int length = Integer.parseInt(cubeInput.getLength().getText());
				Pyramid3D pyramid = new Pyramid3D(x, y, z, length);
				pyramid.drawShape(drawPlace.getImage());
				this.pyramid = pyramid;
			}
			status = 0;
			drawPlace.refreshDrawPlace(drawPlace.getImage());
		}
	}

	private Point getTransInput() {
		JTextField xCoor = new JTextField(4);
		JTextField yCoor = new JTextField(4);
		JPanel getCoor = createGetCoorPanel(xCoor, yCoor);
		int result = showDialog(getCoor);
		int x = 0;
		int y = 0;
		if(result == JOptionPane.OK_OPTION ) {
			x = Integer.parseInt(xCoor.getText());
			y = Integer.parseInt(yCoor.getText());
		}
		
		return new Point(x, y);
	}
	
	private JPanel createGetCoorPanel(JTextField xCoor, JTextField yCoor) {
		JPanel getCoor = new JPanel();
		getCoor.add(new JLabel("X: "));
		getCoor.add(xCoor);
		getCoor.add(new JLabel("Y: "));
		getCoor.add(yCoor);
		return getCoor;
	}
	
	private int showDialog(JPanel getCoor) {
		UIManager.put("OptionPane.minimumSize",new Dimension(200,200));
		return JOptionPane.showConfirmDialog(null, getCoor, "hello", JOptionPane.OK_CANCEL_OPTION);
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
				Cube3D cubeClone = new Cube3D(cube.x, cube.y, cube.z, cube.length);
				cubeClone.rotateY3D(p.getX() - point.getX());
				cubeClone.rotateX3D(p.getY() - point.getY());
				cubeClone.drawShape(imageClone);
			}
			if (status == PYRAMID3D) {
				Pyramid3D pyramidClone = new Pyramid3D(pyramid.x, pyramid.y, pyramid.z, pyramid.length);
				pyramidClone.rotateY3D(p.getX() - point.getX());
				pyramidClone.rotateX3D(p.getY() - point.getY());
				pyramidClone.drawShape(imageClone);
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
	
	public void setStatus(int status, int numb) {
		this.status = status;
		this.numb = numb;
	}

	
	public DrawPlace getDrawPlace() {
		return drawPlace;
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