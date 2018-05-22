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

	public static double[][] scale(Point p, double sx, double sy) {
		double matP[][] = { { p.getX(), p.getY(), 1 } };
		double mat[][] = { { sx, 0, 0 }, { 0, sy, 0 }, { 0, 0, 1 } };
		return multiMatrix(matP, mat);
	}
	
	
	public static Point getPointFromMatrix (double matrix[][]) {
		return new Point((int)matrix[0][0], (int)matrix[0][1]);
	}
}
