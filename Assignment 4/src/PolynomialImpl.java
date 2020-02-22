import java.util.Objects;

/**
 * @ by Yiman Liu
 * @ CS5004 Assignment #4 - Using LinkedList to create a Polynomial class
 */
public class PolynomialImpl implements Polynomial{
    PolyNode head;

    /**
     * Construct a polynomial with no terms
     */
    public PolynomialImpl() {
        this.head = new PolyNode();
    }
    /**
     * Construct a Polynomial using the existing polynomial
     *
     * @param polynomial a polynomial represented by a String
     */
    public PolynomialImpl(String polynomial){
        this.head = new PolyNode();                                             // dummy node
        String[] terms = polynomial.split(" ");
        PolyNode curr = this.head;

        for (String term : terms) {
            int i = 0;
            char sign = '+';
            if (!Character.isDigit(term.charAt(i))) {
                if (term.charAt(i++) == '-') sign = '-';
            }
            int start = i;
            while (i < term.length() && Character.isDigit(term.charAt(i))) {    // find coefficient
                i++;
            }
            int coe = Integer.parseInt(term.substring(start, i));
            int power = 0;
            if (i < term.length()) {
                i = i + 2;                                                      // find power
                power = Integer.parseInt(term.substring(i));
            }
            if (sign == '-') coe = -coe;
            curr.next = new PolyNode(coe, power);
            curr = curr.next;
        }
    }

    /**
     * Get the String format of the Polynomial
     *
     * @return a string representation of the Polynomial
     */
    @Override
    public String toString() {
        String s = "";
        PolyNode curr = this.head.next;
        while (curr != null) {
            int coe = curr.coefficient;
            int power = curr.power;
            if (coe > 0) {
                s += '+';
            }
            if (power == 0) {
                s += String.valueOf(coe);
            } else {
                s += String.valueOf(coe) + "x^" + String.valueOf(power) + " ";
            }
            curr = curr.next;
        }
        if (s.charAt(0) == '-') {
            return s;
        } else {
            return s.substring(1);
        }
    }

    @Override
    public boolean equals(Object other) {
        if (this == other) return true;
        if (other == null || getClass() != other.getClass()) return false;
        PolynomialImpl that = (PolynomialImpl) other;
        PolyNode curr1 = this.head.next;
        PolyNode curr2 = that.head.next;
        while (curr1 != null && curr2 != null) {
            if (curr1.coefficient != curr2.coefficient || curr1.power != curr2.power) return false;
            curr1 = curr1.next;
            curr2 = curr2.next;
        }
        return curr1 == null && curr2 == null;
    }

    /**
     * Adding a term to the Polynomial,
     * throw an IllegalArgumentException if a negative power is passed to it.
     *
     * @param coefficient the coefficient of the adding term
     * @param power       the power of the adding term
     * @return the Polynomial after adding the term
     */
    @Override
    public void addTerm(int coefficient, int power) throws IllegalArgumentException{
        if (coefficient == 0 || power < 0) {
            throw new IllegalArgumentException("The coefficient of the term should be an non-zero number and " +
                    "the power of the term can not be a negative number.");
        }
        PolyNode curr = this.head.next;
        PolyNode prev = head;
        while (curr != null && curr.power > power) {
            prev = curr;
            curr = curr.next;
        }
        if (curr != null && curr.power == power) {               // case1: there exists the same power term in the list
            curr.coefficient += coefficient;
        } else {                                                 // case2: insert the term into the list
            prev.next = new PolyNode(coefficient, power);
            prev.next.next = curr;
        }
    }

    /**
     * Removes all terms in the polynomial with the power.
     *
     * @param power the power of the removing term
     * @return the Polynomial after removing the term with this power
     */
    @Override
    public void removeTerm(int power){
        PolyNode curr = this.head.next;
        PolyNode prev = this.head;
        while (curr != null && curr.power != power) {
            prev = curr;
            curr = curr.next;
        }
        if (curr == null) return;       // when the term with this power doesn't exist in this Polynomial
        prev.next = curr.next;
    }

    /**
     * Get the degree of the Polynomial
     *
     * @return the degree of this Polynomial
     */
    @Override
    public int getDegree() {
        return this.head.next == null ? 0 : this.head.next.power;
    }

    /**
     * Get the coefficient of the term with the specific power
     *
     * @param power the power of the term
     * @return the coefficient for the term with this power
     */
    @Override
    public int getCoefficient(int power) {
        PolyNode curr = this.head.next;
        while (curr != null && curr.power != power) {
            curr = curr.next;
        }
        if (curr == null) return 0;         // when the term with this power doesn't exist in this Polynomial
        return curr.coefficient;
    }

    /**
     * Get the result after evaluating the polynomial with this value
     *
     * @param x the value of the variable in the Polynomial
     * @return the result after evaluating the polynomial with this value
     */
    @Override
    public double evaluate(double x) {
        PolyNode curr = this.head.next;
        double res = 0.0;
        while (curr != null) {
            res += curr.coefficient * (Math.pow(x, curr.power));
            curr = curr.next;
        }
        return res;
    }

    /**
     * Add two Polynomial objects,
     * if two objects are different classes, the method may throw an IllegalArgumentException.
     *
     * @param other another Polynomial object
     * @return the polynomial obtained by adding the two polynomials
     */
    @Override
    public Polynomial add(Polynomial other) throws IllegalArgumentException{
        if (other == null || getClass() != other.getClass()) {
            throw new IllegalArgumentException("Two objects should be the same class");
        }
        PolynomialImpl res = new PolynomialImpl();
        PolyNode newHead = res.head;
        PolyNode curr1 = this.head.next;
        PolyNode curr2 = ((PolynomialImpl) other).head.next;
        while (curr1 != null && curr2 != null) {
            if (curr1.power > curr2.power) {
                newHead.next = new PolyNode(curr1.coefficient, curr1.power);
                curr1 = curr1.next;
            } else if (curr1.power < curr2.power) {
                newHead.next = new PolyNode(curr2.coefficient, curr2.power);
                curr2 = curr2.next;
            } else {
                newHead.next = new PolyNode(curr1.coefficient + curr2.coefficient, curr1.power);
                curr1 = curr1.next;
                curr2 = curr2.next;
            }
            newHead = newHead.next;
        }
        while (curr1 != null) {                                             // if "other" Polynomial exhausted
            newHead.next = new PolyNode(curr1.coefficient, curr1.power);
            curr1 = curr1.next;
            newHead = newHead.next;
        }
        while (curr2 != null) {                                             // if "this" Polynomial exhausted
            newHead.next = new PolyNode(curr2.coefficient, curr2.power);
            curr2 = curr2.next;
            newHead = newHead.next;
        }
        return res;
    }

    public static void main(String[] args) {
        System.out.println("===========================");
        System.out.println("Testing printing Polynomial");
        System.out.println("===========================\n");
        Polynomial poly = new PolynomialImpl("4x^5 -3x^3 -5x^2 -6");
        System.out.println(poly.toString() + "\n");
        Polynomial poly2 = new PolynomialImpl("3x^4 -5x^3 +2x^1 -4");
        System.out.println(poly2.toString() + "\n");

        System.out.println("===========================================");
        System.out.println("Testing adding a term to current Polynomial");
        System.out.println("===========================================\n");
        System.out.println("Add 7x^4 to 4x^5 -3x^3 -5x^2 -6, we get: ");
        poly.addTerm(7,4);
        System.out.println(poly.toString() + "\n");
        System.out.println("Add 5x^3 to 4x^5 +7x^4 -3x^3 -5x^2 -6, we get: ");
        poly.addTerm(5,3);
        System.out.println(poly.toString() + "\n");
        System.out.println("Add -2x^5 to 4x^5 +7x^4 +2x^3 -5x^2 -6, we get: ");
        poly.addTerm(-2,5);
        System.out.println(poly.toString() + "\n");

        System.out.println("Add 10 to 2x^5 +7x^4 +2x^3 -5x^2 -6, we get: ");
        poly.addTerm(10,0);
        System.out.println(poly.toString() + "\n");

        System.out.println("Add 6x^6 to 2x^5 +7x^4 +2x^3 -5x^2 +4, we get: ");
        poly.addTerm(6,6);
        System.out.println(poly.toString() + "\n");

        System.out.println("===============================================");
        System.out.println("Testing removing a term from current Polynomial");
        System.out.println("===============================================\n");
        System.out.println("Remove term with power 4 from 6x^6 +2x^5 +7x^4 +2x^3 -5x^2 +4, we get: ");
        poly.removeTerm(4);
        System.out.println(poly.toString() + "\n");
        System.out.println("Remove term with power 0 from 6x^6 +2x^5 +2x^3 -5x^2 +4, we get: ");
        poly.removeTerm(0);
        System.out.println(poly.toString() + "\n");
        System.out.println("Remove term with power 6 from 6x^6 +2x^5 +2x^3 -5x^2, we get: ");
        poly.removeTerm(6);
        System.out.println(poly.toString() + "\n");

        System.out.println("===============================================");
        System.out.println("Testing getting the degree of this Polynomial");
        System.out.println("===============================================\n");
        System.out.println("The degree of 2x^5 +2x^3 -5x^2 is: ");
        System.out.println(poly.getDegree() + "\n");

        System.out.println("==================================================");
        System.out.println("Testing getting the coefficient of this Polynomial");
        System.out.println("==================================================\n");
        System.out.println("The coefficient of term with power 2 in 2x^5 +2x^3 -5x^2 is: ");
        System.out.println(poly.getCoefficient(2) + "\n");

        System.out.println("===================================================");
        System.out.println("Testing evaluating a Polynomial with certain value");
        System.out.println("==================================================\n");
        System.out.println("When value = 2.5, Polynomial 3x^4 -5x^3 +2x^1 -4 = ");
        System.out.println(poly2.evaluate(2.5) + "\n");

        System.out.println("====================================================");
        System.out.println("Testing adding another Polynomial to this Polynomial");
        System.out.println("====================================================\n");
        Polynomial poly3 = new PolynomialImpl("5x^2 +4x^1 -2");
        System.out.println("Add 5x^2 +4x^1 -2 to 3x^4 -5x^3 +2x -4, we get: ");
        System.out.println(poly2.add(poly3).toString() + "\n");
        Polynomial poly4 = new PolynomialImpl("-50x^3 +1x^2 +3");
        System.out.println("Add -50x^3 +1x^2 +3 to 5x^2 +4x^1 -2, we get: ");
        System.out.println(poly3.add(poly4).toString() + "\n");
    }
}
