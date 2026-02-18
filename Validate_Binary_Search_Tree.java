
public class Validate_Binary_Search_Tree {

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

    public boolean isValidBST(TreeNode root) {
        // Use Long to handle Integer.MIN_VALUE and Integer.MAX_VALUE edge cases
        return isValid(root, Long.MIN_VALUE, Long.MAX_VALUE);
    }

    /**
     * Helper function to validate the BST property.
     * Use long to allow checking against integer boundary values.
     */
    private boolean isValid(TreeNode node, long min, long max) {
        if (node == null) {
            return true;
        }

        // The current node's value must be strictly within the range (min, max)
        if (node.val <= min || node.val >= max) {
            return false;
        }

        // Recursively check the left and right subtrees
        // Left child must be smaller than the current node, so max becomes node.val
        // Right child must be larger than the current node, so min becomes node.val
        return isValid(node.left, min, node.val) && isValid(node.right, node.val, max);
    }

}

/**
 * Approach:
 * 1. Recursive Range Validation:
 * - A valid BST requires that every node's value must be strictly greater than
 * all values in its left subtree
 * and strictly less than all values in its right subtree.
 * - This means each node must satisfy a specific range (min, max).
 * - The root node's range is (-infinity, +infinity).
 * - For a node with value V:
 * - Its left child must be in the range (min, V).
 * - Its right child must be in the range (V, max).
 * 2. Handling Integer Limits:
 * - Since node values can be Integer.MIN_VALUE or Integer.MAX_VALUE, we use
 * `long` for the range boundaries
 * to correctly handle these edge cases without overflow or ambiguity.
 * 3. Algorithm:
 * - Helper function `isValid(node, min, max)` returns true if the subtree
 * rooted at `node` is a valid BST within (min, max).
 * - Base case: If node is null, return true.
 * - If node.val <= min or node.val >= max, return false.
 * - Recursive step: Check `isValid(node.left, min, node.val)` AND
 * `isValid(node.right, node.val, max)`.
 *
 * Complexity Analysis:
 * - Time Complexity: O(N), where N is the number of nodes in the tree. We visit
 * each node once.
 * - Space Complexity: O(H), where H is the height of the tree, corresponding to
 * the recursion stack depth.
 * In the worst case (skewed tree), O(N). In the best case (balanced tree),
 * O(log N).
 */
