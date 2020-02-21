public interface Node {
    /**
     * Find and return the total number of words in this node.
     * @return the number of words as described above
     */
    int count();

    /**
     * Find and return the number of letters in this node.
     * @return the number of letters as described above
     */
    int countLetters();

    /**
     * @return next Node
     */
    Node getNext();
    /**
     * Build a copy of this node, including only words longer than the given cutoff length.
     * @param cutoff the cutoff length of words to include
     * @return the head node of the copy
     */
    Node longWords(int cutoff);

    /**
     * Stringify this node.
     * @return the string form of the node
     */
    String itemize();

    String getWord();
    /**
     * Copy this node, adding a word to the end.
     * @param word the word to add
     * @return the copy of this node
     */
    Node addBack(String word);

    /**
     * Copy this node, removing the node at the specified index relative to this node.
     * Throw an exception if the index is out of bounds.
     * @param index
     * @return the copy of this node
     * @throws IndexOutOfBoundsException
     */
    Node remove(int index) throws IndexOutOfBoundsException;

    /**
     * Find the index of this word, its position relative to this node, or -1.
     * @param word the word to find
     * @return the index of this word
     */
    int findIndex(String word);

    /**
     * Build a deep copy of this node
     * @return the copy of this node
     */
    Node cloneNode();
}
