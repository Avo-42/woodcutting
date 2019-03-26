package cutting;

//import cutting.Rectangle.Plane;
 
public class Triangle extends Shape{
	
	public Triangle(Edge edge, Point point) {
		super(3, 3);
		makeTriangle(edge, point);
	}
	
	public void makeTriangle(Point pointOne, Point pointTwo, Point pointThree) {
		setPoint(0, pointOne);
		setPoint(1, pointTwo);
		setPoint(2, pointThree);
		Edge zero = new Edge(pointOne, pointTwo);
		Edge one = new Edge(pointTwo, pointThree);
		Edge two = new Edge(pointThree, pointOne);
		this.setEdge(zero, 0);
		this.setEdge(one, 1);
		this.setEdge(two, 2);
	}
	
	public void makeTriangle(Edge edge, Point point) {
		setPoint(0, edge.getEdgePoint(0));
		setPoint(1, edge.getEdgePoint(1));
		setPoint(2, point);
		Edge one = new Edge(edge.getEdgePoint(1), point);
		Edge two = new Edge(point, edge.getEdgePoint(0));
		this.setEdge(edge, 0);
		this.setEdge(one, 1);
		this.setEdge(two, 2);
	}
	
//	public void pointShift(int pointNum, Point value) {
//		if (pointNum == 0) {
//			zero = value;
//		}else if(pointNum == 1) {
//			one = value; 
//		}else if(pointNum == 2){
//			two = value;
//		}
//		else {
//			System.out.println("pointNum must be between 0 and 2");
//		}
//	}
	
}
