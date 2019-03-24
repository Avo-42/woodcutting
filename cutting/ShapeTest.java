package cutting;

import org.junit.Test;
import static org.junit.Assert.*;

public class ShapeTest {

	@Test
	public void testGetPoint() {
		Point[] arr = new Point[5];
		Point a = new Point(3, 4, 5);
		Point b = new Point(5, 4, 6);
		Point c = new Point(1, 3, 2);
		arr[0] = a;
		arr[1] = b;
		arr[3] = c;

		assertEquals(a.getX(), arr[3].getY());
		// for some reason assertNotEquals does not work
		// assertNotEquals(a.getX(), arr[1].getX());
		// assertNotEquals(a.getX(),b.getX());
		assertEquals(b.getX(), arr[1].getX());
		assertEquals(b.getY(), arr[1].getY());
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

		assertEquals(b.getX(), arr[1].getX());
		Shape shape = new Shape(3, 3);
		shape.setPoint(0, a);
		assertEquals(a.getX(), (shape.getPoint(0)).getX());
	}

	@Test
	public void testGetCenter() {
		// fail("Not yet implemented");
		// Point a = new Point(1,2,3);
		// Point b = new Point(2,2,2);
		// Point c = new Point(3,4,5);
		Point d = new Point(0, 0, 0);
		Shape shape = new Shape(3, 3);

		assertEquals(d.getX(), shape.makeCenter().getX());
	}

}
