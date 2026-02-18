/**
 * Definition for singly-linked list.
 */
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

public class Add_Two_Number {
    public ListNode addTwoNumbers(ListNode l1, ListNode l2) {
        ListNode dummy = new ListNode(0); // Dummy node to simplify result list creation
        ListNode curr = dummy;
        int carry = 0;

        // Iterate while there are nodes in l1 or l2, or there is a remaining carry
        while (l1 != null || l2 != null || carry != 0) {
            int val1 = (l1 != null) ? l1.val : 0;
            int val2 = (l2 != null) ? l2.val : 0;
            int sum = val1 + val2 + carry;

            // Calculate new digit and carry
            carry = sum / 10;
            curr.next = new ListNode(sum % 10); // Create new node with the digit
            curr = curr.next; // Move current pointer

            // Move l1 and l2 pointers if they exist
            if (l1 != null)
                l1 = l1.next;
            if (l2 != null)
                l2 = l2.next;
        }

        return dummy.next; // Return the head of the result list (skipping dummy)
    }

}

/*
 * EXPLANATION:
 * 
 * Approach: Elementary Math (Digit-by-Digit Addition)
 * ---------------------------------------------------
 * The problem asks to add two numbers represented by linked lists where digits
 * are stored in reverse order.
 * This reverse order actually simplifies things because the head of the list is
 * the least significant digit (ones place),
 * which is exactly where addition starts.
 * 
 * 1. Pointers & Traverse:
 * - We initialize two pointers, `l1` and `l2`, to head of the input lists.
 * - We use a `carry` variable (initially 0) to store the carry-over from the
 * summation of previous digits.
 * - A `dummy` head node is used to simplify the construction of the result
 * list.
 * 
 * 2. Addition Loop:
 * - We iterate as long as `l1` is not null OR `l2` is not null OR `carry` is
 * not zero.
 * - At each step, we take values from `l1` and `l2` (if null, value is 0).
 * - `sum = val1 + val2 + carry`
 * - The new digit to be stored is `sum % 10`.
 * - The new carry is `sum / 10`.
 * - We create a new node with the digit and attach it to our result list.
 * 
 * 3. Result:
 * - The result list starts at `dummy.next`.
 * 
 * Complexity Analysis:
 * --------------------
 * Time Complexity: O(max(N, M))
 * - We iterate through the linked lists at most max(N, M) + 1 times, where N
 * and M are lengths of the lists (the +1 comes from a possible final carry).
 * 
 * Space Complexity: O(max(N, M))
 * - The new list will have at most max(N, M) + 1 nodes.
 */
