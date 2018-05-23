package kythuatdohoa;

import java.awt.image.BufferedImage;
import java.util.Vector;

import javax.swing.JLabel;

import kythuatdohoa.Main;
import kythuatdohoa.Point;

public class Paint extends Thread {
	Point Pi = new Point();
	Vector E = new Vector();
	BufferedImage iImage;
	JLabel lbImage;
	Point P;
	String mauTo;
	String mauBien;

	public Paint(BufferedImage iImage, JLabel lbImage, Point p, String mauTo, String mauBien) {

		this.iImage = iImage;
		this.lbImage = lbImage;
		P = p;
		this.mauTo = mauTo;
		this.mauBien = mauBien;
	}

	public boolean dk(Point dinh) {
		String mauHienTai = dinh.getPointRGB(iImage);
		if (dinh.getX() < 1 || dinh.getY() < 1 || dinh.getX() > 638 || dinh.getY() > 478) {
			return false;
		}
		if ((mauHienTai == mauTo) || (mauHienTai == mauBien)) {
			return false;
		}
		return true;
	}

	public void Loang(Point P) {

		Point Q;
		if (mauTo == "GREEN")
			P.paint(iImage, "GREEN");
		else if (mauTo == "RED")
			P.paint(iImage, "RED");
		else if (mauTo == "BLUE")
			P.paint(iImage, "BLUE");
		else if (mauTo == "BLACK")
			P.paint(iImage, "BLACK");
		else if (mauTo == "WHITE")
			P.paint(iImage, "WHILE");
		else if (mauTo == "SADDLEBROWN")
			P.paint(iImage, "SADDLEBROWN");

		int x = P.getX();
		int y = P.getY();
		Q = new Point(x - 1, y);
		if (dk(Q))
			E.addElement(Q);
		Q = new Point(x + 1, y);
		if (dk(Q))
			E.addElement(Q);
		Q = new Point(x, y - 1);
		if (dk(Q))
			E.addElement(Q);
		Q = new Point(x, y + 1);
		if (dk(Q))
			E.addElement(Q);

	}

	public void run() {
		E.addElement(P);
		while (E.size() != 0) {
//			Main.capnhat();
			System.out.println("thinh");
			Pi = (Point) E.remove(E.size() - 1);
			Loang(Pi);

		}

	}

	public BufferedImage GetTomau() {
		return iImage;
	}

}
