
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
     * Inverts a binary tree.
     * 
     * Time Complexity: O(n) - We visit every node exactly once.
     * Space Complexity: O(h) - Where h is the height of the tree (recursion stack).
     * O(n) for skewed, O(log n) for balanced.
     */
    public TreeNode invertTree(TreeNode root) {
        if (root == null) {
            return null;
        }

        // Recursively invert the left and right subtrees
        TreeNode left = invertTree(root.left);
        TreeNode right = invertTree(root.right);

        // Swap the left and right children
        root.left = right;
        root.right = left;

        return root;
    }
}

public class Invert_Binary_Tree {

    /**
     * Helper method to create a binary tree from an array (Level Order).
     */
    public static TreeNode createTree(Integer[] arr) {
        if (arr == null || arr.length == 0 || arr[0] == null) {
            return null;
        }

        TreeNode root = new TreeNode(arr[0]);
        java.util.Queue<TreeNode> queue = new java.util.LinkedList<>();
        queue.add(root);

        int i = 1;
        while (i < arr.length) {
            TreeNode current = queue.poll();

            // Left child
            if (i < arr.length) {
                if (arr[i] != null) {
                    current.left = new TreeNode(arr[i]);
                    queue.add(current.left);
                }
                i++;
            }

            // Right child
            if (i < arr.length) {
                if (arr[i] != null) {
                    current.right = new TreeNode(arr[i]);
                    queue.add(current.right);
                }
                i++;
            }
        }

        return root;
    }

    /**
     * Helper method to print tree in Level Order to verify output.
     */
    public static void printTree(TreeNode root) {
        if (root == null) {
            System.out.println("[]");
            return;
        }

        java.util.Queue<TreeNode> queue = new java.util.LinkedList<>();
        queue.add(root);

        StringBuilder sb = new StringBuilder("[");
        while (!queue.isEmpty()) {
            TreeNode current = queue.poll();
            if (current == null) {
                sb.append("null,");
            } else {
                sb.append(current.val).append(",");
                // Only add children if not all remaining nodes are null (simplified for basic
                // viz)
                // For strict LeetCode format, we'd need more complex logic,
                // but for debugging simple cases, adding all children including nulls for
                // leaves helps.
                // However, standard BFS printing usually omits trailing nulls.
                // Here we essentially add children to queue if current is not null.
                // To print mostly correct array format:
                if (current.left != null || current.right != null) {
                    queue.add(current.left);
                    queue.add(current.right);
                } else if (!queue.isEmpty()) {
                    // Check if there are non-null nodes left in queue or pending in tree level
                    // This simple print might miss some nulls or add extra, but sufficient for
                    // verifying inversion of values.
                }
            }
        }

        // Clean up trailing comma
        if (sb.length() > 1) {
            sb.setLength(sb.length() - 1);
        }
        sb.append("]");
        System.out.println(sb.toString());
    }

    // A better print approach for verification: simply standard BFS with null
    // handling
    public static void printLevelOrder(TreeNode root) {
        if (root == null) {
            System.out.println("[]");
            return;
        }

        java.util.List<String> values = new java.util.ArrayList<>();
        java.util.Queue<TreeNode> queue = new java.util.LinkedList<>();
        queue.add(root);

        while (!queue.isEmpty()) {
            TreeNode curr = queue.poll();
            if (curr == null) {
                values.add("null");
            } else {
                values.add(String.valueOf(curr.val));
                queue.add(curr.left);
                queue.add(curr.right);
            }
        }

        // Remove trailing "null"s
        int i = values.size() - 1;
        while (i >= 0 && values.get(i).equals("null")) {
            values.remove(i);
            i--;
        }

        System.out.println(values);
    }

    public static void main(String[] args) {
        Solution solution = new Solution();

        // Test Case 1: [4,2,7,1,3,6,9] -> [4,7,2,9,6,3,1]
        System.out.println("Test Case 1:");
        Integer[] input1 = { 4, 2, 7, 1, 3, 6, 9 };
        TreeNode root1 = createTree(input1);
        System.out.print("Input: ");
        printLevelOrder(root1);
        TreeNode result1 = solution.invertTree(root1);
        System.out.print("Output: ");
        printLevelOrder(result1);
        System.out.println("Expected: [4, 7, 2, 9, 6, 3, 1]");
        System.out.println();

        // Test Case 2: [2,1,3] -> [2,3,1]
        System.out.println("Test Case 2:");
        Integer[] input2 = { 2, 1, 3 };
        TreeNode root2 = createTree(input2);
        System.out.print("Input: ");
        printLevelOrder(root2);
        TreeNode result2 = solution.invertTree(root2);
        System.out.print("Output: ");
        printLevelOrder(result2);
        System.out.println("Expected: [2, 3, 1]");
        System.out.println();

        // Test Case 3: Empty Tree []
        System.out.println("Test Case 3:");
        Integer[] input3 = {};
        TreeNode root3 = createTree(input3);
        System.out.println("Input: []");
        TreeNode result3 = solution.invertTree(root3);
        System.out.print("Output: ");
        printLevelOrder(result3);
        System.out.println("Expected: []");
        System.out.println();

        System.out.println("\n✅ All test cases completed!");
    }
}

/*
 * Approach:
 * 1. The problem asks us to invert a binary tree, meaning ensuring that for
 * every node, its left and right children are swapped.
 * 
 * Algorithm:
 * 1. We use a recursive Depth First Search (DFS) approach.
 * 2. Base Case: If the `root` is null, there is nothing to invert, so return
 * null.
 * 3. Recursive Step:
 * - Recursively invert the left subtree: `invertTree(root.left)`
 * - Recursively invert the right subtree: `invertTree(root.right)`
 * - Swap the left and right children of the current `root`.
 * 4. Return the `root` after the swap.
 * 
 * Complexity Analysis:
 * - Time Complexity: O(N)
 * - We visit each node exactly once to perform the swap.
 * - Space Complexity: O(H)
 * - H is the height of the tree, used for the recursion stack.
 * - O(N) in the worst case (skewed tree), O(log N) in the best case (balanced
 * tree).
 */
