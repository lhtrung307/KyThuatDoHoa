package kythuatdohoa;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.JButton;
import javax.swing.JComboBox;

import java.awt.Color;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.image.BufferedImage;

public class Main {
	public static int SCR_HEIGHT = 482;
	public static int SCR_WIDTH = 642;
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
	private JButton btnTranslation;
	private JButton btnRotation;
	private JComboBox boxReflection;

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
		drawContainer.setBackground(Color.WHITE);
		drawContainer.setBounds(10, 45, SCR_WIDTH, SCR_HEIGHT);
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

//		createBtnReflection();
//		frame.getContentPane().add(boxReflection);

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
		
		createBtnTranslation();
		frame.getContentPane().add(btnTranslation);
		
		createBtnRotation();
		frame.getContentPane().add(btnRotation);
		
		JButton btnSaveImage = new JButton("Save Image");
		btnSaveImage.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				if(!drawContainer.getDrawPlace().isSaveImage()) {
					JOptionPane.showConfirmDialog(btnSaveImage, null, "Saved", JOptionPane.OK_OPTION);
				} else {
					JOptionPane.showConfirmDialog(btnSaveImage, null, "Error! Try again!", JOptionPane.OK_OPTION);
				}
			}
		});
		btnSaveImage.setBounds(563, 538, 89, 23);
		frame.getContentPane().add(btnSaveImage);
		
		JButton btnPyramid = new JButton("Pyramid");
		btnPyramid.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				drawContainer.setStatus(DrawContainer.PYRAMID3D);
			}
		});
		btnPyramid.setBounds(819, 11, 89, 23);
		frame.getContentPane().add(btnPyramid);
	
//		JLabel lblChooseColor = new JLabel("");
//		lblChooseColor.setBounds(544, 15, 46, 14);
//		frame.getContentPane().add(lblChooseColor);
//		JColorChooser chooseColor = new
//		JColorChooser(lblChooseColor.getForeground());
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
				drawContainer.setStatus(DrawContainer.LINE);
				;
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

//	private void createBtnReflection() {
//		int[] numb = { 1, 2, 3 };
//		String[] name = { "Doi xung tam O", "Doi xung truc Ox", "Doi xung truc Oy" };
//		boxReflection = new JComboBox<String>(name);
//		boxReflection.addActionListener(new ActionListener() {
//			public void actionPerformed(ActionEvent arg0) {
//				drawContainer.setStatus(DrawContainer.REFLECTION, numb[boxReflection.getSelectedIndex()]);
//			}
//		});
//		boxReflection.setBounds(830, 11, 100, 23);
//	}
	
	private void createBtnRotation() {
		btnRotation = new JButton("Rotation");
		btnRotation.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				drawContainer.setStatus(DrawContainer.ROTATION);
			}
		});
		btnRotation.setBounds(747, 11, 62, 23);
	}
	
	private void createBtnTranslation() {
		btnTranslation = new JButton("Translation");
		btnTranslation.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				drawContainer.setStatus(DrawContainer.TRANSLATION);
			}
		});
		btnTranslation.setBounds(667, 11, 70, 23);
	}
}