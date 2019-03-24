package cutting;

import org.junit.Test;
import static org.junit.Assert.*;

class PointTest {

    @Test
    void isSame() {
        double delta=1e-11;
        assert(new Point(0, 0, 0).isSame(new Point(delta, -delta, 0)));
    }

    @Test
    void getRotatedZ() {
        Point original = new Point (1, 0, 10);
        Point rotated = new Point(0, 1, 10);
        Point center = new Point(0, 0, 0);
        assert(original.getRotatedZ(center,Math.PI/2).isSame(rotated));
    }
}