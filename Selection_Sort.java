public class Selection_Sort {
    /**
     * Sorts an array of integers using the Selection Sort algorithm.
     * 
     * @param arr The array to be sorted
     */
    public static void selectionSort(int[] arr) {
        int n = arr.length;

        // One by one move boundary of unsorted subarray
        for (int i = 0; i < n - 1; i++) {
            // Find the minimum element in unsorted array
            int min_idx = i;
            for (int j = i + 1; j < n; j++) {
                if (arr[j] < arr[min_idx]) {
                    min_idx = j;
                }
            }

            // Swap the found minimum element with the first element
            int temp = arr[min_idx];
            arr[min_idx] = arr[i];
            arr[i] = temp;
        }
    }


}

/*
 * Approach:
 * 1. The algorithm maintains two subarrays in a given array:
 *    - The subarray which is already sorted.
 *    - Remaining subarray which is unsorted.
 * 2. In every iteration of the selection sort, the minimum element (considering ascending order)
 *    from the unsorted subarray is picked and moved to the sorted subarray.
 * 3. Specifically, valid for index i from 0 to n-1:
 *    a. Find the smallest element in the sub-array arr[i...n-1].
 *    b. Swap it with the element at arr[i].
 *    c. Increment i to point to the next position.
 * 
 * Time Complexity:
 * - Best Case: O(n^2) - The comparisons are always made to find the minimum.
 * - Average Case: O(n^2)
 * - Worst Case: O(n^2)
 * 
 * Space Complexity:
 * - O(1) - In-place sorting algorithm.
 */
