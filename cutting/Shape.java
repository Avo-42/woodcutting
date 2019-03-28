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
	
	public double[] polygonX() {
		double[] result = new double[this.pointsSize()];
		for(int i = 0; i < this.pointsSize(); i++) {
			result[i] = this.getPoint(i).getX();
		}
		return result;
	}
	
	public double[] polygonY() {
		double[] result = new double[this.pointsSize()];
		for(int i = 0; i < this.pointsSize(); i++) {
			result[i] = this.getPoint(i).getY();
		}
		return result;
	}
	
	public double[] polygonZ() {
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
		for (int i = 0; i<points.length; i++) {
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
		XY, YZ, ZX;

		private Point direction;
		private Point perpendicular1;
		private Point perpendicular2;

		static {
			XY.direction = new Point(0, 0, 1);
			YZ.direction = new Point(1, 0, 0);
			ZX.direction = new Point(0, 1, 0);
			XY.perpendicular1 = YZ.direction;
			YZ.perpendicular1 = ZX.direction;
			ZX.perpendicular1 = XY.direction;
			XY.perpendicular2 = ZX.direction;
			YZ.perpendicular2 = XY.direction;
			ZX.perpendicular2 = YZ.direction;
		}

		public Point getDirection() { return direction; }
		public Point getPerpendicular1() { return perpendicular1; }
		public Point getPerpendicular2() { return perpendicular2; }
	}

	//there will only be a rotate shape method b/c rotating a creation will be useless and edges rotating is not very important
	//it will be assumed that the point center will be the shapes center but it will not be enforced so that if the user wants they can translate the shape about another point
	public void rotate(Plane plane, double angle) {
		Point center = makeCenter();
		if (plane == Plane.XY) {
			for(int i = 0; i<this.pointsSize(); i++) {
				this.getPoint(i).setPoint(this.getPoint(i).getRotatedZ(center,  angle));
			}
		}else if(plane == Plane.ZX) {
			for(int i = 0; i<this.pointsSize(); i++) {
				this.getPoint(i).setPoint(this.getPoint(i).getRotatedY(center,  angle));
			}
		}else{
			for(int i = 0; i<this.pointsSize(); i++) {
				this.getPoint(i).setPoint(this.getPoint(i).getRotatedX(center,  angle));
			}
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
		int edgeCount = result.edgesSize();
		for(int i = 0; i<edgeCount; i++) {
			result.setEdge(new Edge(result.getPoint(i), result.getPoint((i+1) % edgeCount)), i);
		}
//		result.setEdge(new Edge(result.getPoint(result.pointsSize()-1), result.getPoint(0)), result.pointsSize()-1);
//		result.setEdge(new Edge(result.getPoint(3), result.getPoint(0)), 3);
		return result;
	}

	/**
	 * Returns the angle between this shape and the given shape.
	 * How much to cut off of each shape to make this angle should be calculated elsewhere.
	 */
	public double getAngle(Shape shape) {

		Point perpendicularA = getPerpendicular();
		Point perpendicularB = shape.getPerpendicular();
		double angle = perpendicularA.getAngle(perpendicularB);
		return angle;
	}

	/**
	 * Returns the angle between the two edge that meet at this point.
	 * @param pointIndex The integer index of the point.
	 * @return The angel at this point.
	 */
	public double getAngle(int pointIndex) {
		int count = pointsSize();
		// Get the three points of interest (this point and the two adjacent points)
		Point p = points[pointIndex];
		Point nextP = points[(pointIndex + 1) % count];
		Point previousP = points[(pointIndex + count -1) % count];
		// Calculate the directions of the two edges adjacent to this point
		Point directionA = nextP.getDifference(p);
		Point directionB = previousP.getDifference(p);
		// Return the angle between these directions
		return directionA.getAngle(directionB);
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
