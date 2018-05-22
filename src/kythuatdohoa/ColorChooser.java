package kythuatdohoa;

import java.awt.Color;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.util.Vector;

import javax.swing.JButton;
import javax.swing.JColorChooser;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JTextField;



public class ColorChooser extends JFrame {

	 private JTextField txt;
	private JButton btn;
	
	
	Point Pi = new Point();
	Vector E = new Vector();
	BufferedImage iImage;
	JLabel lbImage;
	Point P;
	String mauto;
	String maubien;


	public ColorChooser() {
		// TODO Auto-generated constructor stub
		this.setTitle("JColorChooser demo");
		this.setSize(350, 100);
		this.setLayout(new FlowLayout());
		this.setLocationRelativeTo(null);
		this.setDefaultCloseOperation(EXIT_ON_CLOSE);

		 txt = new JTextField(30);
		 this.getContentPane().add(txt);
		btn = new JButton("click me");
		this.getContentPane().add(btn);

		btn.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				Color color = JColorChooser.showDialog(null, "Choose Color", getBackground());
				// txt.setForeground(color);
			}
		});
	}

}

