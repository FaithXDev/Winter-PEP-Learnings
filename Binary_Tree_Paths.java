import java.util.ArrayList;
import java.util.List;

public class Binary_Tree_Paths {

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
     * Returns all root-to-leaf paths.
     * 
     * Time Complexity: O(N) where N is the number of nodes in the tree. We visit
     * each node once.
     * Space Complexity: O(H) where H is the height of the tree for recursion stack.
     * In worst case (skewed tree), H = N. In best case (balanced tree), H = log N.
     */
    public List<String> binaryTreePaths(TreeNode root) {
        List<String> paths = new ArrayList<>();
        if (root != null) {
            constructPaths(root, "", paths);
        }
        return paths;
    }

    private void constructPaths(TreeNode root, String path, List<String> paths) {
        if (root != null) {
            path += Integer.toString(root.val);
            if (root.left == null && root.right == null) { // if reach a leaf
                paths.add(path); // update paths
            } else {
                path += "->"; // extend the current path
                constructPaths(root.left, path, paths);
                constructPaths(root.right, path, paths);
            }
        }
    }

    public static void main(String[] args) {
        Binary_Tree_Paths solution = new Binary_Tree_Paths();

        // Test Case 1: [1,2,3,null,5]
        TreeNode root1 = new TreeNode(1);
        root1.left = new TreeNode(2);
        root1.right = new TreeNode(3);
        root1.left.right = new TreeNode(5);

        System.out.println("Test Case 1: " + solution.binaryTreePaths(root1)); // Expected: ["1->2->5", "1->3"]

        // Test Case 2: [1]
        TreeNode root2 = new TreeNode(1);
        System.out.println("Test Case 2: " + solution.binaryTreePaths(root2)); // Expected: ["1"]
    }
}

/*
 * Approach:
 * 1. The problem asks for all root-to-leaf paths in a binary tree.
 * 
 * Algorithm:
 * 1. We use a Depth First Search (DFS) traversal to explore paths.
 * 2. We maintain a string `path` that accumulates the values of nodes visited
 * so far.
 * 3. Recursive Function `constructPaths(node, path, resultList)`:
 * - Append the current node's value to `path`.
 * - Leaf Check: If the current node is a leaf (no left and no right child), add
 * the current `path` to the `resultList`.
 * - Recursive Step: If not a leaf, append "->" to the current `path` and
 * recursively call for `node.left` and `node.right`.
 * 4. Since strings in Java are immutable, passing `path + "->"` creates a new
 * string for the next recursive call. This implicitly handles backtracking, as
 * the `path` variable in the current frame remains unchanged for other
 * branches.
 * 
 * Complexity Analysis:
 * - Time Complexity: O(N)
 * - We visit every node once.
 * - Note: String concatenation can take linear time relative to the string
 * length. In the worst case of a skewed tree with long paths, the total time
 * could degrade to O(N^2). using logic like StringBuilder can optimize this,
 * but simple string concatenation is sufficient for typical constraints.
 * - Space Complexity: O(H) (Recursion Stack) + O(N * L) (Result Storage)
 * - H is the height of the tree for recursion stack (O(N) in worst case, O(log
 * N) in best).
 * - We store all paths, which takes space proportional to the number of paths
 * times their average length.
 */
