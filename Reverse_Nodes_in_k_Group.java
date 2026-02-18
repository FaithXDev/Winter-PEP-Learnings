public class Reverse_Nodes_in_k_Group {

    // Definition for singly-linked list.
    public static class ListNode {
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

    public ListNode reverseKGroup(ListNode head, int k) {
        if (head == null || k == 1) {
            return head;
        }

        // Count the total number of nodes in the linked list
        ListNode temp = head;
        int count = 0;
        while (temp != null) {
            count++;
            temp = temp.next;
        }

        // Dummy node initialization to handle edge cases easily
        ListNode dummy = new ListNode(0);
        dummy.next = head;

        ListNode pre = dummy;
        ListNode curr = dummy;
        ListNode nex = dummy;

        // Process groups of size k
        while (count >= k) {
            curr = pre.next;
            nex = curr.next;

            // Reverse k-1 links to reverse k nodes
            for (int i = 1; i < k; i++) {
                curr.next = nex.next;
                nex.next = pre.next;
                pre.next = nex;
                nex = curr.next;
            }

            // Move pre to the end of the reversed group
            pre = curr;
            count -= k;
        }

        return dummy.next;
    }


}
/**
 * Approach:
 * 1. Calculate the total length of the linked list.
 * This allows us to determine how many full groups of `k` nodes exist.
 * 2. Use a dummy node pointing to the head to handle head reversals simplify
 * pointer manipulation.
 * 3. Use three pointers: `pre`, `curr`, and `nex`.
 * - `pre`: Points to the node immediately before the group currently being
 * reversed.
 * - `curr`: Points to the first node of the group being reversed (which will
 * become the last node after reversal).
 * - `nex`: Points to the node to be moved to the front of the group.
 * 4. Iterate through the list as long as there are at least `k` nodes remaining
 * (`count >= k`):
 * - Perform the actual reversal logic inside the group using the `curr` and
 * `nex` pointers.
 * - This loop runs `k-1` times for each group.
 * - Update `pre` to `curr` after the group is reversed to prepare for the next
 * group.
 * - Decrement `count` by `k`.
 * 5. If `count < k`, the remaining nodes are left as is.
 *
 * Complexity Analysis:
 * - Time Complexity: O(N)
 * - We traverse the list once to count nodes (N steps).
 * - We traverse the list again to reverse groups (N steps).
 * - Total time is O(2N) ~ O(N).
 * - Space Complexity: O(1)
 * - We only use a few pointers (`dummy`, `pre`, `curr`, `nex`) for storage,
 * regardless of input size.
 */
