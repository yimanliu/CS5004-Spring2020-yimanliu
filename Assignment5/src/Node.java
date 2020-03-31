public abstract class Node {
    Player player;
    Node left, right;

    public Node (Player player, Node left, Node right) {
        this.left = left;
        this.right = right;
        this.player = player;
    }
}
