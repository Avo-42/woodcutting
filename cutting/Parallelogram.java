package cutting;

public class Parallelogram extends Shape {
	//old stuff from rectangle
//	public enum Plane{
//		XY, XZ, YZ
//	}
		
	public Parallelogram(Point center, Point zero, Point one, double x, double y, double z) {
		super(4, 4, center);
		Point two = translatePoint(zero, x, y, z);
		Point three = translatePoint(one, x, y, z);
		this.setPoint(0, zero);
		this.setPoint(1, one);
		this.setPoint(2, two);
		this.setPoint(3, three);
	}
	
	public static Parallelogram makeParallelogram(Point zero, Point one, double x, double y, double z) {
		Point center = makeCenter(zero, one, x, y, z);
		Parallelogram temp = new Parallelogram(center, zero, one, x, y, z);
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
	
	//to be made
	public void rotateAboutPoint() {
		
	}
}
