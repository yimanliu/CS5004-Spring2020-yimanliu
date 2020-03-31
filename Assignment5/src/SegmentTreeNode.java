public class SegmentTreeNode extends Node {
    int maxScore;
    int low;
    int high;

    public SegmentTreeNode(int start, int end, Player player, Node left, Node right) {
        super(player, left, right);
        this.low = start;
        this.high = end;
    }
}