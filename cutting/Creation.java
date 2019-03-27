package cutting;

import java.awt.Color;
import java.util.ArrayList;
import java.util.List;

import cutting.Shape.Plane;

public class Creation {
	private List<Shape> shapes = new ArrayList<>(1);
	private List<Light> lights = new ArrayList<>(1);
	private Color defaultColor = new Color(100,100,100);
	
	
	public Creation() {
	}
	
	//gets the information of an edge given the two connected Shapes
	public String getCut(int ShapeA, int ShapeB) {
//		Edge edgeA, edgeB;
		int aNum = 0, bNum=0;
		for(int i = 0; i < shapes.get(ShapeA).edgesSize(); i++) {
			for(int j = 0; j < shapes.get(ShapeB).edgesSize(); j++) {
				Edge a = shapes.get(ShapeA).getEdge(i);
				Edge b = shapes.get(ShapeA).getEdge(j);
				if(a.fullyOverlaps(b)) {
					aNum = i;
					bNum = j;
				}
			}
		}
//		if (edgeA == null) {
//			return "no connecting edge";
//		}
		String result = "Shape A and Shape B are connected by Shape A's edge "+aNum+", and Shape B's edge "+bNum+".\n" ;
		double angle = shapes.get(ShapeA).getAngle(shapes.get(ShapeB));
		result = result + " The total angle between the two shapes is " +angle+ ", and half the angle is " +(angle/2)+".";
		return result;
	}
	
	public Color getColor(Shape shape, Color color){
		int red=defaultColor.getRed(); 
		int blue=defaultColor.getBlue();
		int green=defaultColor.getGreen();
		Point perpendicular = shape.getPerpendicular();
		for (Light light: lights) {
			Point temp = light.getPoint().getDifference(shape.makeCenter());
			temp.normalize();
			double a = temp.dotProduct(perpendicular);
			if (a<0) {
				continue;
			}
			red+=a*light.getColor().getRed();
			green+=a*light.getColor().getGreen();
			blue+=a*light.getColor().getBlue();
		}
		red=color.getRed()*red/255;
		green=color.getGreen()*green/255;
		blue=color.getBlue()*blue/255;
		if (red>255) {red=255;}
		if (green>255) {green=255;}
		if (blue>255) {blue=255;}
		if (red<0) {red=0;}
		if (green<0) {green=0;}
		if (blue<0) {blue=0;}
		return new Color(red,green,blue);
	}
	
	public void addLight(Light light) {
		lights.add(light);
	}
	
	public void removeLight(Light light) {
		lights.remove(light);
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
			this.addShape(left);
			this.addShape(right);
		} else {
			Triangle left  = new Triangle(a, b.getEdgePoint(0));
			Triangle right = new Triangle(b, a.getEdgePoint(1));
			this.addShape(left);
			this.addShape(right);
		}
	}
	
	//angle will rotate the opposite shape, and translation will translate the opposite shape
	public void makeRotatedCreation(Shape base, double angle, Point translation, Plane plane) {
		Shape temp = base;
		this.addShape(temp);
//		System.out.println("Base set");
		Shape last = Shape.getTranslatedShape(base, translation);
		last.rotate(plane, angle);
		this.addShape(last);
//		for (int i = 0; i < temp.pointsSize(); i++) {
//			System.out.println("made triangle for " + i);
//			this.makeTwoTriangles(temp.getEdge(i), last.getEdge(i), true);
//		}
		this.makeTwoTriangles(temp.getEdge(0), last.getEdge(0), true);
		this.makeTwoTriangles(temp.getEdge(1), last.getEdge(1), true);
		this.makeTwoTriangles(temp.getEdge(2), last.getEdge(2), true);
		this.makeTwoTriangles(temp.getEdge(3), last.getEdge(3), true);
	}
	
	public void makeRotatedCube(Point center, double width, double angle, Plane plane) {
		Point centerFive = Point.getTranslation(center, 0, 0, width);
		Square zero = new Square(center, width, plane);
		Square five = new Square(centerFive, width, plane);
		five.rotate(plane, angle);
		this.addShape(zero);
		this.addShape(five);
		this.makeTwoTriangles(zero.getEdge(0),five.getEdge(0), true);
		this.makeTwoTriangles(zero.getEdge(1),five.getEdge(1), true);
		this.makeTwoTriangles(zero.getEdge(2),five.getEdge(2), true);
		this.makeTwoTriangles(zero.getEdge(3),five.getEdge(3), true);
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
