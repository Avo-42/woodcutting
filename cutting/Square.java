package cutting;

public class Square extends Shape{
	public enum Plane{
		XY, XZ, YZ
	}

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
		if (plane == Plane.XY ) {
			int i = 3;
			for (int j = -1; j<=1; j+=2) {
				for (int k = -1; k<=1; k+=2) {
					double x = j*width/2 + center.getX();
					double y = k*width/2 + center.getY();
					double z = center.getZ();
					Point temp = new Point(x, y, z);
					this.setPoint(i, temp);
					i--;
				}
			}
		}else if(plane == Plane.YZ) {
			int i = 3;
			for (int j = -1; j<=1; j+=2) {
				for (int k = -1; k<=1; k+=2) {
					double x = center.getX();
					double y = j*width/2 + center.getY();
					double z = k*width/2 + center.getZ();
					Point temp = new Point(x, y, z);
					this.setPoint(i, temp);
					i--;
				}
			}
		}else {
			int i = 3;
			for (int j = -1; j<=1; j+=2) {
				for (int k = -1; k<=1; k+=2) {
					double x = j*width/2 + center.getX();
					double y = center.getY();
					double z = k*width/2 + center.getZ();
					Point temp = new Point(x, y, z);
					this.setPoint(i, temp);
				}
			}
	
		}
	}
}
