package cutting;

public class Parallelogram extends Shape {
		
	public Parallelogram(Point center, Edge edge, Point translation) {
		super(4, 4, center);
		this.setPoint(0, edge.getEdgePoint(0));
		this.setPoint(1, edge.getEdgePoint(1));
		this.setPoint(2, Point.getTranslation(edge.getEdgePoint(0), translation.getX(), translation.getY(), translation.getZ()));
		this.setPoint(3, Point.getTranslation(edge.getEdgePoint(1), translation.getX(), translation.getY(), translation.getZ()));
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
