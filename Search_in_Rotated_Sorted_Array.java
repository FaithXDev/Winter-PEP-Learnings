public class Search_in_Rotated_Sorted_Array {
    /**
     * Searches for a target value in a rotated sorted array.
     */
    public int search(int[] nums, int target) {
        int left = 0;
        int right = nums.length - 1;

        while (left <= right) {
            int mid = left + (right - left) / 2;

            if (nums[mid] == target)
                return mid;

            // One half of the array must be sorted. We determine which half.

            // Check if the left half [left...mid] is sorted
            if (nums[left] <= nums[mid]) {
                // If the target lies within the sorted left half range, search there
                if (nums[left] <= target && target < nums[mid]) {
                    right = mid - 1;
                } else {
                    // Otherwise, search in the right half
                    left = mid + 1;
                }
            }
            // Otherwise, the right half [mid...right] must be sorted
            else {
                // If the target lies within the sorted right half range, search there
                if (nums[mid] < target && target <= nums[right]) {
                    left = mid + 1;
                } else {
                    // Otherwise, search in the left half
                    right = mid - 1;
                }
            }
        }
        return -1;
    }

}

/*
 * EXPLANATION:
 * 
 * Approach: Binary Search (Modified)
 * ----------------------------------
 * The array is sorted but rotated. This means it consists of two sorted
 * subarrays appended together.
 * Standard binary search relies on the entire range being sorted. Here, at any
 * pivot point `mid`,
 * at least one half of the array (either `left` to `mid` OR `mid` to `right`)
 * is guaranteed to be sorted.
 * 
 * 1. Find `mid`.
 * 2. Check if `nums[left] <= nums[mid]`:
 * - If true, the LEFT half is sorted.
 * - Check if `target` is within this sorted range [`nums[left]`, `nums[mid]`).
 * - If yes, eliminate right half (`right = mid - 1`).
 * - If no, eliminate left half (`left = mid + 1`).
 * 3. Else (`nums[left] > nums[mid]`):
 * - The RIGHT half must be sorted.
 * - Check if `target` is within this sorted range (`nums[mid]`, `nums[right]`].
 * - If yes, eliminate left half (`left = mid + 1`).
 * - If no, eliminate right half (`right = mid - 1`).
 * 
 * Complexity Analysis:
 * --------------------
 * Time Complexity: O(log N)
 * - We reduce the search space by half in each iteration, preserving the
 * logarithmic time complexity of binary search.
 * 
 * Space Complexity: O(1)
 * - Constant extra space used.
 */
