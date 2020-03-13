import java.util.Objects;

/**
 * @By Yiman Liu
 * @Lab 7
 */

public class BST<T extends Comparable<T>> {
    TreeNode root;

    /**
     * Construct a BST
     */
    BST() { this.root = null; }

    /**
     * Insert a value into BST
     * @param val the value inserted to BST
     */
    public void insert(T val) { root = insertRec(root, val); }

    private TreeNode insertRec(TreeNode root, T val) {
        if (root == null) {
            root = new TreeNode(val);
            return root;
        }
        int comp = val.compareTo(root.val);
        if (comp < 0) {
            root.left = insertRec(root.left, val);
        } else if (comp > 0) {
            root.right = insertRec(root.right, val);
        }
        return root;
    }

    /**
     * In-order traversal this BST
     * @param root root of the BST
     */
    public void printInOrderRec(TreeNode root) {
        if (root == null) return;
        printInOrderRec(root.left);
        System.out.print(root.val + " ");
        printInOrderRec(root.right);
    }

    /**
     * Convert the array to a BST
     * @param arr input array
     */
    public void bTreeIns(T[] arr) {
        for (T value : arr) {
            insert(value);
        }
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        toString(root, sb);
        return sb.toString();
    }

    private void toString(TreeNode root, StringBuilder sb) {
        if (root == null) return;
        toString(root.left, sb);
        sb.append(root.val + " ");
        toString(root.right, sb);
    }

    class TreeNode {
        T val;
        TreeNode left, right;

        public TreeNode(T item) {
            this.val = item;
        }
    }

    // main method to test on 1-2 examples
    public static void main(String[] args) {
        System.out.println("===============================");
        System.out.println("Testing insert Integer into BST");
        System.out.println("===============================");
        BST tree = new BST<Integer>();
        Integer[] arr1 = new Integer[]{5, 6, 3, 12, 4, 0, 7, 9};
        System.out.print("Original array: ");
        for (Integer num : arr1) {
            System.out.print(num + " ");
        }
        System.out.println();
        tree.bTreeIns(arr1);
        System.out.print("Resulting array: ");
        tree.printInOrderRec(tree.root);

        System.out.println();
        System.out.println();

        BST tree2 = new BST<Integer>();
        Integer[] arr2 = new Integer[]{-4, 3, 43, 2, 0, -5, 7, 9, -23, 54};
        System.out.print("Original array: ");
        for (Integer num : arr2) {
            System.out.print(num + " ");
        }
        System.out.println();
        tree2.bTreeIns(arr2);
        System.out.print("Resulting array: ");
        tree.printInOrderRec(tree2.root);

        System.out.println();
        System.out.println();

        System.out.println("==============================");
        System.out.println("Testing insert String into BST");
        System.out.println("==============================");
        BST tree3 = new BST<String>();
        String[] words = new String[]{"this", "is", "a", "example", "test"};
        System.out.print("Original array: ");
        for (String s : words) {
            System.out.print(s + " ");
        }
        System.out.println();
        tree3.bTreeIns(words);
        System.out.print("Resulting array: ");
        tree3.printInOrderRec(tree3.root);

        System.out.println();
        System.out.println();

        BST tree4 = new BST<String>();
        String[] arr = new String[]{"adf", "vgrgg", "zasf", "drtg", "tgggf", "lijm"};
        System.out.print("Original array: ");
        for (String s : arr) {
            System.out.print(s + " ");
        }
        System.out.println();
        tree4.bTreeIns(arr);
        System.out.print("Resulting array: ");
        tree4.printInOrderRec(tree4.root);

        System.out.println();
    }
}
