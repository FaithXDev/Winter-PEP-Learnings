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

    public static void main(String[] args) {
        Kth_Largest_Element_in_an_Array solver = new Kth_Largest_Element_in_an_Array();

        // Test Case 1
        int[] nums1 = { 3, 2, 1, 5, 6, 4 };
        int k1 = 2;
        System.out.println("Test Case 1:");
        System.out.println("Input: nums = " + Arrays.toString(nums1) + ", k = " + k1);
        System.out.println("Output: " + solver.findKthLargest(nums1, k1)); // Expected: 5
        System.out.println();

        // Test Case 2
        int[] nums2 = { 3, 2, 3, 1, 2, 4, 5, 5, 6 };
        int k2 = 4;
        System.out.println("Test Case 2:");
        System.out.println("Input: nums = " + Arrays.toString(nums2) + ", k = " + k2);
        System.out.println("Output: " + solver.findKthLargest(nums2, k2)); // Expected: 4
        System.out.println();

        // Edge Case: k = 1 (Largest element)
        int[] nums3 = { 10, 20, 30 };
        int k3 = 1;
        System.out.println("Test Case 3 (Largest Element):");
        System.out.println("Input: nums = " + Arrays.toString(nums3) + ", k = " + k3);
        System.out.println("Output: " + solver.findKthLargest(nums3, k3)); // Expected: 30
        System.out.println();

        // Edge Case: k = length of array (Smallest element)
        int[] nums4 = { 10, 20, 30 };
        int k4 = 3;
        System.out.println("Test Case 4 (Smallest Element):");
        System.out.println("Input: nums = " + Arrays.toString(nums4) + ", k = " + k4);
        System.out.println("Output: " + solver.findKthLargest(nums4, k4)); // Expected: 10
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