import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

public class FractionTest {
    private Fraction oneSecond;
    private Fraction fourSeconds;
    private Fraction oneFifths;
    private Fraction twoSixths;
    private Fraction twoTens;

    @Before
    public void setUp() throws Exception {
        oneSecond = new Fraction(1, 2);
        fourSeconds = new Fraction(4, 2);
        oneFifths = new Fraction(1, 5);
        twoSixths = new Fraction(2,6);
        twoTens = new Fraction(2,10);
    }

    @Test
    public void toDouble() {
        assertTrue(oneSecond.toDouble() == 0.5);
        assertTrue(fourSeconds.toDouble() == 2.0);
        assertTrue(oneFifths.toDouble() == 0.2);
    }

    @Test
    public void testToString() {
        assertTrue("1/2".equals(oneSecond.toString()));
        assertTrue("4/2".equals(fourSeconds.toString()));
        assertTrue("1/5".equals(oneFifths.toString()));
    }

    @Test
    public void testEquals() {
        assertTrue(oneSecond.equals(new Fraction(2,4)));
        assertTrue(fourSeconds.equals(new Fraction(8,4)));
        assertTrue(oneFifths.equals(new Fraction(2,10)));
    }

    @Test
    public void reciprocal() {
        assertTrue(oneSecond.reciprocal().toDouble() == 2/1);
        assertTrue(oneFifths.reciprocal().toDouble() == 5/1);
    }

    @Test
    public void add() {
        Fraction f = new Fraction(5, 6);
        assertTrue(twoSixths.add(new Fraction(1,2)).equals(f));
    }

    @Test
    public void compareTo() {
        assertTrue(oneSecond.compareTo(fourSeconds) < 0);
        assertTrue(twoSixths.compareTo(oneFifths) > 0);
        assertTrue(oneFifths.compareTo(twoTens) == 0);
    }
}