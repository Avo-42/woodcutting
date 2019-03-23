package cutting;

public class Edge {
	private Point[] points = new Point[2];
	
	public Edge(Point pointZero, Point pointOne) {
		points[0] = pointZero;
		points[1] = pointOne;
	}
	
	public Edge getTranslatedEdge(Point t){
		Point a = Point.getTranslation(this.getEdgePoint(0), t);
		Point b = Point.getTranslation(this.getEdgePoint(1), t);
		return new Edge(a,b);
	}
	
	public void setEdgePoint(int pointNum, Point point){
		points[pointNum] = point;
	}
	
	//making an isSame method for edges so I just check for overlapping points easily
	public boolean hasOverlap(Edge otherEdge) {
		//will return true if the edges share a point
		if (getEdgePoint(0)==otherEdge.getEdgePoint(0)&&getEdgePoint(1)==otherEdge.getEdgePoint(1)) {
			//a0=b0 and a1=b1, same edge
			return true;
		}else if (getEdgePoint(0)==otherEdge.getEdgePoint(1)&&getEdgePoint(1)==otherEdge.getEdgePoint(0)) {
			//a0=b1 and a1=b0, would be same edge just written in reverse order.
			return true;
		}else if (getEdgePoint(0)==otherEdge.getEdgePoint(0)||getEdgePoint(1)==otherEdge.getEdgePoint(1)) {
			//only one shared point
			return true;
		}else if (getEdgePoint(0)==otherEdge.getEdgePoint(1)||getEdgePoint(1)==otherEdge.getEdgePoint(0)) {
			//another shared point check
			return true;
		}else {
			//if no points are shared then the edges are independent
			return false;
		}
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
