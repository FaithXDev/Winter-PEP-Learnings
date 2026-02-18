
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
