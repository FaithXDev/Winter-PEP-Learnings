public class Balanced_binary_tree {

    // Definition for a binary tree node.
    static class TreeNode {
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

    static class Solution {
        /**
         * Determines if the binary tree is height-balanced.
         * 
         * Time Complexity: O(n) - We visit every node once (Bottom-up approach).
         * Space Complexity: O(h) - Where h is the height of the tree (recursion stack).
         */
        public boolean isBalanced(TreeNode root) {
            return checkHeight(root) != -1;
        }

        /**
         * Helper function to calculate height and check balance simultaneously.
         * Returns the height of the tree if balanced, or -1 if unbalanced.
         */
        private int checkHeight(TreeNode node) {
            if (node == null) {
                return 0; // Height of null node is 0
            }

            // Recursively check left subtree
            int leftHeight = checkHeight(node.left);
            if (leftHeight == -1) {
                return -1; // Left subtree is unbalanced
            }

            // Recursively check right subtree
            int rightHeight = checkHeight(node.right);
            if (rightHeight == -1) {
                return -1; // Right subtree is unbalanced
            }

            // Check current node balance
            if (Math.abs(leftHeight - rightHeight) > 1) {
                return -1; // Current node is unbalanced
            }

            // Return height of current node
            return Math.max(leftHeight, rightHeight) + 1;
        }
    }


}

/*
 * Approach:
 * 1. The problem asks if a binary tree is height-balanced. A tree is balanced
 * if, for every node, the height difference between its left and right subtrees
 * is at most 1.
 * 
 * Algorithm:
 * 1. A naive approach would be to calculate the height of the left and right
 * subtrees for every node, taking O(N^2) time.
 * 2. An optimized approach is to use a Bottom-Up DFS (Post-order traversal).
 * - Instead of just returning height, our helper function returns:
 * - The actual height if the subtree is balanced.
 * - `-1` if the subtree is unbalanced.
 * 3. Steps in `checkHeight(node)`:
 * - Base Case: If node is null, return 0 (height is 0).
 * - Left Subtree: Recursively call `checkHeight(node.left)`. If it returns -1,
 * propagate -1 immediately (sub-tree is unbalanced).
 * - Right Subtree: Recursively call `checkHeight(node.right)`. If it returns
 * -1, propagate -1 immediately.
 * - Current Node Check: If `Math.abs(leftHeight - rightHeight) > 1`, return -1
 * (current node is unbalanced).
 * - Return Height: If balanced, return `max(leftHeight, rightHeight) + 1`.
 * 4. Finally, `isBalanced` returns true if `checkHeight(root) != -1`.
 * 
 * Complexity Analysis:
 * - Time Complexity: O(N)
 * - We visit every node exactly once because we calculate height and check
 * balance in the same traversal.
 * - Space Complexity: O(H)
 * - H is the height of the tree, used for the recursion stack.
 */
