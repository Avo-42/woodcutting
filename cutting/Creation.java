package cutting;

import java.util.ArrayList;

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
