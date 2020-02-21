public class WordNode implements Node{
    String word;
    Node next;

    /**
     * Construct a WordNode
     *
     * @param myWord the word stored in the node
     * @param next the next node
     */
    public WordNode(String myWord, Node next) {
        this.word = myWord;
        this.next = next;
    }
    /**
     * Find the number of words in this node
     * @return the number of words in the sentence
     */
    @Override
    public int count() {
        return 1 + next.count();
    }

    public String getWord() {
        return this.word;
    }

    /**
     * Find and return the number of letters in this node.
     * @return the number of letters as described above
     */
    @Override
    public int countLetters() {
        return this.word.length() + next.countLetters();
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
    public WordNode longWords(int cutoff) {
        WordNode newNode = cloneNode();
        WordNode curr = newNode;
        int index = 0;
        while (curr != null) {
            if (curr.word.length() <= cutoff) {
                remove(index);
            } else {
                index++;
                curr = (WordNode) curr.next;
            }
        }
        return newNode;
    }
    /**
     * Stringify this node.
     * @return the string form of the node
     */
    @Override
    public String itemize() {
        String s = "";
        WordNode curr = this;
        while (curr != null) {
            s += curr.word;
            curr = (WordNode) curr.next;
        }
        return s;
    }

    /**
     * Copy this node, adding a word to the end.
     * @param word the word to add
     * @return the copy of this node
     */
    @Override
    public Node addBack(String word) {
        WordNode newNode = cloneNode();
        WordNode curr = newNode;
        while (curr.next != null) {
            curr = (WordNode)curr.next;
        }
        curr.next = new WordNode(word, null);
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
        WordNode newNode = cloneNode();
        WordNode dummy = new WordNode("", newNode);
        WordNode curr = newNode;
        WordNode prev = dummy;
        while (curr != null && index > 0) {
            index--;
            prev = curr;
            curr = (WordNode)curr.next;
        }
        if (index < 0 || curr == null) {
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
        WordNode curr = this;
        int index = 0;
        while (curr != null) {
            if (curr.word.equals(word)) {
                return index;
            } else {
                curr = (WordNode)curr.next;
                index++;
            }
        }
        return -1;
    }

    /**
     * Build a deep copy of this node
     * @return the copy of this node
     */
    @Override
    public WordNode cloneNode() {
        WordNode dummyHead = new WordNode("", null);
        WordNode currOld = this;
        WordNode currNew = dummyHead;
        while (currOld != null) {
            currNew.next = new WordNode(currOld.word, currOld.next);
            currOld = (WordNode)currOld.next;
        }
        return (WordNode)dummyHead.next;
    }
}
