package cutting;

public class Edge {
	private Point[] arr = new Point[2];

	public Edge(Point zero, Point one, Shape[] shapes, Shape origin) {
		arr[0] = zero;
		arr[1] = one;
		shapes[0] = origin;
	}

	//this code was replaced with getEdgePoint instead of me making getOne
//	public Point getZero(Edge x) {
//		return x[0];
//	}
	
	//making an isSame method for edges so I just check for overlapping points easily
	public boolean isSame(Edge a, Edge b) {
		//will return false if the edges share an edge
		if (a.getEdgePoint(0)==b.getEdgePoint(0)&&a.getEdgePoint(1)==b.getEdgePoint(1)) {
			//a0=b0 and a1=b1, same edge
			return false;
		}else if (a.getEdgePoint(0)==b.getEdgePoint(1)&&a.getEdgePoint(1)==b.getEdgePoint(0)) {
			//a0=b1 and a1=b0, would be same edge just written in reverse order.
			return false;
		}else if (a.getEdgePoint(0)==b.getEdgePoint(0)||a.getEdgePoint(1)==b.getEdgePoint(1)) {
			//only one shared point
			return false;
		}else if (a.getEdgePoint(0)==b.getEdgePoint(1)||a.getEdgePoint(1)==b.getEdgePoint(0)) {
			//another shared point check
			return false;
		}else {
			//if no points are shared then the edges are independent
			return true;
		}
	}
	
	public Point getEdgePoint(int edgeNum) {
		assert edgeNum >=0 && edgeNum <=1: "Inputs for getEdgePoint can only be a 0 or a 1.";
		if (edgeNum == 0) {
			return arr[0];
		}else {
			return arr[1];
		}
	}
}
