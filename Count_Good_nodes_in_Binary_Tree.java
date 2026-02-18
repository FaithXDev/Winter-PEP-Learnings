public class Count_Good_nodes_in_Binary_Tree {

    // Definition for a binary tree node.
    public static class TreeNode {
        int val;
        TreeNode left;
        TreeNode right;

        TreeNode() {
        }

        TreeNode(int val) {
            this.val = val;
        }

        TreeNode(int val, TreeNode left, TreeNode right) {
            this.val = val;
            this.left = left;
            this.right = right;
        }
    }

    /**
     * Counts the number of "good" nodes in the binary tree.
     * A node is good if in the path from root to the node there are no nodes with a
     * value greater than X.
     *
     * Time Complexity: O(N), where N is the number of nodes in the tree. We visit
     * each node once.
     * Space Complexity: O(H), where H is the height of the tree. This is for the
     * recursion stack.
     */
    public int goodNodes(TreeNode root) {
        // Start DFS traversal from the root.
        // The initial max value encountered is the root's value itself (or
        // Integer.MIN_VALUE).
        return dfs(root, Integer.MIN_VALUE);
    }

    private int dfs(TreeNode node, int maxVal) {
        if (node == null) {
            return 0;
        }

        int count = 0;

        // If the current node's value is greater than or equal to the max value
        // encountered so far on the path from the root, it is a "good" node.
        if (node.val >= maxVal) {
            count = 1;
            maxVal = node.val; // Update the max value for the child nodes
        }

        // Recurse for left and right children
        count += dfs(node.left, maxVal);
        count += dfs(node.right, maxVal);

        return count;
    }

}

/*
 * ==================== APPROACH ====================
 *
 * Problem: LeetCode 1448 - Count Good Nodes in Binary Tree
 * ---------------------------------------------------------
 * A node X in the tree is "good" if in the path from root to X,
 * there are NO nodes with a value GREATER than X.
 * Return the number of good nodes in the binary tree.
 *
 * Approach: DFS (Pre-order Traversal) with Max Tracking
 *
 * Intuition:
 * - As we traverse from the root to any node, we need to know the
 * maximum value seen so far along that path.
 * - If the current node's value >= max value on the path, it is a "good" node.
 * - We pass this max value down to child nodes during DFS.
 *
 * Algorithm:
 * 1. Start DFS from the root with maxVal = Integer.MIN_VALUE.
 * 2. At each node:
 * - If node.val >= maxVal → it's a good node, count = 1.
 * - Update maxVal = max(maxVal, node.val) for children.
 * 3. Recurse on left and right subtrees, passing the updated maxVal.
 * 4. Return count (current) + count (left) + count (right).
 *
 * Dry Run:
 * 3
 * / \
 * 1 4
 * / / \
 * 3 1 5
 *
 * - Node 3 (root): maxVal = MIN → 3 >= MIN → good ✓, maxVal = 3
 * - Node 1: maxVal = 3 → 1 < 3 → not good ✗
 * - Node 3 (leaf): maxVal = 3 → 3 >= 3 → good ✓
 * - Node 4: maxVal = 3 → 4 >= 3 → good ✓, maxVal = 4
 * - Node 1: maxVal = 4 → 1 < 4 → not good ✗
 * - Node 5: maxVal = 4 → 5 >= 4 → good ✓
 * Total good nodes = 4 ✓
 *
 * Time Complexity: O(N) — We visit every node exactly once.
 * Space Complexity: O(H) — Recursion stack depth, where H is the
 * height of the tree. O(log N) for balanced, O(N) for skewed.
 *
 * Key Insight:
 * - By passing maxVal as a parameter, each recursive call maintains
 * its own path context. This avoids any need for backtracking or
 * global state — the value naturally "resets" when the call returns.
 * =================================================
 */
