package kythuatdohoa;

import java.awt.Color;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JLabel;

public class DrawPlace extends JLabel{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private BufferedImage image;
	private ImageIcon drawPlaceBG;
	public static Color BGColor;
	public static int size = 20;
	public static int wCell = 20;
	public static int hCell = 10;


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
		image = new BufferedImage(Main.SCR_WIDTH, Main.SCR_HEIGHT, type);
		BGColor = Color.WHITE;
		setBGColor(BGColor);
		drawPlaceBG = new ImageIcon(image);
		this.setIcon(drawPlaceBG);
		
		this.drawCoordinate3D(Main.color);
	}
	
	public void refreshDrawPlace(BufferedImage image){
		this.setIcon(new ImageIcon(image));
	}
	
	public void setBGColor(Color color) {
		for (int x = 0; x < Main.SCR_WIDTH; x++) {
			for (int y = 0; y < Main.SCR_HEIGHT; y++) {
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
	
	public boolean isSaveImage() {
		File output = new File("nguoituyet.jpg");
		try {
			ImageIO.write(image, "jpg", output);
		} catch (Exception e) {
			return false;
		}
		return true;
	}
	
	public boolean loadImage() {
		try {
	         File input = new File("nguoituyet.jpg");
	         image = ImageIO.read(input);
		} catch(Exception e){
			return false;
		}
		return true;
	}
		
	public void drawCoordinate2D(Color coorColor) {
		Color blue = Color.BLUE;
		System.out.println(this.getWidth() - 2);
		int fixW = (Main.SCR_WIDTH - 2) % size == 0 ? (Main.SCR_WIDTH - 2)
				: (Main.SCR_WIDTH - 2) - (Main.SCR_WIDTH - 2) % size;
		int fixH = (Main.SCR_HEIGHT - 2) % size == 0 ? (Main.SCR_HEIGHT - 2)
				: (Main.SCR_HEIGHT - 2) - (Main.SCR_HEIGHT - 2) % size;
		for (int w = 0; w <= fixW; w++) {
			Point top = new Point(w, 0);
			Point bot = new Point(w, fixH);
			// if (w % size == 0) {
			// Main.color = blue;
			// Line line = new Line(top, bot);
			// line.drawShape(image);
			// }
			if (w == fixW / 2) {
				Main.color = coorColor;
				BresenhamLine line = new BresenhamLine(top, bot);
				line.drawShape(image);
				Main.color = coorColor;
			}
			Main.color = coorColor;
		}
		for (int h = 0; h <= fixH; h++) {
			// if (h % size == 0) {
			// Main.color = blue;
			// Point left = new Point(0, h);
			// Point right = new Point(fixW, h);
			// Line line = new Line(left, right);
			// line.drawShape(image);
			// }
			if (h == fixH / 2) {
				Main.color = coorColor;
				Point left = new Point(0, h);
				Point right = new Point(fixW, h);
				System.out.println(left.toString());
				System.out.println(right.toString());
				BresenhamLine line = new BresenhamLine(left, right);
				
				line.drawShape(image);
			}
		}
		Main.color = coorColor;
		// g.setColor(Color.red);
		// g.drawString("O", fixW / 2 + size / 3, fixH / 2 + 2 * size / 3);
		// g.drawString("Y", fixW / 2 + size / 3, 15);
		// g.drawString("X", fixW - 15, fixH / 2 - 5);
	}

	public void drawCoordinate3D(Color coorColor) {
		Color blue = Color.BLUE;
		int fixW = Main.SCR_WIDTH % size == 0 ? Main.SCR_WIDTH : Main.SCR_WIDTH - Main.SCR_WIDTH % size;
		int fixH = Main.SCR_HEIGHT % size == 0 ? Main.SCR_HEIGHT : Main.SCR_HEIGHT - Main.SCR_HEIGHT % size;
		for (int w = 0; w <= fixW; w++) {
//			if (w % size == 0) {
//				Main.color = blue;
//				Point top = new Point(w, 0);
//				Point bot = new Point(w, fixH);
//				Line line = new Line(top, bot);
//				line.BresLineForCoor();
//			}
			if (w == fixW / 2) {
				Main.color = coorColor;
				Point top = new Point(w, 0);
				Point bot = new Point(w, fixH / 2);
				BresenhamLine line = new BresenhamLine(top, bot);
				line.drawShape(image);

				int ww = fixW / 2;
				int hh = fixH / 2;
				for (int h = hh; h > 0; h--, ww--) {
					Main.drawPoint(new Point(ww, fixH - h), image);
				}
				Main.color = coorColor;
			}
		}
		for (int h = 0; h <= fixH; h++) {
//			if (h % size == 0) {
//				Main.color = blue;
//				Point left = new Point(0, h);
//				Point right = new Point(fixW, h);
//				Line line = new Line(left, right);
//				line.BresLineForCoor();
//			}
			if (h == fixH / 2) {
				Main.color = coorColor;
				Point left = new Point(fixW / 2, h);
				Point right = new Point(fixW, h);
				BresenhamLine line = new BresenhamLine(left, right);
				line.drawShape(image);
				Main.color = blue;
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