
/**
 * LeetCode 145: Binary Tree Postorder Traversal
 * 
 * Problem Description:
 * Given the root of a binary tree, return the postorder traversal of its nodes' values.
 * Postorder traversal visits nodes in the order: Left Subtree -> Right Subtree -> Root.
 * 
 * Example 1:
 * Input: root = [1,null,2,3]
 * Output: [3,2,1]
 * 
 * Example 2:
 * Input: root = []
 * Output: []
 * 
 * Example 3:
 * Input: root = [1]
 * Output: [1]
 * 
 * Constraints:
 * - The number of the nodes in the tree is in the range [0, 100].
 * - -100 <= Node.val <= 100
 */

import java.util.ArrayList;
import java.util.List;
import java.util.Stack;

// Definition for a binary tree node.
class TreeNode_145 {
    int val;
    TreeNode_145 left;
    TreeNode_145 right;

    TreeNode_145() {
    }

    TreeNode_145(int val) {
        this.val = val;
    }

    TreeNode_145(int val, TreeNode_145 left, TreeNode_145 right) {
        this.val = val;
        this.left = left;
        this.right = right;
    }
}

class Solution_145 {
    public List<Integer> postorderTraversal(TreeNode_145 root) {
        List<Integer> result = new ArrayList<>();
        helper(root, result);
        return result;
    }

    private void helper(TreeNode_145 node, List<Integer> result) {
        if (node == null) {
            return;
        }

        // Traverse Left Subtree
        helper(node.left, result);

        // Traverse Right Subtree
        helper(node.right, result);

        // Visit Root
        result.add(node.val);
    }

    // Iterative Approach using 2 Stacks (for reference)
    public List<Integer> postorderTraversalIterative(TreeNode_145 root) {
        List<Integer> result = new ArrayList<>();
        if (root == null)
            return result;

        Stack<TreeNode_145> stack1 = new Stack<>();
        Stack<TreeNode_145> stack2 = new Stack<>();

        stack1.push(root);

        while (!stack1.isEmpty()) {
            TreeNode_145 node = stack1.pop();
            stack2.push(node);

            if (node.left != null) {
                stack1.push(node.left);
            }
            if (node.right != null) {
                stack1.push(node.right);
            }
        }

        while (!stack2.isEmpty()) {
            result.add(stack2.pop().val);
        }

        return result;
    }
}

public class Binary_Tree_Postorder_Traversal {

    /**
     * Helper method to create a binary tree from an array (Level Order).
     * Uses Integer array to support null values for missing nodes.
     */
    public static TreeNode_145 createTree(Integer[] arr) {
        if (arr == null || arr.length == 0 || arr[0] == null) {
            return null;
        }

        TreeNode_145 root = new TreeNode_145(arr[0]);
        java.util.Queue<TreeNode_145> queue = new java.util.LinkedList<>();
        queue.add(root);

        int i = 1;
        while (i < arr.length) {
            TreeNode_145 current = queue.poll();

            // Left child
            if (i < arr.length && arr[i] != null) {
                current.left = new TreeNode_145(arr[i]);
                queue.add(current.left);
            }
            i++;

            // Right child
            if (i < arr.length && arr[i] != null) {
                current.right = new TreeNode_145(arr[i]);
                queue.add(current.right);
            }
            i++;
        }

        return root;
    }

    public static void main(String[] args) {
        Solution_145 solution = new Solution_145();

        // Test Case 1: [1,null,2,3]
        System.out.println("Test Case 1:");
        Integer[] input1 = { 1, null, 2, 3 };
        TreeNode_145 root1 = createTree(input1);
        System.out.println("Input: root = [1, null, 2, 3]");
        List<Integer> result1 = solution.postorderTraversal(root1);
        System.out.println("Output: " + result1);
        System.out.println("Expected: [3, 2, 1]");
        System.out.println();

        // Test Case 2: []
        System.out.println("Test Case 2:");
        Integer[] input2 = {};
        TreeNode_145 root2 = createTree(input2);
        System.out.println("Input: root = []");
        List<Integer> result2 = solution.postorderTraversal(root2);
        System.out.println("Output: " + result2);
        System.out.println("Expected: []");
        System.out.println();

        // Test Case 3: [1]
        System.out.println("Test Case 3:");
        Integer[] input3 = { 1 };
        TreeNode_145 root3 = createTree(input3);
        System.out.println("Input: root = [1]");
        List<Integer> result3 = solution.postorderTraversal(root3);
        System.out.println("Output: " + result3);
        System.out.println("Expected: [1]");
        System.out.println();

        System.out.println("\u2705 All test cases completed!");
    }
}

/**
 * Approach:
 * Recursive Depth-First Search (DFS)
 * 
 * 1. Postorder traversal follows the pattern: Left -> Right -> Root.
 * 2. We define a helper function that takes the current node and the result
 * list.
 * 3. Base case: If the node is null, return.
 * 4. Recursive step:
 * - Recursively call the helper for the left child.
 * - Recursively call the helper for the right child.
 * - Add the current node's value to the result list.
 * 
 * Time Complexity: O(n)
 * - We find visit every node exactly once.
 * 
 * Space Complexity: O(n)
 * - In the worst case (skewed tree), the recursion stack can go up to O(n).
 * - In the average case (balanced tree), it is O(log n).
 * - storing the result takes O(n) space.
 */
