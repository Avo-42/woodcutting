package cutting;

public class Parallelogram extends Shape {
		
	public Parallelogram(Edge edge, Point translation) {
		super(4, 4);
		setPoint(0, edge.getEdgePoint(0));
		setPoint(1, edge.getEdgePoint(1));
		setPoint(2, Point.getTranslation(getPoint(1), translation));
		setPoint(3, Point.getTranslation(getPoint(0), translation));
		Edge zero = new Edge(getPoint(0), getPoint(1));
		Edge one = new Edge(getPoint(1), getPoint(2));
		Edge two = new Edge(getPoint(2), getPoint(3));
		Edge three = new Edge(getPoint(3), getPoint(0));
		setEdge(zero, 0);
		setEdge(one, 1);
		setEdge(two, 2);
		setEdge(three, 3);
	}
	
	public static Parallelogram makeParallelogram(Edge edge, Point translation) {
		Parallelogram temp = new Parallelogram(edge, translation);
		return temp;
	}
	
}
