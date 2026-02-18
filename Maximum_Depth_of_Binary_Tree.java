
class TreeNode {
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

class Solution {
    /**
     * Calculates the maximum depth of a binary tree.
     * 
     * Time Complexity: O(n) - We visit every node exactly once.
     * Space Complexity: O(h) - Where h is the height of the tree (recursion stack).
     * In worst case (skewed tree) O(n), best case (balanced) O(log n).
     */
    public int maxDepth(TreeNode root) {
        if (root == null) {
            return 0; // Base case: depth of empty tree is 0
        }

        // Recursively find the depth of left and right subtrees
        int leftDepth = maxDepth(root.left);
        int rightDepth = maxDepth(root.right);

        // The depth of the current node is 1 + maximum of left and right depths
        return 1 + Math.max(leftDepth, rightDepth);
    }
}


/*
 * Approach:
 * 1. The problem asks for the maximum depth (height) of a binary tree.
 * 
 * Algorithm:
 * 1. We use a recursive Depth First Search (DFS) approach.
 * 2. Base Case: If the `root` is null, the depth is 0.
 * 3. Recursive Step:
 * - Recursively find the maximum depth of the left subtree:
 * `maxDepth(root.left)`
 * - Recursively find the maximum depth of the right subtree:
 * `maxDepth(root.right)`
 * 4. The depth of the current node is 1 (for the node itself) + the maximum of
 * the left and right subtree depths.
 * - Result: `1 + Math.max(leftDepth, rightDepth)`
 * 
 * Complexity Analysis:
 * - Time Complexity: O(N)
 * - We visit every node exactly once to calculate its contribution to the
 * depth.
 * - Space Complexity: O(H)
 * - H is the height of the tree, used for the recursion stack.
 * - O(N) for a skewed tree, O(log N) for a balanced tree.
 */
