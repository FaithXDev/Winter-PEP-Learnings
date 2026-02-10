public class delete_node_in_bst {

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
     * Deletes a node with the given key from the BST.
     * 
     * Time Complexity: O(H), where H is the height of the tree.
     * Space Complexity: O(H) (Recursive stack).
     */
    public TreeNode deleteNode(TreeNode root, int key) {
        if (root == null) {
            return null;
        }

        // Search for the node to delete
        if (key < root.val) {
            root.left = deleteNode(root.left, key);
        } else if (key > root.val) {
            root.right = deleteNode(root.right, key);
        } else {
            // Node to be deleted found

            // Case 1: Node has no children (leaf node)
            if (root.left == null && root.right == null) {
                return null;
            }

            // Case 2: Node has only one child
            if (root.left == null) {
                return root.right;
            } else if (root.right == null) {
                return root.left;
            }

            // Case 3: Node has two children
            // Find the inorder successor (smallest node in the right subtree)
            TreeNode successor = findMin(root.right);

            // Replace the key of the node to be deleted with the successor's key
            root.val = successor.val;

            // Delete the successor node from the right subtree
            root.right = deleteNode(root.right, successor.val);
        }

        return root;
    }

    // Helper method to find the minimum value node in a subtree
    private TreeNode findMin(TreeNode node) {
        while (node.left != null) {
            node = node.left;
        }
        return node;
    }

    // Helper method to print Inorder traversal (to verify the tree structure)
    public static void printInorder(TreeNode root) {
        if (root != null) {
            printInorder(root.left);
            System.out.print(root.val + " ");
            printInorder(root.right);
        }
    }

    public static void main(String[] args) {
        delete_node_in_bst solution = new delete_node_in_bst();

        // Construct the BST for testing:
        // root = [5,3,6,2,4,null,7]
        TreeNode root = new TreeNode(5);
        root.left = new TreeNode(3);
        root.right = new TreeNode(6);
        root.left.left = new TreeNode(2);
        root.left.right = new TreeNode(4);
        root.right.right = new TreeNode(7);

        System.out.println("Original BST (Inorder):");
        printInorder(root);
        System.out.println();

        // Test Case 1: Delete key 3 (Node with two children)
        int key1 = 3;
        System.out.println("\nDeleting node with key " + key1 + "...");
        root = solution.deleteNode(root, key1);
        System.out.print("Modified BST (Inorder): ");
        printInorder(root);
        System.out.println();
        // Expected Inorder after deleting 3: 2 4 5 6 7

        // Test Case 2: Delete key 5 (Root node)
        int key2 = 5;
        System.out.println("\nDeleting node with key " + key2 + "...");
        root = solution.deleteNode(root, key2);
        System.out.print("Modified BST (Inorder): ");
        printInorder(root);
        System.out.println();

        // Test Case 3: Delete key 7 (Leaf node)
        int key3 = 7;
        System.out.println("\nDeleting node with key " + key3 + "...");
        root = solution.deleteNode(root, key3);
        System.out.print("Modified BST (Inorder): ");
        printInorder(root);
        System.out.println();

        System.out.println("\n✅ All test cases completed!");
    }
}

/*
 * Approach:
 * 1. The problem asks to delete a node with a specific `key` from a Binary
 * Search Tree (BST)
 * and return the root reference of the BST.
 * 2. BST Property:
 * - Left subtree elements are smaller than the node.
 * - Right subtree elements are larger than the node.
 * 3. Algorithm:
 * - Search for the node:
 * - If `key < root.val`, recursively search in the left subtree.
 * - If `key > root.val`, recursively search in the right subtree.
 * - If `key == root.val`, we found the node to delete.
 * - Handle the 3 cases for deletion:
 * - Case 1: Node is a leaf (no children).
 * - Simply remove it by returning `null`.
 * - Case 2: Node has only one child.
 * - Return the non-null child to the parent (bypassing the current node).
 * - Case 3: Node has two children.
 * - Find the Inorder Successor (minimum value in the right subtree).
 * - Replace the current node's value with the successor's value.
 * - Recursively delete the successor node from the right subtree.
 * (Standard reduction to Case 1 or Case 2).
 * 
 * Complexity Analysis:
 * - Time Complexity: O(H)
 * - We search for the node (O(H)) and potentially search for the successor
 * (O(H)).
 * - Overall time is proportional to the height of the tree.
 * - In a balanced BST, H = log N. In a skewed BST, H = N.
 * - Space Complexity: O(H)
 * - Recursion stack depth is equal to the height of the tree.
 */
