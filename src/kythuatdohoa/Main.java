package kythuatdohoa;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JButton;
import javax.swing.JColorChooser;

import java.awt.Color;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.image.BufferedImage;
import java.util.ArrayList;
import javax.swing.JLabel;

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
		frame.setBounds(100, 100, SCR_HEIGHT, SCR_WIDTH);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);
		drawContainer = new DrawContainer();
		drawContainer.setBounds(10, 45, SCR_HEIGHT, SCR_WIDTH);
//		drawContainer.drawCoordinate3D(color); // Ve Toa Do
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
				drawContainer.setStatus(DrawContainer.LINE);;
			}
		});
		btnLine.setBounds(82, 11, 62, 23);
		frame.getContentPane().add(btnLine);

		JButton btnRect = new JButton("Rectangle");
		btnRect.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				drawContainer.setStatus(DrawContainer.RECTANGLE);

			}
		});
		btnRect.setBounds(155, 11, 62, 23);
		frame.getContentPane().add(btnRect);

		JButton btnSquare = new JButton("Square");
		btnSquare.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				drawContainer.setStatus(DrawContainer.SQUARE);
			}
		});
		btnSquare.setBounds(227, 11, 62, 23);
		frame.getContentPane().add(btnSquare);

		JButton btnDuongtron = new JButton("DuongTron");
		btnDuongtron.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				drawContainer.setStatus(DrawContainer.DUONG_TRON);
			}
		});
		btnDuongtron.setBounds(299, 11, 83, 23);
		frame.getContentPane().add(btnDuongtron);

		btnElip = new JButton("elip");
		btnElip.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				drawContainer.setStatus(DrawContainer.ELLIPSE);
			}
		});
		btnElip.setBounds(392, 11, 62, 23);
		frame.getContentPane().add(btnElip);

		JButton btnScale = new JButton("Scale");
		btnScale.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				drawContainer.setStatus(DrawContainer.SCALE);
			}
		});
		btnScale.setBounds(464, 11, 70, 23);
		frame.getContentPane().add(btnScale);
		
		JButton btnColoring = new JButton("Coloring");
		btnColoring.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				drawContainer.setStatus(DrawContainer.COLORING);
			}
		});
		btnColoring.setBounds(537, 11, 56, 23);
		frame.getContentPane().add(btnColoring);
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
}