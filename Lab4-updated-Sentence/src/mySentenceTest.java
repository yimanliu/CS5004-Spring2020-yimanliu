import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

public class mySentenceTest {
    Sentence sentence1 = new mySentence();
    Sentence sentence2 = new mySentence();

    @Before
    public void setUp() throws Exception {
        sentence1.addWord("Coding", true);
        sentence1.addWord("is", true);
        sentence1.addWord("tough", true);
        sentence1.addWord(",", false);
        sentence1.addWord("but", true);
        sentence1.addWord("interesting", true);
        sentence1.addWord("!", false);

        sentence2.addWord("I", true);
        sentence2.addWord("love", true);
        sentence2.addWord("NEU", true);
        sentence2.addWord(",", false);
        sentence2.addWord("because", true);
        sentence2.addWord("it", true);
        sentence2.addWord("is", true);
        sentence2.addWord("cool", true);
    }

    @Test
    public void testToString() {
        assertTrue("Coding is tough, but interesting!".equals(sentence1.toString()));
        assertTrue("I love NEU, because it is cool.".equals(sentence2.toString()));
    }

    @Test
    public void getNumberOfWords() {
        assertTrue(5 == sentence1.getNumberOfWords());
        assertTrue(7 == sentence2.getNumberOfWords());
    }

    @Test
    public void longestWord() {
        assertTrue("interesting".equals(sentence1.longestWord()));
        assertTrue("because".equals(sentence2.longestWord()));
    }

    @Test
    public void testClone() {
        assertTrue("Coding is tough, but interesting!".equals(sentence1.clone().toString()));
        assertTrue("I love NEU, because it is cool.".equals(sentence2.clone().toString()));
    }

    @Test
    public void merge() {
        assertTrue("Coding is tough, but interesting! I love NEU, because it is cool.".equals(
                sentence1.merge(sentence2).toString()));
    }

    @Test
    public void remove() {
        assertTrue("I love NEU, it is cool.".equals(sentence2.remove("because").toString()));
        sentence1.remove("tough");
        sentence1.remove(",");
        sentence1.remove("but");
        assertTrue("Coding is interesting!".equals(sentence1.toString()));
    }
}