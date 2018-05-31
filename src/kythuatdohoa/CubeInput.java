package kythuatdohoa;

import javax.swing.JPanel;
import javax.swing.JLabel;
import javax.swing.JTextField;

public class CubeInput extends JPanel {
	private JTextField xCoor;
	private JTextField yCoor;
	private JTextField zCoor;
	private JTextField length;

	/**
	 * Create the panel.
	 */
	public CubeInput() {
		setLayout(null);
		
		JLabel lblX = new JLabel("X: ");
		lblX.setBounds(10, 7, 19, 32);
		add(lblX);
		
		xCoor = new JTextField();
		xCoor.setBounds(62, 13, 50, 20);
		add(xCoor);
		xCoor.setColumns(10);
		
		JLabel lblY = new JLabel("Y: ");
		lblY.setBounds(10, 42, 19, 20);
		add(lblY);
		
		yCoor = new JTextField();
		yCoor.setBounds(61, 44, 51, 20);
		add(yCoor);
		yCoor.setColumns(10);
		
		JLabel lblZ = new JLabel("Z: ");
		lblZ.setBounds(10, 75, 19, 20);
		add(lblZ);
		
		zCoor = new JTextField();
		zCoor.setBounds(62, 75, 50, 20);
		zCoor.setColumns(4);
		add(zCoor);
		
		JLabel lblLength = new JLabel("Length");
		lblLength.setBounds(10, 106, 50, 20);
		add(lblLength);
		
		length = new JTextField();
		length.setBounds(62, 106, 50, 20);
		length.setColumns(4);
		add(length);

	}

	public JTextField getxCoor() {
		return xCoor;
	}

	public JTextField getyCoor() {
		return yCoor;
	}

	public JTextField getzCoor() {
		return zCoor;
	}

	public JTextField getLength() {
		return length;
	}

}
