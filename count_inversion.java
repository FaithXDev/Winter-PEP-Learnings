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
