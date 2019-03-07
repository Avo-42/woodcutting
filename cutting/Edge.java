package src.cutting;

public class Edge {
	private Point[] arr = new Point[2];
	
	public Edge(Point zero, Point one, Shape[] shapes, Shape origin) {
		arr[0] = zero;
		arr[1] = one;
		shapes[0] = origin;
	}
	
//	public Point getZero(Edge x) {
//		return x[0];
//	}
	
	public Point getEdgePoint(Edge edgeNum, int pointNum) {
		if (pos == 0)return arr[0];
		else return arr[1];
	}
}
