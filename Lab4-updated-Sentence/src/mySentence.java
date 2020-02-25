/**
 * @ by Yiman Liu
 * @ Lab 4,5,6 - implement a sentence class using LinkedList
 *
 * Here is the iteration way.
 */
public class mySentence implements Sentence{
    Node head;

    public mySentence() {
        this.head = new Node("", false);        // dummy node
    }

    /**
     * Add a word into the sentence
     * @param word the word inserted
     * @param isWord if it is a word
     */
    public void addWord(String word, boolean isWord) {
        Node curr = head;
        while (curr.next != null) {
            curr = curr.next;
        }
        curr.next = new Node(word, isWord);
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
        Node curr = head.next;
        String res = "";
        while (curr != null) {
            res += curr.toString();
            if (curr.next == null && curr.isWord) {
                res += ".";
            }
            curr = curr.next;
        }
        return res.substring(1);        // remove the heading whitespace
    }

    /**
     * Find the total number of words in this sequence,
     * The punctuation does not count as a word.
     *
     * @return the number of words as described above
     */
    @Override
    public int getNumberOfWords() {
        Node curr = head.next;
        int count = 0;
        while (curr != null) {
            if (curr.isWord) {
                count++;
            }
            curr = curr.next;
        }
        return count;
    }
    /**
     * Find the longest word in this sequence
     * @return the longest word in this sequence
     */
    @Override
    public String longestWord() {
        Node curr = head.next;
        String max = "";
        while (curr != null) {
            if (curr.isWord && curr.word.length() > max.length()) {
                max = curr.word;
            }
            curr = curr.next;
        }
        return max;
    }

    /**
     * Returns a duplicate of a given sentence.
     * A duplicate is a list that has the same words and punctuation in the same sequence,
     * but is independent of the original list.
     *
     * @return the deep copy of this sequence.
     */
    @Override
    public mySentence clone() {
        mySentence newSen = new mySentence();
        Node curr = head.next;
        while (curr != null) {
            newSen.addWord(curr.word, curr.isWord);
            curr = curr.next;
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
        mySentence newSen = clone();
        mySentence newOther = (mySentence) other.clone();
        Node curr = newSen.head;
        while (curr.next != null) {
            curr = curr.next;
        }
        curr.next = newOther.head.next;
        return newSen;
    }

    /**
     * Remove a word from the sentence
     * @param word the removed word
     * @return the sentence after removing the word
     */
    public mySentence remove(String word) {
        Node dummyNode = new Node("", true);
        Node curr = dummyNode;
        Node ptr = head.next;
        while (ptr != null) {
            if (!ptr.word.equals(word)) {
                curr.next = ptr;
                curr = curr.next;
            }
            ptr = ptr.next;
        }
        head = dummyNode;
        return this;
    }

    public static void main(String[] args) {
        mySentence sentence = new mySentence();
        sentence.addWord("Coding", true);
        sentence.addWord("is", true);
        sentence.addWord("tough", true);
        sentence.addWord(",", false);
        sentence.addWord("but", true);
        sentence.addWord("interesting", true);
        sentence.addWord("!", false);
        System.out.println("=========================================================================");
        System.out.println("Testing printing a sentence when the end of the sentence is a punctuation");
        System.out.println("=========================================================================\n");
        System.out.println(sentence.toString() + "\n");
        System.out.println("The number of words in above sentence is: ");
        System.out.println(sentence.getNumberOfWords() + "\n");
        System.out.println("The longest word in above sentence is: ");
        System.out.println(sentence.longestWord() + "\n");
        System.out.println("Clone the sentence, we get: ");
        System.out.println(sentence.clone().toString() + "\n");
        mySentence newSentence = new mySentence();
        newSentence.addWord("I", true);
        newSentence.addWord("love", true);
        newSentence.addWord("NEU", true);
        newSentence.addWord(",", false);
        newSentence.addWord("because", true);
        newSentence.addWord("it", true);
        newSentence.addWord("is", true);
        newSentence.addWord("cool", true);
        System.out.println("==================================================================");
        System.out.println("Testing printing a sentence when the end of the sentence is a word");
        System.out.println("==================================================================\n");
        System.out.println(newSentence.toString() + "\n");
        System.out.println("The number of words in above sentence is: ");
        System.out.println(newSentence.getNumberOfWords() + "\n");
        System.out.println("The longest word in above sentence is: ");
        System.out.println(newSentence.longestWord() + "\n");
        System.out.println("Clone the sentence, we get: ");
        System.out.println(newSentence.clone().toString() + "\n");
        System.out.println("Merge two sentences, we get: ");
        System.out.println(sentence.merge(newSentence).toString() + "\n");
        System.out.println("Remove 'because' from the second sentence, we get: ");
        System.out.println(((mySentence) newSentence).remove("because").toString() + "\n");
    }
}
