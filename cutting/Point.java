package cutting;

public class Point {
	private double xVal,yVal,zVal;
	public Point(double x, double y, double z) {
		xVal = x;
		yVal = y;
		zVal = z;
	}
	
	public boolean isSame(Point a, Point b) {
		if (a.getX()!=b.getX()||a.getY()!=b.getY()||a.getZ()!=b.getZ()) {
			return false;
		}
		return true;
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
		xVal=x;
		yVal=y;
		zVal=z;
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
