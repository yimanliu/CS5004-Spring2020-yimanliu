import org.junit.Before;
import org.junit.Test;


import java.util.NoSuchElementException;

import static org.junit.Assert.*;
/**
 * RectangleTest Class
 */
public class RectangleTest {
    private Rectangle Rec;
    private Rectangle RecNoOverlap;
    private Rectangle RecOverlap;
    private Rectangle r1;
    private Rectangle r2;
    private Rectangle r3;
    private Rectangle r4;
    private Rectangle r5;
    private Rectangle r6;
    private Rectangle r7;

    @Before
    public void setUp() throws Exception {
        // for overlap test
        Rec = new Rectangle(1,2,2,3);
        RecOverlap = new Rectangle(-5,-2,10,5);
        RecNoOverlap = new Rectangle(5, 1,3,3);

        // for intersect and union test
        r1 = new Rectangle(2, 1,3,3);
        r2 = new Rectangle(2,4,2,3);
        r3 = new Rectangle(0,3,2,3);
        r4 = new Rectangle(0,0,2,3);
        r5 = new Rectangle(0,0,5,8);

        // for toString test
        r6 = new Rectangle(1,2,1,1);
        r7 = new Rectangle(1,2,2,3);
    }

    @Test
    public void overlap() {
        assertTrue(Rec.overlap(RecOverlap));
        assertFalse(Rec.overlap(RecNoOverlap));

        assertTrue(Rec.overlap(r1));
        assertTrue(Rec.overlap(r2));
        assertTrue(Rec.overlap(r3));
        assertTrue(Rec.overlap(r4));
        assertTrue(Rec.overlap(r5));
    }

    @Test
    public void intersect() {
        Rectangle r = Rec.intersect(r1);
        Rectangle sol = new Rectangle(2,2,1,2);
        assertTrue(r.equals(sol));

        r = Rec.intersect(r2);
        sol = new Rectangle(2,4,1,1);
        assertTrue(r.equals(sol));

        r = Rec.intersect(r3);
        sol = new Rectangle(1,3,1,2);
        assertTrue(r.equals(sol));

        r = Rec.intersect(r4);
        sol = new Rectangle(1,2,1,1);
        assertTrue(r.equals(sol));

        r = Rec.intersect(r5);
        sol = new Rectangle(1,2,2,3);
        assertTrue(r.equals(sol));
    }
    
    @Test(expected = NoSuchElementException.class)
    public void testException() {
        Rectangle a = new Rectangle(1,1,2,2);
        Rectangle b = new Rectangle(5,5,1,1);
        a.intersect(b);

        Rec.intersect(RecNoOverlap);
    }

    @Test
    public void union() {
        Rectangle r = Rec.union(r1);
        Rectangle sol = new Rectangle(1,1,4,4);
        assertTrue(r.equals(sol));

        r = Rec.union(RecNoOverlap);
        sol = new Rectangle(1,1,7,4);
        assertTrue(r.equals(sol));
    }

    @Test
    public void testToString() {
        assertTrue("x:1, y:2, w:2, h:3".equals(Rec.toString()));
        assertFalse("x:1, y:8, w:2, h:3".equals(RecNoOverlap.toString()));
        assertTrue("x:1, y:2, w:1, h:1".equals(r6.toString()));
        assertTrue("x:1, y:2, w:2, h:3".equals(r7.toString()));
    }
}
