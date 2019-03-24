package cutting;

import org.junit.Test;
import static org.junit.Assert.*;

public class ShapeTest {
	private static final double expectedPrecision = 1e-10;

	@Test
	public void testGetPoint() {
		Point[] arr = new Point[5];
		Point a = new Point(3, 4, 5);
		Point b = new Point(5, 4, 6);
		Point c = new Point(1, 3, 2);
		arr[0] = a;
		arr[1] = b;
		arr[3] = c;

		assertEquals(a.getX(), arr[3].getY(), expectedPrecision);
		// for some reason assertNotEquals does not work
		// assertNotEquals(a.getX(), arr[1].getX());
		// assertNotEquals(a.getX(),b.getX());
		assertEquals(b.getX(), arr[1].getX(), expectedPrecision);
		assertEquals(b.getY(), arr[1].getY(), expectedPrecision);
	}

	@Test
	public void testSetPoint() {
		// fail("Not yet implemented");
		Point[] arr = new Point[5];
		Point a = new Point(3, 4, 5);
		Point b = new Point(5, 4, 6);
		Point c = new Point(1, 3, 2);
		arr[0] = a;
		arr[1] = b;
		arr[3] = c;

		assertEquals(b.getX(), arr[1].getX(), expectedPrecision);
		Shape shape = new Shape(3, 3);
		shape.setPoint(0, a);
		assertEquals(a.getX(), (shape.getPoint(0)).getX(), expectedPrecision);
	}

	@Test
	public void testGetCenter() {
		Shape shape = new Shape(4, 4);
		Point center = new Point(0,0, 0);
		shape.setPoint(0, new Point(-1, -1, 0));
		shape.setPoint(1, new Point(-1, 1, 0));
		shape.setPoint(2, new Point(1, 1, 0));
		shape.setPoint(3, new Point(1, -1, 0));
		assertEquals(center.getX(), shape.makeCenter().getX(), expectedPrecision);
	}

}
