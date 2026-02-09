/**
 * LeetCode 110: Balanced Binary Tree
 * 
 * Problem Description:
 * Given a binary tree, determine if it is height-balanced.
 * For this problem, a height-balanced binary tree is defined as:
 * a binary tree in which the left and right subtrees of every node differ in
 * height by no more than 1.
 * 
 * Example 1:
 * Input: root = [3,9,20,null,null,15,7]
 * Output: true
 * 
 * Example 2:
 * Input: root = [1,2,2,3,3,null,null,4,4]
 * Output: false
 * 
 * Example 3:
 * Input: root = []
 * Output: true
 * 
 * Constraints:
 * - The number of nodes in the tree is in the range [0, 5000].
 * - -10^4 <= Node.val <= 10^4
 */

// Definition for a binary tree node.
// Ensuring TreeNode is defined here for standalone execution.
// If compiling multiple files, this might clash, so generally run this file
// individually.
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

    /**
     * Helper method to create a binary tree from an array (Level Order).
     * Handles nulls correctly to build the tree structure.
     */
    public static TreeNode createTree(Integer[] arr) {
        if (arr == null || arr.length == 0 || arr[0] == null) {
            return null;
        }

        TreeNode root = new TreeNode(arr[0]);
        java.util.Queue<TreeNode> queue = new java.util.LinkedList<>();
        queue.add(root);

        int i = 1;
        while (i < arr.length) {
            TreeNode current = queue.poll();

            // Left child
            if (i < arr.length) {
                if (arr[i] != null) {
                    current.left = new TreeNode(arr[i]);
                    queue.add(current.left);
                }
                i++;
            }

            // Right child
            if (i < arr.length) {
                if (arr[i] != null) {
                    current.right = new TreeNode(arr[i]);
                    queue.add(current.right);
                }
                i++;
            }
        }

        return root;
    }

    public static void main(String[] args) {
        Solution solution = new Solution();

        // Test Case 1: [3,9,20,null,null,15,7]
        System.out.println("Test Case 1:");
        Integer[] input1 = { 3, 9, 20, null, null, 15, 7 };
        TreeNode root1 = createTree(input1);
        boolean result1 = solution.isBalanced(root1);
        System.out.println("Input: root = [3,9,20,null,null,15,7]");
        System.out.println("Output: " + result1);
        System.out.println("Expected: true");
        System.out.println();

        // Test Case 2: [1,2,2,3,3,null,null,4,4]
        System.out.println("Test Case 2:");
        Integer[] input2 = { 1, 2, 2, 3, 3, null, null, 4, 4 };
        TreeNode root2 = createTree(input2);
        boolean result2 = solution.isBalanced(root2);
        System.out.println("Input: root = [1,2,2,3,3,null,null,4,4]");
        System.out.println("Output: " + result2);
        System.out.println("Expected: false");
        System.out.println();

        // Test Case 3: Empty Tree []
        System.out.println("Test Case 3:");
        Integer[] input3 = {};
        TreeNode root3 = createTree(input3);
        boolean result3 = solution.isBalanced(root3);
        System.out.println("Input: root = []");
        System.out.println("Output: " + result3);
        System.out.println("Expected: true");
        System.out.println();

        // Test Case 4: Single Node [1]
        System.out.println("Test Case 4:");
        Integer[] input4 = { 1 };
        TreeNode root4 = createTree(input4);
        boolean result4 = solution.isBalanced(root4);
        System.out.println("Input: root = [1]");
        System.out.println("Output: " + result4);
        System.out.println("Expected: true");

        System.out.println("\n✅ All test cases completed!");
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
