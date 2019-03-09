package cutting;

//import cutting.Rectangle.Plane;
 
public class Triangle extends Shape{
	public Point Zero, one, two;
	
	public Triangle(Point[] points, Point center) {
		super(3,center);
		Zero = points[0];
		one = points[1];
		two = points[2];
	}
	
	public void pointShift(int pointNum, Point value) {
		if (pointNum == 0) {
			Zero = value;
		}else if(pointNum == 1) {
			one = value; 
		}else if(pointNum == 2){
			two = value;
		}
		//for some reason the system command is not recognized
		//ignore that forgor to capitalize the s in System
		else {
			System.out.println("point must be between 0 and 2");
		}
	}
	
	//for moving a shape a defined amount through space
	//no longer needed
//	public void translateTriangle(Triangle tri, double x, double y, double z) {
//		for (int i = 0; i<=2; i++) {
//			Point temp = tri.getPoint(i);
//			translatePoint(temp, x, y, z);
//		}
//	}
}
