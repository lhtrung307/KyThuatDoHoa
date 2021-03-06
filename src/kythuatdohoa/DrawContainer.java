package kythuatdohoa;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionAdapter;
import java.awt.event.MouseMotionListener;
import java.awt.image.BufferedImage;
import java.util.ArrayList;

import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.UIManager;

public class DrawContainer extends JPanel implements MouseMotionListener, MouseListener {
	private static final long serialVersionUID = 1L;
	public static int POINT = 1;
	public static int LINE = 2;
	public static int DUONG_TRON = 3;
	public static int ELLIPSE = 4;
	public static int RECTANGLE = 5;
	public static int SQUARE = 6;
	public static int TRANSLATION = 7;
	public static int ROTATION = 13;
	public static int SCALING = 9;
	public static int REFLECTION = 10;
	public static int CUBE3D = 11;
	public static int COLORING = 12;
	public static int PYRAMID3D = 14;
	public static int ROTATO = 13;
	public static int SUPER_DUPER_SHAPE = 15;
	public static int SUPER_DUPER_SHIP = 16;
	
	private Color color;
	private int shapesSize;
	private int status;
	private ArrayList<Point> scalePoints;
	private int statusTemp;
	public JLabel lblMouseCoor;
	private int numb;
	private Point point;
	private DrawPlace drawPlace;
	private ArrayList<Shape> shapes;
	private Cube3D cube;
	private Pyramid3D pyramid;

	public DrawContainer() {
		status = 0;
		drawPlace = new DrawPlace();
		drawPlace.setBounds(0, 0, Main.SCR_WIDTH, Main.SCR_HEIGHT);
		this.add(drawPlace);
		shapes = new ArrayList<>();
		point = new Point();
		shapesSize = 0;
		scalePoints = new ArrayList<>();
		this.addMouseListener(this);
		this.addMouseMotionListener(this);
		lblMouseCoor = new JLabel("");
		lblMouseCoor.setBounds(10, 538, 60, 20);
		this.add(lblMouseCoor);
	}
	
	public ArrayList<Shape> getShapes() {
		return shapes;
	}

	public void setShapes(ArrayList<Shape> shapes) {
		this.shapes = shapes;
	}

	public ArrayList<Point> getScalePoints() {
		return scalePoints;
	}

	public void setScalePoints(ArrayList<Point> scalePoints) {
		this.scalePoints = scalePoints;
	}

	@Override
	public void mouseClicked(MouseEvent e) {
		
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
			point = p;
		}
	}

	@Override
	public void mouseReleased(MouseEvent e) {
		if (e.getButton() == MouseEvent.BUTTON1) {
			Point p = new Point(e.getX(), e.getY());
			if (status == POINT) {
				Main.drawPoint(p, drawPlace.getImage());
				drawPlace.refreshDrawPlace(drawPlace.getImage());
			}
			if (status == LINE) {
				drawPlace.drawCoordinate3D(Color.WHITE);
				drawPlace.drawCoordinate2D(Color.BLACK);
				BresenhamLine line = new BresenhamLine(point, p);
				line.drawShape(drawPlace.getImage());
				shapes.add(line);
			}
			if (status == RECTANGLE) {
				drawPlace.drawCoordinate3D(Color.WHITE);
				drawPlace.drawCoordinate2D(Color.BLACK);
				Rectangle rect = new Rectangle(point, p);
				rect.drawShape(drawPlace.getImage());
				shapes.add(rect);
			}
			if (status == SQUARE) {
				drawPlace.drawCoordinate3D(Color.WHITE);
				drawPlace.drawCoordinate2D(Color.BLACK);
				Square sq = new Square(point, p);
				sq.drawShape(drawPlace.getImage());
				shapes.add(sq);
			}
			if (status == DUONG_TRON) {
				drawPlace.drawCoordinate3D(Color.WHITE);
				drawPlace.drawCoordinate2D(Color.BLACK);
				int R = Point.distance(point, p);
				DuongTron dTron = new DuongTron(point, R);
				dTron.drawShape(drawPlace.getImage());
				shapes.add(dTron);
			}
			if (status == ELLIPSE) {
				drawPlace.drawCoordinate3D(Color.WHITE);
				drawPlace.drawCoordinate2D(Color.BLACK);
				int bankinhNho = Point.distance(point, new Point(p.getX(), point.getY()));
				int bankinhLon = Point.distance(point, new Point(point.getX(), p.getY()));
				Ellipse elip = new Ellipse(point, bankinhNho, bankinhLon);
				elip.drawShape(drawPlace.getImage());
				shapes.add(elip);
			}
			if (status == SUPER_DUPER_SHAPE) {
				drawPlace.drawCoordinate3D(Color.WHITE);
				drawPlace.drawCoordinate2D(Color.BLACK);
				SuperAmazingShape superAmazingShape = new SuperAmazingShape();
				superAmazingShape.drawShape(drawPlace.getImage());
				shapes.add(superAmazingShape);
			}
			if (status == SUPER_DUPER_SHIP) {
				drawPlace.drawCoordinate3D(Color.WHITE);
				drawPlace.drawCoordinate2D(Color.BLACK);
				SuperAmazingShip superAmazingShip = new SuperAmazingShip();
				superAmazingShip.drawShape(drawPlace.getImage());
				shapes.add(superAmazingShip);
			}
			// tinh tien
			if (status == TRANSLATION) {
				Point input = getTransInput();
				for (Shape<Shape> shape : shapes) {
					for (Point shapePoint : shape.getPoints()) {
						try {
							shapePoint = PhepBienDoi.translation(shapePoint, input.getX(), input.getY());
							Main.drawPoint(shapePoint, drawPlace.getImage());
						} catch (Exception exc) {
							System.out.println(exc);
						}
					}
				}
			}
			if (status == ROTATION) {
				Point input = getTransInput();
				String value = JOptionPane.showInputDialog("Enter theta", "");
				double theta = Double.parseDouble(value);
				for (Shape shape : shapes) {
						Shape shapeRot = (Shape) shape.rotation(theta, input);
						shapeRot.drawShape(drawPlace.getImage());
				}
				
			}
			if (status == SCALING) {
				String value = JOptionPane.showInputDialog("Enter scale", "");
				double scale = Double.parseDouble(value);
//				for (Shape shape: shapes) {
//					shape.scale(scale, scale);
//					shape.drawShape(drawPlace.getImage());
//				}
				for (Point temp : scalePoints) {
					Point anotherPoint = PhepBienDoi.scaling(temp, scale, scale, new Point(0,0)).clone();
					Main.drawPoint(anotherPoint, drawPlace.getImage());
				}
				status = 0;
			}

			if (status == REFLECTION) {
				Point input = null;
				if (numb == 4) {
					input = getTransInput();
				}
				for(Shape<Shape> shape : shapes) {
					for(Point shapePoint : shape.getPoints()) {
						shapePoint.translateRealToCoordinate();
						try {
							Point temp = null;
							if(numb == 1) {
								temp = PhepBienDoi.getPointFromMatrix(PhepBienDoi.reflectionO(shapePoint));
							}else if(numb == 2) {
								temp = PhepBienDoi.getPointFromMatrix(PhepBienDoi.reflectionOx(shapePoint));
							}else if(numb == 3) {
								temp = PhepBienDoi.getPointFromMatrix(PhepBienDoi.reflectionOy(shapePoint));
							}else if(numb == 4) {
								int dx = input.getX()-shapePoint.getX();
								int dy = input.getY()-shapePoint.getY();
								temp = PhepBienDoi.getPointFromMatrix(PhepBienDoi.reflectionPoint(shapePoint, dx, dy));
							}
							temp.translateCoordinateToReal();
							Main.drawPoint(temp, drawPlace.getImage());
							shapePoint.translateCoordinateToReal();
						} catch (Exception exc) {
							System.out.println(exc);
						}
					}
				}
			}

			if (status == COLORING) {
				coloring(point.getX(), point.getY(), Main.color);
			}
			if (status == CUBE3D) {
				ThreeDInput cubeInput = new ThreeDInput();
				int result = showDialog(cubeInput);
				if(result == JOptionPane.OK_OPTION) {
					int x = Integer.parseInt(cubeInput.getxCoor().getText());
					int y = Integer.parseInt(cubeInput.getyCoor().getText());
					int z = Integer.parseInt(cubeInput.getzCoor().getText());
					Cube3D cube = new Cube3D(x, y, z);
					cube.drawShape(drawPlace.getImage());
					this.cube = cube;
				}
			}
			if (status == PYRAMID3D) {
				
				ThreeDInput cubeInput = new ThreeDInput();
				cubeInput.setBounds(0, 0, 150, 150);
				int result = showDialog(cubeInput);
				if(result == JOptionPane.OK_OPTION) {
					int x = Integer.parseInt(cubeInput.getxCoor().getText());
					int y = Integer.parseInt(cubeInput.getyCoor().getText());
					int z = Integer.parseInt(cubeInput.getzCoor().getText());
					Pyramid3D pyramid = new Pyramid3D(x, y, z);
					pyramid.drawShape(drawPlace.getImage());
					this.pyramid = pyramid;
				}
			}
			status = 0;
			System.out.println(shapes.size());
			if(shapesSize < shapes.size()) {
				shapesSize = shapes.size();
				scalePoints.addAll(shapes.get(shapesSize - 1).getPoints());
			}
			drawPlace.refreshDrawPlace(drawPlace.getImage());
//			drawPlace.repaint(shapes);
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
				BresenhamLine line = new BresenhamLine(point, p);
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
				Cube3D cubeClone = new Cube3D(cube.x, cube.y, cube.z);
				cubeClone.rotateY3D(p.getX() - point.getX());
				cubeClone.rotateX3D(p.getY() - point.getY());
				cubeClone.drawShape(imageClone);
				cube = cubeClone;
				System.out.println("rotate");
			}
			if (status == PYRAMID3D) {
				Pyramid3D pyramidClone = new Pyramid3D(pyramid.x, pyramid.y, pyramid.z);
				pyramidClone.rotateY3D(p.getX() - point.getX());
				pyramidClone.rotateX3D(p.getY() - point.getY());
				pyramidClone.drawShape(imageClone);
			}
			drawPlace.refreshDrawPlace(imageClone);
		}
	}

	@Override
	public void mouseMoved(MouseEvent e) {
		Point p = new Point(e.getX(), e.getY());
		if(p.x > drawPlace.getWidth()) {
			p.x = drawPlace.getWidth() - 2;
		}
		if(p.y > drawPlace.getHeight()) {
			p.y = drawPlace.getHeight() - 2;
		}
		p.translateRealToCoordinate();
		lblMouseCoor.setText(p.toString());
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