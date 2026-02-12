import java.util.Collections;
import java.util.PriorityQueue;

public class Find_Median_from_Data_Stream {

    private PriorityQueue<Integer> minHeap;
    private PriorityQueue<Integer> maxHeap;

    /** initialize your data structure here. */
    public Find_Median_from_Data_Stream() {
        // minHeap stores the larger half of the numbers
        minHeap = new PriorityQueue<>();
        // maxHeap stores the smaller half of the numbers
        maxHeap = new PriorityQueue<>(Collections.reverseOrder());
    }

    public void addNum(int num) {
        // Always add to maxHeap first
        maxHeap.offer(num);

        // Balance: maxHeap's largest element should not be greater than minHeap's
        // smallest
        if (!minHeap.isEmpty() && maxHeap.peek() > minHeap.peek()) {
            minHeap.offer(maxHeap.poll());
        }

        // Maintain size property: maxHeap size can be equal to or 1 greater than
        // minHeap size
        if (maxHeap.size() > minHeap.size() + 1) {
            minHeap.offer(maxHeap.poll());
        } else if (minHeap.size() > maxHeap.size()) {
            maxHeap.offer(minHeap.poll());
        }
    }

    public double findMedian() {
        if (maxHeap.size() > minHeap.size()) {
            return maxHeap.peek();
        } else if (minHeap.size() > maxHeap.size()) {
            return minHeap.peek();
        } else {
            return (maxHeap.peek() + minHeap.peek()) / 2.0;
        }
    }

    public static void main(String[] args) {
        Find_Median_from_Data_Stream medianFinder = new Find_Median_from_Data_Stream();

        // Example 1
        medianFinder.addNum(1); // arr = [1]
        medianFinder.addNum(2); // arr = [1, 2]
        System.out.println("Median after adding 1 and 2 (Expected: 1.5): " + medianFinder.findMedian());

        medianFinder.addNum(3); // arr = [1, 2, 3]
        System.out.println("Median after adding 3 (Expected: 2.0): " + medianFinder.findMedian());

        // Additional Test Case
        medianFinder.addNum(4); // arr = [1, 2, 3, 4]
        System.out.println("Median after adding 4 (Expected: 2.5): " + medianFinder.findMedian());

        medianFinder.addNum(5); // arr = [1, 2, 3, 4, 5]
        System.out.println("Median after adding 5 (Expected: 3.0): " + medianFinder.findMedian());
    }
}

/**
 * Approach:
 * 1. Two Heaps:
 * - Use a Max-Heap (`maxHeap`) to store the smaller half of the numbers.
 * - Use a Min-Heap (`minHeap`) to store the larger half of the numbers.
 * 2. Balancing:
 * - The size of the two heaps should be balanced.
 * - `maxHeap` can have at most one more element than `minHeap` (or vice versa,
 * but we stick to `maxHeap` >= `minHeap`).
 * - All elements in `maxHeap` must be less than or equal to elements in
 * `minHeap`.
 * 3. Add Number:
 * - Add `num` to `maxHeap`.
 * - If the largest element in `maxHeap` is greater than the smallest in
 * `minHeap`, move it to `minHeap`.
 * - Rebalance sizes if their difference > 1.
 * 4. Find Median:
 * - If `maxHeap` has more elements, the median is `maxHeap.peek()`.
 * - If `minHeap` has more elements, the median is `minHeap.peek()`.
 * - If sizes are equal, the median is the average of the tops of both heaps.
 *
 * Complexity Analysis:
 * - Time Complexity:
 * - addNum: O(log N) - due to heap insertion and deletion.
 * - findMedian: O(1) - accessing the top of the heaps is constant time.
 * - Space Complexity:
 * - O(N) - to store all the numbers in the heaps.
 */
