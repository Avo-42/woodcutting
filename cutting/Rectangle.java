package cutting;

public class Rectangle extends Shape {
	// just writing junk here
	
	public enum Plane{
		XY, XZ, YZ
	}
	
	double l, w;
	
	//plane is for telling which plane the rect will spawn in 1=xy plane, 2=yz plane, 3=xz plane
	public Rectangle(Point center, double length, double width, Plane plane) {
		super(4,center);
		l = length;
		w = width;
		if (plane == Plane.XY ) {
			int i = 3;
			for (int j = -1; j<=1; j+=2) {
				for (int k = -1; k<=1; k+=2) {
					double x = j*length/2 + center.getX();
					double y = k*width/2 + center.getY();
					double z = center.getZ();
					Point temp = new Point(x, y, z);
					setPoint(i, temp);
					i--;
				}
			}
		}else if(plane == Plane.YZ) {
			int i = 3;
			for (int j = -1; j<=1; j+=2) {
				for (int k = -1; k<=1; k+=2) {
					double x = center.getX();
					double y = j*width/2 + center.getY();
					double z = k*length/2 + center.getZ();
					Point temp = new Point(x, y, z);
					setPoint(i, temp);
					i--;
				}
			}
		}else {
			int i = 3;
			for (int j = -1; j<=1; j+=2) {
				for (int k = -1; k<=1; k+=2) {
					double x = j*length/2 + center.getX();
					double y = center.getY();
					double z = k*width/2 + center.getZ();
					Point temp = new Point(x, y, z);
					setPoint(i, temp);
				}
			}
		}
	}
	
	public Plane getPlane(Rectangle rect) {
		return rect.getPlane(rect);
	}
	
	//not needed anymore b/c of translateShape 
//	public void translateRectangle(Rectangle rect, double x, double y, double z) {
//		for (int i = 0; i<=3; i++) {
//			Point temp = rect.getPoint(i);
//			translatePoint(temp, x, y, z);
//		} 
//	}
	
}
