package kythuatdohoa;

import java.awt.Color;
import java.awt.image.BufferedImage;

public class Cube3D extends Shape {

	int[] node0 = { -100, -100, -100 };
	int[] node1 = { -100, -100, 100 };
	int[] node2 = { -100, 100, -100 };
	int[] node3 = { -100, 100, 100 };
	int[] node4 = { 100, -100, -100 };
	int[] node5 = { 100, -100, 100 };
	int[] node6 = { 100, 100, -100 };
	int[] node7 = { 100, 100, 100 };
	int[][] nodes = { node0, node1, node2, node3, node4, node5, node6, node7 };

	int[] edge0 = { 0, 1 };
	int[] edge1 = { 1, 3 };
	int[] edge2 = { 3, 2 };
	int[] edge3 = { 2, 0 };
	int[] edge4 = { 4, 5 };
	int[] edge5 = { 5, 7 };
	int[] edge6 = { 7, 6 };
	int[] edge7 = { 6, 4 };
	int[] edge8 = { 0, 4 };
	int[] edge9 = { 1, 5 };
	int[] edge10 = { 2, 6 };
	int[] edge11 = { 3, 7 };
	int[][] edges = { edge0, edge1, edge2, edge3, edge4, edge5, edge6, edge7, edge8, edge9, edge10, edge11 };
	Color nodeColor = new Color(40, 168, 107);
	Color edgeColor = new Color(34, 68, 204);
	int nodeSize = 4;

	int type = BufferedImage.TYPE_INT_ARGB;
	BufferedImage image;

	public BufferedImage getImage() {
		return image;
	}

	public void setImage(BufferedImage image) {
		this.image = image;
	}

	public void drawCube() {
		drawNode();
		drawEdge();
	}

	public void drawNode() {
		Main.color = nodeColor;
		for (int[] node : this.nodes) {
			Point temp = new Point(node[0], node[1]);
			temp.translateCoordinateToReal();
			Ellipse elip = new Ellipse(temp, this.nodeSize, this.nodeSize);
			elip.ellipseBre();
			points.addAll(elip.getPoints());
		}
	}

	public void drawEdge() {
		Main.color = edgeColor;
		for (int e = 0; e < edges.length; e++) {
			int n0 = edges[e][0];
			int n1 = edges[e][1];
			int[] node0 = nodes[n0];
			int[] node1 = nodes[n1];
			Point temp = new Point(node0[0], node0[1]);
			temp.translateCoordinateToReal();
			Point temp1 = new Point(node1[0], node1[1]);
			temp1.translateCoordinateToReal();
			BresenhamLine line = new BresenhamLine(temp, temp1);
			line.drawLine();
			points.addAll(line.getPoints());
		}
	}

	public void rotateZ3D(int theta) {
		double radians = Math.toRadians(theta);
		double sinTheta = Math.sin(radians);
		double cosTheta = Math.cos(radians);
		for (int i = 0; i < nodes.length; i++) {
			int[] node = nodes[i];
			int x = node[0];
			int y = node[1];
			node[0] = (int) (x * cosTheta - y * sinTheta);
			node[1] = (int) (y * cosTheta + x * sinTheta);
		}
	}

	public void rotateY3D(int theta) {
		double radians = Math.toRadians(theta);
		double sinTheta = Math.sin(radians);
		double cosTheta = Math.cos(radians);

		for (int i = 0; i < nodes.length; i++) {

			int[] node = nodes[i];
			int x = node[0];
			int z = node[2];
			node[0] = (int) (x * cosTheta - z * sinTheta);
			node[2] = (int) (z * cosTheta + x * sinTheta);
		}
	}

	public void rotateX3D(int theta) {
		double radians = Math.toRadians(theta);
		double sinTheta = Math.sin(radians);
		double cosTheta = Math.cos(radians);

		for (int i = 0; i < nodes.length; i++) {
			int[] node = nodes[i];
			int y = node[1];
			int z = node[2];
			node[1] = (int) (y * cosTheta - z * sinTheta);
			node[2] = (int) (z * cosTheta + y * sinTheta);
		}
	}

	@Override
	public void drawShape(BufferedImage image) {
		this.drawCube();
		for (Point point : points) {
			Main.drawPoint(point, image);
		}
	}

	// public Point convert3Dto2D(int x,int y,int z) {
	// return new Point((int)(x-y*(Math.sqrt(2)/2))*DrawContainer.size,(int) (z -
	// y*(Math.sqrt(2)/2))*DrawContainer.size);
	// }
}