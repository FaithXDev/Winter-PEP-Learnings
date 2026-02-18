
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
