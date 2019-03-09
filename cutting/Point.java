package cutting;

public class Point {
	private double xVal, yVal, zVal;

	public Point(double x, double y, double z) {
		xVal = x;
		yVal = y;
		zVal = z;
	}
	
	//fix this so if a rounding mismatch occurs the points will still be counted as the same
	//fixed it so that it no longer requires two points
	public boolean isSame(Point b) {
		if (this.getX() != b.getX() || this.getY() != b.getY() || this.getZ() != b.getZ()) {
			return false;
		}
		return true;
	}

	public Point midpoint(Point a) {
		Point center = new Point(0,0,0);
		center.setPoint((this.getX()-a.getX())/2, (this.getY()-a.getY())/2, (this.getZ()-a.getZ())/2);
		return center;
	}

	public void setX(double x) {
		xVal = x;
	}

	public void setY(double y) {
		yVal = y;
	}

	public void setZ(double z) {
		zVal = z;
	}

	public void setPoint(double x, double y, double z) {
		xVal = x;
		yVal = y;
		zVal = z;
	}

	public double[] getCoord() {
		double[] a = new double[3];
		a[0] = xVal;
		a[1] = yVal;
		a[2] = zVal;
		return a;
	}

	public double getX() {
		return xVal;
	}

	public double getY() {
		return yVal;
	}

	public double getZ() {
		return zVal;
	}
}
