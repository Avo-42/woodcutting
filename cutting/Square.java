package cutting;

public class Square extends Shape{
//	public enum Plane{
//		XY, XZ, YZ
//	}

	double l, w;
		
	public Square (Point center, double width, Plane plane) {
		super(4, 4, center);
		this.makeSquare(center, width, plane);
	}
	
	//makes a square given the bottom left point
	public void makeSquareFromPoint(Point point, Point center, double width, Plane plane) {
		this.setCenter(center);
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
			this.addEdge(zero, 0);
			this.addEdge(one, 1);
			this.addEdge(two, 2);
			this.addEdge(three, 3);
		}else if(plane == Plane.XZ) {
			Point a = new Point(point.getX()+width, point.getY(), point.getZ());
			Point b = new Point(point.getX(), point.getY(), point.getZ()+width);
			Point c = new Point(point.getX()+width, point.getY(), point.getZ()+width);
			this.setPoint(1, a);
			this.setPoint(2, b);
			this.setPoint(3, c);
		}else {
			Point a = new Point(point.getX(), point.getY()+width, point.getZ());
			Point b = new Point(point.getX(), point.getY(), point.getZ()+width);
			Point c = new Point(point.getX(), point.getY()+width, point.getZ()+width);
			this.setPoint(1, a);
			this.setPoint(2, b);
			this.setPoint(3, c);
		}
	}
	
	public void makeSquare(Point center, double width, Plane plane) {
		double a, b, c, d;
		a = center.getX();
		b = center.getY();
		c = center.getZ();
		d = width/2;
		if (plane == Plane.XY ) {
			int i = 3;
			for (int j = -1; j<=1; j+=2) {
				for (int k = -1; k<=1; k+=2) {
					double x = j*d/2 + a;
					double y = k*d + b;
					double z = c;
					Point temp = new Point(x, y, z);
					this.setPoint(i, temp);
					i--;
				}
			}
		}else if(plane == Plane.YZ) {
			int i = 3;
			for (int j = -1; j<=1; j+=2) {
				for (int k = -1; k<=1; k+=2) {
					double x = a;
					double y = j*d + b;
					double z = k*d + c;
					Point temp = new Point(x, y, z);
					this.setPoint(i, temp);
					i--;
				}
			}
		}else {
			int i = 3;
			for (int j = -1; j<=1; j+=2) {
				for (int k = -1; k<=1; k+=2) {
					double x = j*d + a;
					double y = b;
					double z = k*d + c;
					Point temp = new Point(x, y, z);
					this.setPoint(i, temp);
				}
			}
		}
		Edge edgeA = new Edge(this.getPoint(0), this.getPoint(1));
		Edge edgeB = new Edge(this.getPoint(1), this.getPoint(2));
		Edge edgeC = new Edge(this.getPoint(2), this.getPoint(3));
		Edge edgeD = new Edge(this.getPoint(3), this.getPoint(0));
		this.addEdge(edgeA, 0);
		this.addEdge(edgeB, 1);
		this.addEdge(edgeC, 2);
		this.addEdge(edgeD, 3);
	}
}
