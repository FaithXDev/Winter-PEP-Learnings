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
