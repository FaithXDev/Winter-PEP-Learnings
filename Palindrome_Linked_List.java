
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
     * Checks if a singly linked list is a palindrome.
     * 
     * @param head The head of the singly linked list
     * @return true if the list is a palindrome, false otherwise
     */
    public boolean isPalindrome(ListNode head) {
        // Edge case: empty list or single node list is a palindrome
        if (head == null || head.next == null) {
            return true;
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
        ListNode secondHalfStart = reverseList(slow.next);

        // Step 3: Compare the first half with the reversed second half
        ListNode firstHalf = head;
        ListNode secondHalf = secondHalfStart;
        boolean isPalindrome = true;

        while (secondHalf != null) {
            if (firstHalf.val != secondHalf.val) {
                isPalindrome = false;
                break;
            }
            firstHalf = firstHalf.next;
            secondHalf = secondHalf.next;
        }

        // Step 4: (Optional) Restore the original list by reversing the second half
        // This is good practice if the caller expects the list to remain unchanged
        slow.next = reverseList(secondHalfStart);

        return isPalindrome;
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
}

public class Palindrome_Linked_List {

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

/*
 * Approach:
 * 1. Find the middle of the linked list using slow and fast pointers
 * 2. Reverse the second half of the list
 * 3. Compare the first half with the reversed second half
 * 4. (Optional) Restore the original list by reversing the second half again
 * 
 * Time Complexity: O(n) - We traverse the list a few times
 * Space Complexity: O(1) - We only use constant extra space
 */
