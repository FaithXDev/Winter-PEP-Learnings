
public class Lowest_Common_Ancestor_of_a_Binary_Search_Tree {

    // Definition for a binary tree node.
    public static class TreeNode {
        int val;
        TreeNode left;
        TreeNode right;

        TreeNode(int x) {
            val = x;
        }
    }

    /**
     * Finds the Lowest Common Ancestor (LCA) of two nodes in a BST.
     * 
     * Time Complexity: O(H), where H is the height of the tree.
     * Space Complexity: O(1) (Iterative) or O(H) (Recursive stack).
     */
    public TreeNode lowestCommonAncestor(TreeNode root, TreeNode p, TreeNode q) {
        // Start from the root
        TreeNode current = root;

        while (current != null) {
            if (p.val > current.val && q.val > current.val) {
                // If both p and q are greater than parent, the LCA must be in the right subtree
                current = current.right;
            } else if (p.val < current.val && q.val < current.val) {
                // If both p and q are smaller than parent, the LCA must be in the left subtree
                current = current.left;
            } else {
                // We have found the split point, or one of the nodes is the parent itself.
                // This is the LCA.
                return current;
            }
        }
        return null;
    }

}

/*
 * Approach:
 * 1. The problem asks for the Lowest Common Ancestor (LCA) of two nodes `p` and
 * `q` in a Binary Search Tree (BST).
 * 2. Property of BST:
 * - For any node `N`, all nodes in its left subtree have values < `N.val`.
 * - All nodes in its right subtree have values > `N.val`.
 * 
 * Algorithm:
 * 1. Start traversing from the `root` node.
 * 2. At each step, compare the values of `p` and `q` with the current node
 * (`root`).
 * 3. Logic:
 * - If both `p.val` and `q.val` are greater than `root.val`:
 * - This means both nodes are in the right subtree.
 * - Move right: `root = root.right`.
 * - If both `p.val` and `q.val` are smaller than `root.val`:
 * - This means both nodes are in the left subtree.
 * - Move left: `root = root.left`.
 * - Otherwise (split point):
 * - If one is on the left and one on the right, OR if the current node is equal
 * to either `p` or `q`.
 * - The current node is the lowest point where `p` and `q` diverge (or one is
 * the ancestor of the other).
 * - This node is the LCA. Return it.
 * 
 * Complexity Analysis:
 * - Time Complexity: O(H) where H is the height of the tree.
 * - In the worst case (skewed tree), we might visit all nodes O(N).
 * - In a balanced tree, finding the path is O(log N).
 * - Space Complexity: O(1)
 * - We are using an iterative approach, so no recursion stack or extra data
 * structures are used. (Recursive approach would be O(H)).
 */
