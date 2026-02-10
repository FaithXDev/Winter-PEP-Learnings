import java.util.ArrayList;
import java.util.List;

public class Binary_Tree_Right_Side_View {

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
     * Returns the values of the nodes you can see ordered from top to bottom.
     * 
     * Time Complexity: O(N), where N is the number of nodes in the tree.
     * Space Complexity: O(H), where H is the height of the tree (recursion stack).
     */
    public List<Integer> rightSideView(TreeNode root) {
        List<Integer> result = new ArrayList<>();
        rightView(root, result, 0);
        return result;
    }

    private void rightView(TreeNode curr, List<Integer> result, int currDepth) {
        if (curr == null) {
            return;
        }

        // If the current depth matches the size of result, it means this is the first
        // node we are visiting at this depth (since we traverse right first).
        if (currDepth == result.size()) {
            result.add(curr.val);
        }

        // Traverse Right first, then Left
        rightView(curr.right, result, currDepth + 1);
        rightView(curr.left, result, currDepth + 1);
    }

    public static void main(String[] args) {
        Binary_Tree_Right_Side_View solution = new Binary_Tree_Right_Side_View();

        // Construct the tree for testing:
        // root = [1,2,3,null,5,null,4]
        // 1
        // / \
        // 2 3
        // \ \
        // 5 4
        TreeNode root = new TreeNode(1);
        root.left = new TreeNode(2);
        root.right = new TreeNode(3);
        root.left.right = new TreeNode(5);
        root.right.right = new TreeNode(4);

        System.out.println("Test Case 1:");
        System.out.println("Input: root = [1,2,3,null,5,null,4]");
        List<Integer> result = solution.rightSideView(root);
        System.out.println("Output: " + result);
        System.out.println("Expected: [1, 3, 4]");
        System.out.println();

        // Test Case 2: [1,null,3]
        // 1
        // \
        // 3
        TreeNode root2 = new TreeNode(1);
        root2.right = new TreeNode(3);

        System.out.println("Test Case 2:");
        System.out.println("Input: root = [1,null,3]");
        List<Integer> result2 = solution.rightSideView(root2);
        System.out.println("Output: " + result2);
        System.out.println("Expected: [1, 3]");
        System.out.println();

        // Test Case 3: []
        TreeNode root3 = null;
        System.out.println("Test Case 3:");
        System.out.println("Input: root = []");
        List<Integer> result3 = solution.rightSideView(root3);
        System.out.println("Output: " + result3);
        System.out.println("Expected: []");
        System.out.println();

        System.out.println("✅ All test cases completed!");
    }
}

/*
 * Approach:
 * 1. The problem asks for the standard "Right Side View" of a binary tree.
 * 2. We can solve this using either Breadth-First Search (BFS) or Depth-First
 * Search (DFS).
 * 3. DFS Approach (Recursive):
 * - We traverse the tree in a modified Preorder fashion: Root -> Right -> Left.
 * - We keep track of the current depth of the recursion.
 * - The idea is that if we visit the Right child before the Left child, the
 * first node
 * we encounter at any given depth `d` is the rightmost node at that level
 * visible
 * from the right side.
 * - We use a list `result` to store the view.
 * - If `currDepth == result.size()`, it implies this is the first time we are
 * visiting
 * this depth level. We add the current node's value to the result.
 * - Recursively call for the right child first (currDepth + 1), then the left
 * child (currDepth + 1).
 * 
 * Complexity Analysis:
 * - Time Complexity: O(N)
 * - We visit each node exactly once.
 * - Space Complexity: O(H)
 * - O(H) for the recursion stack, where H is the height of the tree.
 * - In the worst case (skewed tree), H = N. In the best case (balanced tree), H
 * = log N.
 */
