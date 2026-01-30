public class Bubble_Sort {
    /**
     * Sorts an array of integers using the Bubble Sort algorithm.
     * 
     * @param arr The array to be sorted
     */
    public static void bubbleSort(int[] arr) {
        int n = arr.length;
        boolean swapped;

        // Loop through all array elements
        for (int i = 0; i < n - 1; i++) {
            swapped = false;

            // Last i elements are already in place
            for (int j = 0; j < n - i - 1; j++) {
                // Traverse the array from 0 to n-i-1
                // Swap if the element found is greater than the next element
                if (arr[j] > arr[j + 1]) {
                    int temp = arr[j];
                    arr[j] = arr[j + 1];
                    arr[j + 1] = temp;
                    swapped = true;
                }
            }

            // If no two elements were swapped by inner loop, then break
            if (!swapped) {
                break;
            }
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
        int[] arr1 = { 64, 34, 25, 12, 22, 11, 90 };
        System.out.println("Original Array 1:");
        printArray(arr1);
        bubbleSort(arr1);
        System.out.println("Sorted Array 1:");
        printArray(arr1);
        System.out.println();

        // Test Case 2: Already sorted array (Best Case)
        int[] arr2 = { 1, 2, 3, 4, 5 };
        System.out.println("Original Array 2 (Sorted):");
        printArray(arr2);
        bubbleSort(arr2);
        System.out.println("Sorted Array 2:");
        printArray(arr2);
        System.out.println();

        // Test Case 3: Reverse sorted array (Worst Case)
        int[] arr3 = { 5, 4, 3, 2, 1 };
        System.out.println("Original Array 3 (Reverse):");
        printArray(arr3);
        bubbleSort(arr3);
        System.out.println("Sorted Array 3:");
        printArray(arr3);
        System.out.println();

        // Test Case 4: Array with duplicates
        int[] arr4 = { 3, 1, 4, 1, 5, 9, 2, 6, 5, 3, 5 };
        System.out.println("Original Array 4 (Duplicates):");
        printArray(arr4);
        bubbleSort(arr4);
        System.out.println("Sorted Array 4:");
        printArray(arr4);
    }
}

/*
 * Approach:
 * 1. Start with the first element (index 0) and compare it with the next
 * element (index 1).
 * 2. If the current element is greater than the next element, swap them.
 * 3. Move to the next pair (index 1 and index 2) and repeat the comparison and
 * swap if needed.
 * 4. Continue this process until the end of the array is reached. After the
 * first pass, the largest element will be at the last position.
 * 5. Repeat the entire process for the remaining unsorted part of the array
 * (n-1 times).
 * 6. Optimization: Use a 'swapped' flag to track if any swap happened in a
 * pass. If no swap occurs, the array is already sorted, and the loop can be
 * terminated early.
 * 
 * Time Complexity:
 * - Best Case: O(n) - When the array is already sorted, the algorithm iterates
 * through the array once and breaks as 'swapped' remains false.
 * - Average Case: O(n^2) - As we have nested loops.
 * - Worst Case: O(n^2) - When the array is sorted in descending order,
 * requiring maximum comparisons and swaps.
 * 
 * Space Complexity:
 * - O(1) - This is an in-place sorting algorithm, requiring only a constant
 * amount of extra space for variables.
 */
