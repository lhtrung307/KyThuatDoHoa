package kythuatdohoa;

import java.awt.Color;
import java.awt.image.BufferedImage;

public class Pyramid3D extends Shape{
	
	int[] node4 = { -1, -1, -1 };
	int[] node3 = { 1, -1, -1 };
	int[] node2 = { 1, -1, 1 };
	int[] node1 = { -1, -1, 1 };
	int[] node0 = { 0, 1, 0 };
	int[][] nodes = { node0, node1, node2, node3, node4};

	int[] edge0 = { 0, 1 };
	int[] edge1 = { 0, 2 };
	int[] edge2 = { 0, 3 };
	int[] edge3 = { 0, 4 };
	int[] edge4 = { 1, 4 };
	int[] edge5 = { 1, 2 };
	int[] edge6 = { 2, 3 };
	int[] edge7 = { 3, 4 };
	int[][] edges = { edge0, edge1, edge2, edge3, edge4, edge5, edge6, edge7};
	Color nodeColor = new Color(40, 168, 107);
	Color edgeColor = new Color(34, 68, 204);
	int nodeSize = 4;
	int x, y, z, length;
	
	public Pyramid3D(int x, int y, int z, int length) {
		super();
		this.x = x;
		this.y = y;
		this.z = z;
		generatePyramidNodes();
	}
	
	private void generatePyramidNodes() {
		for(int[] node: nodes) {
			node[0] = node[0] * x;
			node[1] = node[1] * y;
			node[2] = node[2] * z;
		}
	}

	public void drawPyramid() {
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
		for(int i = 0; i < nodes.length; i++) {
			int[] node = nodes[i];
			int x = node[0];
			int y = node[1];
			node[0] = (int)(x * cosTheta - y * sinTheta);
			node[1] = (int)(y * cosTheta + x * sinTheta);
		}
	}

	public void rotateY3D(int theta) {
		double radians = Math.toRadians(theta);
		double sinTheta = Math.sin(radians);
		double cosTheta = Math.cos(radians);
		for(int i = 0; i < nodes.length; i++) {
			int[] node = nodes[i];
			int x = node[0];
			int z = node[2];
			node[0] = (int)(x * cosTheta - z * sinTheta);
			node[2] = (int)(z * cosTheta + x * sinTheta);
		}
	}

	public void rotateX3D(int theta) {
		double radians = Math.toRadians(theta);
		double sinTheta = Math.sin(radians);
		double cosTheta = Math.cos(radians);

		for(int i = 0; i < nodes.length; i++) {
			int[] node = nodes[i];
			int y = node[1];
			int z = node[2];
			node[1] = (int)(y * cosTheta - z * sinTheta);
			node[2] = (int)(z * cosTheta + y * sinTheta);
		}
	}

	@Override
	public void drawShape(BufferedImage image) {
		this.drawPyramid();
		for (Point point : points) {
			Main.drawPoint(point, image);
		}
	}

	@Override
	public void scale(double sx, double sy) {
		// TODO Auto-generated method stub
		
	}
	
//	public Point convert3Dto2D(int x,int y,int z) {
//		return new Point((int)(x-y*(Math.sqrt(2)/2))*DrawContainer.size,(int) (z - y*(Math.sqrt(2)/2))*DrawContainer.size);
//	}
}