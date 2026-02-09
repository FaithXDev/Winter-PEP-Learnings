
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

    /**
     * Helper method to print a linked list.
     */
    public static void printList(ListNode head) {
        StringBuilder sb = new StringBuilder("[");
        ListNode current = head;

        while (current != null) {
            sb.append(current.val);
            if (current.next != null) {
                sb.append(",");
            }
            current = current.next;
        }

        sb.append("]");
        System.out.println(sb.toString());
    }

    public static void main(String[] args) {
        Solution solution = new Solution();

        // Test Case 1: [1,2,2,1] -> true
        System.out.println("Test Case 1:");
        ListNode head1 = createList(new int[] { 1, 2, 2, 1 });
        System.out.print("Input:  ");
        printList(createList(new int[] { 1, 2, 2, 1 }));
        boolean result1 = solution.isPalindrome(head1);
        System.out.println("Output: " + result1);
        System.out.println("Expected: true");
        System.out.println();

        // Test Case 2: [1,2] -> false
        System.out.println("Test Case 2:");
        ListNode head2 = createList(new int[] { 1, 2 });
        System.out.print("Input:  ");
        printList(createList(new int[] { 1, 2 }));
        boolean result2 = solution.isPalindrome(head2);
        System.out.println("Output: " + result2);
        System.out.println("Expected: false");
        System.out.println();

        // Test Case 3: Single element [1] -> true
        System.out.println("Test Case 3:");
        ListNode head3 = createList(new int[] { 1 });
        System.out.print("Input:  ");
        printList(createList(new int[] { 1 }));
        boolean result3 = solution.isPalindrome(head3);
        System.out.println("Output: " + result3);
        System.out.println("Expected: true");
        System.out.println();

        // Test Case 4: Odd number of elements [1,2,3,2,1] -> true
        System.out.println("Test Case 4:");
        ListNode head4 = createList(new int[] { 1, 2, 3, 2, 1 });
        System.out.print("Input:  ");
        printList(createList(new int[] { 1, 2, 3, 2, 1 }));
        boolean result4 = solution.isPalindrome(head4);
        System.out.println("Output: " + result4);
        System.out.println("Expected: true");
        System.out.println();

        // Test Case 5: Non-palindrome [1,2,3,4,5] -> false
        System.out.println("Test Case 5:");
        ListNode head5 = createList(new int[] { 1, 2, 3, 4, 5 });
        System.out.print("Input:  ");
        printList(createList(new int[] { 1, 2, 3, 4, 5 }));
        boolean result5 = solution.isPalindrome(head5);
        System.out.println("Output: " + result5);
        System.out.println("Expected: false");
        System.out.println();

        // Test Case 6: Empty list -> true
        System.out.println("Test Case 6:");
        ListNode head6 = createList(new int[] {});
        System.out.print("Input:  ");
        printList(createList(new int[] {}));
        boolean result6 = solution.isPalindrome(head6);
        System.out.println("Output: " + result6);
        System.out.println("Expected: true");

        System.out.println("\n✅ All test cases completed!");
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
