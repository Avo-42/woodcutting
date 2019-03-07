package src.cutting;

import src.cutting.Rectangle.Plane;

public class Triangle extends Shape{
	public Point Zero, One, Two;
	
	
//	public enum Plane{
//		XY, XZ, YZ;
//	}
	
	public Triangle(Point a, Point b, Point c, Point center) {
		super(3,center);
		Zero = a;
		One = b;
		Two = c;
	}
	
	public void pointShift(int pointNum, Point value) {
		if (pointNum == 0) {
			Zero = value;
		}else if(pointNum == 1) {
			One = value; 
		}else if(pointNum == 2){
			Two = value;
		}else {
			system.out.prinln("point must be between 0 and 2");
		}
	}
	
	//for moving a shape a defined amount through space
	public void translateTriangle(Triangle tri, double x, double y, double z) {
		for (int i = 0; i<=2; i++) {
			Point a = tri.getPoint(i);
			a.setX(x+a.getX());
			a.setY(y+a.getY());
			a.setZ(z+a.getZ());
			tri.setPoint(i, a);
		}
	}
}
