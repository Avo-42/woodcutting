package cutting;

public class Parallelogram extends Shape {
		
	public Parallelogram(Point center, Point pointZero, Point pointOne, double x, double y, double z) {
		super(4, 4, center);
		this.setPoint(0, pointZero);
		this.setPoint(1, pointOne);
		this.setPoint(2, Point.getTranslation(pointZero, x, y, z));
		this.setPoint(3, Point.getTranslation(pointOne, x, y, z));
	}
	
	public static Parallelogram makeParallelogram(Point pointZero, Point pointOne, double x, double y, double z) {
		Point center = makeCenter(pointZero, pointOne, x, y, z);
		Parallelogram temp = new Parallelogram(center, pointZero, pointOne, x, y, z);
		return temp;
	}
	
	public static Parallelogram makeParallelogram(Edge edge, double x, double y, double z) {
		Point center =  makeCenter(edge, x, y, z);
		Parallelogram temp = new Parallelogram(center, edge.getEdgePoint(0), edge.getEdgePoint(1), x, y, z);
		return temp;
	}
	
	private static Point makeCenter(Point pointZero, Point pointOne, double x, double y, double z) {
		Point center = new Point(0,0,0);
		Point three = Point.getTranslation(pointOne, x, y, z);
		center = pointZero.midpoint(three);
		return center;
	}
	
	private static Point makeCenter(Edge edge, double x, double y, double z) {
		Point center = new Point(0,0,0);
		Point three = Point.getTranslation(edge.getEdgePoint(1), x, y, z);
		center = edge.getEdgePoint(0).midpoint(three);
		return center;
	}
	
}
