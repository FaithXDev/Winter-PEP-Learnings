
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

        // Test Case 1: [1,2,3,4] -> [1,4,2,3]
        System.out.println("Test Case 1:");
        ListNode head1 = createList(new int[] { 1, 2, 3, 4 });
        System.out.print("Input:  ");
        printList(createList(new int[] { 1, 2, 3, 4 }));
        solution.reorderList(head1);
        System.out.print("Output: ");
        printList(head1);
        System.out.println("Expected: [1,4,2,3]");
        System.out.println();

        // Test Case 2: [1,2,3,4,5] -> [1,5,2,4,3]
        System.out.println("Test Case 2:");
        ListNode head2 = createList(new int[] { 1, 2, 3, 4, 5 });
        System.out.print("Input:  ");
        printList(createList(new int[] { 1, 2, 3, 4, 5 }));
        solution.reorderList(head2);
        System.out.print("Output: ");
        printList(head2);
        System.out.println("Expected: [1,5,2,4,3]");
        System.out.println();

        // Test Case 3: Single element [1] -> [1]
        System.out.println("Test Case 3:");
        ListNode head3 = createList(new int[] { 1 });
        System.out.print("Input:  ");
        printList(createList(new int[] { 1 }));
        solution.reorderList(head3);
        System.out.print("Output: ");
        printList(head3);
        System.out.println("Expected: [1]");
        System.out.println();

        // Test Case 4: Two elements [1,2] -> [1,2]
        System.out.println("Test Case 4:");
        ListNode head4 = createList(new int[] { 1, 2 });
        System.out.print("Input:  ");
        printList(createList(new int[] { 1, 2 }));
        solution.reorderList(head4);
        System.out.print("Output: ");
        printList(head4);
        System.out.println("Expected: [1,2]");
        System.out.println();

        // Test Case 5: Longer list [1,2,3,4,5,6,7] -> [1,7,2,6,3,5,4]
        System.out.println("Test Case 5:");
        ListNode head5 = createList(new int[] { 1, 2, 3, 4, 5, 6, 7 });
        System.out.print("Input:  ");
        printList(createList(new int[] { 1, 2, 3, 4, 5, 6, 7 }));
        solution.reorderList(head5);
        System.out.print("Output: ");
        printList(head5);
        System.out.println("Expected: [1,7,2,6,3,5,4]");

        System.out.println("\n✅ All test cases completed!");
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