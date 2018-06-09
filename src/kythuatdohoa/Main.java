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
import javax.swing.JTabbedPane;
import javax.swing.JPanel;
import java.awt.SystemColor;
import java.awt.Font;

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
	// private JButton btnRotation;
	private JButton btnRotato;
	private JButton btnSuperdupership;
	private JButton btnDone;

	private JComboBox boxReflection;
	private JComboBox comboBox;

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
		drawContainer.setBackground(SystemColor.menu);
		drawContainer.setBounds(10, 45, 700, 700);
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
		
		createBtnReflection();
		frame.getContentPane().add(boxReflection);

		createBtnCube3d();
		frame.getContentPane().add(btnCube3d);

		createBtnTranslation();
		frame.getContentPane().add(btnTranslation);

		JButton btnSaveImage = new JButton("Save Image");
		btnSaveImage.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				if (!drawContainer.getDrawPlace().isSaveImage()) {
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
				drawContainer.getDrawPlace().drawCoordinate2D(Color.WHITE);
				drawContainer.getShapes().clear();
				drawContainer.getDrawPlace().repaint(drawContainer.getShapes());
				drawContainer.getDrawPlace().drawCoordinate3D(Color.BLACK);
			}
		});
		btnPyramid.setBounds(613, 11, 83, 23);
		frame.getContentPane().add(btnPyramid);

		// {
		// btnRotation = new JButton("Rotation");
		// btnRotation.addActionListener(new ActionListener() {
		// public void actionPerformed(ActionEvent e) {
		// drawContainer.setStatus(DrawContainer.ROTATION);
		// }
		// });
		// btnRotation.setBounds(788, 11, 87, 23);
		// frame.getContentPane().add(btnRotation);
		// }
		{
			btnRotato = new JButton("Rotato");
			btnRotato.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					drawContainer.setStatus(DrawContainer.ROTATO);
				}
			});
			btnRotato.setBounds(897, 11, 77, 23);
			frame.getContentPane().add(btnRotato);
		}

		JButton btnSuperDuperShape = new JButton("SuperDuperShape");
		btnSuperDuperShape.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				drawContainer.setStatus(DrawContainer.SUPER_DUPER_SHAPE);
//				drawContainer.getShapes().clear();
//				drawContainer.getScalePoints().clear();
//				drawContainer.getDrawPlace().repaint(drawContainer.getShapes());
			}
		});
		btnSuperDuperShape.setFont(new Font("Tahoma", Font.PLAIN, 26));
		btnSuperDuperShape.setBounds(720, 190, 242, 61);
		frame.getContentPane().add(btnSuperDuperShape);
		{
			btnSuperdupership = new JButton("SuperDuperShip");
			btnSuperdupership.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					drawContainer.setStatus(DrawContainer.SUPER_DUPER_SHIP);
//					drawContainer.getShapes().clear();
//					drawContainer.getScalePoints().clear();
//					drawContainer.getDrawPlace().repaint(drawContainer.getShapes());
				}
			});
			btnSuperdupership.setFont(new Font("Tahoma", Font.PLAIN, 26));
			btnSuperdupership.setBounds(720, 262, 242, 52);
			frame.getContentPane().add(btnSuperdupership);
		}
		{
			comboBox = new JComboBox();
			comboBox.setBounds(720, 157, 88, 20);
			comboBox.addItem("Green");
			comboBox.addItem("Red");
			comboBox.addItem("Black");
			comboBox.addItem("Blue");
			comboBox.addItem("Yellow");
			comboBox.addItem("Pink");
			comboBox.addItem("Orange");
			comboBox.addItem("White");
			comboBox.setSelectedItem(null);
			frame.getContentPane().add(comboBox);
		}
		{
			btnDone = new JButton("Done");
			btnDone.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent arg0) {
					String value = (String) comboBox.getSelectedItem();
					if (value == "Blue") {
						drawContainer.setStatus(DrawContainer.COLORING);
						color = Color.BLUE;

					}
					if (value == "Red") {
						drawContainer.setStatus(DrawContainer.COLORING);
						color = Color.red;
					}
					if (value == "Black") {
						drawContainer.setStatus(DrawContainer.COLORING);
						color = Color.BLACK;
					}

					if (value == "Green") {
						drawContainer.setStatus(DrawContainer.COLORING);
						color = Color.GREEN;
					}
					if (value == "Yellow") {
						drawContainer.setStatus(DrawContainer.COLORING);
						color = Color.YELLOW;
					}

					if (value == "Pink") {
						drawContainer.setStatus(DrawContainer.COLORING);
						color = Color.PINK;
					}

					if (value == "Orange") {
						drawContainer.setStatus(DrawContainer.COLORING);
						color = Color.ORANGE;
					}

					if (value == "White") {
						drawContainer.setStatus(DrawContainer.COLORING);
						color = Color.WHITE;
					}

				}
			});
			btnDone.setBounds(865, 156, 97, 23);
			frame.getContentPane().add(btnDone);
		}
	}

	public static void drawPoint(Point point, BufferedImage image) {
		if (point.getX() < image.getWidth() && point.getX() > 0 
				&& point.getY() < image.getHeight() && point.getY() > 0) {
			image.setRGB(point.getX(), point.getY(), color.getRGB());
		}
	}

	private void createBtnPoint() {
		btnPoint = new JButton("Point");
		btnPoint.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				drawContainer.setStatus(DrawContainer.POINT);
			}
		});
		btnPoint.setBounds(10, 11, 70, 23);
	}

	private void createBtnLine() {
		btnLine = new JButton("Line");
		btnLine.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				drawContainer.setStatus(DrawContainer.LINE);
			}
		});
		btnLine.setBounds(90, 11, 70, 23);
	}

	private void createBtnRect() {
		btnRect = new JButton("Rectangle");

		btnRect.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				drawContainer.setStatus(DrawContainer.RECTANGLE);
			}
		});

		btnRect.setBounds(170, 11, 83, 23);
	}

	private void createBtnSquare() {
		btnSquare = new JButton("Square");

		btnSquare.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				drawContainer.setStatus(DrawContainer.SQUARE);
			}
		});

		btnSquare.setBounds(263, 11, 77, 23);
	}

	private void createBtnCircle() {
		btnCircle = new JButton("DuongTron");
		btnCircle.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				drawContainer.setStatus(DrawContainer.DUONG_TRON);
			}
		});
		btnCircle.setBounds(350, 11, 88, 23);
	}

	private void createBtnElip() {
		btnElip = new JButton("elip");
		btnElip.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				drawContainer.setStatus(DrawContainer.ELLIPSE);
			}
		});
		btnElip.setBounds(448, 11, 62, 23);
	}

	private void createBtnScale() {
		btnScale = new JButton("Scale");
		btnScale.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				drawContainer.setStatus(DrawContainer.SCALING);
			}
		});

		btnScale.setBounds(707, 11, 70, 23);
	}

	private void createBtnCube3d() {
		btnCube3d = new JButton("Cube 3d");
		btnCube3d.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				drawContainer.setStatus(DrawContainer.CUBE3D);
				drawContainer.getDrawPlace().drawCoordinate2D(Color.WHITE);
				drawContainer.getShapes().clear();
				drawContainer.getDrawPlace().repaint(drawContainer.getShapes());
				drawContainer.getDrawPlace().drawCoordinate3D(Color.BLACK);
			}
		});
		btnCube3d.setBounds(520, 11, 83, 23);
	}

	private void createBtnReflection() {
		int[] numb = { 1, 2, 3, 4 };
		String[] name = { "Doi xung tam O", "Doi xung truc Ox", "Doi xung truc Oy", "Doi xung diem" };
		boxReflection = new JComboBox<String>(name);
		boxReflection.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				drawContainer.setStatus(DrawContainer.REFLECTION, numb[boxReflection.getSelectedIndex()]);
			}
		});
		boxReflection.setBounds(720, 90, 100, 23);
	}

	private void createBtnTranslation() {
		btnTranslation = new JButton("Translation");
		btnTranslation.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				drawContainer.setStatus(DrawContainer.TRANSLATION);
			}
		});
		btnTranslation.setBounds(787, 11, 99, 23);
	}
}