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
	
	
	
	public Point getEdgePoint(int edgeNum) {
		if (edgeNum == 0)return arr[0];
		else if (edgeNum == 1) return arr[1];
		//system command does not work for some reason
		//else system.out.println("getEdgePoint will only take 0, or 1 as a valid input.");
	}
}
