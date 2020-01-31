import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

public class PersonTest {
    private Person Alice;
    private Person Jason;

    @Before
    public void setUp() throws Exception {
        Alice = new Person("Alice", "Wang");
        Jason = new Person("Jason", "Dong");
    }

    @Test
    public void testToString() {
        assertTrue(Alice.toString().equals("Alice Wang"));
        assertTrue(Jason.toString().equals("Jason Dong"));
    }

    @Test
    public void getFirstName() {
        assertTrue(Alice.getFirstName().equals("Alice"));
        assertTrue(Jason.getFirstName().equals("Jason"));
    }

    @Test
    public void getLastName() {
        assertTrue(Alice.getLastName().equals("Wang"));
        assertTrue(Jason.getLastName().equals("Dong"));
    }
}