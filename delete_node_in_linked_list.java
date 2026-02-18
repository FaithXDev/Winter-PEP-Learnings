
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
