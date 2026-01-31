public class Sort_an_array {
    /**
     * Sorts an array of integers in ascending order.
     * This implementation uses the Merge Sort algorithm, which provides O(n log n)
     * time complexity.
     * 
     * @param nums The array to be sorted
     * @return The sorted array
     */
    public int[] sortArray(int[] nums) {
        if (nums == null || nums.length <= 1) {
            return nums;
        }
        mergeSort(nums, 0, nums.length - 1);
        return nums;
    }

    /**
     * Recursively divides the array into halves and merges them.
     */
    private void mergeSort(int[] nums, int left, int right) {
        if (left < right) {
            int mid = left + (right - left) / 2;

            // Sort first and second halves
            mergeSort(nums, left, mid);
            mergeSort(nums, mid + 1, right);

            // Merge the sorted halves
            merge(nums, left, mid, right);
        }
    }

    /**
     * Merges two sorted subarrays.
     * First subarray is arr[left..mid]
     * Second subarray is arr[mid+1..right]
     */
    private void merge(int[] nums, int left, int mid, int right) {
        // Find sizes of two subarrays to be merged
        int n1 = mid - left + 1;
        int n2 = right - mid;

        // Create temp arrays
        int[] L = new int[n1];
        int[] R = new int[n2];

        // Copy data to temp arrays
        for (int i = 0; i < n1; ++i)
            L[i] = nums[left + i];
        for (int j = 0; j < n2; ++j)
            R[j] = nums[mid + 1 + j];

        // Merge the temp arrays

        // Initial indexes of first and second subarrays
        int i = 0, j = 0;

        // Initial index of merged subarray array
        int k = left;
        while (i < n1 && j < n2) {
            if (L[i] <= R[j]) {
                nums[k] = L[i];
                i++;
            } else {
                nums[k] = R[j];
                j++;
            }
            k++;
        }

        // Copy remaining elements of L[] if any
        while (i < n1) {
            nums[k] = L[i];
            i++;
            k++;
        }

        // Copy remaining elements of R[] if any
        while (j < n2) {
            nums[k] = R[j];
            j++;
            k++;
        }
    }

    public static void printArray(int[] arr) {
        System.out.print("[");
        for (int i = 0; i < arr.length; i++) {
            System.out.print(arr[i]);
            if (i < arr.length - 1)
                System.out.print(", ");
        }
        System.out.println("]");
    }

    public static void main(String[] args) {
        Sort_an_array solution = new Sort_an_array();

        // Test Case 1: Random unsorted array
        int[] nums1 = { 5, 2, 3, 1 };
        System.out.println("Test Case 1:");
        System.out.print("Original: ");
        printArray(nums1);
        solution.sortArray(nums1);
        System.out.print("Sorted:   ");
        printArray(nums1);
        System.out.println();

        // Test Case 2: Array with duplicates
        int[] nums2 = { 5, 1, 1, 2, 0, 0 };
        System.out.println("Test Case 2:");
        System.out.print("Original: ");
        printArray(nums2);
        solution.sortArray(nums2);
        System.out.print("Sorted:   ");
        printArray(nums2);
        System.out.println();

        // Test Case 3: Already sorted array
        int[] nums3 = { 1, 2, 3, 4, 5 };
        System.out.println("Test Case 3:");
        System.out.print("Original: ");
        printArray(nums3);
        solution.sortArray(nums3);
        System.out.print("Sorted:   ");
        printArray(nums3);
        System.out.println();

        // Test Case 4: Reverse sorted array
        int[] nums4 = { 9, 8, 7, 6, 5 };
        System.out.println("Test Case 4:");
        System.out.print("Original: ");
        printArray(nums4);
        solution.sortArray(nums4);
        System.out.print("Sorted:   ");
        printArray(nums4);
        System.out.println();
    }
}

/*
 * Approach:
 * We use the Merge Sort algorithm to sort the array.
 * Implement "Divide and Conquer" strategy.
 * 
 * 1. Divide: Calculate the middle index of the array and recursively divide
 * the array into two halves until the sub-arrays contain a single element.
 * 2. Conquer: Sort each half recursively.
 * 3. Combine: Merge the sorted halves back together to form the sorted array.
 * 
 * We use a helper 'merge' function to combine two sorted sub-arrays into a
 * single sorted array.
 * 
 * Complexity Analysis:
 * - Time Complexity: O(N log N)
 * - Dividing the array takes log N steps (base 2).
 * - Merging the subarrays takes O(N) time at each level of recursion.
 * - Overall time complexity is N * log N.
 * - This is consistently O(N log N) for best, average, and worst cases,
 * making it more stable than Quick Sort for worst-case scenarios.
 * 
 * - Space Complexity: O(N)
 * - We use temporary arrays (L and R) during the merge process.
 * - The recursion stack also takes O(log N) space.
 * - Total space complexity is dominated by the auxiliary arrays, O(N).
 */
