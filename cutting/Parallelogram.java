package cutting;

public class Parallelogram extends Shape {
		
	public Parallelogram(Point center, Point zero, Point one, double x, double y, double z) {
		super(4, 4, center);
		this.setPoint(0, zero);
		this.setPoint(1, one);
		this.setPoint(2, getTranslation(zero, x, y, z));
		this.setPoint(3, getTranslation(one, x, y, z));
	}
	
	public static Parallelogram makeParallelogram(Point zero, Point one, double x, double y, double z) {
		Point center = makeCenter(zero, one, x, y, z);
		Parallelogram temp = new Parallelogram(center, zero, one, x, y, z);
		return temp;
	}
	
	public static Parallelogram makeParallelogram(Edge a, double x, double y, double z) {
		Point center =  makeCenter(a, x, y, z);
		Parallelogram temp = new Parallelogram(center, a.getEdgePoint(0), a.getEdgePoint(1), x, y, z);
		return temp;
	}
	
	private static Point makeCenter(Point zero, Point one, double x, double y, double z) {
		Point center = new Point(0,0,0);
		Point three = getTranslation(one, x, y, z);
		center = zero.midpoint(three);
		return center;
	}
	
	private static Point makeCenter(Edge a, double x, double y, double z) {
		Point center = new Point(0,0,0);
		Point three = getTranslation(a.getEdgePoint(1), x, y, z);
		center = a.getEdgePoint(0).midpoint(three);
		return center;
	}
	
	//to be made
	public void rotateAboutPoint(Point about, double angle) {
		
	}
}
