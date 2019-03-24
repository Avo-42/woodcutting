package cutting;
import cutting.Shape.Plane;

public class Test {

	public static void main(String[] args) {
		Point center = new Point (0,0,0);
		Point zero = new Point(1,1,1);
		Point one = new Point(2,2,2);
		
		Parallelogram a = new Parallelogram(new Edge(center, zero), one);
		
	}

}
