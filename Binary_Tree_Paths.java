
/**
 * LeetCode 257: Binary Tree Paths
 *
 * Problem Description: Given the root of a binary tree, return all root-to-leaf paths in any order.
 * A leaf is a node with no children.
 * 
 * Approach:
 * We use a Depth First Search (DFS) traversal to explore all paths from the root to the leaves.
 * 
 * 1. Maintain a `path` string that accumulates the node values as we traverse down.
 * 2. At each node, append the current node's value to the `path`.
 * 3. Base Case (Leaf Node): If the current node is a leaf (both left and right children are null), add the completed `path` to the result list.
 * 4. Recursive Step: If the node is not a leaf, append "->" to the path and recursively call the function for:
 *    - The left child
 *    - The right child
 * 
 * Since strings are immutable in Java, each recursive call gets a new copy of the path string, ensuring correct path construction without explicit backtracking.
 * Time Complexity: O(N) since we visit every node once. String concatenations can add overhead, making it effectively O(N^2) in worst case (skewed tree) depending on string length.
 *
 * Example 1:
 * Input: root = [1,2,3,null,5]
 * Output: ["1->2->5","1->3"]
 *
 * Example 2:
 * Input: root = [1]
 * Output: ["1"]
 * 
 * Constraints:
 * - The number of nodes in the tree is in the range [1, 100].
 * - -100 <= Node.val <= 100

 */
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
