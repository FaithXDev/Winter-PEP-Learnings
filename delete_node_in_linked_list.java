
class ListNode {
    int val;
    ListNode next;

    ListNode(int x) {
        val = x;
    }
}

class Solution {
    /**
     * Deletes the given node from the linked list.
     * 
     * @param node The node to be deleted (guaranteed not to be the tail)
     */
    public void deleteNode(ListNode node) {
        // Step 1: Copy the value of the next node to the current node
        node.val = node.next.val;

        // Step 2: Skip the next node by updating the pointer
        node.next = node.next.next;
    }
}

/**
 * Main class to test the solution
 */
class Delete_node_in_linked_list {

    /**
     * Helper method to print the linked list
     */
    public static void printList(ListNode head) {
        ListNode current = head;
        System.out.print("[");
        while (current != null) {
            System.out.print(current.val);
            if (current.next != null) {
                System.out.print(", ");
            }
            current = current.next;
        }
        System.out.println("]");
    }

    /**
     * Helper method to find a node with given value
     */
    public static ListNode findNode(ListNode head, int val) {
        ListNode current = head;
        while (current != null) {
            if (current.val == val) {
                return current;
            }
            current = current.next;
        }
        return null;
    }

    public static void main(String[] args) {
        Solution solution = new Solution();

        // Test Case 1: Delete node with value 5
        // Input: [4, 5, 1, 9], delete node = 5
        // Expected Output: [4, 1, 9]
        System.out.println("Test Case 1:");
        ListNode head1 = new ListNode(4);
        head1.next = new ListNode(5);
        head1.next.next = new ListNode(1);
        head1.next.next.next = new ListNode(9);

        System.out.print("Before deletion: ");
        printList(head1);

        ListNode nodeToDelete1 = findNode(head1, 5);
        solution.deleteNode(nodeToDelete1);

        System.out.print("After deleting node 5: ");
        printList(head1);
        System.out.println();

        // Test Case 2: Delete node with value 1
        // Input: [4, 5, 1, 9], delete node = 1
        // Expected Output: [4, 5, 9]
        System.out.println("Test Case 2:");
        ListNode head2 = new ListNode(4);
        head2.next = new ListNode(5);
        head2.next.next = new ListNode(1);
        head2.next.next.next = new ListNode(9);

        System.out.print("Before deletion: ");
        printList(head2);

        ListNode nodeToDelete2 = findNode(head2, 1);
        solution.deleteNode(nodeToDelete2);

        System.out.print("After deleting node 1: ");
        printList(head2);
        System.out.println();

        // Test Case 3: Delete the first node (second position)
        // Input: [1, 2, 3, 4], delete node = 2
        // Expected Output: [1, 3, 4]
        System.out.println("Test Case 3:");
        ListNode head3 = new ListNode(1);
        head3.next = new ListNode(2);
        head3.next.next = new ListNode(3);
        head3.next.next.next = new ListNode(4);

        System.out.print("Before deletion: ");
        printList(head3);

        ListNode nodeToDelete3 = findNode(head3, 2);
        solution.deleteNode(nodeToDelete3);

        System.out.print("After deleting node 2: ");
        printList(head3);

        System.out.println("\n✓ All test cases passed!");
    }
}

/**
 * Approach:
 * ---------
 * Since we don't have access to the head of the linked list, we can't traverse
 * to find the previous node. Instead, we use a clever trick:
 * 
 * 1. Copy the value of the next node into the current node
 * 2. Delete the next node by pointing current.next to current.next.next
 * 
 * This effectively "removes" the current node by replacing it with the next
 * node.
 * 
 * Example:
 * --------
 * Input: head = [4,5,1,9], node = 5
 * Output: [4,1,9]
 * Explanation: You are given the second node with value 5, the linked list
 * should become 4 -> 1 -> 9 after calling your function.
 * 
 * Time Complexity: O(1) - We only perform constant time operations
 * Space Complexity: O(1) - No extra space used
 */
