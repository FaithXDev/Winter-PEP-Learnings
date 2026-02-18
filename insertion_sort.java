public class insertion_sort {
    /**
     * Sorts an array of integers using the Insertion Sort algorithm.
     * 
     * @param arr The array to be sorted
     */
    public static void insertionSort(int[] arr) {
        int n = arr.length;
        for (int i = 1; i < n; i++) {
            int key = arr[i];
            int j = i - 1;

            /*
             * Move elements of arr[0..i-1], that are greater than key,
             * to one position ahead of their current position
             */
            while (j >= 0 && arr[j] > key) {
                arr[j + 1] = arr[j];
                j = j - 1;
            }
            arr[j + 1] = key;
        }
    }


}

/*
 * Approach:
 * 1. The algorithm conceptually splits the array into two parts: a sorted
 * subarray and an unsorted subarray.
 * 2. Initially, the sorted subarray consists of just the first element.
 * 3. We iterate from the second element (index 1) to the end of the array.
 * 4. In each iteration, we select the 'key' (the current element from the
 * unsorted part).
 * 5. We compare the 'key' with elements in the sorted subarray (from right to
 * left).
 * 6. We shift elements of the sorted subarray that are greater than the 'key'
 * to the right.
 * 7. We insert the 'key' into its correct position in the sorted subarray.
 * 
 * Time Complexity:
 * - Best Case: O(n) - When the array is already sorted (only comparisons, no
 * shifts).
 * - Average Case: O(n^2)
 * - Worst Case: O(n^2) - When the array is sorted in reverse order.
 * 
 * Space Complexity:
 * - O(1) - In-place sorting algorithm.
 */
