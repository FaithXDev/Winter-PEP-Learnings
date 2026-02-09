
public class Same_tree {

    // Definition for a binary tree node.
    // Using static inner class to avoid file naming collisions in the default
    // package
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
     * Checks if two binary trees are the same.
     * 
     * Time Complexity: O(min(N, M)) where N and M are the number of nodes in the
     * trees.
     * We visit each node once.
     * Space Complexity: O(min(H1, H2)) for the recursion stack, where H is the
     * height of the tree.
     */
    public boolean isSameTree(TreeNode p, TreeNode q) {
        // Base case: both nodes are null, structural identity is maintained
        if (p == null && q == null) {
            return true;
        }

        // If one is null and the other is not, they are different
        if (p == null || q == null) {
            return false;
        }

        // If values are different, they are different
        if (p.val != q.val) {
            return false;
        }

        // Recursively check left and right subtrees
        return isSameTree(p.left, q.left) && isSameTree(p.right, q.right);
    }

    public static void main(String[] args) {
        Same_tree solution = new Same_tree();

        // Test Case 1: p = [1,2,3], q = [1,2,3]
        TreeNode p1 = new TreeNode(1);
        p1.left = new TreeNode(2);
        p1.right = new TreeNode(3);

        TreeNode q1 = new TreeNode(1);
        q1.left = new TreeNode(2);
        q1.right = new TreeNode(3);

        System.out.println("Test Case 1: " + solution.isSameTree(p1, q1)); // Expected: true

        // Test Case 2: p = [1,2], q = [1,null,2]
        TreeNode p2 = new TreeNode(1);
        p2.left = new TreeNode(2);

        TreeNode q2 = new TreeNode(1);
        q2.right = new TreeNode(2);

        System.out.println("Test Case 2: " + solution.isSameTree(p2, q2)); // Expected: false

        // Test Case 3: p = [1,2,1], q = [1,1,2]
        TreeNode p3 = new TreeNode(1);
        p3.left = new TreeNode(2);
        p3.right = new TreeNode(1);

        TreeNode q3 = new TreeNode(1);
        q3.left = new TreeNode(1);
        q3.right = new TreeNode(2);

        System.out.println("Test Case 3: " + solution.isSameTree(p3, q3)); // Expected: false
    }
}

/*
 * Approach:
 * 1. The problem asks us to determine if two binary trees are identical (same
 * structure and same values).
 * 
 * Algorithm:
 * 1. We use a recursive Depth First Search (DFS) approach to traverse both
 * trees simultaneously.
 * 2. At each step, we compare the current nodes `p` and `q`.
 * 3. Base Cases:
 * - If both `p` and `q` are null, it means we reached the end of both trees at
 * the same position, so they are structurally satisfying. Return true.
 * - If one is null and the other is not, there is a structural mismatch. Return
 * false.
 * - If the values of `p` and `q` are different, there is a value mismatch.
 * Return false.
 * 4. Recursive Step:
 * - If the current nodes are identical, recursively check if:
 * - `p.left` is the same as `q.left`
 * - AND
 * - `p.right` is the same as `q.right`
 * 
 * Complexity Analysis:
 * - Time Complexity: O(N)
 * - We visit each node in the trees exactly once. N is the number of nodes in
 * the smaller tree (since we stop at mismatch).
 * - Space Complexity: O(H)
 * - H is the height of the tree. This space is used by the recursion stack.
 * - In the worst case (skewed tree), H = N. In the best case (balanced tree), H
 * = log N.
 */
