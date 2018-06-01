package kythuatdohoa;

import java.awt.image.BufferedImage;

public class SuperAmazingShape extends Shape<SuperAmazingShape> {
	private DuongTron dau;
	private DuongTron dit;
	private BresenhamLine mui1;
	private BresenhamLine mui2;
	private DuongTron matTrai;
	private DuongTron matPhai;
	private BresenhamLine tayTrai;
	private BresenhamLine tayPhai;
	private DuongTron cuc1;
	private DuongTron cuc2;
	private DuongTron cuc3;
	private BresenhamLine ngonTrai1;
	private BresenhamLine ngonTrai2;
	private BresenhamLine ngonTrai3;
	private BresenhamLine ngonPhai1;
	private BresenhamLine ngonPhai2;
	private BresenhamLine ngonPhai3;
	
//	public SuperAmazingShape(DuongTron dau, DuongTron dit, BresenhamLine mui1, BresenhamLine mui2, DuongTron mat1,
//			DuongTron mat2, BresenhamLine tayTrai, BresenhamLine tayPhai) {
//		super();
//		this.dau = dau;
//		this.dit = dit;
//		this.mui1 = mui1;
//		this.mui2 = mui2;
//		this.mat1 = mat1;
//		this.mat2 = mat2;
//		this.tayTrai = tayTrai;
//		this.tayPhai = tayPhai;
//	}
	
	public SuperAmazingShape() {
		super();
		dau = new DuongTron(new Point(160, 90), 30);
		dau.duongtronMid();
		points.addAll(dau.getPoints());
		
		dit = new DuongTron(new Point(160, 170), 50);
		dit.duongtronMid();
		points.addAll(dit.getPoints());
		
		mui1 = new BresenhamLine(new Point(195, 105), new Point(160, 90));
		mui1.drawLine();
		points.addAll(mui1.getPoints());
		
		mui2 = new BresenhamLine(new Point(195, 105), new Point(160, 97));
		mui2.drawLine();
		points.addAll(mui2.getPoints());
		
		matTrai = new DuongTron(new Point(150, 81), 5);
		matTrai.duongtronMid();
		points.addAll(matTrai.getPoints());
		
		matPhai = new DuongTron(new Point(170, 81), 5);
		matPhai.duongtronMid();
		points.addAll(matPhai.getPoints());
		
		tayTrai = new BresenhamLine(new Point(192, 132), new Point(243, 112));
		tayTrai.drawLine();
		points.addAll(tayTrai.getPoints());
		
		ngonTrai1 = new BresenhamLine(new Point(243, 112), new Point(243, 100));
		ngonTrai1.drawLine();
		points.addAll(ngonTrai1.getPoints());
		ngonTrai2 = new BresenhamLine(new Point(243, 112), new Point(260, 103));
		ngonTrai2.drawLine();
		points.addAll(ngonTrai2.getPoints());
		ngonTrai3 = new BresenhamLine(new Point(243, 112), new Point(256, 117));
		ngonTrai3.drawLine();
		points.addAll(ngonTrai3.getPoints());
		
		tayPhai = new BresenhamLine(new Point(128, 132), new Point(77, 112));
		tayPhai.drawLine();
		points.addAll(tayPhai.getPoints());
		
		ngonPhai1 = new BresenhamLine(new Point(77, 112), new Point(77, 100));
		ngonPhai1.drawLine();
		points.addAll(ngonPhai1.getPoints());
		ngonPhai2 = new BresenhamLine(new Point(77, 112), new Point(60, 103));
		ngonPhai2.drawLine();
		points.addAll(ngonPhai2.getPoints());
		ngonPhai3 = new BresenhamLine(new Point(77, 112), new Point(64, 117));
		ngonPhai3.drawLine();
		points.addAll(ngonPhai3.getPoints());
		
		cuc1 = new DuongTron(new Point(160, 150), 7);
		cuc1.duongtronMid();
		points.addAll(cuc1.getPoints());
		
		cuc2 = new DuongTron(new Point(160, 170), 7);
		cuc2.duongtronMid();
		points.addAll(cuc2.getPoints());
		
		cuc3 = new DuongTron(new Point(160, 190), 7);
		cuc3.duongtronMid();
		points.addAll(cuc3.getPoints());
	}

	@Override
	public void drawShape(BufferedImage image) {
		for (Point point : points) {
			Main.drawPoint(point, image);
		}
	}

	@Override
	public void scale(double sx, double sy) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public SuperAmazingShape rotation(double theta, Point p) {
		for (Point point : points) {
			point = point.rotation(theta, p);
//			points.add(temp);
		}
		return this;
	}
	
	
}
