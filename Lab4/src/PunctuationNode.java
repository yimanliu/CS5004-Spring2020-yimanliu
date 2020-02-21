public class PunctuationNode implements Node{
    char punctuation;
    Node next;

    /**
     * Construct a PunctuationNode
     *
     * @param punctuation the punctuation stored in the node
     * @param next the next node
     */
    public PunctuationNode(char punctuation, Node next) {
        this.punctuation = punctuation;
        this.next = next;
    }
    /**
     * Find the number of words in this node
     * @return the number of words in the sentence
     */
    @Override
    public int count() {
        return 0;
    }

    /**
     * Find and return the number of letters in this node.
     * @return the number of letters as described above
     */
    @Override
    public int countLetters() {
        return 0;
    }

    @Override
    public Node getNext() {
        return this.next;
    }

    /**
     * Build a copy of this node, including only words longer than the given cutoff length.
     * @param cutoff the cutoff length of words to include
     * @return the head node of the copy
     */
    @Override
    public Node longWords(int cutoff) {
        return new PunctuationNode(this.punctuation, this.next);
    }

    /**
     * Stringify this node.
     * @return the string form of the node
     */
    @Override
    public String itemize() {
        String s = "";
        PunctuationNode curr = this;
        while (curr != null) {
            s += curr.punctuation;
            curr = (PunctuationNode)curr.next;
        }
        return s;
    }

    @Override
    public String getWord() {
        return "";
    }

    /**
     * Copy this node, adding a word to the end.
     * @param word the word to add
     * @return the copy of this node
     */
    @Override
    public Node addBack(String word) {
        Node newNode = cloneNode();
        return newNode;
    }

    /**
     * Copy this node, removing the node at the specified index relative to this node.
     * Throw an exception if the index is out of bounds.
     *
     * @param index
     * @return the copy of this node
     * @throws IndexOutOfBoundsException
     */
    @Override
    public Node remove(int index) throws IndexOutOfBoundsException {
        PunctuationNode newNode = new PunctuationNode(this.punctuation, this.next);
        PunctuationNode dummy = new PunctuationNode(' ', newNode);
        PunctuationNode prev = dummy;
        while (newNode != null && index > 0) {
            index--;
            prev = newNode;
            newNode = (PunctuationNode)newNode.next;
        }
        if (index < 0 || newNode == null) {
            throw new IndexOutOfBoundsException("Index out of Boundary");
        }
        prev.next = newNode.next;
        return dummy.next;
    }

    /**
     * Find the index of this word, its position relative to this node, or -1.
     * @param word the word to find
     * @return the index of this word
     */
    @Override
    public int findIndex(String word) {
        return -1;
    }

    /**
     * Build a deep copy of this node
     * @return the copy of this node
     */
    @Override
    public Node cloneNode() {
        PunctuationNode dummyHead = new PunctuationNode(' ', null);
        PunctuationNode currOld = this;
        PunctuationNode currNew = dummyHead;
        while (currOld != null) {
            currNew.next = new PunctuationNode(currOld.punctuation, (PunctuationNode)currOld.next);
            currOld = (PunctuationNode)currOld.next;
        }
        return dummyHead.next;
    }
}
