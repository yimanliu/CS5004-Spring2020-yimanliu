
import org.junit.Before;
import org.junit.Test;

import java.util.NoSuchElementException;

import static org.junit.Assert.assertTrue;

public class PolynomialImplTest {
    Polynomial poly;
    Polynomial poly2;
    Polynomial poly3;
    Polynomial poly4;

    @Before
    public void setUp() throws Exception {
        poly = new PolynomialImpl("4x^5 -3x^3 -5x^2 -6");
        poly2 = new PolynomialImpl("3x^4 -5x^3 +2x^1 -4");
        poly3 = new PolynomialImpl("5x^2 +4x^1 -2");
        poly4 = new PolynomialImpl("-50x^3 +1x^2 +3");

    }
    @Test(expected = IllegalArgumentException.class)
    public void testException() {
        Polynomial polyZeroCoefficient = new PolynomialImpl("0x^3 + 5");       // zero coefficient
        Polynomial polyNegativePower = new PolynomialImpl("3x^3 + 5x^2 + 2x^-3");  // negative power
    }
    @Test
    public void testToString() {
        assertTrue("4x^5 -3x^3 -5x^2 -6".equals(poly.toString()));
        assertTrue("3x^4 -5x^3 +2x^1 -4".equals(poly2.toString()));
        assertTrue("5x^2 +4x^1 -2".equals(poly3.toString()));
        assertTrue("-50x^3 +1x^2 +3".equals(poly4.toString()));
    }

    @Test
    public void addTerm() {
        poly.addTerm(7,6);
        Polynomial res = new PolynomialImpl("7x^6 +4x^5 -3x^3 -5x^2 -6");       // add a term to front
        assertTrue(poly.equals(res));

        poly.addTerm(10, 0);                                   // add a term to end
        res = new PolynomialImpl("7x^6 +4x^5 -3x^3 -5x^2 +4");
        assertTrue(poly.equals(res));

        poly.addTerm(-5, 3);                    // insert a term with the same power of a term in list
        res = new PolynomialImpl("7x^6 +4x^5 -8x^3 -5x^2 +4");
        assertTrue(poly.equals(res));
    }

    @Test
    public void removeTerm() {
        poly2.removeTerm(4);                                        // remove the highest power
        Polynomial res = new PolynomialImpl("-5x^3 +2x^1 -4");
        assertTrue(poly2.equals(res));

        poly2.removeTerm(1);                                        // remove power 1 term
        res = new PolynomialImpl("-5x^3 -4");
        assertTrue(poly2.equals(res));

        poly2.removeTerm(5);                                        // remove term's power not exist in the list
        res = new PolynomialImpl("-5x^3 -4");
        assertTrue(poly2.equals(res));

    }

    @Test
    public void getDegree() {
        assertTrue(5 == poly.getDegree());
        assertTrue(4 == poly2.getDegree());
        assertTrue(2 == poly3.getDegree());
        assertTrue(3 == poly4.getDegree());
    }

    @Test
    public void getCoefficient() {
        assertTrue(4 == poly.getCoefficient(5));
        assertTrue(-5 == poly2.getCoefficient(3));
        assertTrue(4 == poly3.getCoefficient(1));
        assertTrue(0 == poly4.getCoefficient(5));    // term with this power does not exist in the list
    }

    @Test
    public void evaluate() {
        assertTrue(11994 == poly.evaluate(5));
        assertTrue(40.0625 == poly2.evaluate(2.5));
        assertTrue(202 == poly3.evaluate(6));
        assertTrue(-163.5 == poly4.evaluate(1.5));
    }

    @Test(expected = IllegalArgumentException.class)
    public void testIllegalArgumentException() {
        poly.add(null);
    }   // invalid input
    @Test
    public void add() {
        Polynomial test1 = new PolynomialImpl("4x^5 -3x^3 -5x^2 -6");
        Polynomial test2 = new PolynomialImpl("-50x^3 +1x^2 +3");
        Polynomial res = new PolynomialImpl("4x^5 -53x^3 -4x^2 -3");
        assertTrue(res.equals(poly4.add(poly)));           // test if this method mutates either Polynomial
        assertTrue(poly4.equals(test2));

        res = new PolynomialImpl("3x^4 -55x^3 +1x^2 +2x^1 -1");
        assertTrue(res.equals(poly4.add(poly2)));

        res = new PolynomialImpl("-50x^3 +6x^2 +4x^1 +1");
        assertTrue(res.equals(poly4.add(poly3)));

    }
}