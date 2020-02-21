import java.lang.annotation.ElementType;

public class EmptyNode implements Node{
    Node next;
    /**
     * Constructor for an Empty node
     */
    public EmptyNode () { }

    /**
     * @return count of elements in this node, 0 = empty
     */
    public int count() {
        return 0;
    }

    public String getWord() {
        return "";
    }

    /**
     * Find the number of letters in this node, 0.
     * @return the number of letters, 0
     */
    public int countLetters() {
        return 0;
    }

    @Override
    public Node getNext() {
        return null;
    }

    /**
     * Stringify this node.
     * @return the string form of this node, ""
     */
    public String itemize() {
        return "";
    }

    /**
     * Build a copy of this node, including only words longer than the given cutoff length.
     * @param cutoff the cutoff length of words to include
     * @return a copy of this EmptyNode
     */
    public Node longWords(int cutoff) {
        return new EmptyNode();
    }

    /**
     * Add a word to the end of this EmptyNode by returning a single node
     * containing the word.
     * @param word the word to add, of type String
     * @return the new node
     */
    public Node addBack(String word) {
        return new WordNode(word, null);
    }

    /**
     * This method will throw an exception, unconditionally.
     * @param index integer indicating which element
     * @throws IndexOutOfBoundsException since no elements
     */
    public Node remove(int index) throws IndexOutOfBoundsException {
        throw new IndexOutOfBoundsException("Empty Node");
    }

    public int findIndex(String word) {
        return -1;
    }

    public Node cloneNode() {
        return new EmptyNode();
    }
}
