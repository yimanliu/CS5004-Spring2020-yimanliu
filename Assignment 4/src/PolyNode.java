public class PolyNode {
    int coefficient;
    int power;
    PolyNode next;

    public PolyNode (int coefficient, int power) throws IllegalArgumentException {
        if (coefficient == 0 || power < 0) {
            throw new IllegalArgumentException("The coefficient of the term should be an non-zero number and " +
                    "the power of the term should be a positive number.");
        }
        this.coefficient = coefficient;
        this.power = power;
        this.next = null;
    }

    public PolyNode() {
        this.coefficient = 0;
        this.power = 0;
    }
}
