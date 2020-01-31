/**
 * A Book Class
 */
public class Book {
    private String title;
    private Person author;
    private double price;

    /**
     * Construct a Book object
     *
     * @param title the title of the book
     * @param author the author of the book
     * @param price the price of the book
     */
    public Book(String title, Person author, double price) {
        this.title = title;
        this.author = author;
        this.price = price;
    }

    /**
     * Get the author of this book
     *
     * @return the author of the book
     */
    public Person getAuthor() {
        return this.author;
    }

    /**
     * Get String representation of Book object
     *
     * @return the string representation of this Book
     */
    public String toString() {
        String res = "Title: ";
        res = res + this.title + "\n";
        res = res + "Author: ";
        res = res + this.author + "\n";
        res = res + "Price: ";
        res = res + this.price;

        return res;
    }

    /**
     * Get the price of the book after applying discount
     *
     * @param discount discount for this book
     * @return the price of the book after applying discount
     * @throws IllegalArgumentException
     */
    public double salePrice(int discount)
            throws IllegalArgumentException {
        if (discount <= 0) {
            throw new IllegalArgumentException("negative discount");
        }
        return this.price - (this.price * discount) / 100.0;
    }

    // this function compares whether the given book has
    // the same author of this book.
    // Return true if they are the same.
    public boolean sameAuthor(Book b) {
        Person p1 = this.author;
        Person p2 = b.getAuthor();

        return p1.getFirstName().equals(p2.getFirstName())
                && p1.getLastName().equals(p2.getLastName());
        //return this.author.equals(b.author);
    }

    // this function compares whether the given book has
    // the same author of this book.
    // Return true if they are the same
    /*
    public boolean sameAuthorOfTwoBooks(Book b1, Book b2) {
        Person p1 = this.author;
        Person p2 = b.getAuthor();

        return p1.getFirstName().equals(p2.getFirstName())
                && p1.getLastName().equals(p2.getLastName());
    }
    */

    // create sequel of "this" book
    public Book createSequel() {
        String newTitle = this.title + " II";
        double newPrice = this.price * 2.0;

        Book newBook = new Book(newTitle, this.author, newPrice);

        return newBook;

        // or you can do:
        // return new Book(newTitle, this.author, newPrice);
    }

    public static void main(String[] args) {
        // Prints "Hello, World" to the terminal window.
        //System.out.println("Hello, World");
        String f = new String("Tom");
        String f2 = new String("Tom");
        Person person = new Person(f, "Cheng");
        Book book = new Book("A book", person, 5.20);
        Book book2 = new Book("Second book", person, 10);

        String res = book.toString();
        System.out.println("The book is: " + res);

        //double newPrice = book2.salePrice(15);
        //System.out.println(newPrice);

        Person person2 = new Person(f2, "Cheng");
        Person person3 = new Person("Eric", "Cheng");
        Book book3 = new Book("A book", person2, 5.20);
        Book book4 = new Book("A book", person3, 5.20);

        // Test1: compare author of book3 and author of book
        boolean res1 = book.sameAuthor(book3);
        System.out.println(res1);

        // Test2: compare author of book4 and author of book
        boolean res2 = book.sameAuthor(book4);
        System.out.println(res2);

        //boolean res3 = book.sameAuthorOfTwoBooks(book, book4);

        // Test createSequel
        Book newBook = book.createSequel();
        System.out.println("Sequel: " + newBook);

        // terrible discount
        try {
            double newPrice2 = book2.salePrice(-15);
            System.out.println(newPrice2);
        } catch (IllegalArgumentException e) {
            // you can do something to handle the error here...
            System.out.println("Exception caught: " + e);
        }
        Book book1 = new Book("Math", new Person("Alice", "Wang"), 25.10);
        System.out.println(book1.salePrice(30));
        System.out.println(book1.createSequel().toString());
    }
}
