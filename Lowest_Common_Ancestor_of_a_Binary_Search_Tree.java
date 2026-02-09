/**
 * LeetCode 235: Lowest Common Ancestor of a Binary Search Tree
 * 
 * Problem Description:
 * Given a binary search tree (BST), find the lowest common ancestor (LCA) node
 * of two given nodes in the BST.
 * 
 * According to the definition of LCA on Wikipedia: “The lowest common ancestor
 * is defined between two nodes p and q
 * as the lowest node in T that has both p and q as descendants (where we allow
 * a node to be a descendant of itself).”
 * 
 * Example 1:
 * Input: root = [6,2,8,0,4,7,9,null,null,3,5], p = 2, q = 8
 * Output: 6
 * Explanation: The LCA of nodes 2 and 8 is 6.
 * 
 * Example 2:
 * Input: root = [6,2,8,0,4,7,9,null,null,3,5], p = 2, q = 4
 * Output: 2
 * Explanation: The LCA of nodes 2 and 4 is 2, since a node can be a descendant
 * of itself according to the LCA definition.
 * 
 * Constraints:
 * - The number of nodes in the tree is in the range [2, 10^5].
 * - -10^9 <= Node.val <= 10^9
 * - All Node.val are unique.
 * - p != q
 * - p and q will exist in the BST.
 */

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

    public static void main(String[] args) {
        Lowest_Common_Ancestor_of_a_Binary_Search_Tree solution = new Lowest_Common_Ancestor_of_a_Binary_Search_Tree();

        // Construct the BST for testing:
        // root = [6,2,8,0,4,7,9,null,null,3,5]
        TreeNode root = new TreeNode(6);
        root.left = new TreeNode(2);
        root.right = new TreeNode(8);
        root.left.left = new TreeNode(0);
        root.left.right = new TreeNode(4);
        root.right.left = new TreeNode(7);
        root.right.right = new TreeNode(9);
        root.left.right.left = new TreeNode(3);
        root.left.right.right = new TreeNode(5);

        // Test Case 1: p = 2, q = 8
        TreeNode p1 = root.left; // 2
        TreeNode q1 = root.right; // 8
        TreeNode lca1 = solution.lowestCommonAncestor(root, p1, q1);
        System.out.println("Test Case 1:");
        System.out.println("Input: root = [6,2,8...], p = 2, q = 8");
        System.out.println("LCA: " + (lca1 != null ? lca1.val : "null"));
        System.out.println("Expected: 6");
        System.out.println();

        // Test Case 2: p = 2, q = 4
        TreeNode p2 = root.left; // 2
        TreeNode q2 = root.left.right; // 4
        TreeNode lca2 = solution.lowestCommonAncestor(root, p2, q2);
        System.out.println("Test Case 2:");
        System.out.println("Input: root = [6,2,8...], p = 2, q = 4");
        System.out.println("LCA: " + (lca2 != null ? lca2.val : "null"));
        System.out.println("Expected: 2");
        System.out.println();

        System.out.println("✅ All test cases completed!");
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
