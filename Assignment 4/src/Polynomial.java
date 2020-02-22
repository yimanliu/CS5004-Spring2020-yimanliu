public interface Polynomial {
    /**
     * Adding a term to the Polynomial,
     * throw an IllegalArgumentException if a negative power is passed to it.
     *
     * @param coefficient the coefficient of the adding term
     * @param power the power of the adding term
     * @return the Polynomial after adding the term
     */
    void addTerm (int coefficient, int power) throws IllegalArgumentException;

    /**
     * Removes all terms in the polynomial with the power.
     *
     * @param power the power of the removing term
     * @return the Polynomial after removing the term with this power
     */
    void removeTerm(int power);

    /**
     * Get the degree of the Polynomial
     *
     * @return the degree of this Polynomial
     */
    int getDegree();

    /**
     * Get the coefficient of the term with the specific power
     *
     * @param power the power of the term
     * @return the coefficient for the term with this power
     */
    int getCoefficient(int power);

    /**
     * Get the result after evaluating the polynomial with this value
     *
     * @param x the value of the variable in the Polynomial
     * @return the result after evaluating the polynomial with this value
     */
    double evaluate(double x);

    /**
     * Add two Polynomial objects,
     * if two objects are different classes, the method may throw an IllegalArgumentException.
     *
     * @param other another Polynomial object
     * @return the polynomial obtained by adding the two polynomials
     */
    Polynomial add(Polynomial other) throws IllegalArgumentException;
}
