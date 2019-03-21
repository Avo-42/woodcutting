package cutting;

public class Shape{
	private Point[] points;
	private Edge[] edges;
	private Point center;
	
	public Shape (int pointCount, int edgeCount, Point centerVal) {
		points = new Point[pointCount];
		edges = new Edge[edgeCount];
		center = centerVal;
		
	}
	
	public Shape makeShape(Point[] points, int edgeCount, Point center) {
		Shape a = new Shape(points.length, edgeCount, center);
		for (int i = 0; i<=points.length; i++) {
			a.setPoint(i, points[i]);
		}
		for (int i = 1; i <=(edgeCount-1); i ++) {
			Edge edge = new Edge(points[i-1], points[i]);
			this.addEdge(edge, i-1);
			//this will add all but one of the edges to edges[]
		}
		// I am assuming that edgeCount and pointCount are equal here, adds the last edge, hence the name last.
		Edge last = new Edge (points[0], points[edgeCount-1]);
		this.addEdge(last, edgeCount);
		return a;
	}
	
	public void addEdge(Edge edge, int edgeNum) {
		edges[edgeNum] = edge;
	}
	
	public Shape makeShape(Edge edgeOne, Edge edgeTwo, Point center, int edgeCount) {
		//this class will take two edges and make a shape out of them
		if (edgeOne.hasOverlap(edgeTwo)==true) {
			System.out.println("please input edges that do not share a point");
			Shape temp = new Shape(0,0,center);
			return temp;
				//will return a false shape as it contains no points
		}else {
			Shape temp = new Shape(4, edgeCount, center);
			temp.setPoint(0, edgeOne.getEdgePoint(0));
			temp.setPoint(1, edgeOne.getEdgePoint(1));
			temp.setPoint(2, edgeTwo.getEdgePoint(0));
			temp.setPoint(4, edgeTwo.getEdgePoint(1));
			edges[0] = edgeOne;
			edges[2] = edgeTwo;
			//making a note right here b/c depending on how makeShape(point[]) is used this may make a diagonal edge, can't think of an easy fix.
			Edge c = new Edge(edgeOne.getEdgePoint(1), edgeTwo.getEdgePoint(1));
			Edge d= new Edge(edgeTwo.getEdgePoint(0), edgeOne.getEdgePoint(0));
			edges[1] = c;
			edges[3] = d;
			return temp;
		}
	}
	
	public enum Plane{
		XY, XZ, YZ
	}
	
	//there will only be a rotate shape method b/c rotating a creation will be useless and edges rotating is not very important
	//it will be assumed that the point center will be the shapes center but it will not be enforced so that if the user wants they can translate the shape about another point
	public void rotate(Shape shape, Plane plane, Point center, double angle) {
		if (plane == Plane.XY) {
			for(int i = 0; i<=shape.pointsSize(); i++) {
				shape.getPoint(i).setPoint(shape.getPoint(i).getRotatedZ(center,  angle));
			}
		}else if(plane == Plane.XZ) {
			for(int i = 0; i<=shape.pointsSize(); i++) {
				shape.getPoint(i).setPoint(shape.getPoint(i).getRotatedY(center,  angle));
			}
		}else{
			for(int i = 0; i<=shape.pointsSize(); i++) {
				shape.getPoint(i).setPoint(shape.getPoint(i).getRotatedX(center,  angle));
			}
		}
	}
	
	public void setEdge(Edge edge, int position) {
		edges[position] = edge;
	}
	
	public Edge getEdge(int position) {
		return edges[position];
	}
	
	public Edge getCopiedEdge(int position) {
		Edge result = edges[position];
		return result;
	}
	
	public int pointsSize() {
		return points.length;
	}
	
	public Point getPoint(int pointNumber) {
		return points[pointNumber];
	}
	
	public void setPoint(int pointPosition,Point point) {
		points[pointPosition] = point;
	}
	
	public Point getCenter() {
		return center;
	}
	
	public void setCenter(Point newCenter) {
		center = newCenter;
	}
	
	//enter values for each change in dimension required for every translate method.
	public void translateCenter(double x, double y, double z) {
		Point.translatePoint(center, x, y, z);
	}
	
	public void translateCenter(Point translation) {
		Point.translatePoint(center, translation.getX(), translation.getY(), translation.getZ());
	}
	
	public void translateShape(Shape shape, double x, double y, double z) {
		this.translateCenter(x, y, z);
		for(int i = 0; i<=shape.pointsSize(); i++) {
			Point.translatePoint(shape.getPoint(i), x, y, z);
		}
	}
	
	public void translateShape(Shape shape, Point t) {
		this.translateCenter(t);
		for(int i = 0; i<=shape.pointsSize(); i++) {
			Point.translatePoint(shape.getPoint(i), t);
		}
	}
	
}
