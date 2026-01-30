public class quick_sort {

    /**
     * Main function that sorts array[low..high] using partition()
     * 
     * @param arr  The array to use
     * @param low  The starting index
     * @param high The ending index
     */
    public static void quickSort(int[] arr, int low, int high) {
        if (low < high) {
            // pi is partitioning index, arr[pi] is now at right place
            int pi = partition(arr, low, high);

            // Recursively sort elements before partition and after partition
            quickSort(arr, low, pi - 1);
            quickSort(arr, pi + 1, high);
        }
    }

    /**
     * This function takes the last element as pivot, places the pivot element at
     * its
     * correct position in sorted array, and places all smaller (smaller than pivot)
     * to left of pivot and all greater elements to right of pivot
     * 
     * @param arr  The array to partition
     * @param low  The starting index
     * @param high The ending index (pivot)
     * @return The partition index
     */
    private static int partition(int[] arr, int low, int high) {
        int pivot = arr[high];
        int i = (low - 1); // index of smaller element

        for (int j = low; j < high; j++) {
            // If current element is smaller than or equal to pivot
            if (arr[j] <= pivot) {
                i++;

                // swap arr[i] and arr[j]
                int temp = arr[i];
                arr[i] = arr[j];
                arr[j] = temp;
            }
        }

        // swap arr[i+1] and arr[high] (or pivot)
        int temp = arr[i + 1];
        arr[i + 1] = arr[high];
        arr[high] = temp;

        return i + 1;
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
        int[] arr1 = { 10, 7, 8, 9, 1, 5 };
        System.out.println("Original Array 1:");
        printArray(arr1);
        quickSort(arr1, 0, arr1.length - 1);
        System.out.println("Sorted Array 1:");
        printArray(arr1);
        System.out.println();

        // Test Case 2: Already sorted array
        int[] arr2 = { 1, 2, 3, 4, 5 };
        System.out.println("Original Array 2 (Sorted):");
        printArray(arr2);
        quickSort(arr2, 0, arr2.length - 1);
        System.out.println("Sorted Array 2:");
        printArray(arr2);
        System.out.println();

        // Test Case 3: Reverse sorted array
        int[] arr3 = { 5, 4, 3, 2, 1 };
        System.out.println("Original Array 3 (Reverse):");
        printArray(arr3);
        quickSort(arr3, 0, arr3.length - 1);
        System.out.println("Sorted Array 3:");
        printArray(arr3);
        System.out.println();

        // Test Case 4: Array with duplicates
        int[] arr4 = { 3, 1, 4, 1, 5, 9, 2, 6, 5 };
        System.out.println("Original Array 4 (Duplicates):");
        printArray(arr4);
        quickSort(arr4, 0, arr4.length - 1);
        System.out.println("Sorted Array 4:");
        printArray(arr4);
    }
}

/*
 * Approach:
 * QuickSort is a divide-and-conquer algorithm.
 * 1. It picks an element as a 'pivot'. There are different ways to pick a pivot
 * (first, last, random, median).
 * Here, we choose the last element as the pivot.
 * 2. It partitions the given array around the picked pivot.
 * 3. Partitioning process:
 * - All elements smaller than the pivot are moved to the left of the pivot.
 * - All elements greater than the pivot are moved to the right of the pivot.
 * - The pivot is then placed in its correct position.
 * 4. The algorithm then recursively sorts the sub-arrays on the left and right
 * of the pivot.
 * 
 * Time Complexity:
 * - Best Case: O(n log n) - Occurs when the pivot always picks the middle
 * element.
 * - Average Case: O(n log n)
 * - Worst Case: O(n^2) - Occurs when the pivot is always the smallest or
 * largest element (e.g., sorted or reverse sorted array).
 * 
 * Space Complexity:
 * - O(log n) - Due to recursive stack space (average case).
 * - O(n) in worst case stack depth.
 */
