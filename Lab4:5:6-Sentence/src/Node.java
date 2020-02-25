public class Node {
    String word;
    Node next;
    boolean isWord;

    public Node(String word, boolean isWord) {
        this.word = word;
        this.isWord = isWord;
    }

    public String toString() {
        String res = "";
        if (this.isWord) {
            res += " ";
        }
        res += this.word;
        return res;
    }
}
