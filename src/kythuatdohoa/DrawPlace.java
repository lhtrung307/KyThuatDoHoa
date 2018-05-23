package kythuatdohoa;

import java.awt.Color;
import java.awt.image.BufferedImage;
import java.util.ArrayList;

import javax.swing.ImageIcon;
import javax.swing.JColorChooser;
import javax.swing.JLabel;

public class DrawPlace extends JLabel{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private BufferedImage image;
	private ImageIcon drawPlaceBG;
	public static Color BGColor;


	public DrawPlace(ImageIcon drawPlaceBG) {
		super("");
		this.drawPlaceBG = drawPlaceBG;
		createDrawPlace();
		this.setIcon(drawPlaceBG);
	}
	public DrawPlace(){
		super("");
		createDrawPlace();
	}
	
	public void createDrawPlace() {
		int type = BufferedImage.TYPE_INT_ARGB;
		image = new BufferedImage(Main.SCR_HEIGHT, Main.SCR_WIDTH, type);
		BGColor = Color.WHITE;
		setBGColor(BGColor);
		drawPlaceBG = new ImageIcon(image);
		this.setIcon(drawPlaceBG);
		
//		this.drawCoordinate();

//		refreshDrawPlace(image);
	}
	
	public void refreshDrawPlace(BufferedImage image){
		this.setIcon(new ImageIcon(image));
	}
	
	public void setBGColor(Color color) {
		for (int x = 0; x < Main.SCR_HEIGHT; x++) {
			for (int y = 0; y < Main.SCR_WIDTH; y++) {
				image.setRGB(x, y, color.getRGB());
			}
		}
	}
	
	public BufferedImage getImage() {
		return image;
	}
	
	public void setImage(BufferedImage image) {
		this.image = image;
	}
	
	
		
}
