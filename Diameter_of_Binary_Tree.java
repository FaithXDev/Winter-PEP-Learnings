/**
 * LeetCode 543: Diameter of Binary Tree
 * 
 * Problem Description:
 * Given the root of a binary tree, return the length of the diameter of the
 * tree.
 * The diameter of a binary tree is the length of the longest path between any
 * two nodes in a tree.
 * This path may or may not pass through the root.
 * The length of a path between two nodes is represented by the number of edges
 * between them.
 * 
 * Example 1:
 * Input: root = [1,2,3,4,5]
 * Output: 3
 * Explanation: 3 is the length of the path [4,2,1,3] or [5,2,1,3].
 * 
 * Example 2:
 * Input: root = [1,2]
 * Output: 1
 * 
 * Constraints:
 * - The number of nodes in the tree is in the range [1, 10^4].
 * - -100 <= Node.val <= 100
 */

// Definition for a binary tree node.
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
    private int maxDiameter = 0;

    public int diameterOfBinaryTree(TreeNode root) {
        maxDiameter = 0; // Reset for each call
        calculateHeight(root);
        return maxDiameter;
    }

    /**
     * Helper method to calculate the height of the tree.
     * As a side effect, it updates the maxDiameter variable.
     * 
     * @param node Current node
     * @return Height of the tree rooted at this node
     */
    private int calculateHeight(TreeNode node) {
        if (node == null) {
            return 0;
        }

        int leftHeight = calculateHeight(node.left);
        int rightHeight = calculateHeight(node.right);

        // The diameter passing through this node is the sum of left and right heights
        maxDiameter = Math.max(maxDiameter, leftHeight + rightHeight);

        // Return the height of this node
        return 1 + Math.max(leftHeight, rightHeight);
    }
}

public class Diameter_of_Binary_Tree {

    /**
     * Helper method to create a binary tree from an array (Level Order).
     * Uses Integer array to support null values for missing nodes.
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
            if (i < arr.length && arr[i] != null) {
                current.left = new TreeNode(arr[i]);
                queue.add(current.left);
            }
            i++;

            // Right child
            if (i < arr.length && arr[i] != null) {
                current.right = new TreeNode(arr[i]);
                queue.add(current.right);
            }
            i++;
        }

        return root;
    }

    public static void main(String[] args) {
        Solution solution = new Solution();

        // Test Case 1: [1,2,3,4,5]
        System.out.println("Test Case 1:");
        Integer[] input1 = { 1, 2, 3, 4, 5 };
        TreeNode root1 = createTree(input1);
        System.out.println("Input: root = [1,2,3,4,5]");
        int result1 = solution.diameterOfBinaryTree(root1);
        System.out.println("Output: " + result1);
        System.out.println("Expected: 3");
        System.out.println();

        // Test Case 2: [1,2]
        System.out.println("Test Case 2:");
        Integer[] input2 = { 1, 2 };
        TreeNode root2 = createTree(input2);
        System.out.println("Input: root = [1,2]");
        int result2 = solution.diameterOfBinaryTree(root2);
        System.out.println("Output: " + result2);
        System.out.println("Expected: 1");
        System.out.println();

        // Test Case 3: Single node [1]
        System.out.println("Test Case 3:");
        Integer[] input3 = { 1 };
        TreeNode root3 = createTree(input3);
        System.out.println("Input: root = [1]");
        int result3 = solution.diameterOfBinaryTree(root3);
        System.out.println("Output: " + result3);
        System.out.println("Expected: 0"); // Diameter is edges, simply 0 for single node
        System.out.println();

        // Test Case 4: Complex tree helping to visualize path doesn't need to go
        // through root
        // 1
        // /
        // 2
        // / \
        // 3 4
        // / \
        // 5 6
        // Longest path: 5 -> 3 -> 2 -> 4 -> 6 (Length 4)
        // Array rep: [1, 2, null, 3, 4, null, null, 5, null, null, 6]
        System.out.println("Test Case 4 (Path not through root):");
        Integer[] input4 = { 1, 2, null, 3, 4, null, null, 5, null, null, 6 };
        TreeNode root4 = createTree(input4);
        System.out.println("Input: root = [1, 2, null, 3, 4, null, null, 5, null, null, 6]");
        int result4 = solution.diameterOfBinaryTree(root4);
        System.out.println("Output: " + result4);
        System.out.println("Expected: 4");

        System.out.println("\n✅ All test cases completed!");
    }
}

/**
 * Approach:
 * Depth-First Search (DFS) / Recursion
 * 
 * 1. The diameter of a binary tree is the length of the longest path between
 * any two nodes.
 * 2. For any given node, the longest path that passes through it is the sum of
 * the
 * height of its left subtree and the height of its right subtree.
 * 3. We can calculate the height of each node recursively:
 * height(node) = 1 + max(height(node.left), height(node.right))
 * 4. While calculating the height, we update a global variable `maxDiameter`
 * with
 * the sum of left and right heights (leftHeight + rightHeight) for every node.
 * 5. The final answer is the maximum value encountered.
 * 
 * Time Complexity: O(n)
 * - We visit every node exactly once.
 * 
 * Space Complexity: O(h)
 * - The space complexity is determined by the height of the recursion stack,
 * which is O(h) where h is the height of the tree.
 * - In the worst case (skewed tree), it is O(n).
 * - In the best case (balanced tree), it is O(log n).
 */
