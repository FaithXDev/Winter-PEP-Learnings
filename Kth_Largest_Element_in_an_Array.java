import java.util.PriorityQueue;
import java.util.Arrays;

public class Kth_Largest_Element_in_an_Array {

    public int findKthLargest(int[] nums, int k) {
        // Min-heap to store the k largest elements
        PriorityQueue<Integer> minHeap = new PriorityQueue<>();

        for (int num : nums) {
            minHeap.add(num);

            // If the heap size exceeds k, remove the smallest element
            if (minHeap.size() > k) {
                minHeap.poll();
            }
        }

        // The root of the min-heap is the kth largest element
        return minHeap.peek();
    }

    public int findKthLargestSorting(int[] nums, int k) {
        Arrays.sort(nums);
        return nums[nums.length - k];
    }

}

/*
 * Approach: Min-Heap
 * 
 * We can use a Min-Heap to keep track of the k largest elements encountered so
 * far.
 * The size of the heap will be maintained at exactly k.
 * 
 * Algorithm:
 * 1. Initialize a Min-Heap (PriorityQueue in Java).
 * 2. Iterate through each element in the array.
 * 3. Add the current element to the heap.
 * 4. If the heap size exceeds k, remove the smallest element (the root).
 * This ensures that we always discard the smallest elements and keep the
 * largest k elements.
 * 5. After processing all elements, the root of the heap (the smallest of the k
 * largest)
 * is the kth largest element in the entire array.
 * 
 * Time Complexity: O(N log K)
 * - We iterate through N elements.
 * - Adding to/removing from the heap takes O(log K) time.
 * - Total time is O(N log K).
 * 
 * Space Complexity: O(K)
 * - To store the k elements in the heap.
 */

/*
 * Approach 2: Sorting
 * 
 * Simply sort the array and pick the element at index N-k.
 * 
 * Time Complexity: O(N log N)
 * Space Complexity: O(1) or O(log N) depending on the sorting algorithm.
 */
