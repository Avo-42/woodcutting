package cutting;

public class Shape{
	private Point[] points;
	private Point center;
	
	public Shape (int pointCount, Point centerVal) {
		//all three Cord variables are for the center coordinate
		points = new Point[pointCount];
		center = centerVal;
		
	}
	
//	
//	public void makeShape(Edge zero, Edge one) {
//		Shape(4, ());
//		
//	}
	
	public int numOfPoints() {
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
	
	//enter values for each change in dimension required.
	public void translateCenter(double x, double y, double z) {
		x = x + center.getX();
		y = y + center.getY();
		z = z + center.getZ();
		Point newCenter = new Point(x,y,z);
		center = newCenter;
	}
	
	public void translatePoint(Point p, double x, double y, double z) {
		x = x + p.getX();
		y = y + p.getY();
		z = z + p.getZ();
		Point newP = new Point (x,y,z);
		p = newP;
	}
	
	
}
