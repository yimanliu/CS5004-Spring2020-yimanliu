import java.util.ArrayList;
import java.util.List;

/**
 * @ By Yiman Liu
 * @ CS5004 Assignment#5
 */

public class LeaderBoardImpl implements LeaderBoard{
    private BSTbyID<Player> bstID;
    private SegmentTree<Player> st;
    private BSTbyScore<Player> bstScore;

    /** construct LeaderBoardImpl
     * @param list a list of Player objects
     */
    public LeaderBoardImpl(List<Player> list) {
        // construct BST by ID
        bstID = new BSTbyID();
        for (Player player : list) {
            bstID.insert(player);
        }

        // construct segment tree
        st = new SegmentTree<Player>(bstID.toArray());

        // construct BST by Score
        bstScore = new BSTbyScore<>();
        for (Player player : list) {
            bstScore.insert(player);
        }
    }

    /**
     * Get the player’s score
     * @param id player's id
     * @return the player’s score
     */
    public int scoreQuery(int id) {
        return bstID.search(id).score;
    }

    public boolean update(int id, int score) {
        int oldScore = scoreQuery(id);
        if (oldScore >= score) {
            return false;
        }
        // update BSTbyScore
        Player player = bstID.search(id);
        bstScore.update(player, score);
        // update SegmentTree
        st.update(id, score);
        // update BSTbyID
        bstID.update(id, score);
        return true;
    }

    /**
     * Get the highest score in a given range
     * @param qLow low boundary of query id
     * @param qHigh high boundary of query id
     * @return the highest score in a given range
     */
    public int rangeHighestQuery(int qLow, int qHigh) {
        return st.rangeMaxQuery(qLow, qHigh);
    }

    /**
     * Get the top 1 ranked player
     * @return the top 1 ranked player
     */
    public int getTopOne() {
        return bstScore.findMax();
    }

    /**
     * Get the the player id who ranks at the given position
     * @param pos rank position
     * @return the player id who ranks at the given position
     */
    public int playerIdAtPosition(int pos) {
        return bstScore.findKth(pos);
    }

    /**
     * Print the board sorted by player's id
     * @return the board information (ordered by ID)
     */
    private String printById() {
        return bstID.inOrder();
    }

    /**
     * Print the board sorted by player's score
     * @return the board information (ordered by score)
     */
    private String printByScore() {
        return bstScore.inOrder();
    }

    public static void main(String[] args) {
        /* self test
        Player p1 = new Player(45, 82);
        Player p2 = new Player(127, 86);
        Player p3 = new Player(21, 77);
        Player p4 = new Player(1002, 84);
        Player p5 = new Player(10, 56);
        Player p6 = new Player(98, 57);

        List<Player> playerInfo = new ArrayList<>();
        playerInfo.add(p1);
        playerInfo.add(p2);
        playerInfo.add(p3);
        playerInfo.add(p4);
        playerInfo.add(p5);
        playerInfo.add(p6);

        LeaderBoardImpl board = new LeaderBoardImpl(playerInfo);
        System.out.println("Initial board: ");
        System.out.println("==================");
        System.out.println(board.printById());
        System.out.println();

        System.out.println("Get player 1002 score: ");
        System.out.println(board.scoreQuery(1002));
        System.out.println();
        System.out.println("Highest score between player 20 - 200: ");
        System.out.println(board.rangeHighestQuery(20,200));
        System.out.println();

        System.out.println("Print board by Score: ");
        System.out.println("======================");
        System.out.println(board.printByScore());
        System.out.println("top 1: " + board.getTopOne());

        System.out.println();
        System.out.println("top1: " + board.playerIdAtPosition(1));
        System.out.println("top2: " + board.playerIdAtPosition(2));
        System.out.println("top3: " + board.playerIdAtPosition(3));
        System.out.println("top4: " + board.playerIdAtPosition(4));
        System.out.println("top5: " + board.playerIdAtPosition(5));
        System.out.println("top6: " + board.playerIdAtPosition(6));
        System.out.println();

        board.update(21, 98);
        System.out.println("Update id - 21 player score to 98: ");
        System.out.println("===================================");
        System.out.println(board.printById());
        System.out.println(board.printByScore());
        System.out.println("ID-21 player's score: ");
        System.out.println(board.scoreQuery(21));
        System.out.println();
        System.out.println("Highest score between player 20 - 200: ");
        System.out.println(board.rangeHighestQuery(20,200));
        System.out.println();
        System.out.println("top 1: " + board.getTopOne());

        System.out.println();
        System.out.println("top1: " + board.playerIdAtPosition(1));
        System.out.println("top2: " + board.playerIdAtPosition(2));
        System.out.println("top3: " + board.playerIdAtPosition(3));
        System.out.println("top4: " + board.playerIdAtPosition(4));
        System.out.println("top5: " + board.playerIdAtPosition(5));
        System.out.println("top6: " + board.playerIdAtPosition(6));
        System.out.println();
        */

        Player p1 = new Player(45, 82); // 1st arg: playerId, 2nd arg: score
        Player p2 = new Player(127, 86);
        Player p3 = new Player(21, 77);
        Player p4 = new Player(1002, 84);
        Player p5 = new Player(10, 56);
        Player p6 = new Player(98, 57);

        List<Player> playerInfo = new ArrayList<>();
        playerInfo.add(p1);
        playerInfo.add(p2);
        playerInfo.add(p3);
        playerInfo.add(p4);
        playerInfo.add(p5);
        playerInfo.add(p6);

        LeaderBoard board = new LeaderBoardImpl(playerInfo);
        System.out.println(board.scoreQuery(1002));
        System.out.println(board.rangeHighestQuery(20,200));
        System.out.println(board.getTopOne());

        System.out.println();
        System.out.println(board.playerIdAtPosition(1));
        System.out.println(board.playerIdAtPosition(2));
        System.out.println(board.playerIdAtPosition(3));
        System.out.println(board.playerIdAtPosition(4));
        System.out.println(board.playerIdAtPosition(5));
        System.out.println(board.playerIdAtPosition(6));
        System.out.println();

        board.update(21, 98);
        System.out.println(board.scoreQuery(21));
        System.out.println(board.rangeHighestQuery(20,200));
        System.out.println(board.getTopOne());

        System.out.println();
        System.out.println(board.playerIdAtPosition(1));
        System.out.println(board.playerIdAtPosition(2));
        System.out.println(board.playerIdAtPosition(3));
        System.out.println(board.playerIdAtPosition(4));
        System.out.println(board.playerIdAtPosition(5));
        System.out.println(board.playerIdAtPosition(6));
        System.out.println();

    }
}
