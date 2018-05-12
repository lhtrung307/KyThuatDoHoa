package kythuatdohoa;

import java.awt.Color;
import java.awt.image.BufferedImage;

public class Point {
private int X;
private int Y;
public Point() {
	 this.X=0;
	 this.Y=0;
}
public Point(int x, int y) {
	 if(x<0) x=0; else if(x>639) x=639;
	 this.X=x;
	 if(y<0) y=0; else if(y>479) y=479;
	 this.Y=y;
}

public int getX() {
	return X;
}
public void setX(int x) {
	X = x;
}
public int getY() {
	return Y;
}
public void setY(int y) {
	Y = y;
}

public void paint(BufferedImage iImage,String mau){
	Color c;
	if (mau=="BIEN") c = new Color(230,0,0, 255);
	else if(mau=="BLACK") c = new Color(0,0,0, 255);
	else if(mau=="RED") c = new Color(255,0,0, 255);
	else if(mau=="OXY") c = new Color(1,1,1, 255);
	else if(mau=="GREEN") c = new Color(0,255,0, 255);
	else if(mau=="BLUE") c = new Color(0,0,255, 255);
	else c = new Color(255,255,255, 255);
	int k = c.getRGB();
	iImage.setRGB(X, Y, k);
}
 public String getPointRGB(BufferedImage iImage){
	 int pixel = iImage.getRGB(X, Y);
     int alpha = (pixel >> 24) & 0xff;
     int r = (pixel >> 16) & 0xff;
     int g = (pixel >> 8) & 0xff;
     int b = (pixel) & 0xff;
     if(r==255 && g ==0 & b==0) return ("RED");
     if(r==230 && g ==0 & b==0) return ("BIEN");
     if(r==0 && g ==0 & b==0) return ("BLACK");
     if(r==1 && g ==1 & b==1) return ("OXY");
     if(r==255 && g ==255 & b==255) return ("WHITE");
     if(r==0 && g ==255 & b==0) return ("GREEN");
     if(r==0 && g ==0 & b==255) return ("BLUE");
     return "t";
 }
 public String getPoint(){
	 return ("("+ this.X+" ; "+this.Y+")");
 }
 

}
