
import java.util.ArrayList;
import java.util.List;
import java.util.Stack;

// Definition for a binary tree node.
class TreeNode_145 {
    int val;
    TreeNode_145 left;
    TreeNode_145 right;

    TreeNode_145() {
    }

    TreeNode_145(int val) {
        this.val = val;
    }

    TreeNode_145(int val, TreeNode_145 left, TreeNode_145 right) {
        this.val = val;
        this.left = left;
        this.right = right;
    }
}

class Solution_145 {
    public List<Integer> postorderTraversal(TreeNode_145 root) {
        List<Integer> result = new ArrayList<>();
        helper(root, result);
        return result;
    }

    private void helper(TreeNode_145 node, List<Integer> result) {
        if (node == null) {
            return;
        }

        // Traverse Left Subtree
        helper(node.left, result);

        // Traverse Right Subtree
        helper(node.right, result);

        // Visit Root
        result.add(node.val);
    }

    // Iterative Approach using 2 Stacks (for reference)
    public List<Integer> postorderTraversalIterative(TreeNode_145 root) {
        List<Integer> result = new ArrayList<>();
        if (root == null)
            return result;

        Stack<TreeNode_145> stack1 = new Stack<>();
        Stack<TreeNode_145> stack2 = new Stack<>();

        stack1.push(root);

        while (!stack1.isEmpty()) {
            TreeNode_145 node = stack1.pop();
            stack2.push(node);

            if (node.left != null) {
                stack1.push(node.left);
            }
            if (node.right != null) {
                stack1.push(node.right);
            }
        }

        while (!stack2.isEmpty()) {
            result.add(stack2.pop().val);
        }

        return result;
    }
}

public class Binary_Tree_Postorder_Traversal {

    /**
     * Helper method to create a binary tree from an array (Level Order).
     * Uses Integer array to support null values for missing nodes.
     */
    public static TreeNode_145 createTree(Integer[] arr) {
        if (arr == null || arr.length == 0 || arr[0] == null) {
            return null;
        }

        TreeNode_145 root = new TreeNode_145(arr[0]);
        java.util.Queue<TreeNode_145> queue = new java.util.LinkedList<>();
        queue.add(root);

        int i = 1;
        while (i < arr.length) {
            TreeNode_145 current = queue.poll();

            // Left child
            if (i < arr.length && arr[i] != null) {
                current.left = new TreeNode_145(arr[i]);
                queue.add(current.left);
            }
            i++;

            // Right child
            if (i < arr.length && arr[i] != null) {
                current.right = new TreeNode_145(arr[i]);
                queue.add(current.right);
            }
            i++;
        }

        return root;
    }

}

/**
 * Approach:
 * Recursive Depth-First Search (DFS)
 * 
 * 1. Postorder traversal follows the pattern: Left -> Right -> Root.
 * 2. We define a helper function that takes the current node and the result
 * list.
 * 3. Base case: If the node is null, return.
 * 4. Recursive step:
 * - Recursively call the helper for the left child.
 * - Recursively call the helper for the right child.
 * - Add the current node's value to the result list.
 * 
 * Time Complexity: O(n)
 * - We find visit every node exactly once.
 * 
 * Space Complexity: O(n)
 * - In the worst case (skewed tree), the recursion stack can go up to O(n).
 * - In the average case (balanced tree), it is O(log n).
 * - storing the result takes O(n) space.
 */
