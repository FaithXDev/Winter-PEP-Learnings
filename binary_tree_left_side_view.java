import java.util.ArrayList;

public class binary_tree_left_side_view {

    // Definition for a binary tree node.
    static class Node {
        int data;
        Node left, right;

        Node(int item) {
            data = item;
            left = right = null;
        }
    }

    /*
     * Function to return list containing elements of left view of binary tree.
     * 
     * Time Complexity: O(N), where N is the number of nodes in the binary tree.
     * Space Complexity: O(H), where H is the height of the tree (for recursion
     * stack).
     */
    ArrayList<Integer> leftView(Node root) {
        ArrayList<Integer> result = new ArrayList<>();
        solve(root, 0, result);
        return result;
    }

    // Helper function for recursive traversal (DFS)
    // We pass the current level and the result list
    private void solve(Node root, int level, ArrayList<Integer> res) {
        // Base case: if node is null, return
        if (root == null)
            return;

        // If the size of the result list is equal to the current level,
        // it means we are visiting this level for the first time.
        // Since we are doing a Preorder traversal (Root -> Left -> Right),
        // the first node encountered at each level will be the leftmost one.
        if (level == res.size()) {
            res.add(root.data);
        }

        // Recur for left and right subtrees
        solve(root.left, level + 1, res);
        solve(root.right, level + 1, res);
    }

    public static void main(String[] args) {
        binary_tree_left_side_view tree = new binary_tree_left_side_view();

        // Test Case 1
        // Constructing the following tree:
        // 1
        // / \
        // 2 3
        // /
        // 4
        // \
        // 5
        // Expected Left View: 1, 2, 4, 5
        Node root = new Node(1);
        root.left = new Node(2);
        root.right = new Node(3);
        root.right.left = new Node(4);
        root.right.left.right = new Node(5);

        System.out.println("Test Case 1:");
        ArrayList<Integer> result = tree.leftView(root);
        System.out.println("Left view of the binary tree is: " + result);
        // Output should be [1, 2, 4, 5]

        // Test Case 2
        // Constructing the following tree:
        // 10
        // / \
        // 20 30
        // / \
        // 40 60
        // Expected Left View: 10, 20, 40
        Node root2 = new Node(10);
        root2.left = new Node(20);
        root2.right = new Node(30);
        root2.left.left = new Node(40);
        root2.left.right = new Node(60);

        System.out.println("\nTest Case 2:");
        ArrayList<Integer> result2 = tree.leftView(root2);
        System.out.println("Left view of the binary tree is: " + result2);
        // Output should be [10, 20, 40]

        System.out.println("\n✅ All test cases completed!");
    }
}

/*
 * Approach:
 * 1. The problem asks for the "Left View" of a Binary Tree. This means we need
 * to find the
 * first node visible at each level when looking from the left side.
 * 2. This is equivalent to finding the first node encountered at every depth
 * level of the tree.
 * 3. We can use a Depth First Search (DFS) traversal, specifically a Preorder
 * Traversal
 * (Root -> Left -> Right).
 * 
 * Algorithm:
 * - Maintain a variable `level` to track the current depth of the recursion.
 * - Maintain a list `result` to store the left view nodes.
 * - Start the traversal from the root at level 0.
 * - For each node:
 * - Check if the current level has been visited before. We do this by comparing
 * the
 * `level` with the size of the `result` list.
 * - If `level == result.size()`, it means this is the first time we are
 * visiting this
 * depth level (because we traverse left child before right child). Thus, we add
 * the
 * current node to the `result` list.
 * - Recursively visit the left child with `level + 1`.
 * - Recursively visit the right child with `level + 1`.
 * 
 * Key Logic:
 * - By visiting the left child before the right child, we ensure that for any
 * level,
 * the leftmost node is processed first and added to the list. Any subsequent
 * nodes
 * at the same level (from the right subtree or right siblings) will be ignored
 * because `level` will be less than `result.size()`.
 * 
 * Complexity Analysis:
 * - Time Complexity: O(N)
 * - We visit each node once.
 * - Space Complexity: O(H)
 * - This is due to the recursion stack. In the worst case (skewed tree), H = N.
 * - In the best case (balanced tree), H = log N.
 * - The auxiliary space for the result list is O(H) as it stores one node per
 * level.
 */
