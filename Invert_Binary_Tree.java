
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
     * Inverts a binary tree.
     * 
     * Time Complexity: O(n) - We visit every node exactly once.
     * Space Complexity: O(h) - Where h is the height of the tree (recursion stack).
     * O(n) for skewed, O(log n) for balanced.
     */
    public TreeNode invertTree(TreeNode root) {
        if (root == null) {
            return null;
        }

        // Recursively invert the left and right subtrees
        TreeNode left = invertTree(root.left);
        TreeNode right = invertTree(root.right);

        // Swap the left and right children
        root.left = right;
        root.right = left;

        return root;
    }
}


/*
 * Approach:
 * 1. The problem asks us to invert a binary tree, meaning ensuring that for
 * every node, its left and right children are swapped.
 * 
 * Algorithm:
 * 1. We use a recursive Depth First Search (DFS) approach.
 * 2. Base Case: If the `root` is null, there is nothing to invert, so return
 * null.
 * 3. Recursive Step:
 * - Recursively invert the left subtree: `invertTree(root.left)`
 * - Recursively invert the right subtree: `invertTree(root.right)`
 * - Swap the left and right children of the current `root`.
 * 4. Return the `root` after the swap.
 * 
 * Complexity Analysis:
 * - Time Complexity: O(N)
 * - We visit each node exactly once to perform the swap.
 * - Space Complexity: O(H)
 * - H is the height of the tree, used for the recursion stack.
 * - O(N) in the worst case (skewed tree), O(log N) in the best case (balanced
 * tree).
 */
