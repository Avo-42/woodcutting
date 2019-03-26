package cutting;

public class Square extends Shape{
		
	public Square (Point center, double width, Plane plane) {
		super(4, 4);
		this.makeSquare(center, width, plane);
	}
	
	//makes a square given the bottom left point
	public void makeSquareFromPoint(Point point, double width, Plane plane) {
		this.setPoint(0, point);
		if (plane == Plane.XY) {
			Point a = new Point(point.getX()+width, point.getY(), point.getZ());
			Point b = new Point(point.getX(), point.getY()+width, point.getZ());
			Point c = new Point(point.getX()+width, point.getY()+width, point.getZ());
			this.setPoint(1, a);
			this.setPoint(2, b);
			this.setPoint(3, c);
			Edge zero = new Edge(point, a);
			Edge one = new Edge(a, b);
			Edge two = new Edge(b, c);
			Edge three = new Edge(c, point);
			this.setEdge(zero, 0);
			this.setEdge(one, 1);
			this.setEdge(two, 2);
			this.setEdge(three, 3);
		}else if(plane == Plane.XZ) {
			Point a = new Point(point.getX()+width, point.getY(), point.getZ());
			Point b = new Point(point.getX(), point.getY(), point.getZ()+width);
			Point c = new Point(point.getX()+width, point.getY(), point.getZ()+width);
			this.setPoint(1, a);
			this.setPoint(2, b);
			this.setPoint(3, c);
			Edge zero = new Edge(point, a);
			Edge one = new Edge(a, b);
			Edge two = new Edge(b, c);
			Edge three = new Edge(c, point);
			this.setEdge(zero, 0);
			this.setEdge(one, 1);
			this.setEdge(two, 2);
			this.setEdge(three, 3);
		}else {
			Point a = new Point(point.getX(), point.getY()+width, point.getZ());
			Point b = new Point(point.getX(), point.getY(), point.getZ()+width);
			Point c = new Point(point.getX(), point.getY()+width, point.getZ()+width);
			this.setPoint(1, a);
			this.setPoint(2, b);
			this.setPoint(3, c);
			Edge zero = new Edge(point, a);
			Edge one = new Edge(a, b);
			Edge two = new Edge(b, c);
			Edge three = new Edge(c, point);
			this.setEdge(zero, 0);
			this.setEdge(one, 1);
			this.setEdge(two, 2);
			this.setEdge(three, 3);
		}
	}
	
	public void makeSquare(Point center, double width, Plane plane) {
		double a, b, c, d;
		a = center.getX();
		b = center.getY();
		c = center.getZ();
		d = width/2;
		if (plane == Plane.XY ) {
			Point zero = new Point(center.getX()-d, center.getY()-d, center.getZ());
			Point one = new Point(center.getX()+d, center.getY()-d, center.getZ());
			Point two = new Point(center.getX()+d, center.getY()+d, center.getZ());
			Point three = new Point(center.getX()-d, center.getY()+d, center.getZ());
			this.setPoint(0, zero);
			this.setPoint(1, one);
			this.setPoint(2, two);
			this.setPoint(3, three);
		}else if(plane == Plane.YZ) {
			Point zero = new Point(center.getX(), center.getY()-d, center.getZ()-d);
			Point one = new Point(center.getX(), center.getY()-d, center.getZ()+d);
			Point two = new Point(center.getX(), center.getY()+d, center.getZ()+d);
			Point three = new Point(center.getX(), center.getY()+d, center.getZ()-d);
			this.setPoint(0, zero);
			this.setPoint(1, one);
			this.setPoint(2, two);
			this.setPoint(3, three);
		}else {
			Point zero = new Point(center.getX()-d, center.getY(), center.getZ()-d);
			Point one = new Point(center.getX()+d, center.getY(), center.getZ()-d);
			Point two = new Point(center.getX()+d, center.getY(), center.getZ()+d);
			Point three = new Point(center.getX()-d, center.getY(), center.getZ()+d);
			this.setPoint(0, zero);
			this.setPoint(1, one);
			this.setPoint(2, two);
			this.setPoint(3, three);
		}
		Edge edgeA = new Edge(this.getPoint(0), this.getPoint(1));
		Edge edgeB = new Edge(this.getPoint(1), this.getPoint(2));
		Edge edgeC = new Edge(this.getPoint(2), this.getPoint(3));
		Edge edgeD = new Edge(this.getPoint(3), this.getPoint(0));
		this.setEdge(edgeA, 0);
		this.setEdge(edgeB, 1);
		this.setEdge(edgeC, 2);
		this.setEdge(edgeD, 3);
	}
}
