package kythuatdohoa;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import java.awt.Color;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.image.BufferedImage;
import java.util.ArrayList;

import javax.swing.JPanel;

import javax.swing.JLabel;
import java.awt.event.MouseMotionAdapter;

public class Main {
	public static int SCR_HEIGHT = 640;
	public static int SCR_WIDTH = 480;
	private static int POINT = 1;
	private static int LINE = 2;
	private static int DUONG_TRON = 3;
	private static int ELLIPSE = 4;
	private static int RECTANGLE = 5;
	private static int SQUARE = 6;
	private static int SCALE = 7;
	private Point point;
	private JFrame frame;
	public static Color color;
	private JPanel drawContainer;
	private JButton btnPoint;
	private BufferedImage image;
	private ImageIcon drawPlaceBG;
	private JLabel drawPlace;
	private int status;
	private BufferedImage imageClone;
	private JButton btnElip;
	private Ellipse ellipse;
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		System.out.println("Hello!");

		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Main window = new Main();
					window.frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	public Main() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frame = new JFrame();
		point = new Point();
		color = new Color(0, 0, 0);
		frame.setBounds(100, 100, SCR_HEIGHT, SCR_WIDTH);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);

		drawContainer = new JPanel();
		drawContainer.addMouseMotionListener(new MouseMotionAdapter() {
			@Override
			public void mouseDragged(MouseEvent e) {
				if (e.getButton() == MouseEvent.NOBUTTON) {
					if (status == LINE) {
						imageClone.setData(image.getRaster());
						Line line = new Line(imageClone, point, new Point(e.getX(), e.getY()));
						line.Bres_Line();
						refreshDrawPlace(line.getImage());
						System.out.println("draw");
					}
					if (status == DUONG_TRON) {
						imageClone.setData(image.getRaster());
						int R = Point.distance(point, new Point(e.getX(), e.getY()));

						DuongTron dTron = new DuongTron(imageClone, point, R);
						dTron.duongtronMid();

						refreshDrawPlace(dTron.getImage());
						System.out.println(e.getX() + " - " + e.getY());

					}
					if (status == ELLIPSE) {
						imageClone.setData(image.getRaster());
						int bankinhNho = Point.distance(point, new Point(e.getX(), point.getY()));
						int bankinhLon = Point.distance(point, new Point(point.getX(), e.getY()));
						Ellipse elip = new Ellipse(imageClone, point, bankinhNho, bankinhLon);
						elip.ellipseBre();

						refreshDrawPlace(elip.getImage());
						System.out.println(e.getX() + "-" + e.getY());
					}

				}
				if (e.getButton() == MouseEvent.NOBUTTON && status == RECTANGLE) {
					imageClone.setData(image.getRaster());
					Rectangle rect = new Rectangle(imageClone, point, new Point(e.getX(), e.getY()));
					rect.paint();

					refreshDrawPlace(rect.getImage());

					System.out.println(e.getX() + " - " + e.getY());
				}
				if (e.getButton() == MouseEvent.NOBUTTON && status == SQUARE) {
					imageClone.setData(image.getRaster());
					Square sq = new Square(imageClone, point, new Point(e.getX(), e.getY()));
					sq.paint();
					refreshDrawPlace(sq.getImage());
					System.out.println(e.getX() + " - " + e.getY());
				}
			}

		});
		drawContainer.setBounds(10, 45, SCR_HEIGHT, SCR_WIDTH);
		frame.getContentPane().add(drawContainer);
		drawContainer.setLayout(null);

		drawPlace = new JLabel("");
		drawPlace.setBounds(0, 0, SCR_HEIGHT, SCR_WIDTH);
		drawContainer.add(drawPlace);

		createDrawPlace();
		imageClone = new BufferedImage(image.getWidth(), image.getHeight(), image.getType());
		drawContainer.addMouseListener(new MouseAdapter() {
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
					rect.doiXung(point);
				}
				if (e.getButton() == MouseEvent.BUTTON1 && status == SQUARE) {
					Square sq = new Square(image, point, new Point(e.getX(), e.getY()));
					sq.paint();
					image = sq.getImage();
					refreshDrawPlace(image);
					System.out.println("released");
					status = 0;
				}

				if (e.getButton() == MouseEvent.BUTTON1 && status == DUONG_TRON) {

					int R = Point.distance(point, new Point(e.getX(), e.getY()));

					DuongTron dTron = new DuongTron(image, point, R);
					dTron.duongtronMid();
					System.out.println(e.getX() + " - " + e.getY());
					image = dTron.getImage();
					refreshDrawPlace(image);
					System.out.println("released");
					status = 0;
				}
				if (e.getButton() == MouseEvent.BUTTON1 && status == ELLIPSE) {

					int bankinhNho = Point.distance(point, new Point(e.getX(), point.getY()));
					int bankinhLon = Point.distance(point, new Point(point.getX(), e.getY()));
					Ellipse elip = new Ellipse(image, point, bankinhNho, bankinhLon);
					elip.ellipseBre();
					ellipse = elip;
					System.out.println(e.getX() + "-" + e.getY());
					image = elip.getImage();
					refreshDrawPlace(image); 
					System.out.println("released");
					status = 0;

				}
				if (e.getButton() == MouseEvent.BUTTON1 && status == SCALE) {
					for (Point p : ellipse.getPoints()) {
						Point temp = PhepBienDoi.getPointFromMatrix(PhepBienDoi.scale(p, 0.5, 0.5));
						Main.drawPoint(temp, image);
					}
					refreshDrawPlace(image);
					System.out.println("scale");
					
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
		});

		btnPoint = new JButton("Point");
		btnPoint.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				status = POINT;
			}
		});
		btnPoint.setBounds(10, 11, 62, 23);
		frame.getContentPane().add(btnPoint);

		JButton btnLine = new JButton("Line");
		btnLine.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				status = LINE;
			}
		});
		btnLine.setBounds(82, 11, 62, 23);
		frame.getContentPane().add(btnLine);

		JButton btnRect = new JButton("Rectangle");
		btnRect.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				status = RECTANGLE;
			}
		});
		btnRect.setBounds(155, 11, 62, 23);
		frame.getContentPane().add(btnRect);

		JButton btnSquare = new JButton("Square");
		btnSquare.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				status = SQUARE;
			}
		});
		btnSquare.setBounds(227, 11, 83, 23);
		frame.getContentPane().add(btnSquare);

		JButton btnDuongtron = new JButton("DuongTron");
		btnDuongtron.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				status = DUONG_TRON;
			}
		});
		btnDuongtron.setBounds(320, 11, 83, 23);
		frame.getContentPane().add(btnDuongtron);

		btnElip = new JButton("elip");
		btnElip.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				status = ELLIPSE;
			}
		});
		btnElip.setBounds(413, 11, 83, 23);
		frame.getContentPane().add(btnElip);

		JButton btnScale = new JButton("Scale");
		btnScale.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				status = SCALE;
			}
		});
		btnScale.setBounds(500, 11, 89, 23);
		frame.getContentPane().add(btnScale);
	}

	public static void drawPoint(Point point, BufferedImage image) {
		image.setRGB(point.getX(), point.getY(), color.getRGB());
	}

	private void createDrawPlace() {
		int type = BufferedImage.TYPE_INT_ARGB;
		image = new BufferedImage(SCR_HEIGHT, SCR_WIDTH, type);
		drawPlaceBG = new ImageIcon(image);
	}

	private void refreshDrawPlace(BufferedImage image) {
		drawPlace.setIcon(new ImageIcon(image));
	}
}
