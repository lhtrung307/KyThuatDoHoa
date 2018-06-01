package kythuatdohoa;

public class PhepBienDoi {

	public static double[][] multiMatrix(double[][] matA, double[][] matB) {
		double res[][] = new double[matA.length][];
		for (int i = 0; i < res.length; i++) {
			res[i] = new double[matA[i].length];
		}
		for (int i = 0; i < matA.length; i++) {
			for (int k = 0; k < matB.length; k++) {
				double temp = 0;
				for (int j = 0; j < matA[i].length; j++) {
					temp += matA[i][j] * matB[j][k];
				}
				res[i][k] = temp;
			}
		}
		return res;
	}

	public static Point scaling(Point p, double sx, double sy) {
		double matP[][] = { { p.getX(), p.getY(), 1 } };
		double mat[][] = { { sx, 0, 0 }, { 0, sy, 0 }, { 0, 0, 1 } };
		p = getPointFromMatrix(multiMatrix(matP, mat));
		return p;
	}

	public static double[][] reflectionO(Point p) {
		double matP[][] = { { p.getX(), p.getY(), 1 } };
		double mat[][] = { { -1, 0, 0 }, { 0, -1, 0 }, { 0, 0, 1 } };
		return multiMatrix(matP, mat);
	}
	
	public static double[][] reflectionOx(Point p) {
		double matP[][] = { { p.getX(), p.getY(), 1 } };
		double mat[][] = { { 1, 0, 0 }, { 0, -1, 0 }, { 0, 0, 1 } };
		return multiMatrix(matP, mat);
	}
	
	public static double[][] reflectionOy(Point p) {
		double matP[][] = { { p.getX(), p.getY(), 1 } };
		double mat[][] = { { -1, 0, 0 }, { 0, 1, 0 }, { 0, 0, 1 } };
		return multiMatrix(matP, mat);
	}
	
	public static double[][] reflectionPoint(Point p, int xDistance, int yDistance) {
		double matP[][] = { { p.getX(), p.getY(), 1 } };
		double mat[][] = { { -1, 0, 0 }, { 0, -1, 0 }, { 2*xDistance, 2*yDistance, 1 } };
		return multiMatrix(matP, mat);
	}

	public static double[][] translation(Point p, int xDistance, int yDistance) {
		double matP[][] = { { p.getX(), p.getY(), 1 } };
		double mat[][] = { { 1, 0, 0 }, { 0, 1, 0 }, { xDistance, yDistance, 1 } };
		return multiMatrix(matP, mat);
	}

	public static Point rotation(Point p, Point p1, double theta) {
		double radians = Math.toRadians(theta);
		p.x -= p1.x;
		p.y -= p1.y;
		double matP[][] = { { p.getX(), p.getY(), 1 } };
		double sinTheta = Math.sin(radians);
		double cosTheta = Math.cos(radians);
		double[][] rotMatrix = { { cosTheta, -sinTheta, 0 }, { sinTheta, cosTheta, 0 }, { 0, 0, 1 } };
		Point newPoint = getPointFromMatrix(multiMatrix(matP, rotMatrix));
		p.x = newPoint.x + p1.x;
		p.y = newPoint.y + p1.y;
		return p;
	}

//	public static Point getPointFromMatrix(double matrix[][]) {
//		return new Point((int)Math.round((matrix[0][0])), (int)Math.round((matrix[0][1])));
//	}
	
	public static Point getPointFromMatrix(double matrix[][]) {
		return new Point((int)(matrix[0][0]), (int)(matrix[0][1]));
	}
}
