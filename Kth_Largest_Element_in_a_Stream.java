import java.util.PriorityQueue;

public class Kth_Largest_Element_in_a_Stream {

    private PriorityQueue<Integer> minHeap;
    private int k;

    public Kth_Largest_Element_in_a_Stream(int k, int[] nums) {
        this.k = k;
        // Min-Heap to store the k largest elements seen so far.
        // The smallest element in this heap (the root) is the kth largest overall.
        this.minHeap = new PriorityQueue<>();

        for (int num : nums) {
            add(num);
        }
    }

    public int add(int val) {
        // Add the new value to the heap
        minHeap.offer(val);

        // If the heap size exceeds k, remove the smallest element.
        // We only care about the k largest elements, so any element smaller than the
        // top k
        // is discarded.
        if (minHeap.size() > k) {
            minHeap.poll();
        }

        // The top of the min-heap is now the kth largest element.
        return minHeap.peek();
    }

    public static void main(String[] args) {
        int k = 3;
        int[] nums = { 4, 5, 8, 2 };
        Kth_Largest_Element_in_a_Stream kthLargest = new Kth_Largest_Element_in_a_Stream(k, nums);

        System.out.println("Initial stream: [4, 5, 8, 2], k=" + k);
        System.out.println("Add 3, return " + kthLargest.add(3)); // returns 4
        System.out.println("Add 5, return " + kthLargest.add(5)); // returns 5
        System.out.println("Add 10, return " + kthLargest.add(10)); // returns 5
        System.out.println("Add 9, return " + kthLargest.add(9)); // returns 8
        System.out.println("Add 4, return " + kthLargest.add(4)); // returns 8
    }
}
/**
 * Approach:
 * 1. Use a Min-Heap (PriorityQueue) to store the `k` largest elements seen in
 * the stream so far.
 * 2. Why a Min-Heap?
 * - A Min-Heap of size `k` keeps the smallest element at the root.
 * - If we maintain exactly `k` elements in the heap, the root (smallest of the
 * top k) is exactly the kth largest element of the entire stream.
 * 3. Constructor:
 * - Initialize the min-heap.
 * - Iterate through the initial numbers and call `add()` for each to populate
 * the heap.
 * 4. `add(int val)` method:
 * - Add the new value to the heap.
 * - If the heap size grows larger than `k`, remove the minimum element
 * (`poll()`). This ensures we only keep the `k` largest elements.
 * - Return the root of the heap (`peek()`), which represents the kth largest
 * element.
 *
 * Complexity Analysis:
 * - Time Complexity:
 * - Constructor: O(N log k), where N is the length of the initial array.
 * We perform N `add` operations, each taking O(log k).
 * - `add(int val)`: O(log k).
 * Heap insertion and deletion both take O(log k) time since the heap size is
 * capped at k.
 * - Space Complexity: O(k)
 * - The heap stores at most k + 1 elements.
 */
