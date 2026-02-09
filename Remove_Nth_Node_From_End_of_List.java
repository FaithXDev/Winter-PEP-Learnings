
class ListNode {
    int val;
    ListNode next;

    ListNode() {
    }

    ListNode(int val) {
        this.val = val;
    }

    ListNode(int val, ListNode next) {
        this.val = val;
        this.next = next;
    }
}

class Solution {
    public ListNode removeNthFromEnd(ListNode head, int n) {
        // Dummy node handles edge case of removing the head
        ListNode dummy = new ListNode(0, head);
        ListNode slow = dummy;
        ListNode fast = head;

        // Move fast n steps ahead
        for (int i = 0; i < n; i++) {
            fast = fast.next;
        }

        // Move both until fast reaches the end
        while (fast != null) {
            slow = slow.next;
            fast = fast.next;
        }

        // Skip the nth node from end
        slow.next = slow.next.next;

        return dummy.next;
    }
}

public class Remove_Nth_Node_From_End_of_List {

    // Helper method to create a linked list from an array
    public static ListNode createList(int[] arr) {
        if (arr == null || arr.length == 0)
            return null;

        ListNode head = new ListNode(arr[0]);
        ListNode current = head;

        for (int i = 1; i < arr.length; i++) {
            current.next = new ListNode(arr[i]);
            current = current.next;
        }

        return head;
    }

    // Helper method to print a linked list
    public static void printList(ListNode head) {
        System.out.print("[");
        ListNode current = head;
        while (current != null) {
            System.out.print(current.val);
            if (current.next != null)
                System.out.print(",");
            current = current.next;
        }
        System.out.println("]");
    }

    public static void main(String[] args) {
        Solution solution = new Solution();

        // Test Case 1: head = [1,2,3,4,5], n = 2
        // Expected Output: [1,2,3,5]
        System.out.println("Test Case 1:");
        ListNode head1 = createList(new int[] { 1, 2, 3, 4, 5 });
        System.out.print("Input: ");
        printList(head1);
        System.out.println("n = 2");
        ListNode result1 = solution.removeNthFromEnd(head1, 2);
        System.out.print("Output: ");
        printList(result1);
        System.out.println();

        // Test Case 2: head = [1], n = 1
        // Expected Output: []
        System.out.println("Test Case 2:");
        ListNode head2 = createList(new int[] { 1 });
        System.out.print("Input: ");
        printList(head2);
        System.out.println("n = 1");
        ListNode result2 = solution.removeNthFromEnd(head2, 1);
        System.out.print("Output: ");
        printList(result2);
        System.out.println();

        // Test Case 3: head = [1,2], n = 1
        // Expected Output: [1]
        System.out.println("Test Case 3:");
        ListNode head3 = createList(new int[] { 1, 2 });
        System.out.print("Input: ");
        printList(head3);
        System.out.println("n = 1");
        ListNode result3 = solution.removeNthFromEnd(head3, 1);
        System.out.print("Output: ");
        printList(result3);
    }
}

/**
 * Approach: Two Pointer Technique (One Pass)
 * 1. Use a dummy node to handle edge case of removing the head
 * 2. Move fast pointer n steps ahead
 * 3. Move both slow and fast together until fast reaches null
 * 4. slow.next is now the node to be removed, skip it
 * 
 * Time Complexity: O(sz) - Single pass through the list
 * Space Complexity: O(1) - Only two pointers used
 */