import java.util.ArrayList;
import java.util.List;

public class BSTbyID<T> {
    Node root;

    /**
     * Insert a player into BST
     * @param player the inserted player
     */
    public void insert(Player player) {
        root = insertRec(root, player);
    }

    private Node insertRec(Node node, Player player) {
        if (node == null) {
            node = new BSTbyIDNode(player, null, null);
            return node;
        }
        int comp = player.id - node.player.id;
        if (comp == 0) {
            if (player.score > node.player.score) {
                node.player.score = player.score;
            }
        } else if (comp < 0) {
            node.left = insertRec(node.left, player);
        } else if (comp > 0) {
            node.right = insertRec(node.right, player);
        }
        return node;
    }

    /**
     * Find the player's score
     * @param id the player's id
     * @return the score of the player
     */
    public Player search(int id) {
        return searchRec(root, id);
    }

    private Player searchRec(Node node, int id) {
        if (node == null) return null;
        if (node.player.id == id) {
            return node.player;
        } else if (node.player.id < id) {
            return searchRec(node.right, id);
        } else {
            return searchRec(node.left, id);
        }
    }

    /**
     * Update the player's score
     * @param id updated player's id
     * @param score updated score
     * @return true if the updated score is higher than the original one, otherwise return false
     */
    public boolean update(int id, int score) {
        return updateRec(root, id, score);
    }

    private boolean updateRec(Node node, int id, int score) {
        if (node == null) return false;
        if (node.player.id == id) {
            if (node.player.score >= score) {
                return false;
            } else {
                node.player.score = score;
                return true;
            }
        } else if (node.player.id < id) {
            return updateRec(node.right, id, score);
        } else {
            return updateRec(node.left, id, score);
        }
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
        sb.append("ID: " + node.player.id + " Score: " + node.player.score + "\n");
        inOrderRec(node.right, sb);
    }

    /**
     * Get the array representation of this tree
     * @return the array representation of this tree
     */
    public Player[] toArray() {
        List<Player> list = new ArrayList<>();
        helper(list, root);
        Player[] res = new Player[list.size()];
        for (int i = 0; i < list.size(); i++) {
            res[i] = list.get(i);
        }
        return res;
    }

    private void helper(List<Player> list, Node node) {
        if (node == null) return;
        helper(list, node.left);
        list.add(node.player);
        helper(list, node.right);
    }
}