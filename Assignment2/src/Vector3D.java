/**
 * @ by Yiman Liu
 * @ A class vector3D, represented by three components: x, y and z
 */
public class Vector3D {
    private double x;
    private double y;
    private double z;

    /**
     * Construct a Vector3D, given its three components: x, y and z
     *
     * @param x component x
     * @param y component y
     * @param z component z
     */
    public Vector3D(double x, double y, double z) {
        this.x = x;
        this.y = y;
        this.z = z;
    }

    /**
     * @return component x in Vector3D
     */
    public double getX() {
        return x;
    }

    /**
     * @return component y in Vector3D
     */
    public double getY() {
        return y;
    }

    /**
     * @return component z in Vector3D
     */
    public double getZ() {
        return z;
    }

    /**
     * @return a string representation of a Vector3D object
     */
    @Override
    public String toString() {
        return String.format("(%.2f,%.2f,%.2f)", x, y, z);
    }

    /**
     * The magnitude of a vector is denoted as |v| = sqrt(x^2 + y^2 + z^2).
     *
     * @return the magnitude of a vector
     */
    public double getMagnitude() {
        return Math.sqrt(x * x + y * y + z * z);
    }

    /**
     * Normalizing a vector by dividing each component of a vector by its magnitude.
     *
     * @return the vector with the same direction, but with magnitude to 1
     */
    public Vector3D normalize() throws IllegalStateException {
        double magnitude = this.getMagnitude();
        if (magnitude == 0) {
            throw new IllegalStateException ("zero divisor");
        } else {
            this.x = this.x / magnitude;
            this.y = this.y / magnitude;
            this.z = this.z / magnitude;
        }
        return this;
    }

    /**
     * Adding respective components of the two vectors
     *
     * @param that another vector object
     * @return the result of adding this vector to another vector
     */
    public Vector3D add(Vector3D that) {
        double a = this.x + that.x;
        double b = this.y + that.y;
        double c = this.z + that.z;
        return new Vector3D(a, b, c);
    }

    /**
     * Multiplying each component of the current vector by the provided constant
     *
     * @param k a constant
     * @return the result of multiplying this vector by a constant
     */
    public Vector3D multiply(double k) {
        return new Vector3D(k * this.x, k * this.y, k * this.z);
    }

    /**
     * @param that another vector
     * @return the dot product of this vector and another vector
     */
    public double dotProduct(Vector3D that) {
        return this.x * that.x + this.y * that.y + this.z * that.z;
    }

    /**
     * @param that another vector object
     * @return the angle between two vectors
     */
    public double angleBetween(Vector3D that) throws IllegalStateException {
        if (this == null || that == null) {
            throw new IllegalStateException("Null Pointer");
        }
        double magnitude1 = this.getMagnitude();
        double magnitude2 = that.getMagnitude();
        if (magnitude1 == 0 || magnitude2 == 0) {
            throw new IllegalStateException("invalid input");
        }
        return this.dotProduct(that) / (magnitude1 * magnitude2);
    }

    public static void main(String args[]) {
        //test1: valid input
        Vector3D v = new Vector3D(4.132,2.234,4.543);
        System.out.println("===================");
        System.out.println("Testing Valid Input");
        System.out.println("===================");
        System.out.println();
        System.out.println("Create a Vector3D object");
        System.out.println("x = 4.312 \ny = 2.234 \nz = 4.543 \n");

        //getX(), should return 4.132
        double x = v.getX();
        System.out.println("When I getX(), I get: " + x);

        //getY(), should return 2.234
        double y = v.getY();
        System.out.println("When I getY(), I get: " + y);

        //getZ(), should return 4.543
        double z = v.getZ();
        System.out.println("When I getZ(), I get: " + z + "\n");

        //toString(), should return (4.13,2.23,4.54)
        String s = v.toString();
        System.out.println("Here is what Vector3D looks like: " + s + "\n");

        //getMagnitude()
        double magnitude = v.getMagnitude();
        System.out.println("The magnitude of this Vector3D is: " + magnitude + "\n");

        //add(Vector3D vec)
        Vector3D res = v.add(new Vector3D(4, 2, 4));
        System.out.println("Add Vector3D (4,2,4) and Vector3D (4.132,2.234,4.543), the new Vector3D is: ");
        System.out.println(res.toString() + "\n");

        //multiply(double k)
        res = v.multiply(1.3);
        System.out.println("Multiply Vector3D (4.132,2.234,4.543) by 1.3, the new Vector3D is: ");
        System.out.println(res.toString() + "\n");

        //docProduct(Vector3D vec)
        res = v.add(new Vector3D(4, 2, 4));
        System.out.println("Product of Vector3D (4.132,2.234,4.543) and Vector3D (4,2,4) is: ");
        System.out.println(res.toString() + "\n");

        //angleBetween(Vector3D v)
        double angle = v.angleBetween(new Vector3D(4, 2, 4));
        System.out.println("Angle between Vector3D (4.132,2.234,4.543) and Vector3D (4,2,4) is: ");
        System.out.println(angle + "\n");

        //normalize(), should return Vector3D with magnitude == 1.0
        res = v.normalize();
        System.out.println("After normalizing this Vector3D, now the magnitude becomes: ");
        System.out.println(res.getMagnitude() + "\n");

        //test2: invalid input which will throw IllegalStateException
        System.out.println("=================================================");
        System.out.println("Testing Invalid Input, when input has 0 magnitude");
        System.out.println("=================================================");
        System.out.println();
        Vector3D v1 = new Vector3D(0,0,0);

        try {
            v1.normalize();
            System.out.println("After normalization, now the Vector becomes: " + v1);
        } catch (IllegalStateException e) {
            System.out.println("Invalid Vector3D object, please check your input \n");
        }

        //test3: invalid input which will throw IllegalStateException
        System.out.println("=========================================");
        System.out.println("Testing Invalid Input, when input is null");
        System.out.println("=========================================");
        System.out.println();
        Vector3D v2 = new Vector3D(1,2,3);

        try {
            double angle2 = v2.angleBetween(null);
            System.out.println("Angle between Vector3D (1,2,3) and null is: " + angle2);
        } catch (IllegalStateException e) {
            System.out.println("Invalid Vector3D object, please check your input");
        }
    }
}


















