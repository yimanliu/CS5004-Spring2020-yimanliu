import java.util.Objects;

/**
 * A class Fraction represents a fraction
 */
public class Fraction {
    private int numerator;
    private int denominator;

    /**
     * Construct a Fraction object
     *
     * @param numerator an integer as numerator
     * @param denominator an integer as denominator
     * @throws IllegalArgumentException
     */
    public Fraction(int numerator, int denominator) throws IllegalArgumentException {
        if (denominator <= 0) {
            throw new IllegalArgumentException("Invalid denominator");
        }
        this.numerator = numerator;
        this.denominator = denominator;
    }

    /**
     * Convert the Fraction to a scientific value (decimal number)
     *
     * @return the scientific value (decimal) of the fraction
     */
    public double toDouble() {
        return (double)numerator / (double)denominator;
    }

    /**
     * Get the String representation of this Fraction.
     *
     * @return a String representation of this fraction
     */
    @Override
    public String toString() {
        int gcd = 1;
        for (int i = 1; i < this.numerator && i < this.denominator; i++) {
            if (this.numerator % i == 0 && this.denominator % i == 0) {
                gcd = i;
            }
        }
        int a = this.numerator / gcd;
        int b = this.denominator / gcd;
        if (a == 0) return "0";
        return a + "/" + b;
    }

    /**
     * Check if two Fractions are equal
     *
     * @param other another Fraction object
     * @return if two Fractions' values are equal
     */
    @Override
    public boolean equals(Object other) {
        if (this == other) return true;
        Fraction fraction = (Fraction) other;
        return this.toDouble() == ((Fraction) other).toDouble();
    }

    /**
     * Find the reciprocal of this Fraction
     *
     * @return the reciprocal of this fraction
     * @throws IllegalArgumentException
     */
    public Fraction reciprocal() throws IllegalArgumentException {
        if (this.denominator == 0) {
            throw new IllegalArgumentException ("Zero does not have a reciprocal");
        }
        int temp = this.numerator;
        this.numerator = this.denominator;
        this.denominator = temp;
        return this;
    }

    /**
     * Add two fractions
     *
     * @param other another Fraction object
     * @return the result as a fraction object
     */
    public Fraction add(Fraction other) {
        int newDen = this.denominator * other.denominator;
        int newNum = this.numerator * other.denominator + other.numerator * this.denominator;
        return new Fraction(newNum, newDen);
    }

    /**
     * Compare two Fraction
     *  if they are equal, return 0
     *  if this > other, return 1
     *  if this < other, return -1
     *
     * @param other another Fraction object
     * @return a negative number if this < other, a positive number if this > other, otherwise return 0
     */
    public int compareTo(Fraction other) {
        if (this.toDouble() < other.toDouble()) {
            return -1;
        } else if (this.toDouble() > other.toDouble()) {
            return 1;
        } else {
            return 0;
        }
    }

    public static void main(String args[]) {
        /**
         *Test case 1, normal case
         */
        Fraction fra = new Fraction(1, 2);
        double res = fra.toDouble();
        System.out.println("Double of this Fraction: " + res);

        String s = fra.toString();
        System.out.println("String representation: " + s);

        boolean equal = fra.equals(new Fraction(5, 10));
        System.out.println("If 1/2 equal 5/10: " + equal);

        Fraction ans = fra.reciprocal();
        System.out.println("Reciprocal of this fraction: " + ans.toString());

        ans = fra.add(new Fraction(2, 4));
        System.out.println("After adding (2/4) to (2/1), fraction becomes: " + ans.toString());

        int com = fra.compareTo(new Fraction(12, 4));
        boolean less = com < 0;
        System.out.println("If (2/1) less than (12/4)): " + less);

        System.out.println();

        /**
         * Test case 2, when numerator is negative
         */
        Fraction num = new Fraction(-1, 3);

        res = fra.toDouble();
        System.out.println("Double of this Fraction: " + res);

        s = fra.toString();
        System.out.println("String representation: " + s);

        equal = fra.equals(new Fraction(5, 10));
        System.out.println("If -1/3 equal 5/10: " + equal);

        ans = fra.reciprocal();
        System.out.println("Reciprocal of this fraction: " + ans.toString());

        ans = fra.add(new Fraction(-2, 4));
        System.out.println("After adding (-2/4) to (1/2), fraction becomes: " + ans.toString());

        com = fra.compareTo(new Fraction(3, 4));
        less = com < 0;
        System.out.println("If (1/2) less than (3/4)): " + less);

        /**
         * Test case 3, when denominator is zero, throw IllegalArgumentException
         */
        Fraction num2 = new Fraction(1, 0);

    }
}


