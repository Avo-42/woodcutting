package cutting;

public class Corner {
	private final int pointIndex;
	private final Shape shape;
	public Corner(Shape shape, int pointIndex) {
		this.shape = shape;
		this.pointIndex = pointIndex;
	}
	
	public int getPosition() {
		return pointIndex;
	}
	
	public Shape getShape() {
		return shape;
	}
}
