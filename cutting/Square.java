package cutting;

public class Square extends Shape{
		
	public Square (Point center, double width, Plane plane) {
		super(4, 4);
		makeSquare(center, width, plane);
	}
	
	//makes a square given the bottom left point
	public void makeSquareFromPoint(Point point, double width, Plane plane) {
		setPoint(0, point);
		if (plane == Plane.XY) {
			Point a = new Point(point.getX()+width, point.getY(), point.getZ());
			Point b = new Point(point.getX(), point.getY()+width, point.getZ());
			Point c = new Point(point.getX()+width, point.getY()+width, point.getZ());
			setPoint(1, a);
			setPoint(2, b);
			setPoint(3, c);
			Edge zero = new Edge(point, a);
			Edge one = new Edge(a, b);
			Edge two = new Edge(b, c);
			Edge three = new Edge(c, point);
			setEdge(zero, 0);
            setEdge(one, 1);
            setEdge(two, 2);
            setEdge(three, 3);
		}else if(plane == Plane.ZX) {
			Point a = new Point(point.getX()+width, point.getY(), point.getZ());
			Point b = new Point(point.getX(), point.getY(), point.getZ()+width);
			Point c = new Point(point.getX()+width, point.getY(), point.getZ()+width);
			setPoint(1, a);
			setPoint(2, b);
			setPoint(3, c);
			Edge zero = new Edge(point, a);
			Edge one = new Edge(a, b);
			Edge two = new Edge(b, c);
			Edge three = new Edge(c, point);
			setEdge(zero, 0);
			setEdge(one, 1);
			setEdge(two, 2);
			setEdge(three, 3);
		}else {
			Point a = new Point(point.getX(), point.getY()+width, point.getZ());
			Point b = new Point(point.getX(), point.getY(), point.getZ()+width);
			Point c = new Point(point.getX(), point.getY()+width, point.getZ()+width);
			setPoint(1, a);
			setPoint(2, b);
			setPoint(3, c);
			Edge zero = new Edge(point, a);
			Edge one = new Edge(a, b);
			Edge two = new Edge(b, c);
			Edge three = new Edge(c, point);
			setEdge(zero, 0);
			setEdge(one, 1);
			setEdge(two, 2);
			setEdge(three, 3);
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
			setPoint(0, zero);
			setPoint(1, one);
			setPoint(2, two);
			setPoint(3, three);
		}else if(plane == Plane.YZ) {
			Point zero = new Point(center.getX(), center.getY()-d, center.getZ()-d);
			Point one = new Point(center.getX(), center.getY()-d, center.getZ()+d);
			Point two = new Point(center.getX(), center.getY()+d, center.getZ()+d);
			Point three = new Point(center.getX(), center.getY()+d, center.getZ()-d);
			setPoint(0, zero);
			setPoint(1, one);
			setPoint(2, two);
			setPoint(3, three);
		}else {
			Point zero = new Point(center.getX()-d, center.getY(), center.getZ()-d);
			Point one = new Point(center.getX()+d, center.getY(), center.getZ()-d);
			Point two = new Point(center.getX()+d, center.getY(), center.getZ()+d);
			Point three = new Point(center.getX()-d, center.getY(), center.getZ()+d);
			setPoint(0, zero);
			setPoint(1, one);
			setPoint(2, two);
			setPoint(3, three);
		}
		Edge edgeA = new Edge(getPoint(0), getPoint(1));
		Edge edgeB = new Edge(getPoint(1), getPoint(2));
		Edge edgeC = new Edge(getPoint(2), getPoint(3));
		Edge edgeD = new Edge(getPoint(3), getPoint(0));
		setEdge(edgeA, 0);
		setEdge(edgeB, 1);
		setEdge(edgeC, 2);
		setEdge(edgeD, 3);
	}
}
