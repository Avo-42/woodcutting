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
	public boolean isSame(Point otherPoint) {
		if (round(getX(), 4) != round(otherPoint.getX(),4) || round(getY(),4) != round(otherPoint.getY(),4) || round(getZ(),4) != round(otherPoint.getZ(),4)) {
			return false;
		}
		return true;
	}
	
	public double round(double input, int places) {
		double scale = Math.pow(10, places);
		return Math.round((input*scale)/scale);
	}
	
	public Point getRotatedZ(Point center, double angle) {
		double c = Math.cos(angle);
		double s = Math.sin(angle);
		Point r = getDifference(center);
		Point rotated = new Point(
				r.getX()*c + r.getY()*s,
				r.getY()*c - r.getX()*s,
				r.getZ());
		return rotated.getSum(center);
	}
	
	public Point getRotatedY(Point center, double angle) {
		double c = Math.cos(angle);
		double s = Math.sin(angle);
		Point r = getDifference(center);
		Point rotated = new Point(
				r.getX()*c - r.getZ()*s,
				r.getY(),
				r.getZ()*c + r.getX()*s);
		return rotated.getSum(center);
	}
	
	public Point getRotatedX(Point center, double angle) {
		double c = Math.cos(angle);
		double s = Math.sin(angle);
		Point r = getDifference(center);
		Point rotated = new Point(
				r.getX(),
				r.getY()*c + r.getZ()*s,
				r.getZ()*c - r.getY()*s);
		return rotated.getSum(center);
	}
	
	public Point midpoint(Point otherPoint) {
		return new Point((getX()-otherPoint.getX())/2, (getY()-otherPoint.getY())/2, (getZ()-otherPoint.getZ())/2);
	}
	
	public double getAngle(Point otherPoint) {
		Point a = getNormal();
		Point b = otherPoint.getNormal();
		return Math.acos(a.dotProduct(b));
	}
	
	public Point getNormal() {
		double l = abs();
		Point result = new Point(getX()/l, getY()/l, getZ()/l);
		return result;
	}
	
	public void normalize() {
		double l = abs();
		setX(getX()/l);
		setY(getY()/l);
		setZ(getZ()/l);
	}
	
	public double abs() {
		double result = Math.sqrt(xValue*xValue + yValue*yValue + zValue*zValue);
		return result;
	}
	
	public double dotProduct(Point otherPoint) {
		double x = this.getX() * otherPoint.getX();
		double y = this.getY() * otherPoint.getY();
		double z = this.getZ() * otherPoint.getZ();
		double result = x + y + z;
		return result;
	}
	
	public Point crossProduct(Point otherPoint) {
		double x = getY() * otherPoint.getZ() - getZ() * otherPoint.getY();
		double y = getZ() * otherPoint.getX() - getX() * otherPoint.getZ();
		double z = getX() * otherPoint.getY() - getY() * otherPoint.getX();
		Point result = new Point(x, y, z);
		return result;
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
	
	public void setPoint(Point p) {
		xValue = p.xValue;
		yValue = p.yValue;
		zValue = p.zValue;
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
	
	// makes a new point translated according to the inputs
	// use when making new points to be added
	public static Point getTranslation(Point point, double x, double y, double z) {
		x+=point.getX();
		y+=point.getY();
		z+=point.getZ();
		Point result = new Point(x, y, z);
		return result;
	}
	
	public static Point getTranslation(Point a, Point b) {
		return new Point(a.getX()+b.getX(), a.getY()+b.getY(), a.getZ()+b.getZ());
	}
	
	public Point getTranslation(Point b) {
		return new Point(this.getX()+b.getX(), this.getY()+b.getY(), this.getZ()+b.getZ());
	}
	
	public Point getDifference(Point a) {
		return getTranslation(this, -a.getX(), -a.getY(), -a.getZ());
	}
	
	public Point getSum(Point a) {
		return getTranslation(this, a.getX(), a.getY(), a.getZ());
	}
	
	// translates a point, no new points are created
	public static void translatePoint(Point point, double x, double y, double z) {
		x += point.getX();
		y += point.getY();
		z += point.getZ();
		point.setPoint(x, y, z);
	}
	
	public static void translatePoint(Point point, Point t) {
		point.setX(t.getX() + point.getX());
		point.setY(t.getY() + point.getY() );
		point.setZ(t.getZ() + point.getZ());
	}
	
	public void translatePoint(Point t) {
		this.setX(t.getX() + this.getX());
		this.setY(t.getY() + this.getY() );
		this.setZ(t.getZ() + this.getZ());
	}
}
