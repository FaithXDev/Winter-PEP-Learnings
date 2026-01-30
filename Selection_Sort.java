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

    /**
     * Helper method to print the array.
     * 
     * @param arr The array to print
     */
    public static void printArray(int[] arr) {
        for (int value : arr) {
            System.out.print(value + " ");
        }
        System.out.println();
    }

    public static void main(String[] args) {
        // Test Case 1: Unsorted array
        int[] arr1 = { 64, 25, 12, 22, 11 };
        System.out.println("Original Array 1:");
        printArray(arr1);
        selectionSort(arr1);
        System.out.println("Sorted Array 1:");
        printArray(arr1);
        System.out.println();

        // Test Case 2: Already sorted array
        int[] arr2 = { 11, 12, 22, 25, 64 };
        System.out.println("Original Array 2 (Sorted):");
        printArray(arr2);
        selectionSort(arr2);
        System.out.println("Sorted Array 2:");
        printArray(arr2);
        System.out.println();

        // Test Case 3: Reverse sorted array
        int[] arr3 = { 64, 25, 22, 12, 11 };
        System.out.println("Original Array 3 (Reverse):");
        printArray(arr3);
        selectionSort(arr3);
        System.out.println("Sorted Array 3:");
        printArray(arr3);
        System.out.println();

        // Test Case 4: Array with duplicates
        int[] arr4 = { 3, 1, 4, 1, 5, 9, 2, 6, 5 };
        System.out.println("Original Array 4 (Duplicates):");
        printArray(arr4);
        selectionSort(arr4);
        System.out.println("Sorted Array 4:");
        printArray(arr4);
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
