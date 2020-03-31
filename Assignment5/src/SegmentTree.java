
public class SegmentTree<T> {
    Node root;

    /**
     * Construct a segment tree
     *
     * @param players A list with players
     */
    public SegmentTree(Player[] players) {
        if (players == null || players.length == 0) return;
        root = buildTree(players, 0, players.length - 1);
    }

    private Node buildTree(Player[] players, int lo, int hi) {
        if (lo > hi) {
            return null;
        }
        Node root = new SegmentTreeNode(players[lo].id, players[hi].id, null, null, null);
        if (lo == hi) {
            ((SegmentTreeNode) root).maxScore = players[lo].score;
            root.player = players[lo];
        } else {
            int mid = lo + (hi - lo) / 2;
            root.left = buildTree(players, lo, mid);
            root.right = buildTree(players, mid + 1, hi);
            ((SegmentTreeNode) root).maxScore = Math.max(((SegmentTreeNode) root.left).maxScore,
                    ((SegmentTreeNode) root.right).maxScore);
        }
        return root;
    }

    /**
     * Get the highest score in a given range
     *
     * @param qLow  low boundary of query id
     * @param qHigh high boundary of query id
     * @return the highest score in a given range
     */
    public int rangeMaxQuery(int qLow, int qHigh) {
        return rangeMaxQuery(root, qLow, qHigh);
    }

    private int rangeMaxQuery(Node node, int qLow, int qHigh) {
        // total overlap
        if (qLow <= ((SegmentTreeNode) node).low && qHigh >= ((SegmentTreeNode) node).high) {
            return ((SegmentTreeNode) node).maxScore;
        }
        // no overlap
        if (qLow > ((SegmentTreeNode) node).high || qHigh < ((SegmentTreeNode) node).low) {
            return Integer.MIN_VALUE;
        }
        // partial overlap
        return Math.max(rangeMaxQuery(node.left, qLow, qHigh), rangeMaxQuery(node.right, qLow, qHigh));
    }

    /**
     * Update the score of the player in the segment tree
     *
     * @param id    the updated player's id
     * @param score the updated score
     */
    public void update(int id, int score) {
        updateSegmentTree(root, id, score);
    }

    private void updateSegmentTree(Node node, int id, int score) {
        if (id < ((SegmentTreeNode) node).low || id > ((SegmentTreeNode) node).high) {
            return;
        }
        if (((SegmentTreeNode) node).low == ((SegmentTreeNode) node).high) {
            ((SegmentTreeNode) node).maxScore = Math.max(((SegmentTreeNode) node).maxScore, score);
            return;
        }
        updateSegmentTree(node.left, id, score);
        updateSegmentTree(node.right, id, score);
        ((SegmentTreeNode) node).maxScore = Math.max(((SegmentTreeNode) node.left).maxScore,
                ((SegmentTreeNode) node.right).maxScore);
    }
}