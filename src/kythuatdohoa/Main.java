package kythuatdohoa;

import java.awt.EventQueue;
import java.awt.Image;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.UIManager;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import java.awt.Color;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.image.BufferedImage;
import java.util.ArrayList;

public class Main {

	public static int SCR_HEIGHT = 642;
	public static int SCR_WIDTH = 482;
	private JFrame frame;
	public static Color color;
	private DrawContainer drawContainer;
	private JButton btnPoint;
	private JButton btnElip;
	private BufferedImage image;

	private Ellipse ellipse;
	private DuongTron circle;

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
		color = new Color(0, 0, 0);
		frame.setBounds(100, 100, 740, 482);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);
		drawContainer = new DrawContainer();
		drawContainer.setBounds(10, 45, SCR_HEIGHT, SCR_WIDTH);
		drawContainer.drawCoordinate2D(color);
		frame.getContentPane().add(drawContainer);
		drawContainer.setLayout(null);

		btnPoint = new JButton("Point");
		btnPoint.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				drawContainer.setStatus(DrawContainer.POINT);
			}
		});
		btnPoint.setBounds(10, 11, 62, 23);
		frame.getContentPane().add(btnPoint);

		JButton btnLine = new JButton("Line");
		btnLine.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				drawContainer.setStatus(DrawContainer.LINE);
				;
			}
		});
		btnLine.setBounds(82, 11, 51, 23);
		frame.getContentPane().add(btnLine);

		JButton btnRect = new JButton("Rectangle");
		btnRect.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				drawContainer.setStatus(DrawContainer.RECTANGLE);

			}
		});
		btnRect.setBounds(136, 11, 51, 23);
		frame.getContentPane().add(btnRect);

		JButton btnSquare = new JButton("Square");
		btnSquare.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				drawContainer.setStatus(DrawContainer.SQUARE);
			}
		});
		btnSquare.setBounds(197, 11, 62, 23);
		frame.getContentPane().add(btnSquare);

		JButton btnDuongtron = new JButton("DuongTron");
		btnDuongtron.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				drawContainer.setStatus(DrawContainer.DUONG_TRON);
			}
		});
		btnDuongtron.setBounds(269, 11, 62, 23);
		frame.getContentPane().add(btnDuongtron);

		btnElip = new JButton("elip");
		btnElip.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				drawContainer.setStatus(DrawContainer.ELLIPSE);
			}
		});
		btnElip.setBounds(341, 11, 73, 23);
		frame.getContentPane().add(btnElip);

		JButton btnScale = new JButton("Scale");
		btnScale.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				drawContainer.setStatus(DrawContainer.SCALE);
			}
		});
		btnScale.setBounds(424, 11, 57, 23);
		frame.getContentPane().add(btnScale);

		JButton btnUndo = new JButton("Undo");
		btnUndo.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				drawContainer.setStatus(DrawContainer.UNDO);
			}
		});
		btnUndo.setBounds(491, 11, 59, 23);
		frame.getContentPane().add(btnUndo);

		JButton btnColor = new JButton("Color");
		btnColor.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				// drawContainer.setStatus(DrawContainer.COLOR);
				try {
					UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
					new ColorChooser().show();
				} catch (Exception e2) {
					// TODO: handle exception
				}
			}
		});
		btnColor.setBounds(551, 11, 65, 23);
		frame.getContentPane().add(btnColor);

		JButton btnColouring = new JButton("Colouring");
		btnColouring.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				drawContainer.setStatus(DrawContainer.COLOURING);
			}
		});
		btnColouring.setBounds(625, 11, 89, 23);
		frame.getContentPane().add(btnColouring);
	}

	public static void drawPoint(Point point, BufferedImage image) {
		image.setRGB(point.getX(), point.getY(), color.getRGB());
	}
}