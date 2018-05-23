package kythuatdohoa;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JButton;

import java.awt.Color;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.image.BufferedImage;

public class Main {
	public static int SCR_HEIGHT = 642;
	public static int SCR_WIDTH = 482;
	private JFrame frame;
	public static Color color;
	private DrawContainer drawContainer;
	private JButton btnPoint;
	private JButton btnLine;
	private JButton btnRect;
	private JButton btnSquare;
	private JButton btnCircle;
	private JButton btnElip;
	private JButton btnScale;
	private JButton btnCube3d;
	private JButton btnNewButton;
	private JButton btnRotation;
	
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
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
		color = new Color(0, 0, 0);
		frame.setBounds(100, 100, 1000, 700);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);
		drawContainer = new DrawContainer();
		drawContainer.setBounds(10, 45, SCR_HEIGHT, SCR_WIDTH);
		drawContainer.drawCoordinate2D(color); // Ve Toa Do
		frame.getContentPane().add(drawContainer);
		drawContainer.setLayout(null);	

		createBtnPoint();
		frame.getContentPane().add(btnPoint);

		createBtnLine();
		frame.getContentPane().add(btnLine);

		createBtnRect();
		frame.getContentPane().add(btnRect);

		createBtnSquare();
		frame.getContentPane().add(btnSquare);

		createBtnCircle();
		frame.getContentPane().add(btnCircle);

		createBtnElip();
		frame.getContentPane().add(btnElip);

		createBtnScale();
		frame.getContentPane().add(btnScale);
		
		JButton btnColoring = new JButton("Coloring");
		btnColoring.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				drawContainer.setStatus(DrawContainer.COLORING);
			}
		});
		btnColoring.setBounds(537, 11, 56, 23);
		frame.getContentPane().add(btnColoring);
		
		createBtnCube3d();
		frame.getContentPane().add(btnCube3d);
		{
			btnNewButton = new JButton("Translation");
			btnNewButton.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					drawContainer.setStatus(DrawContainer.TRANSLATION);
				}
			});
			btnNewButton.setBounds(667, 11, 70, 23);
			frame.getContentPane().add(btnNewButton);
		}
		{
			btnRotation = new JButton("Rotation");
			btnRotation.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					drawContainer.setStatus(DrawContainer.ROTATION);
				}
			});
			btnRotation.setBounds(747, 11, 62, 23);
			frame.getContentPane().add(btnRotation);
		}
//		
//		JLabel lblChooseColor = new JLabel("");
//		lblChooseColor.setBounds(544, 15, 46, 14);
//		frame.getContentPane().add(lblChooseColor);
//		JColorChooser chooseColor = new JColorChooser(lblChooseColor.getForeground());
//		frame.getContentPane().add(chooseColor);
	}

	

	public static void drawPoint(Point point, BufferedImage image) {
		image.setRGB(point.getX(), point.getY(), color.getRGB());
	}
	
	private void createBtnPoint() {
		btnPoint = new JButton("Point");
		btnPoint.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				drawContainer.setStatus(DrawContainer.POINT);
			}
		});
		btnPoint.setBounds(10, 11, 62, 23);
	}
	
	private void createBtnLine() {
		btnLine = new JButton("Line");
		btnLine.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				drawContainer.setStatus(DrawContainer.LINE);;
			}
		});
		btnLine.setBounds(82, 11, 49, 23);
	}
	
	private void createBtnRect() {
		btnRect = new JButton("Rectangle");

		btnRect.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				drawContainer.setStatus(DrawContainer.RECTANGLE);
			}
		});

		btnRect.setBounds(141, 11, 62, 23);
	}
	
	private void createBtnSquare() {
		btnSquare = new JButton("Square");

		btnSquare.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				drawContainer.setStatus(DrawContainer.SQUARE);
			}
		});

		btnSquare.setBounds(213, 11, 62, 23);
	}
	
	private void createBtnCircle() {
		btnCircle = new JButton("DuongTron");
		btnCircle.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				drawContainer.setStatus(DrawContainer.DUONG_TRON);
			}
		});
		btnCircle.setBounds(299, 11, 83, 23);
	}
	
	private void createBtnElip() {
		btnElip = new JButton("elip");
		btnElip.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				drawContainer.setStatus(DrawContainer.ELLIPSE);
			}
		});
		btnElip.setBounds(392, 11, 62, 23);
	}
	
	private void createBtnScale() {
		btnScale = new JButton("Scale");
		btnScale.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				drawContainer.setStatus(DrawContainer.SCALING);
			}
		});

		btnScale.setBounds(464, 11, 70, 23);
	}
	
	private void createBtnCube3d() {
		btnCube3d = new JButton("Cube 3d");
		btnCube3d.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				drawContainer.setStatus(DrawContainer.CUBE3D);
			}
		});
		btnCube3d.setBounds(595, 11, 62, 23);
	}
}