public class mySentence implements Sentence{
    int n;
    Node head;

    public mySentence(Node node) {
        this.head = new EmptyNode();
    }
    /**
     * Find the total number of words in this sequence,
     * The punctuation does not count as a word.
     *
     * @return the number of words as described above
     */
    @Override
    public int getNumberOfWords() {
        return head.count();
    }
    /**
     * Find the longest word in this sequence
     * @return the longest word in this sequence
     */
    @Override
    public String longestWord() {
        Node curr = head;
        String max = "";
        while (curr != null && curr.count() != 0) {
            if (curr.countLetters() > max.length()) {
                max = curr.getWord();
            }
            curr = curr.getNext();
        }
        return max;
    }
    /**
     * Convert the sentence into one string.
     * There must be a space between every two words.
     * There is no space between the last word and the end of the sentence.
     * If there is no punctuation mark at the end of the sentence,
     * this string should end with a period (it shouldn't add
     * the period to the original sentence)
     *
     * @return the string representation of this sentence.
     */
    @Override
    public String toString() {
        String s = "";
        Node curr = head;
        while (curr != null) {
            s += curr.getWord();
            curr = curr.getNext();
        }
        return s;
    }
    /**
     * Returns a duplicate of a given sentence.
     * A duplicate is a list that has the same words and punctuation in the same sequence,
     * but is independent of the original list.
     *
     * @return the deep copy of this sequence.
     */
    @Override
    public Sentence clone() {
        mySentence newSen = new mySentence(this.head);
        mySentence currOld = this;
        mySentence currNew = newSen;
        while (currOld != null) {
            currNew.head = new WordNode(currOld.head.getWord(), currOld.head);
            currOld.head = currOld.head.getNext();
            currNew.head = currNew.head.getNext();
        }
        return newSen;
    }
    /**
     * Merges two sentences into a single sentence.
     * The merged Sentence should preserve all the punctuation.
     * The original Sentence should be unchanged.
     *
     * @param other another Sentence object
     * @return the merged list
     */
    @Override
    public Sentence merge(Sentence other) {
        Node node = new EmptyNode();
        mySentence newSen = new mySentence(node);
        newSen.head = this.head;
        return newSen;
    }
}
