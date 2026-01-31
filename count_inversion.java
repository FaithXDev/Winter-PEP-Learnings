public class count_inversion {
    /**
     * Calculates the number of inversions in an array.
     * An inversion pair is defined as (i, j) such that i < j and arr[i] > arr[j].
     * 
     * This implementation uses a variation of Merge Sort to count inversions
     * in O(n log n) time complexity.
     * 
     * @param arr The input array
     * @return The total count of inversions
     */
    public static int inversionCount(int[] arr) {
        if (arr == null || arr.length <= 1) {
            return 0;
        }
        return mergeSort(arr, 0, arr.length - 1);
    }

    private static int mergeSort(int[] arr, int left, int right) {
        int count = 0;

        if (left < right) {
            int mid = left + (right - left) / 2;

            count += mergeSort(arr, left, mid);
            count += mergeSort(arr, mid + 1, right);
            count += merge(arr, left, mid, right);
        }

        return count;
    }

    private static int merge(int[] arr, int left, int mid, int right) {
        int[] temp = new int[right - left + 1];

        int i = left;
        int j = mid + 1;
        int k = 0;
        int inversions = 0;

        while (i <= mid && j <= right) {
            if (arr[i] <= arr[j]) {
                temp[k++] = arr[i++];
            } else {
                // If arr[i] > arr[j], then arr[i] and all elements appearing
                // after it in the left subarray are also greater than arr[j]
                // because the left subarray is sorted.
                temp[k++] = arr[j++];
                inversions += (mid - i + 1);
            }
        }

        // Copy remaining elements of the left subarray
        while (i <= mid) {
            temp[k++] = arr[i++];
        }

        // Copy remaining elements of the right subarray
        while (j <= right) {
            temp[k++] = arr[j++];
        }

        // Copy the sorted elements back to the original array
        for (int p = 0; p < temp.length; p++) {
            arr[left + p] = temp[p];
        }

        return inversions;
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
        // Test Case 1: Standard case
        int[] arr1 = { 8, 4, 2, 1 };
        System.out.println("Test Case 1:");
        System.out.print("Original: ");
        printArray(arr1);
        int inv1 = inversionCount(arr1);
        System.out.println("Inversions: " + inv1); // Expected: 6 (8>4, 8>2, 8>1, 4>2, 4>1, 2>1)
        System.out.println();

        // Test Case 2: Already sorted
        int[] arr2 = { 1, 2, 3, 4 };
        System.out.println("Test Case 2 (Sorted):");
        System.out.print("Original: ");
        printArray(arr2);
        int inv2 = inversionCount(arr2);
        System.out.println("Inversions: " + inv2); // Expected: 0
        System.out.println();

        // Test Case 3: Reverse sorted
        int[] arr3 = { 5, 4, 3, 2, 1 };
        System.out.println("Test Case 3 (Reverse Sorted):");
        System.out.print("Original: ");
        printArray(arr3);
        int inv3 = inversionCount(arr3);
        System.out.println("Inversions: " + inv3); // Expected: 10
        System.out.println();

        // Test Case 4: Duplicates
        // Duplicates (arr[i] == arr[j]) are not inversions.
        int[] arr4 = { 2, 2, 1, 1 }; // (2,1), (2,1), (2,1), (2,1) ??
        // Pairs:
        // (0, 2): 2 > 1 -> Inv
        // (0, 3): 2 > 1 -> Inv
        // (1, 2): 2 > 1 -> Inv
        // (1, 3): 2 > 1 -> Inv
        // Total 4.
        System.out.println("Test Case 4 (Duplicates):");
        System.out.print("Original: ");
        printArray(arr4);
        int inv4 = inversionCount(arr4);
        System.out.println("Inversions: " + inv4);
        System.out.println();
    }
}

/*
 * Approach:
 * We use the Merge Sort algorithm to count inversions.
 * This is a classic application of the "Divide and Conquer" strategy.
 * 
 * 1. Divide the array into two halves.
 * 2. Recursively count inversions in the left half and right half.
 * 3. Count "split inversions" where one element is in the left half and the
 * other is in the right half.
 * - This happens during the merge step.
 * - If arr[i] > arr[j] (where i is in left half, j is in right half), then
 * arr[i] is greater than arr[j].
 * Since the left half is sorted, ALL elements from i to mid are also greater
 * than arr[j].
 * So we add (mid - i + 1) to the inversion count.
 * 
 * Complexity Analysis:
 * - Time Complexity: O(N log N)
 * - Same as Merge Sort.
 * 
 * - Space Complexity: O(N)
 * - Used for the temporary array during merge.
 */
