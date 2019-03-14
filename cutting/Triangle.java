package cutting;

//import cutting.Rectangle.Plane;
 
public class Triangle extends Shape{
	public Point zero, one, two;
	
	public Triangle(Point[] points, Point center) {
		super(3, 3, center);
		zero = points[0];
		one = points[1];
		two = points[2];
	}
	
	public void makeTriangle(Point center, Point a, Point b, Point c) {
		this.setCenter(center);
		this.setPoint(0, a);
		this.setPoint(1, b);
		this.setPoint(2, c);
	}
	
	public void makeTriangle(Point center, Edge a, Point b) {
		this.setCenter(center);
		this.setPoint(0, a.getEdgePoint(0));
		this.setPoint(1, a.getEdgePoint(1));
		this.setPoint(2, b);
	}
	
	public void pointShift(int pointNum, Point value) {
		if (pointNum == 0) {
			zero = value;
		}else if(pointNum == 1) {
			one = value; 
		}else if(pointNum == 2){
			two = value;
		}
		else {
			System.out.println("point must be between 0 and 2");
		}
	}
	
}
