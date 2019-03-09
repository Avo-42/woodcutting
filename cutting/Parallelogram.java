package cutting;

public class Parallelogram extends Shape {
	// just writing junk here
	
//	public enum Plane{
//		XY, XZ, YZ
//	}
	
	double l, w;
	
	//plane is for telling which plane the rect will spawn in based on the enum xy = xy plane
	//the rectangle will be centered around center, ie. points will be 1/2 L and 1/2 W away from the center
	public Parallelogram(Point center, Point Zero, Point One, double x, double y, double z) {
		super(4,center);
		
	}
	
	public static Parallelogram makeParallelogram(Point Zero, Point One, double x, double y, double z) {
		Point center = makeCenter(Zero, One, x, y, z);
		Parallelogram temp = new Parallelogram(center, Zero, One, x, y, z);
		return temp;
	}
	
	public static Parallelogram makeParallelogram(Edge a, double x, double y, double z) {
		Point center =  makeCenter(a.getEdgePoint(0), a.getEdgePoint(1), x, y, z);
		Parallelogram temp = new Parallelogram(center, a.getEdgePoint(0), a.getEdgePoint(1), x, y, z);
		return temp;
	}
	
	private static Point makeCenter(Point zero, Point one, double x, double y, double z) {
		Point center = new Point(0,0,0);
		Point three = translatePoint(one, x, y, z);
		center = zero.midpoint(three);
		return center;
	}
	
//	public Rectangle makeRectangle(Edge a, double x, double y, double z, Point center) {
//		Point[] points = new Point[4];
//		Point zero = a.getEdgePoint(0);
//		Point one = a.getEdgePoint(1);
//		Point two = new Point(0,0,0), three = new Point(0,0,0);
//		two.setPoint((zero.getX()+x), (zero.getY()+y), (zero.getZ()+z));
//		three.setPoint((one.getX()+x), (one.getY()+y), (one.getZ()+z));
//		//I would make it a rectangle if I could but this method allows you to create things outside of the three planes
//		Shape temp = new Shape(4,center);
//	}
//	
//	//conversion method to make a shape into a rectangle, assumes that your points make parallel edges
//	private Rectangle makeRectangle(Shape shape, Point center) {
//		
//	}
	
	//planes will be removed from rect as they are no longer needed
//	public Plane getPlane(Parallelogram rect) {
//		return rect.getPlane(rect);
//	}
		
}
