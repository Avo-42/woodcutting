package cutting;

public class Parallelogram extends Shape {
		
	public Parallelogram(Point center, Edge edge, Point translation) {
		super(4, 4, center);
		this.setPoint(0, edge.getEdgePoint(0));
		this.setPoint(1, edge.getEdgePoint(1));
		this.setPoint(2, Point.getTranslation(this.getPoint(0), translation);
		this.setPoint(3, Point.getTranslation(this.getPoint(1), translation);
		Edge zero = new Edge(this.getPoint(0), this.getPoint(1));
		Edge one = new Edge(this.getPoint(1), this.getPoint(2));
		Edge two = new Edge(this.getPoint(2), this.getPoint(3));
		Edge three = new Edge(this.getPoint(3), this.getPoint(0));
		this.setEdge(zero, 0);
		this.setEdge(one, 1);
		this.setEdge(two, 2);
		this.setEdge(three, 3);
	}
	
	//old make parallelogram
//	public static Parallelogram makeParallelogram(Point pointZero, Point pointOne, Point translation) {
//		Point center = makeCenter(pointZero, pointOne, translation.getX(), translation.getY(), translation.getZ());
//		Parallelogram temp = new Parallelogram(center, pointZero, pointOne, translation);
//		return temp;
//	}
	
	public static Parallelogram makeParallelogram(Edge edge, Point translation) {
		Point center =  makeCenter(edge, translation);
		Parallelogram temp = new Parallelogram(center, edge, translation);
		return temp;
	}
	
	//old makecenter
//	private static Point makeCenter(Point pointZero, Point pointOne, double x, double y, double z) {
//		Point center = new Point(0,0,0);
//		Point three = Point.getTranslation(pointOne, x, y, z);
//		center = pointZero.midpoint(three);
//		return center;
//	}
	
	private static Point makeCenter(Edge edge, Point translation) {
		Point center = new Point(0,0,0);
		Point three = Point.getTranslation(edge.getEdgePoint(1), translation);
		center = edge.getEdgePoint(0).midpoint(three);
		return center;
	}
	
}
