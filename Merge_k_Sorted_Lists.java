import java.util.PriorityQueue;

public class Merge_k_Sorted_Lists {

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

    public ListNode mergeKLists(ListNode[] lists) {
        if (lists == null || lists.length == 0) {
            return null;
        }

        // Min-Heap to store the current nodes of each list
        // PriorityQueue orders elements based on their value (smallest first)
        PriorityQueue<ListNode> minHeap = new PriorityQueue<>((a, b) -> a.val - b.val);

        // Add the head of each non-empty list to the heap
        for (ListNode node : lists) {
            if (node != null) {
                minHeap.offer(node);
            }
        }

        // Dummy node to simplify result list construction
        ListNode dummy = new ListNode(0);
        ListNode current = dummy;

        // Process the heap until it's empty
        while (!minHeap.isEmpty()) {
            // Extract the smallest node
            ListNode smallestNode = minHeap.poll();

            // Add it to the result list
            current.next = smallestNode;
            current = current.next;

            // If the extracted node has a next node, push it into the heap
            if (smallestNode.next != null) {
                minHeap.offer(smallestNode.next);
            }
        }

        return dummy.next;
    }


}
/**
 * Approach:
 * 1. Use a Min-Heap (PriorityQueue) to keep track of the current head of each
 * of the k linked lists.
 * 2. The heap is ordered by the node's value, so the top of the heap is always
 * the smallest available node among all lists.
 * 3. Initialize the heap by adding the head node of each non-empty list.
 * 4. While the heap is not empty:
 * - Remove the smallest node (top of the heap) and append it to the result
 * list.
 * - If the removed node has a next node, add that next node to the heap.
 * 5. This process effectively merges the lists in sorted order, akin to the
 * merge step in Merge Sort, but generalized for k lists.
 *
 * Complexity Analysis:
 * - Time Complexity: O(N log k)
 * - N is the total number of nodes across all lists.
 * - k is the number of linked lists.
 * - The heap size is at most k. Each insertion and deletion operation on the
 * heap takes O(log k).
 * - We perform these operations for every node, so total time is O(N log k).
 * - Space Complexity: O(k)
 * - The heap stores at most k nodes at any one time.
 * - The result list is just a rearrangement of existing nodes (O(1) extra space
 * excluding the output),
 * but the PriorityQueue takes O(k) space.
 */
