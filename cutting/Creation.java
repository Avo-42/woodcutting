package cutting;

import java.util.ArrayList;
import java.util.List;

import cutting.Shape.Plane;

public class Creation {
	private List<Shape> shapes = new ArrayList<>(1);
	
	public Creation() {
	}

	public Creation(Shape firstShape) {
		shapes.add(firstShape);
	}
	
	public List<Corner> hasSameCorner(Point point){
		List<Corner> corners = new ArrayList<>();
		//declaring shape and corner here so they are not created and destroyed by the for loops
		for(Shape shape: shapes) {
			for(int j = 0; j < shape.pointsSize(); j++) {
				if (point.isSame(shape.getPoint(j))) {
					corners.add(new Corner(shape, j));
				}
			}
		}
		return corners;
	}
	
	//a should be "above" b
	//the diag boolean is used to determine which way the diagonal line between the two triangles faces, true for increasing diagonal and vice versa
	public void makeTwoTriangles(Edge a, Edge b, boolean diag) {
		if(diag) {
			Triangle left  = new Triangle(b, a.getEdgePoint(0));
			Triangle right = new Triangle(a, b.getEdgePoint(1));
			addShape(left);
			addShape(right);
		} else {
			Triangle left  = new Triangle(a, b.getEdgePoint(0));
			Triangle right = new Triangle(b, a.getEdgePoint(1));
			addShape(left);
			addShape(right);
		}
	}
	
	public void makeParallelepiped(Edge edge, Point translation, Point t) {
		Parallelogram zero = new Parallelogram (edge, translation);
		Edge fiveEdge = edge.getTranslatedEdge(t); 
		Parallelogram five = new Parallelogram (fiveEdge, translation);
		Shape one   = Shape.makeShape(zero.getEdge(0), five.getEdge(0));
		Shape two   = Shape.makeShape(zero.getEdge(1), five.getEdge(1));
		Shape three = Shape.makeShape(zero.getEdge(2), five.getEdge(2));
		Shape four  = Shape.makeShape(zero.getEdge(3), five.getEdge(3));
		this.addShape(zero);
		this.addShape(one);
		this.addShape(two);
		this.addShape(three);
		this.addShape(four);
		this.addShape(five);
	}
	
	//give this method the center and plane of the bottom of the cube and it will do the rest
	public void makeCube(Point center, double width, Plane plane) {
		Point centerFive = Point.getTranslation(center, 0, 0, width);
		Square zero = new Square(center, width, plane);
		Square five = new Square(centerFive, width, plane);
		Shape one   = Shape.makeShape(zero.getEdge(0), five.getEdge(0));
		Shape two   = Shape.makeShape(zero.getEdge(1), five.getEdge(1));
		Shape three = Shape.makeShape(zero.getEdge(2), five.getEdge(2));
		Shape four  = Shape.makeShape(zero.getEdge(3), five.getEdge(3));
		this.addShape(zero);
		this.addShape(one);
		this.addShape(two);
		this.addShape(three);
		this.addShape(four);
		this.addShape(five);
	}
	
	public int getShapeCount() {
		return shapes.size();
	}
	public Shape getShape(int position) {
		return shapes.get(position);
	}
	
	public void addShape(Shape shape) {
		shapes.add(shape);
	}
	
	public void removeShape(int num) {
		shapes.remove(num);
	}
	public String toString() {
		StringBuilder b = new StringBuilder();
		b.append("[");
		for(Shape shape: shapes) {
			b.append("\n  ");
			b.append(shape.toString());
		}
		b.append("\n]");
		return b.toString();
	}

}
