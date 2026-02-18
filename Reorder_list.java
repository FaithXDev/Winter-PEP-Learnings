
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
    /**
     * Reorders the linked list in-place.
     * 
     * @param head The head of the singly linked list
     */
    public void reorderList(ListNode head) {
        // Edge case: if list is empty or has only one node
        if (head == null || head.next == null) {
            return;
        }

        // Step 1: Find the middle of the linked list using slow and fast pointers
        ListNode slow = head;
        ListNode fast = head;

        while (fast.next != null && fast.next.next != null) {
            slow = slow.next;
            fast = fast.next.next;
        }

        // slow is now at the middle of the list
        // Step 2: Reverse the second half of the list
        ListNode secondHalf = reverseList(slow.next);
        slow.next = null; // Cut the list into two halves

        // Step 3: Merge the two halves alternately
        ListNode firstHalf = head;
        mergeLists(firstHalf, secondHalf);
    }

    /**
     * Reverses a linked list and returns the new head.
     * 
     * @param head The head of the list to reverse
     * @return The new head of the reversed list
     */
    private ListNode reverseList(ListNode head) {
        ListNode prev = null;
        ListNode current = head;

        while (current != null) {
            ListNode nextTemp = current.next;
            current.next = prev;
            prev = current;
            current = nextTemp;
        }

        return prev;
    }

    /**
     * Merges two lists by alternating nodes from each list.
     * 
     * @param first  The first list
     * @param second The second list
     */
    private void mergeLists(ListNode first, ListNode second) {
        while (second != null) {
            ListNode firstNext = first.next;
            ListNode secondNext = second.next;

            first.next = second;
            second.next = firstNext;

            first = firstNext;
            second = secondNext;
        }
    }
}

public class Reorder_list {

    /**
     * Helper method to create a linked list from an array.
     */
    public static ListNode createList(int[] arr) {
        if (arr == null || arr.length == 0) {
            return null;
        }

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
 * * Approach:
 * 1. Find the middle of the linked list using slow and fast pointers
 * 2. Reverse the second half of the list
 * 3. Merge the two halves alternately
 * 
 * Time Complexity: O(n) - We traverse the list a few times
 * Space Complexity: O(1) - We only use constant extra space
 */
