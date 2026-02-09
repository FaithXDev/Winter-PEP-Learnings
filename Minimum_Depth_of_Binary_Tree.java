
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
     * Calculates the minimum depth of a binary tree.
     * 
     * Time Complexity: O(n) - We might visit every node.
     * Space Complexity: O(h) - Where h is the height of the tree.
     */
    public int minDepth(TreeNode root) {
        if (root == null) {
            return 0;
        }

        // If left subtree is null, we must go right
        if (root.left == null) {
            return 1 + minDepth(root.right);
        }

        // If right subtree is null, we must go left
        if (root.right == null) {
            return 1 + minDepth(root.left);
        }

        // If both children exist, take the minimum of both paths
        return 1 + Math.min(minDepth(root.left), minDepth(root.right));
    }
}

public class Minimum_Depth_of_Binary_Tree {

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
        int result1 = solution.minDepth(root1);
        System.out.println("Output: " + result1);
        System.out.println("Expected: 2");
        System.out.println();

        // Test Case 2: [2,null,3,null,4,null,5,null,6] (Skewed tree)
        System.out.println("Test Case 2:");
        Integer[] input2 = { 2, null, 3, null, 4, null, 5, null, 6 };
        TreeNode root2 = createTree(input2);
        System.out.println("Input: root = [2,null,3,null,4,null,5,null,6]");
        int result2 = solution.minDepth(root2);
        System.out.println("Output: " + result2);
        System.out.println("Expected: 5");
        System.out.println();

        // Test Case 3: Empty Tree []
        System.out.println("Test Case 3:");
        Integer[] input3 = {};
        TreeNode root3 = createTree(input3);
        System.out.println("Input: root = []");
        int result3 = solution.minDepth(root3);
        System.out.println("Output: " + result3);
        System.out.println("Expected: 0");
        System.out.println();

        // Test Case 4: Single Node [1]
        System.out.println("Test Case 4:");
        Integer[] input4 = { 1 };
        TreeNode root4 = createTree(input4);
        System.out.println("Input: root = [1]");
        int result4 = solution.minDepth(root4);
        System.out.println("Output: " + result4);
        System.out.println("Expected: 1");

        System.out.println("\n✅ All test cases completed!");
    }
}
