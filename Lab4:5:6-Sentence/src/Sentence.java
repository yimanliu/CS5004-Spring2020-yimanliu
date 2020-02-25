/**
 * Word sequence
 */
public interface Sentence {

    /**
     * Add a word into the sentence
     * @param word the word inserted
     * @param isWord if it is a word
     */
    void addWord(String word, boolean isWord);
    /**
     * Find the total number of words in this sequence,
     * The punctuation does not count as a word.
     *
     * @return the number of words as described above
     */
    int getNumberOfWords();

    /**
     * Find the longest word in this sequence
     * @return the longest word in this sequence
     */
    String longestWord();

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
    String toString();

    /**
     * Returns a duplicate of a given sentence.
     * A duplicate is a list that has the same words and punctuation in the same sequence,
     * but is independent of the original list.
     *
     * @return the deep copy of this sequence.
     */
    Sentence clone();

    /**
     * Merges two sentences into a single sentence.
     * The merged Sentence should preserve all the punctuation.
     * The original Sentence should be unchanged.
     *
     * @param other another Sentence object
     * @return the merged list
     */
    Sentence merge(Sentence other);
    /**
     * Remove a word from the sentence
     * @param word the removed word
     * @return the sentence after removing the word
     */
    Sentence remove(String word);
}


