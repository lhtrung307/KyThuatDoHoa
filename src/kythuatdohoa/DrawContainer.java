package kythuatdohoa;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;
import java.awt.image.BufferedImage;

import javax.swing.BorderFactory;
import javax.swing.JColorChooser;
import javax.swing.JComponent;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;

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
	public static int COLOR =8;
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
//		this.add(drawPlace);
//		 image = drawPlace.getImage();
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
			convertToCoordinatePoints(p);
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
		}
	}

	@Override
	public void mouseReleased(MouseEvent e) {
		if (e.getButton() == MouseEvent.BUTTON1) {
			Point p = new Point(e.getX(), e.getY());
			DrawContainer.convertToCoordinatePoints(p);
			if(status == LINE) {
				Line line = new Line(drawPlace.getImage(), point, p);
				line.Bres_Line();
				drawPlace.refreshDrawPlace(drawPlace.getImage());
				System.out.println("released");
				status = 0;
			}
			if(status == RECTANGLE) {
				Rectangle rect = new Rectangle(drawPlace.getImage(), point, p);
				rect.paint();
				drawPlace.refreshDrawPlace(drawPlace.getImage());
				System.out.println("released");
				status = 0;
//				rect.doiXung(point);
			}
			if(status == SQUARE) {
				Square sq = new Square(drawPlace.getImage(), point, p);
				sq.paint();
				drawPlace.refreshDrawPlace(drawPlace.getImage());
				System.out.println("released");
				status = 0;
			}
			if(status == DUONG_TRON) {
				int R = Point.distance(point, p);
				
				DuongTron dTron = new DuongTron(drawPlace.getImage(), point, R);
				dTron.duongtronMid();
				System.out.println(p.getX() + " - " + p.getY());
				drawPlace.refreshDrawPlace(drawPlace.getImage());
				System.out.println("released");
				status = 0;
			}
			if(status == ELLIPSE) {
				int bankinhNho = Point.distance(point, new Point(p.getX(), point.getY()));
				int bankinhLon = Point.distance(point, new Point(point.getX(), p.getY()));
				Ellipse elip = new Ellipse(drawPlace.getImage(), point, bankinhNho, bankinhLon);
				elip.ellipseBre();
				ellipse = elip;
				System.out.println(p.getX() + "-" + p.getY());
				drawPlace.refreshDrawPlace(drawPlace.getImage()); 
				System.out.println("released");
				status = 0;

			}
			
			if(status == SCALE) {
				for (Point elipPoint : ellipse.getEllipse()) {
					Point temp = PhepBienDoi.getPointFromMatrix(PhepBienDoi.scale(elipPoint, 0.80, 0.80));
					Main.drawPoint(temp, drawPlace.getImage());
				}
				drawPlace.refreshDrawPlace(drawPlace.getImage());
				System.out.println("scale");
			}
			
//			if(status == COLOR) {
//				ColorChooser choose = new ColorChooser();
//				choose.setBounds(0, 0, 400, 500);
//				choose.setOpaque(true);
//				this.add(choose);			
//			}
		}
	}

	

	@Override
	public void mouseDragged(MouseEvent e) {
		// TODO Auto-generated method stub
		if (e.getButton() == MouseEvent.NOBUTTON) {
			if (status == LINE) {
				imageClone.setData(drawPlace.getImage().getRaster());
				Line line = new Line(imageClone, point, new Point(e.getX(), e.getY()));
				line.Bres_Line();
				drawPlace.refreshDrawPlace(line.getImage());
				System.out.println("draw");
			}
			if (status == DUONG_TRON) {
				imageClone.setData(drawPlace.getImage().getRaster());
				int R = Point.distance(point, new Point(e.getX(), e.getY()));

				DuongTron dTron = new DuongTron(imageClone, point, R);
				dTron.duongtronMid();

				drawPlace.refreshDrawPlace(dTron.getImage());
				System.out.println(e.getX() + " - " + e.getY());

			}
			if (status == ELLIPSE) {
				imageClone.setData(drawPlace.getImage().getRaster());
				int bankinhNho = Point.distance(point, new Point(e.getX(), point.getY()));
				int bankinhLon = Point.distance(point, new Point(point.getX(), e.getY()));
				Ellipse elip = new Ellipse(imageClone, point, bankinhNho, bankinhLon);
				elip.ellipseBre();

				drawPlace.refreshDrawPlace(elip.getImage());
				System.out.println(e.getX() + "-" + e.getY());
			}
			if (status == RECTANGLE) {
				imageClone.setData(drawPlace.getImage().getRaster());
				Rectangle rect = new Rectangle(imageClone, point, new Point(e.getX(), e.getY()));
				rect.paint();

				drawPlace.refreshDrawPlace(rect.getImage());

				System.out.println(e.getX() + " - " + e.getY());
			}
			if (status == SQUARE) {
				imageClone.setData(drawPlace.getImage().getRaster());
				Square sq = new Square(imageClone, point, new Point(e.getX(), e.getY()));
				sq.paint();
				drawPlace.refreshDrawPlace(sq.getImage());
				System.out.println(e.getX() + " - " + e.getY());
			}
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
				Line line = new Line(drawPlace.getImage(), top, bot);
				line.BresLineForCoor();
			}
			if (w == fixW / 2) {
				Main.color = coorColor;
				Point top = new Point(w, 0);
				Point bot = new Point(w, fixH);
				Line line = new Line(drawPlace.getImage(), top, bot);
				line.BresLineForCoor();
				Main.color = coorColor;
			}
		}
		for (int h = 0; h <= fixH; h++) {
			if (h % size == 0) {
				Main.color = green;
				Point left = new Point(0, h);
				Point right = new Point(fixW, h);
				Line line = new Line(drawPlace.getImage(), left, right);
				line.BresLineForCoor();
			}
			if (h == fixH / 2) {
				Main.color = coorColor;
				Point left = new Point(0, h);
				Point right = new Point(fixW, h);
				Line line = new Line(drawPlace.getImage(), left, right);
				line.BresLineForCoor();
				// Main.color = coorColor;
			}
		}
		Main.color = coorColor;
//		 g.setColor(Color.red);
//		 g.drawString("O", fixW / 2 + size / 3, fixH / 2 + 2 * size / 3);
//		 g.drawString("Y", fixW / 2 + size / 3, 15);
//		 g.drawString("X", fixW - 15, fixH / 2 - 5);
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
				Line line = new Line(drawPlace.getImage(), top, bot);
				line.BresLineForCoor();
			}
			if (w == fixW / 2) {
				Main.color = coorColor;
				Point top = new Point(w, 0);
				Point bot = new Point(w, fixH / 2);
				Line line = new Line(drawPlace.getImage(), top, bot);
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
				Line line = new Line(drawPlace.getImage(), left, right);
				line.BresLineForCoor();
			}
			if (h == fixH / 2) {
				Main.color = coorColor;
				Point left = new Point(fixW / 2, h);
				Point right = new Point(fixW, h);
				Line line = new Line(drawPlace.getImage(), left, right);
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
}

//class ColorChooser extends JPanel implements ChangeListener {
//	/**
//		 * 
//		 */
//	private static final long serialVersionUID = 1L;
//	public JColorChooser tcc;
//	protected JLabel banner;
//
//	public ColorChooser() {
//		// TODO Auto-generated constructor stub
//		super(new BorderLayout());
//		banner = new JLabel();
//		banner.setForeground(Color.yellow);
//		banner.setBackground(Color.BLUE);
//		banner.setOpaque(true);
//		banner.setFont(new Font("SansSerif", Font.BOLD, 24));
//		banner.setPreferredSize(new Dimension(100, 65));
//
////		JPanel bannerPanel = new JPanel(new BorderLayout());
////		bannerPanel.add(banner, BorderLayout.CENTER);
////		bannerPanel.setBorder(BorderFactory.createTitledBorder("Banner"));
//
//		// Set up color chooser for setting text color
//		tcc = new JColorChooser(banner.getForeground());
//		tcc.getSelectionModel().addChangeListener(this);
//		tcc.setBorder(BorderFactory.createTitledBorder("Choose Text Color"));
//
////		add(bannerPanel, BorderLayout.CENTER);
//		add(tcc, BorderLayout.PAGE_END);
//	}
//
//	/**
//	 * 
//	 */
//
//	@Override
//	public void stateChanged(ChangeEvent e) {
//		// TODO Auto-generated method stub
//		Color newColor = tcc.getColor();
//		banner.setForeground(newColor);
//
//	}
//
//	public static void createAndShowGUI() {
//		// Create and set up the window.
//		JFrame frame = new JFrame("ColorChooserDemo");
//		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
//
//		// Create and set up the content pane.
//		JComponent newContentPane = new ColorChooser();
//		newContentPane.setOpaque(true); // content panes must be opaque
//		frame.setContentPane(newContentPane);
//
//		// Display the window.
//		frame.pack();
//		frame.setVisible(true);
//	}
//
//}

