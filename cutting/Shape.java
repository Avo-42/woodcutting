package cutting;

import java.util.Arrays;

public class Shape{
	private Point[] points;
	private Edge[] edges;
	private Point center;
	
	public Shape (int pointCount, int edgeCount) {
		points = new Point[pointCount];
		edges = new Edge[edgeCount];
	}
	
	public double[] plygonX() {
		double[] result = new double[this.pointsSize()];
		for(int i = 0; i < this.pointsSize(); i++) {
			result[i] = this.getPoint(i).getX();
		}
		return result;
	}
	
	public double[] plygonY() {
		double[] result = new double[this.pointsSize()];
		for(int i = 0; i < this.pointsSize(); i++) {
			result[i] = this.getPoint(i).getY();
		}
		return result;
	}
	
	public double[] plygonZ() {
		double[] result = new double[this.pointsSize()];
		for(int i = 0; i < this.pointsSize(); i++) {
			result[i] = this.getPoint(i).getZ();
		}
		return result;
	}
	
	public Point makeCenter() {
		return new Point(
				Arrays.stream(points).mapToDouble(Point::getX).average().orElse(-1),
				Arrays.stream(points).mapToDouble(Point::getY).average().orElse(-1),
				Arrays.stream(points).mapToDouble(Point::getZ).average().orElse(-1));
	}
	
	public Shape makeShape(Point[] points, int edgeCount) {
		Shape a = new Shape(points.length, edgeCount);
		for (int i = 0; i<=points.length; i++) {
			a.setPoint(i, points[i]);
		}
		for (int i = 1; i <=(edgeCount-1); i ++) {
			Edge edge = new Edge(points[i-1], points[i]);
			this.setEdge(edge, i-1);
			//this will add all but one of the edges to edges[]
		}
		// I am assuming that edgeCount and pointCount are equal here, adds the last edge, hence the name last.
		Edge last = new Edge (points[0], points[edgeCount-1]);
		this.setEdge(last, edgeCount);
		return a;
	}

	/**
	 * Returns a normalized point for the direction that is perpendicular to this shape.
	 * @return The perpendicular direction.
	 */
	public Point getPerpendicular(){
		Point edgeDirection0 = getPoint(0).getDifference(getPoint(1));
		Point edgeDirection1 = getPoint(1).getDifference(getPoint(2));
		Point perpendicular = edgeDirection0.crossProduct(edgeDirection1);
		perpendicular.normalize();
		return perpendicular;
	}
	
	public static Shape makeShape(Edge edgeOne, Edge edgeTwo) {
		//this class will take two edges and make a shape out of them
		if (edgeOne.hasOverlap(edgeTwo)==true) {
			throw new Error("please input edges that do not share a point");
		}
		Shape shape = new Shape(4, 4);
		shape.setPoint(0, edgeOne.getEdgePoint(0));
		shape.setPoint(1, edgeOne.getEdgePoint(1));
		shape.setPoint(2, edgeTwo.getEdgePoint(1));
		shape.setPoint(3, edgeTwo.getEdgePoint(0));
		shape.edges[0] = edgeOne;
		shape.edges[2] = edgeTwo;
		//making a note right here b/c depending on how makeShape(point[]) is used this may make a diagonal edge, can't think of an easy fix.
		shape.edges[1] = new Edge(edgeOne.getEdgePoint(1), edgeTwo.getEdgePoint(1));
		shape.edges[3] = new Edge(edgeTwo.getEdgePoint(0), edgeOne.getEdgePoint(0));
		return shape;
	}
	
	public enum Plane{
		XY, XZ, YZ
	}
	
	//there will only be a rotate shape method b/c rotating a creation will be useless and edges rotating is not very important
	//it will be assumed that the point center will be the shapes center but it will not be enforced so that if the user wants they can translate the shape about another point
	public void rotate(Plane plane, double angle) {
		Point center = makeCenter();
		if (plane == Plane.XY) {
			for(int i = 0; i<this.pointsSize(); i++) {
				this.getPoint(i).setPoint(this.getPoint(i).getRotatedZ(center,  angle));
			}
		}else if(plane == Plane.XZ) {
			for(int i = 0; i<this.pointsSize(); i++) {
				this.getPoint(i).setPoint(this.getPoint(i).getRotatedY(center,  angle));
			}
		}else{
			for(int i = 0; i<this.pointsSize(); i++) {
				this.getPoint(i).setPoint(this.getPoint(i).getRotatedX(center,  angle));
			}
		}
		for (int i = 0; i < edges.length ; i++) {
			
		}
	}
	
	public void setEdge(Edge edge, int position) {
		edges[position] = edge;
	}
	
	public Edge getEdge(int position) {
		return edges[position];
	}
	
	public Edge getCopiedEdge(int position) {
		Edge result = edges[position];
		return result;
	}
	
	public int pointsSize() {
		return points.length;
	}
	
	public Point getPoint(int pointNumber) {
//		if(pointNumber > points.length) {
//			System.out.println("pointNum larger than points");
//		}
		return points[pointNumber];
	}
	
	public Point getCopiedPoint(int pointNum) {
		Point p = points[pointNum];
		return p;
	}
	
	public void setPoint(int pointPosition,Point point) {
		points[pointPosition] = point;
	}
	
	public void translateShape(Shape shape, double x, double y, double z) {
		for(int i = 0; i<shape.pointsSize(); i++) {
			Point.translatePoint(shape.getPoint(i), x, y, z);
		}
	}
	
	public void translateShape(Shape shape, Point t) {
		for(int i = 0; i<shape.pointsSize(); i++) {
			Point.translatePoint(shape.getPoint(i), t);
		}
	}
	
	public void copyShape(Shape otherShape) {
		for(int i = 0; i < points.length; i++) {
			this.setPoint(i, otherShape.getCopiedPoint(i));
		}
		for(int i = 0; i < edges.length; i++) {
			this.setEdge(otherShape.getEdge(i), i);
		}
	}
	
	public int edgesSize() {
		return edges.length;
	}
	
	public static Shape getTranslatedShape(Shape shapeToCopy, Point t) {
		Shape result = new Shape(shapeToCopy.pointsSize(), shapeToCopy.edgesSize()); 
		result.copyShape(shapeToCopy);
		for(int i = 0; i<shapeToCopy.pointsSize(); i++) {
			result.setPoint(i, result.getPoint(i).getTranslation(t));
		}
		for(int i = 0; i<result.edgesSize()-2; i++) {
			result.setEdge(new Edge(result.getPoint(i), result.getPoint(i+1)), i);
		}
//		result.setEdge(new Edge(result.getPoint(result.pointsSize()-1), result.getPoint(0)), result.pointsSize()-1);
		result.setEdge(new Edge(result.getPoint(3), result.getPoint(0)), 3);
		return result;
	}
	
	public double getAngle(Shape shape) {
		Point centerA = this.makeCenter();
		Point centerB = shape.makeCenter();
		double angle = centerA.getAngle(centerB)/2;
		return angle;
	}
	
	public String toString() {
		StringBuilder b = new StringBuilder();
		b.append("[");
		for(int i = 0; i<points.length; i++) {
			Point p = points[i];
			if(i>0)
				b.append(", ");
			b.append(String.format("(%.1f, %.1f, %.1f)", p.getX(), p.getY(), p.getZ()));
		}
		b.append("]");
		return b.toString();
	}
	
}
