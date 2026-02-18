
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
