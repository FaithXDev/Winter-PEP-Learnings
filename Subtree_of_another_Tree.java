
public class Subtree_of_another_Tree {

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

    /**
     * Checks if subRoot is a subtree of root.
     * 
     * Time Complexity: O(M * N) in worst case (e.g., skewed trees), where M is
     * nodes in root and N is nodes in subRoot.
     * Space Complexity: O(H) for recursion stack, where H is the height of the root
     * tree.
     */
    public boolean isSubtree(TreeNode root, TreeNode subRoot) {
        if (subRoot == null) {
            return true; // An empty tree is a subtree of any tree
        }
        if (root == null) {
            return false; // Non-empty subRoot cannot be a subtree of empty root
        }

        // If the trees rooted at current node are identical, return true
        if (isSameTree(root, subRoot)) {
            return true;
        }

        // Otherwise, check if subRoot is in the left or right subtree
        return isSubtree(root.left, subRoot) || isSubtree(root.right, subRoot);
    }

    /**
     * Helper method to check if two trees are identical.
     * Reused from LeetCode 100 logic.
     */
    private boolean isSameTree(TreeNode p, TreeNode q) {
        if (p == null && q == null) {
            return true;
        }
        if (p == null || q == null || p.val != q.val) {
            return false;
        }
        return isSameTree(p.left, q.left) && isSameTree(p.right, q.right);
    }

}

/*
 * Approach:
 * 1. The problem asks if a binary tree `subRoot` is a subtree of another binary
 * tree `root`.
 * 2. A subtree of `root` consists of a node in `root` and all of its
 * descendants.
 *
 * Algorithm:
 * 1. Traverse the `root` tree. For each node in `root`, treat it as a potential
 * root of a subtree that matches `subRoot`.
 * 2. To check if the subtree rooted at the current node matches `subRoot`, we
 * use a helper function `isSameTree` (similar to LeetCode 100).
 * - `isSameTree(p, q)` checks if two trees rooted at `p` and `q` are identical
 * in structure and node values.
 * 3. Step-by-step logic for `isSubtree(root, subRoot)`:
 * - Base Case 1: If `subRoot` is null, it is structurally a subtree of any tree
 * (including an empty one). Return true.
 * - Base Case 2: If `root` is null (and `subRoot` is not, covered by above),
 * then `subRoot` cannot be a subtree. Return false.
 * - Check current node: If `isSameTree(root, subRoot)` returns true, then we
 * found a match. Return true.
 * - Recursive Step: If the current node doesn't match, check recursively in the
 * left child (`isSubtree(root.left, subRoot)`) OR the right child
 * (`isSubtree(root.right, subRoot)`).
 * 
 * Complexity Analysis:
 * - Time Complexity: O(M * N)
 * - M is the number of nodes in the `root` tree.
 * - N is the number of nodes in the `subRoot` tree.
 * - In the worst case (e.g., both trees are skewed or identical structure but
 * different leaf values), for each node in `root`, we might visit all nodes in
 * `subRoot`.
 * - Space Complexity: O(H_root)
 * - H_root is the height of the `root` tree.
 * - This space is used by the recursion stack for traversing `root`.
 */
