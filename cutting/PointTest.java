package cutting;

import org.junit.Test;
import static org.junit.Assert.*;

public class PointTest {

    @Test
    public void testIsSame() {
        double delta=1e-11;
        Point p1 = new Point(0, 0, 0);
        Point p2 = new Point(delta, -delta, 0);
        assertTrue(p1.isSame(p2));
    }

    @Test
    public void testGetRotatedZ() {
        Point original = new Point (1, 0, 10);
        Point rotated = new Point(0, 1, 10);
        Point center = new Point(0, 0, 0);
        // assertTrue(original.getRotatedZ(center,Math.PI/2).isSame(rotated));
        assertTrue(original.getRotatedZ(center,Math.PI/2).isSame(rotated));
    }
}