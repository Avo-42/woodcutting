package cutting;

public class Corner {
	private int num;
	private Shape polygon;
	public Corner(Shape shape, int pointNumber) {
		polygon = shape;
		num = pointNumber;
	}
	
	public int getPosition() {
		return num;
	}
	
	public Shape getShape() {
		return polygon;
	}
}
