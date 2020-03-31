public class BSTbyScore<T> {
    Node root;

    /**
     * Insert a player into BST
     * @param player the inserted player
     */
    public void insert(Player player) {
        root = insertRec(root, player, 0);
    }

    private Node insertRec(Node node, Player player, int count) {
        if (node == null) {
            node = new BSTbyScoreNode(player, null, null);
            return node;
        }
        int comp = player.score - node.player.score;
        if (comp < 0) {
            node.right = insertRec(node.right, player, ((BSTbyScoreNode)node).leftCount + 1);
        } else if (comp > 0) {
            ((BSTbyScoreNode)node).leftCount++;
            node.left = insertRec(node.left, player, count);
        }
        return node;
    }

    /**
     * Find the maximum score
     * @return the max score
     */
    public int findMax() {
        Player[] max = new Player[]{new Player(-1, -1)};
        findMax(root, max);
        return max[0].id;
    }

    private void findMax(Node node, Player[] max) {
        if (node == null) return;
        findMax(node.left, max);
        if (node.player.score > max[0].score) {
            max[0] = node.player;
        }
        findMax(node.right, max);
    }

    /**
     * Update the player's score
     * @param player the player whose score needs to be updated
     */
    public void update(Player player, int score) {
        // 1. delete the score associated with this id
        delete(player);
        // 2. insert new score
        insert(new Player(player.id, score));
    }

    private void delete(Player player) {
        root = deleteRec(root, player);
    }

    private Node deleteRec(Node node, Player player) {
        if (node == null) return null;
        int comp = player.score - node.player.score;
        if (comp > 0) {
            ((BSTbyScoreNode)node).leftCount--;
            node.left = deleteRec(node.left, player);
        } else if (comp < 0) {
            node.right = deleteRec(node.right, player);
        } else {
            if (node.right == null) {
                return node.left;
            }
            if (node.left == null) {
                return node.right;
            }
            Node x = node;
            node = successor(x.right);
            node.right = unlink(x.right);
            node.left = x.left;
        }
        return node;
    }

    private Node successor(Node node) {
        while (node.left != null) {
            node = node.left;
        }
        return node;
    }

    private Node unlink(Node node) {
        Node curr = node;
        while (curr.left != null) {
            curr = curr.left;
        }
        curr = null;
        return node;
    }

    /**
     * In-order traversal the tree
     * @return a string representation of the tree
     */
    public String inOrder() {
        StringBuilder sb = new StringBuilder();
        inOrderRec(root, sb);
        return sb.toString();
    }

    private void inOrderRec(Node node, StringBuilder sb) {
        if (node == null) return;
        inOrderRec(node.left, sb);
        sb.append("Score: " + node.player.score + " ID: " + node.player.id + "\n");
        inOrderRec(node.right, sb);
    }

    public int findKth(int k) {
        return findKth(root, k);
    }

    private int findKth(Node root, int k) {
        if (root == null) return 0;
        int leftCount = ((BSTbyScoreNode)root).leftCount;

        if (k == leftCount + 1) {
            return root.player.id;
        } else if (k <= leftCount) {
            return findKth(root.left, k);
        } else {
            return findKth(root.right, k - leftCount - 1);
        }
    }
}