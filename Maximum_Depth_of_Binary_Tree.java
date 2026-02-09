
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

public class Maximum_Depth_of_Binary_Tree {

    /**
     * Helper method to create a binary tree from an array (Level Order).
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
        System.out.println("Input: root = [3,9,20,null,null,15,7]");
        int result1 = solution.maxDepth(root1);
        System.out.println("Output: " + result1);
        System.out.println("Expected: 3");
        System.out.println();

        // Test Case 2: [1,null,2]
        System.out.println("Test Case 2:");
        Integer[] input2 = { 1, null, 2 };
        TreeNode root2 = createTree(input2);
        System.out.println("Input: root = [1,null,2]");
        int result2 = solution.maxDepth(root2);
        System.out.println("Output: " + result2);
        System.out.println("Expected: 2");
        System.out.println();

        // Test Case 3: Empty Tree []
        System.out.println("Test Case 3:");
        Integer[] input3 = {};
        TreeNode root3 = createTree(input3);
        System.out.println("Input: root = []");
        int result3 = solution.maxDepth(root3);
        System.out.println("Output: " + result3);
        System.out.println("Expected: 0");
        System.out.println();

        // Test Case 4: Single Node [1]
        System.out.println("Test Case 4:");
        Integer[] input4 = { 1 };
        TreeNode root4 = createTree(input4);
        System.out.println("Input: root = [1]");
        int result4 = solution.maxDepth(root4);
        System.out.println("Output: " + result4);
        System.out.println("Expected: 1");

        System.out.println("\n✅ All test cases completed!");
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
