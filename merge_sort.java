public class merge_sort {
    /**
     * Merges two sorted arrays nums1 and nums2 into nums1 as one sorted array.
     * This implementation solves the "Merge Sorted Array" problem (LeetCode 88).
     * 
     * Note: This is NOT the standard Merge Sort algorithm which sorts a single
     * unsorted array.
     * This function merges two already sorted arrays.
     * 
     * @param nums1 The first sorted array, with extra space at the end to hold
     *              nums2
     * @param m     The number of initial elements in nums1
     * @param nums2 The second sorted array
     * @param n     The number of elements in nums2
     */
    public static void merge(int[] nums1, int m, int[] nums2, int n) {
        int i = m - 1; // last element in initialized nums1
        int j = n - 1; // last element in nums2
        int k = m + n - 1; // last position in nums1 array

        // Merge in reverse order to avoid overwriting elements in nums1
        while (i >= 0 && j >= 0) {
            if (nums1[i] > nums2[j]) {
                nums1[k--] = nums1[i--];
            } else {
                nums1[k--] = nums2[j--];
            }
        }

        // If nums2 still has elements, copy them
        // (If nums1 has elements left, they are already in place)
        while (j >= 0) {
            nums1[k--] = nums2[j--];
        }
    }


}

/*
 * Approach:
 * We use a three-pointer approach starting from the end of the arrays.
 * 1. Pointer 'i' tracks the end of the valid elements in nums1.
 * 2. Pointer 'j' tracks the end of nums2.
 * 3. Pointer 'k' tracks the end of the total merged array (nums1).
 * 
 * We compare elements at nums1[i] and nums2[j] and place the larger element at
 * nums1[k].
 * This allows us to merge in-place without overwriting elements in nums1 that
 * haven't been processed yet.
 * 
 * Time Complexity:
 * - O(m + n): We iterate through both arrays at most once.
 * 
 * Space Complexity:
 * - O(1): We are modifying nums1 in-place.
 */
