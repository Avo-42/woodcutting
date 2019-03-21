package cutting;

//import cutting.Rectangle.Plane;
 
public class Triangle extends Shape{
	private Point zero, one, two;
	
	public Triangle(Point center, Edge a, Point b) {
		super(3, 3, center);
		makeTriangle(center, a, b);
	}
	
	public void makeTriangle(Point center, Point pointOne, Point pointTwo, Point pointThree) {
		setCenter(center);
		setPoint(0, pointOne);
		setPoint(1, pointTwo);
		setPoint(2, pointThree);
	}
	
	public void makeTriangle(Point center, Edge edge, Point point) {
		setCenter(center);
		setPoint(0, edge.getEdgePoint(0));
		setPoint(1, edge.getEdgePoint(1));
		setPoint(2, point);
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
			System.out.println("pointNum must be between 0 and 2");
		}
	}
	
}
