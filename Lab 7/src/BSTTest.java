import org.junit.Before;
import org.junit.Test;
import java.io.ByteArrayOutputStream;
import java.io.PrintStream;

import static org.junit.Assert.*;

public class BSTTest {
    BST tree1;      // Integer type tree test
    BST tree2;      // Integer type tree test
    BST tree3;      // String type tree test
    BST tree4;      // String type tree test

    @Before
    public void setUp() throws Exception {
        tree1 = new BST<Integer>();
        tree2 = new BST<Integer>();
        tree3 = new BST<String>();
        tree4 = new BST<String>();

        // initial BST here for "printInOrderRec" test
        Integer[] arr = new Integer[]{-4, 3, 43, 2, 0, -5, 7, 9, -23, 54};
        tree1.bTreeIns(arr);

        String[] arr2 = new String[]{"adf", "vgrgg", "zasf", "drtg", "tgggf", "lijm"};
        tree3.bTreeIns(arr2);
    }

    @Test
    public void insert() {
        tree2.insert(4);
        tree2.insert(1);
        tree2.insert(-5);
        tree2.insert(0);
        tree2.insert(-9);
        tree2.insert(6);
        String expectedOutput  = "-9 -5 0 1 4 6 ";
        assertTrue(expectedOutput.equals(tree2.toString()));

        tree4.insert("ab");
        tree4.insert("zh");
        tree4.insert("dgt");
        tree4.insert("edc");
        tree4.insert("wacf");
        tree4.insert("yuh");
        expectedOutput  = "ab dgt edc wacf yuh zh ";
        assertTrue(expectedOutput.equals(tree4.toString()));
    }

    @Test
    public void bTreeIns() {
        Integer[] arr1 = new Integer[]{5, 6, 3, 12, 4, 0, 7, 9};
        tree2.bTreeIns(arr1);
        String expectedOutput = "0 3 4 5 6 7 9 12 ";
        assertTrue(expectedOutput.equals(tree2.toString()));

        String[] arr2 = new String[]{"this", "is", "a", "example", "test"};
        tree4.bTreeIns(arr2);
        expectedOutput = "a example is test this ";
        assertTrue(expectedOutput.equals(tree4.toString()));
    }

    @Test
    public void printInOrderRec() {
        ByteArrayOutputStream outContent = new ByteArrayOutputStream();
        System.setOut(new PrintStream(outContent));
        tree1.printInOrderRec(tree1.root);
        String expectedOutput  = "-23 -5 -4 0 2 3 7 9 43 54 ";
        assertEquals(expectedOutput, outContent.toString());

        outContent = new ByteArrayOutputStream();
        System.setOut(new PrintStream(outContent));
        tree3.printInOrderRec(tree3.root);
        expectedOutput  = "adf drtg lijm tgggf vgrgg zasf ";
        assertEquals(expectedOutput, outContent.toString());
    }
}