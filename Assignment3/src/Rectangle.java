import java.util.NoSuchElementException;
import java.util.Objects;

/**
 * @ Implement a Rectangle class
 * @ by Yiman Liu
 * @ CS5004 Assignment#3
 */
public class Rectangle {
    private int x;
    private int y;
    private int width;
    private int height;

    /**
     * Creates a Rectangle using (x,y) coordinates as its lower left corner, width and height as its width and height
     *
     * @param x The X coordinate of the lower-left corner of the Rectangle.
     * @param y The Y coordinate of the lower-left corner of the Rectangle.
     * @param width The width of this Rectangle
     * @param height The height of this Rectangle
     * @throws IllegalArgumentException
     */
    public Rectangle(int x, int y, int width, int height) throws IllegalArgumentException{
        if (width <= 0 || height <= 0) {
            throw new IllegalArgumentException("Width and Height of Rectangle have to be positive numbers");
        }
        this.x = x;
        this.y = y;
        this.width = width;
        this.height = height;
    }
    /**
     * Check if two rectangles overlap
     *
     * @param other another Rectangle object
     * @return true if this rectangle overlaps with other, false otherwise
     */
    public boolean overlap(Rectangle other) {
        if (this.x + this.width <= other.x ||
                other.x + other.width <= this.x ||
                this.y + this.height <= other.y ||
                other.y + other.height <= this.y) {
            return false;
        } else {
            return true;
        }
    }
    /**
     * Computes the intersection of this Rectangle with another Rectangle.
     *
     * @param other another Rectangle object
     * @return a Rectangle object that represents the overlap of the two rectangles
     */
    public Rectangle intersect(Rectangle other) throws NoSuchElementException {
        if (!this.overlap(other)) {
            throw new NoSuchElementException("The two rectangles do not have overlap");
        }
        int x1 = Math.max(this.x, other.x);
        int y1 = Math.max(this.y, other.y);
        int newWidth = Math.min(this.x + this.width, other.x + other.width) - Math.max(this.x, other.x);
        int newHeight = Math.min(this.y + this.height, other.y + other.height) - Math.max(this.y, other.y);
        return new Rectangle(x1, y1, newWidth, newHeight);
    }

    /**
     * Computes the union of this Rectangle with another Rectangle.
     *
     * @param other another Rectangle object
     * @return a Rectangle object that represents the union of the two rectangles
     */
    public Rectangle union(Rectangle other) {
        int x1 = Math.min(this.x, other.x);
        int y1 = Math.min(this.y, other.y);
        int newWidth = Math.max(this.x + this.width, other.x + other.width) - Math.min(this.x, other.x);
        int newHeight = Math.max(this.y + this.height, other.y + other.height) - Math.min(this.y, other.y);
        return new Rectangle(x1, y1, newWidth, newHeight);
    }

    /**
     * A String representation of the Rectangle object
     *
     * @return a String representing this Rectangle.
     */
    @Override
    public String toString() {
        return "x:" + this.x + ", y:" + this.y + ", w:" + this.width + ", h:" + this.height;
    }

    @Override
    public boolean equals(Object other) {
        if (this == other) return true;
        if (other == null || getClass() != other.getClass()) return false;
        Rectangle rectangle = (Rectangle) other;
        return x == rectangle.x &&
                y == rectangle.y &&
                width == rectangle.width &&
                height == rectangle.height;
    }

    public static void main(String[] args) {
        System.out.println("============================================");
        System.out.println("Testing creating an invalid Rectangle object");
        System.out.println("============================================\n");
        try {
            Rectangle rec = new Rectangle(1,2,-6,4);
            System.out.println(rec.toString());
        } catch (IllegalArgumentException e) {
            System.out.println("Width and Height of Rectangle have to be positive numbers\n");
        }

        Rectangle r1 = new Rectangle(1,2,4,5);
        System.out.println("=================================");
        System.out.println("Testing printing Rectangle object");
        System.out.println("=================================\n");
        System.out.println(r1.toString() + "\n");

        System.out.println("======================================");
        System.out.println("Testing if two rectangles have overlap");
        System.out.println("======================================\n");
        System.out.println("    ________   \t\t\n" +
                "   |\t  __|______\t    \n" +
                "   |_____|__|\t   |\n" +
                "     \t |_________|\t");
        Rectangle r2 = new Rectangle(1,2,2,3);
        Rectangle r3 = new Rectangle(2, 1,3,3);
        System.out.println(r2.overlap(r3) + "\n");

        System.out.println("    ________ \t\t\n" +
                "   |\t  \t|\t _________\t  \n" +
                "   |_____ __|\t|   \t  |\n" +
                "   \t\t \t\t|_________|\t");
        Rectangle r4 = new Rectangle(5, 1,3,3);
        System.out.println(r2.overlap(r4) + "\n");

        System.out.println("==========================================");
        System.out.println("Testing the intersection of two rectangles");
        System.out.println("==========================================\n");
        System.out.println("1) When intersection does not exist:");
        try {
            Rectangle r = r2.intersect(r4);
            System.out.println("The intersection of two rectangles is: " + r.toString());
        } catch (NoSuchElementException e) {
            System.out.println("    The two rectangles do not have overlap\n");
        }
        System.out.println("1) When intersection exists:");
        System.out.println("    ________ \t\n" +
                "   |\t  __|______\t\n" +
                "   |_____|__|\t   |\n" +
                "   1\t |_________|\t\t \n" +
                "   \t     2");
        Rectangle r = r2.intersect(r3);
        System.out.println("    A. The intersection of two rectangles is: " + r.toString());

        Rectangle r5 = new Rectangle(2,4,2,3);
        r = r2.intersect(r5);
        System.out.println("   \t\t ________\n" +
                "    ____|__\t\t | \t\n" +
                "   |  2\t|__|_____|\t\n" +
                "   |_______|\t   \n" +
                "   1");
        System.out.println("    B. The intersection of two rectangles is: " + r.toString());

        Rectangle r6 = new Rectangle(0,3,2,3);
        r = r2.intersect(r6);
        System.out.println("   \t\t _______\n" +
                "    \t|\t  __|____ \t\n" +
                "     \t|____|__|\t |\t\n" +
                "   \t\t2\t |_______|\t   \n" +
                "   \t\t\t 1\t");
        System.out.println("    C. The intersection of two rectangles is: " + r.toString());

        Rectangle r7 = new Rectangle(0,0,2,3);
        r = r2.intersect(r7);
        System.out.println("    \t\t  _______ \t\n" +
                "     \t ____|__\t |\t\n" +
                "   \t\t|\t1|__|____|\t   \n" +
                "   \t\t|_______|\t\t \t\t \n" +
                "   \t   2");
        System.out.println("    D. The intersection of two rectangles is: " + r.toString());

        Rectangle r8 = new Rectangle(0,0,5,8);
        r = r2.intersect(r8);
        System.out.println("    \t ____________\t    \n" +
                "   \t\t|    ___\t |\t\t \t\t \n" +
                "   \t    |\t|_1_|\t |\n" +
                "   \t    |____________|\n" +
                "   \t\t2");
        System.out.println("    E. The intersection of two rectangles is: " + r.toString() + "\n");

        System.out.println("    When we change the role of 1 and 2, there still are another 4 situations:");
        r = r3.intersect(r2);
        System.out.println("    The intersection of two rectangles is: " + r.toString());
        r = r5.intersect(r2);
        System.out.println("    The intersection of two rectangles is: " + r.toString());
        r = r6.intersect(r2);
        System.out.println("    The intersection of two rectangles is: " + r.toString());
        r = r7.intersect(r2);
        System.out.println("    The intersection of two rectangles is: " + r.toString());
        r = r8.intersect(r2);
        System.out.println("    The intersection of two rectangles is: " + r.toString() + "\n");

        System.out.println("===================================");
        System.out.println("Testing the union of two rectangles");
        System.out.println("===================================\n");
        System.out.println("    ________ \t\n" +
                "   |\t  __|______\t\n" +
                "   |_____|__|\t   |\n" +
                "   1\t |_________|\t\t \n" +
                "   \t     2");
        r = r2.union(r3);
        System.out.println("The union of two rectangles is: " + r.toString());

        System.out.println("    ________ \t\t\n" +
                "   |\t  \t|\t _________\t  \n" +
                "   |_____ __|\t|   \t  |\n" +
                "   \t\t \t\t|_________|\t");
        r = r2.union(r4);
        System.out.println("The union of two rectangles is: " + r.toString());
    }
}

























