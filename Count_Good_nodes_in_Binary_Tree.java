public class Count_Good_nodes_in_Binary_Tree {

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
     * Counts the number of "good" nodes in the binary tree.
     * A node is good if in the path from root to the node there are no nodes with a
     * value greater than X.
     *
     * Time Complexity: O(N), where N is the number of nodes in the tree. We visit
     * each node once.
     * Space Complexity: O(H), where H is the height of the tree. This is for the
     * recursion stack.
     */
    public int goodNodes(TreeNode root) {
        // Start DFS traversal from the root.
        // The initial max value encountered is the root's value itself (or
        // Integer.MIN_VALUE).
        return dfs(root, Integer.MIN_VALUE);
    }

    private int dfs(TreeNode node, int maxVal) {
        if (node == null) {
            return 0;
        }

        int count = 0;

        // If the current node's value is greater than or equal to the max value
        // encountered so far on the path from the root, it is a "good" node.
        if (node.val >= maxVal) {
            count = 1;
            maxVal = node.val; // Update the max value for the child nodes
        }

        // Recurse for left and right children
        count += dfs(node.left, maxVal);
        count += dfs(node.right, maxVal);

        return count;
    }

    public static void main(String[] args) {
        Count_Good_nodes_in_Binary_Tree solver = new Count_Good_nodes_in_Binary_Tree();

        // Test Case 1: [3,1,4,3,null,1,5]
        // 3
        // / \
        // 1 4
        // / / \
        // 3 1 5
        // Good nodes: 3 (root), 4 (4>=3), 3 (3>=3, left child of 1? Wait, path is
        // 3->1->3. 3>=1 and 3>=3. Yes), 5 (5>=4>=3).
        // Let's trace carefully:
        // Path 3: Good (Root) -> max=3
        // Path 3->1: Not Good (1 < 3) -> max=3
        // Path 3->1->3: Good (3 >= 3) -> max=3
        // Path 3->4: Good (4 >= 3) -> max=4
        // Path 3->4->1: Not Good (1 < 4) -> max=4
        // Path 3->4->5: Good (5 >= 4) -> max=5
        // Total: 4 good nodes.
        TreeNode root1 = new TreeNode(3);
        root1.left = new TreeNode(1);
        root1.right = new TreeNode(4);
        root1.left.left = new TreeNode(3);
        root1.right.left = new TreeNode(1);
        root1.right.right = new TreeNode(5);

        System.out.println("Test Case 1: " + solver.goodNodes(root1)); // Expected: 4

        // Test Case 2: [3,3,null,4,2]
        // 3
        // /
        // 3
        // / \
        // 4 2
        // Path 3: Good. Max=3.
        // Path 3->3: Good (3>=3). Max=3.
        // Path 3->3->4: Good (4>=3). Max=4.
        // Path 3->3->2: Not Good (2<3). Max=3.
        // Total: 3 good nodes.
        TreeNode root2 = new TreeNode(3);
        root2.left = new TreeNode(3);
        root2.left.left = new TreeNode(4);
        root2.left.right = new TreeNode(2);

        System.out.println("Test Case 2: " + solver.goodNodes(root2)); // Expected: 3

        // Test Case 3: [1]
        TreeNode root3 = new TreeNode(1);
        System.out.println("Test Case 3: " + solver.goodNodes(root3)); // Expected: 1
    }
}
