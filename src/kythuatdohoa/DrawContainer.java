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
	public static int ROTATION = 13;
	public static int SCALING = 9;
	public static int REFLECTION = 10;
	public static int CUBE3D = 11;
	public static int COLORING = 12;
	public static int PYRAMID3D = 14;
	public static int ROTATO = 13;

	private int status;

	private int statusTemp;

	private int numb;

	private Point point;
	private Point pointRotato;
	private DrawPlace drawPlace;
	private ArrayList<Shape> shapes;
	private ArrayList<Cube3D> cubes;
	private Pyramid3D pyramid;
	int x2,y2;
	int goc=0;
	String valueX;
	String valueY;
	double rotatoX;
	double rotatoY;

	public DrawContainer() {
		status = 0;
		drawPlace = new DrawPlace();
		drawPlace.setBounds(0, 0, Main.SCR_WIDTH, Main.SCR_HEIGHT);
		this.add(drawPlace);
		shapes = new ArrayList<>();
		cubes = new ArrayList<>();
		point = new Point();
		this.addMouseListener(this);
		this.addMouseMotionListener(this);
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
			// convertToCoordinatePoints(p);
			point = p;
			if (status == LINE) {
				pointRotato = p;
			}
			if (status == RECTANGLE) {
				pointRotato = p;
			}
			if (status == SQUARE) {
				pointRotato = p;
			}

			if (status == DUONG_TRON) {
				pointRotato = p;
			}
			if (status == ELLIPSE) {
				pointRotato = p;
			}
		}
	}

	@Override
	public void mouseReleased(MouseEvent e) {
		if (e.getButton() == MouseEvent.BUTTON1) {
			Point p = new Point(e.getX(), e.getY());
			// DrawContainer.convertToCoordinatePoints(p);
			if (status == POINT) {
				Main.drawPoint(p, drawPlace.getImage());
				drawPlace.refreshDrawPlace(drawPlace.getImage());
			}
			if (status == LINE) {
				statusTemp=LINE;
				x2=p.getX();
				y2=p.getY();
				BresenhamLine line = new BresenhamLine(point, p);
				line.drawShape(drawPlace.getImage());
				shapes.add(line);
			}
			if (status == RECTANGLE) {
				statusTemp=RECTANGLE;
				Rectangle rect = new Rectangle(point, p);
				rect.drawShape(drawPlace.getImage());
				shapes.add(rect);
				x2=p.getX();
				y2=p.getY();
			}
			if (status == SQUARE) {
				statusTemp=SQUARE;
				Square sq = new Square(point, p);
				sq.drawShape(drawPlace.getImage());
				shapes.add(sq);
				x2=p.getX();
				y2=p.getY();
			}
			if (status == DUONG_TRON) {
				statusTemp=DUONG_TRON;
				int R = Point.distance(point, p);
				DuongTron dTron = new DuongTron(point, R);
				dTron.drawShape(drawPlace.getImage());
				shapes.add(dTron);
				x2=p.getX();
				y2=p.getY();
			}
			if (status == ELLIPSE) {
				statusTemp=ELLIPSE;
				int bankinhNho = Point.distance(point, new Point(p.getX(), point.getY()));
				int bankinhLon = Point.distance(point, new Point(point.getX(), p.getY()));
				Ellipse elip = new Ellipse(point, bankinhNho, bankinhLon);
				elip.drawShape(drawPlace.getImage());
				shapes.add(elip);
				x2=p.getX();
				y2=p.getY();
			}
			//xoay
//			if(status == ROTATO) {
//				Rotato(pointRotato.getX(),pointRotato.getY(),x2,y2);
//				goc++;
//			}
			// tinh tien
			if (status == TRANSLATION) {
				Point input = getTransInput();
				for (Shape shape : shapes) {
					for (Point shapePoint : shape.getPoints()) {
						shapePoint.translateRealToCoordinate();
						try {
							Point temp = PhepBienDoi.getPointFromMatrix(PhepBienDoi.translation(shapePoint, input.getX(), input.getY()));
							temp.translateCoordinateToReal();
							Main.drawPoint(temp, drawPlace.getImage());
							shapePoint.translateCoordinateToReal();
						} catch (Exception exc) {
							System.out.println(exc);
						}
					}
				}
			}
			if (status == ROTATION) {
//				Point input = getTransInput();
				for (Shape shape : shapes) {
//					for (Point shapePoint : shape.getPoints()) {
//						shapePoint.translateRealToCoordinate();
//						try {
//							Point temp = PhepBienDoi.getPointFromMatrix(PhepBienDoi.rotation(shapePoint, -30));
//							temp.translateCoordinateToReal();
//							Main.drawPoint(temp, drawPlace.getImage());
//							shapePoint.translateCoordinateToReal();
//						} catch (Exception exc) {
//							System.out.println(exc);
//						}
//					}
					shape.rotation(30);
					shape.drawShape(drawPlace.getImage());
				}
				
			}
			if (status == SCALING) {
				String value = JOptionPane.showInputDialog("Enter scale", "");
				double scale = Double.parseDouble(value);
				for (Shape shape: shapes) {
					shape.scale(scale, scale);
					shape.drawShape(drawPlace.getImage());
				}
				status = 0;
			}

			if (status == REFLECTION) {
				Point input = null;
				if (numb > 3) {
					input = getTransInput();
					input.translateRealToCoordinate();
				}
				for(Shape shape : shapes) {
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
								temp = PhepBienDoi.getPointFromMatrix(PhepBienDoi.reflectionPoint(shapePoint, input.getX()-shapePoint.getX(), input.getY()-shapePoint.getY()));
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
				coloring(point.getX(), point.getY(), Color.RED);
			}
			if (status == CUBE3D) {
				ThreeDInput cubeInput = new ThreeDInput();
				cubeInput.setBounds(0, 0, 150, 150);
				int result = showDialog(cubeInput);
				if(result == JOptionPane.OK_OPTION) {
					int x = Integer.parseInt(cubeInput.getxCoor().getText());
					int y = Integer.parseInt(cubeInput.getyCoor().getText());
					int z = Integer.parseInt(cubeInput.getzCoor().getText());
					Cube3D cube = new Cube3D(x, y, z);
					cube.drawShape(drawPlace.getImage());
					cubes.add(cube);
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
				for(Cube3D cube: cubes) {
					Cube3D cubeClone = new Cube3D(cube.x, cube.y, cube.z);
					cubeClone.rotateY3D(p.getX() - point.getX());
					cubeClone.rotateX3D(p.getY() - point.getY());
					cubeClone.drawShape(imageClone);
					cube = cubeClone;
				}
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
	
	int i=0;
//	public void Rotato(int x11, int y11, int x22, int y22)  {
//    		
//		
//			if(i==0) {
//				
//					 valueX = JOptionPane.showInputDialog("Enter X", "");
//					 valueY = JOptionPane.showInputDialog("Enter Y", "");
//					 rotatoX = Double.parseDouble(valueX);
//				     rotatoY = Double.parseDouble(valueY);
//				     i=1;
//				
//			}
//           //Line
//		   Rotation r = new Rotation();
//			
//           if(status==ROTATO && statusTemp==LINE) {
//        	   
//        	    Point d1 = r.xoay(x11 ,y11, (int)rotatoX-320, (int)rotatoY-240, 20);
//             	Point d2 = r.xoay(x22, y22, (int)rotatoX-320, (int)rotatoY-240, 20);
//             	
//             	BresenhamLine line = new BresenhamLine( d1, d2);
//                 
//     			line.drawLine();
//				line.drawShape(drawPlace.getImage());
//	       		
//    			 //cap nhat lai diem tiep theo
//                pointRotato.setX(d1.getX());
//                pointRotato.setY(d1.getY());
//                x2=d2.getX();
//                y2=d2.getY();
//           }
//           
//           //Duong tron
//          
////           if(status==ROTATO && statusTemp==DUONG_TRON) {
////        	   
////        	   Point d1 = r.xoay(x11, y11, (int)rotatoX-320, (int)rotatoY-240, 20);
////               Point d2 = r.xoay(x22, y22,(int)rotatoX-320, (int)rotatoY-240, 20);
////          
////               int R = Point.distance(d1, d2); 
////               DuongTron dTron = new DuongTron(d1, R);
////               System.out.println(d2.getX() + " - " + d2.getY());
////               this.setVisible(false);
////               dTron.drawShape(drawPlace.getImage());
////               this.setVisible(true);
////               
////          		
////               
////               //cap nhat lai diem tiep theo
////               pointRotato.setX(d1.getX());
////               pointRotato.setY(d1.getY());
////               x2=d2.getX();
////               y2=d2.getY();
////     			
////           }
//			
//			//Hinh chu nhat
//           if(status==ROTATO && statusTemp==RECTANGLE) {
//        	   
//        	   Point d1 = new Point();
//        	   d1 = r.xoay(x11, y11, (int)rotatoX-320, (int)rotatoY-240, goc);
//        	   
//               Point d2 = new Point();
//               d2 = r.xoay(x22, y22, (int)rotatoX-320, (int)rotatoY-240, goc);
//               
//               Point d3 = new Point();
//               d3 = r.xoay(x22, y11, (int)rotatoX-320, (int)rotatoY-240, goc);
//               
//       		   Point d4 = new Point();
//       		   d4 = r.xoay(x11, y22, (int)rotatoX-320, (int)rotatoY-240, goc);
//
//       		BresenhamLine line1 = new BresenhamLine(d1, d3);
//       		BresenhamLine line2 = new BresenhamLine(d1, d4);
//       		BresenhamLine line3 = new BresenhamLine(d2, d3);
//       		BresenhamLine line4 = new BresenhamLine(d2, d4);
//       		
//       		line1.drawShape(drawPlace.getImage());
//       		line2.drawShape(drawPlace.getImage());
//       		line3.drawShape(drawPlace.getImage());
//       		line4.drawShape(drawPlace.getImage());
//       		
//			//cap nhat lai diem tiep theo
//          //  pointRotato.setX(d1.getX());
//          //  pointRotato.setY(d1.getY());
//          //  x2=d2.getX();
//           // y2=d2.getY();
//   
//					
//           }
//	}
}