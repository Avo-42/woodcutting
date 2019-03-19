package cutting;

import java.util.ArrayList;

import cutting.Shape.Plane;

public class Creation {
	//public Shape shapes[];
	ArrayList<Shape> shapes = new ArrayList(1);
	//public int count = 0;
	
	public Creation(Shape first) {
		
	}
	
	//a should be "above" b
	//the diag boolean is used to determine which way the diagonal line between the two triangles faces, true for increasing diagonal and vice versa
	public void makeTwoTriangles(Edge a, Edge b, boolean diag) {
		if(diag == true) {
			Point trueMid = a.getEdgePoint(0).midpoint(b.getEdgePoint(1));
			Point leftMid = b.getEdgePoint(0).midpoint(trueMid);
			Point rightMid = a.getEdgePoint(1).midpoint(trueMid);
			Triangle left = new Triangle(leftMid, b, a.getEdgePoint(0));
			Triangle right = new Triangle(rightMid, a, b.getEdgePoint(1));
			addShape(left);
			addShape(right);
		}else {
			Point trueMid = a.getEdgePoint(1).midpoint(b.getEdgePoint(0));
			Point leftMid = a.getEdgePoint(0).midpoint(trueMid);
			Point rightMid = b.getEdgePoint(1).midpoint(trueMid);
			Triangle left = new Triangle(leftMid, a, b.getEdgePoint(0));
			Triangle right = new Triangle(rightMid, b, a.getEdgePoint(1));
			addShape(left);
			addShape(right);
		}
	}
	
	public void makeParallelepiped(Point center, Point one, Point two, Point translation) {
		
	}
	
	//give this method the center and plane of the bottom of the cube and it will do the rest
	public void makeCube(Point center, double width, Plane plane) {
		Point centerFive = Point.getTranslation(center, 0, 0, width);
		Square zero = new Square(center, width, plane);
		Square five = new Square(centerFive, width, plane);
		Point centerOne = zero.getEdge(0).getEdgePoint(0).midpoint(five.getEdge(0).getEdgePoint(1));
		Shape one = new Shape (4,4,centerOne);
		one.makeShape(zero.getEdge(0), five.getEdge(0), centerOne, 4);
		Point centerTwo = zero.getEdge(1).getEdgePoint(0).midpoint(five.getEdge(1).getEdgePoint(1));
		Shape two = new Shape (4,4,centerTwo);
		two.makeShape(zero.getEdge(1), five.getEdge(1), centerTwo, 4);
		Point centerThree = zero.getEdge(2).getEdgePoint(0).midpoint(five.getEdge(2).getEdgePoint(1));
		Shape three = new Shape (4,4,centerThree);
		three.makeShape(zero.getEdge(2), one.getEdge(2), centerThree, 4);
		Point centerFour = zero.getEdge(3).getEdgePoint(0).midpoint(five.getEdge(3).getEdgePoint(1));
		Shape four = new Shape (4,4,centerFour);
		four.makeShape(zero.getEdge(3), five.getEdge(3), centerFour, 4);
		this.addShape(zero);
		this.addShape(one);
		this.addShape(two);
		this.addShape(three);
		this.addShape(four);
		this.addShape(five);
	}
	
	public Shape getShape(int position) {
		Shape temp = shapes.get(position);
		return temp;
	}
	
	public void addShape(Shape shape) {
		shapes.set(shapes.size(), shape);
		//count ++;
	}
	
	public void removeShape(int num) {
		for(int i = num; i < shapes.size(); i++) {
			shapes.set(i, shapes.get(i+1));
		}
		shapes.remove(shapes.size()-1);
		//count --;
	}
}
