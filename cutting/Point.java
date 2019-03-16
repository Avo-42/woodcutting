package cutting;

public class Point {
	private double xValue, yValue, zValue;

	public Point(double x, double y, double z) {
		xValue = x;
		yValue = y;
		zValue = z;
	}
	
	//fix this so if a rounding mismatch occurs the points will still be counted as the same
	//fixed it so that it no longer requires two points
	public boolean isSame(Point point) {
		if (this.getX() != point.getX() || this.getY() != point.getY() || this.getZ() != point.getZ()) {
			return false;
		}
		return true;
	}

	public Point midpoint(Point point) {
		Point center = new Point(0,0,0);
		center.setPoint((this.getX()-point.getX())/2, (this.getY()-point.getY())/2, (this.getZ()-point.getZ())/2);
		return center;
	}

	public void setX(double x) {
		xValue = x;
	}

	public void setY(double y) {
		yValue = y;
	}

	public void setZ(double z) {
		zValue = z;
	}

	public void setPoint(double x, double y, double z) {
		xValue = x;
		yValue = y;
		zValue = z;
	}

	public double[] getCoord() {
		double[] a = new double[3];
		a[0] = xValue;
		a[1] = yValue;
		a[2] = zValue;
		return a;
	}

	public double getX() {
		return xValue;
	}

	public double getY() {
		return yValue;
	}

	public double getZ() {
		return zValue;
	}
}
