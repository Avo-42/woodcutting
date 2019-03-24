package cutting;

public class Parallelogram extends Shape {
		
	public Parallelogram(Edge edge, Point translation) {
		super(4, 4);
		this.setPoint(0, edge.getEdgePoint(0));
		this.setPoint(1, edge.getEdgePoint(1));
		this.setPoint(2, Point.getTranslation(this.getPoint(1), translation));
		this.setPoint(3, Point.getTranslation(this.getPoint(0), translation));
		Edge zero = new Edge(this.getPoint(0), this.getPoint(1));
		Edge one = new Edge(this.getPoint(1), this.getPoint(2));
		Edge two = new Edge(this.getPoint(2), this.getPoint(3));
		Edge three = new Edge(this.getPoint(3), this.getPoint(0));
		this.setEdge(zero, 0);
		this.setEdge(one, 1);
		this.setEdge(two, 2);
		this.setEdge(three, 3);
	}
	
	public static Parallelogram makeParallelogram(Edge edge, Point translation) {
		Parallelogram temp = new Parallelogram(edge, translation);
		return temp;
	}
	
}
