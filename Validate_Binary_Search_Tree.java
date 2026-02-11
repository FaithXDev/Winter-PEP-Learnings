
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

    public static void main(String[] args) {
        Validate_Binary_Search_Tree solution = new Validate_Binary_Search_Tree();

        // Test Case 1: Valid BST
        // Input: [2,1,3]
        // Tree:
        // 2
        // / \
        // 1 3
        TreeNode root1 = new TreeNode(2);
        root1.left = new TreeNode(1);
        root1.right = new TreeNode(3);
        System.out.println("Test Case 1 (Expected: true): " + solution.isValidBST(root1));

        // Test Case 2: Invalid BST
        // Input: [5,1,4,null,null,3,6]
        // Tree:
        // 5
        // / \
        // 1 4
        // / \
        // 3 6
        TreeNode root2 = new TreeNode(5);
        root2.left = new TreeNode(1);
        root2.right = new TreeNode(4);
        root2.right.left = new TreeNode(3);
        root2.right.right = new TreeNode(6);
        System.out.println("Test Case 2 (Expected: false): " + solution.isValidBST(root2));

        // Test Case 3: Edge case with Integer limits
        // This test ensures that using Long handles integer boundary values correctly.
        TreeNode root3 = new TreeNode(Integer.MAX_VALUE);
        System.out.println("Test Case 3 (Expected: true): " + solution.isValidBST(root3));
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