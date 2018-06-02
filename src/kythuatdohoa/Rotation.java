package kythuatdohoa;

public class Rotation {

	
public Point xoay(int x, int y, int tamx, int tamy, double goc) {
		
		int x1= x-(tamx+320), y1= y-(tamy+240);
		Point a = new Point();
		int[] h = new int[3]; h[0]=h[1]=h[2]=0;
		int[] b = new int[3]; b[0]=x1; b[1]=y1; b[2]=1;
		double[][] c = new double[3][3];
		
		goc = Math.PI * goc / 180;
		c[0][0] = Math.cos(goc);            c[0][1] = Math.sin(goc);				c[0][2]=0;	
		c[1][0] = -Math.sin(goc);			c[1][1] = Math.cos(goc);				c[1][2]=0;	
		c[2][0] = 0;						c[2][1] = 0;							c[2][2]=1;
		for(int i=0; i<3; i++) {
			for(int j=0; j<3; j++) {
				
				double ta = Math.round(b[j]*c[j][i]);
				System.out.println(ta);
				h[i]+=(int)	ta;
				
			}
		}
		a.setX(h[0]+tamx+320); a.setY(h[1]+tamy+240);
		
		
		return a;
		
	}
}