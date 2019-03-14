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
	
	public Shape makeShape(Edge a, Edge b, Point center, int edgeCount) {
		//this class will take two edges and make a shape out of them
		if (a.isSame(b)==false) {
			System.out.println("please input edges that do not share a point");
			Shape temp = new Shape(0,0,center);
			return temp;
				//will return a false shape as it contains no points
		}else {
			Shape temp = new Shape(4, edgeCount, center);
			temp.setPoint(0, a.getEdgePoint(0));
			temp.setPoint(1, a.getEdgePoint(1));
			temp.setPoint(2, b.getEdgePoint(0));
			temp.setPoint(4, b.getEdgePoint(1));
			edges[0] = a;
			edges[2] = b;
			//making a note right here b/c depending on how makeShape(point[]) is used this may make a diagonal edge, can't think of an easy fix.
			Edge c = new Edge(a.getEdgePoint(1), b.getEdgePoint(1));
			Edge d= new Edge(b.getEdgePoint(0), a.getEdgePoint(0));
			edges[1] = c;
			edges[3] = d;
			return temp;
		}
	}
	
	public int pointsSize() {
		return points.length;
	}
	
	public Point getPoint(int a) {
		return points[a];
	}
	
	public void setPoint(int pos,Point val) {
		points[pos] = val;
	}
	
	public Point getCenter() {
		return center;
	}
	
	public void setCenter(Point newCenter) {
		center = newCenter;
	}
	
	//enter values for each change in dimension required for every translate method.
	public void translateCenter(double x, double y, double z) {
		translatePoint(center, x, y, z);
	}
	
	//this will remain in Shape b/c otherwise translate center and translate shape will not work, as shape does not extend point
	public static void translateThisPoint(Point point, double x, double y, double z) {
		x = x + point.getX();
		y = y + point.getY();
		z = z + point.getZ();
		point.setPoint(x, y, z);
	}
	
	// makes a new point translated according to the inputs
	// use when making new points to be added
	public static Point getTranslation(Point point, double x, double y, double z) {
		x+=point.getX();
		y+=point.getY();
		z+=point.getZ();
		Point result = new Point(x, y, z);
		return result;
	}
	
	// translates a point, no new points are created
	public static void translatePoint(Point point, double x, double y, double z) {
		x = x + point.getX();
		y = y + point.getY();
		z = z + point.getZ();
		point.setPoint(x, y, z);
	}
	
	public void translateShape(Shape shape, double x, double y, double z) {
		for(int i = 0; i<=shape.pointsSize(); i++) {
			translatePoint(shape.getPoint(i), x, y, z);
		}
	}
	
}
