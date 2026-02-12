import java.util.Collections;
import java.util.PriorityQueue;

public class Last_Stone_Weight {

    public int lastStoneWeight(int[] stones) {
        // Create a Max-Heap using PriorityQueue with reverse order comparator
        PriorityQueue<Integer> maxHeap = new PriorityQueue<>(Collections.reverseOrder());

        // Add all stones to the max heap
        for (int stone : stones) {
            maxHeap.offer(stone);
        }

        // Process the stones until one or none is left
        while (maxHeap.size() > 1) {
            int stone1 = maxHeap.poll(); // Heaviest stone
            int stone2 = maxHeap.poll(); // Second heaviest stone

            if (stone1 != stone2) {
                // Determine the new weight of the heavier stone
                maxHeap.offer(stone1 - stone2);
            }
            // If stone1 == stone2, both are destroyed (nothing added back)
        }

        // Return the weight of the last stone, or 0 if no stones are left
        return maxHeap.isEmpty() ? 0 : maxHeap.peek();
    }

    public static void main(String[] args) {
        Last_Stone_Weight solution = new Last_Stone_Weight();

        // Test Case 1
        int[] stones1 = { 2, 7, 4, 1, 8, 1 };
        System.out.println("Last stone weight for [2,7,4,1,8,1] (Expected: 1): " + solution.lastStoneWeight(stones1));

        // Test Case 2
        int[] stones2 = { 1 };
        System.out.println("Last stone weight for [1] (Expected: 1): " + solution.lastStoneWeight(stones2));

        // Test Case 3: Empty (conceptually, though constraints say 1 <= stones.length)
        // If we conceptually had [2, 2], result is 0
        int[] stones3 = { 2, 2 };
        System.out.println("Last stone weight for [2, 2] (Expected: 0): " + solution.lastStoneWeight(stones3));
    }
}
/**
 * Approach:
 * 1. Use a Max-Heap to store the weights of the stones. This allows us to
 * efficiently access the two heaviest stones.
 * In Java, PriorityQueue is a Min-Heap by default, so we use
 * `Collections.reverseOrder()`.
 * 2. Add all stones to the Max-Heap.
 * 3. While the heap has more than one stone:
 * - Remove the two heaviest stones (say y and x, where y >= x).
 * - If x != y, the new stone weight is y - x. Add this back to the heap.
 * - If x == y, both are destroyed, so nothing is added back.
 * 4. If the heap is not empty, the remaining element is the last stone weight.
 * Otherwise, return 0.
 *
 * Complexity Analysis:
 * - Time Complexity: O(N log N)
 * - Building the heap takes O(N) (or O(N log N) with repeated insertions).
 * - In the worst case, we perform about N iterations. In each iteration, we do
 * heap poll and offer operations which take O(log N).
 * - Total time: O(N log N).
 * - Space Complexity: O(N)
 * - To store the stones in the priority queue.
 */
