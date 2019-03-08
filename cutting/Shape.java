package cutting;

public class Shape{
	private Point[] points;
	private Point center;
	
	public Shape (int pointCount, Point centerVal) {
		//all three Cord variables are for the center coordinate
		points = new Point[pointCount];
		center = centerVal;
		
	}
	
	//had to copy this code into here b/c it could not be accessed from Edge
	public boolean isSame(Edge a, Edge b) {
		//will return false if the edges share an edge
		if (a.getEdgePoint(0)==b.getEdgePoint(0)&&a.getEdgePoint(1)==b.getEdgePoint(1)) {
			//a0=b0 and a1=b1, same edge
			return false;
		}else if (a.getEdgePoint(0)==b.getEdgePoint(1)&&a.getEdgePoint(1)==b.getEdgePoint(0)) {
			//a0=b1 and a1=b0, would be same edge just written in reverse order.
			return false;
		}else if (a.getEdgePoint(0)==b.getEdgePoint(0)||a.getEdgePoint(1)==b.getEdgePoint(1)) {
			//only one shared point
			return false;
		}else if (a.getEdgePoint(0)==b.getEdgePoint(1)||a.getEdgePoint(1)==b.getEdgePoint(0)) {
			//another shared point check
			return false;
		}else {
			//if no points are shared then the edges are independent
			return true;
		}
	}
	
	public Shape makeShape(Edge a, Edge b, Point center) {
		//this class will take two edges and make a shape out of them
		if (isSame(a,b)==false) {
			System.out.println("please input edges that do not share a point");
			Shape temp = new Shape(0,center);
			return temp;
			//will return a false shape as it contains no points
		}else {
			Shape temp = new Shape(4, center);
			temp.setPoint(0, a.getEdgePoint(0));
			temp.setPoint(1, a.getEdgePoint(1));
			temp.setPoint(2, b.getEdgePoint(0));
			temp.setPoint(4, b.getEdgePoint(1));
			return temp;
		}
	}
	
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
	
	//enter values for each change in dimension required for every translate method.
	public void translateCenter(double x, double y, double z) {
		translatePoint(center, x, y, z);
	}
	
	//this will remain in Shape b/c otherwise translate center and translate shape will not work, as shape does not extend point
	public void translatePoint(Point p, double x, double y, double z) {
		x = x + p.getX();
		y = y + p.getY();
		z = z + p.getZ();
		p.setPoint(x, y, z);
	}
	
	public void translateShape(Shape shape, double x, double y, double z) {
		for(int i = 0; i<=shape.numOfPoints(); i++) {
			translatePoint(shape.getPoint(i), x, y, z);
		}
	}
	
}
