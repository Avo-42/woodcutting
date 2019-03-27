package cutting;

public class Edge {
	private Point[] points = new Point[2];
	
	public Edge(Point pointZero, Point pointOne) {
		points[0] = pointZero;
		points[1] = pointOne;
	}
	
	public Edge getTranslatedEdge(Point t){
		Point a = Point.getTranslation(getEdgePoint(0), t);
		Point b = Point.getTranslation(getEdgePoint(1), t);
		return new Edge(a, b);
	}
	
	public void translateEdge(Point t){
		points[0].translatePoint(t);
		points[1].translatePoint(t);
	}
	
	public void setEdgePoint(int pointNum, Point point){
		points[pointNum] = point;
	}

	// Returns true if the edges share both points
	public boolean fullyOverlaps(Edge otherEdge) {
		if (getEdgePoint(0)==otherEdge.getEdgePoint(0)&&getEdgePoint(1)==otherEdge.getEdgePoint(1)) {
			//a0=b0 and a1=b1, same edge
			return true;
		}else if (getEdgePoint(0)==otherEdge.getEdgePoint(1)&&getEdgePoint(1)==otherEdge.getEdgePoint(0)) {
			//a0=b1 and a1=b0, would be same edge just written in reverse order.
			return true;
		}
		return false;
	}
	
	//making an isSame method for edges so I just check for overlapping points easily
	// Returns true if the edges share at least one point
	public boolean hasOverlap(Edge otherEdge) {
		if (getEdgePoint(0)==otherEdge.getEdgePoint(0)||getEdgePoint(1)==otherEdge.getEdgePoint(1)) {
			return true;
		} else if (getEdgePoint(0)==otherEdge.getEdgePoint(1)||getEdgePoint(1)==otherEdge.getEdgePoint(0)) {
			return true;
		}
		//if no points are shared then the edges are independent
		return false;
	}
	
	public Point getEdgePoint(int edgeNum) {
		assert edgeNum >=0 && edgeNum <=1: "Inputs for getEdgePoint can only be a 0 or a 1.";
		if (edgeNum == 0) {
			return points[0];
		}else {
			return points[1];
		}
	}
}
