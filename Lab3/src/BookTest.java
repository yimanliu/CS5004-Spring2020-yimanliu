import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

public class BookTest {
    private Book book1;
    private Book book2;
    private Book book3;

    @Before
    public void setUp() throws Exception {
        this.book1 = new Book("Math", new Person("Alice", "Wang"), 20.30);
        this.book2 = new Book("Chinese", new Person("David", "Tang"), 18.00);
        this.book3 = new Book("Music", new Person("Windy", "Lin"), 25.10);
    }
    @Test
    public void getAuthor() {
        assertTrue(book1.getAuthor().toString().equals("Alice Wang"));
        assertTrue(book2.getAuthor().toString().equals("David Tang"));
        assertTrue(book3.getAuthor().toString().equals("Windy Lin"));
    }

    @Test
    public void salePrice() {
        assertTrue(book2.salePrice(10) == 16.2);
        assertTrue(book3.salePrice(30) == 17.57);
    }

    @Test
    public void sameAuthor() {
        assertFalse(book1.sameAuthor(book2));
        assertFalse(book2.sameAuthor(book3));
    }
}