import java.util.Arrays;

public class Sort_colors {
    public void sortColors(int[] nums) {
        int low = 0;
        int mid = 0;
        int high = nums.length - 1;

        while (mid <= high) {
            if (nums[mid] == 0) {
                swap(nums, low, mid);
                low++;
                mid++;
            } else if (nums[mid] == 1) {
                mid++;
            } else { // nums[mid] == 2
                swap(nums, mid, high);
                high--;
            }
        }
    }

    private void swap(int[] nums, int i, int j) {
        int temp = nums[i];
        nums[i] = nums[j];
        nums[j] = temp;
    }

    public static void main(String[] args) {
        Sort_colors solution = new Sort_colors();

        // Test Case 1
        int[] nums1 = { 2, 0, 2, 1, 1, 0 };
        System.out.println("Original Array: " + Arrays.toString(nums1));
        solution.sortColors(nums1);
        System.out.println("Sorted Array:   " + Arrays.toString(nums1));
        System.out.println();

        // Test Case 2
        int[] nums2 = { 2, 0, 1 };
        System.out.println("Original Array: " + Arrays.toString(nums2));
        solution.sortColors(nums2);
        System.out.println("Sorted Array:   " + Arrays.toString(nums2));
    }
}

/*
 * Approach:
 * The problem asks us to sort an array containing only 0s, 1s, and 2s. This is
 * a classic variation of the Dutch National Flag problem proposed by Edsger W.
 * Dijkstra.
 * 
 * Algorithm:
 * 1. We use three pointers to partition the array into three sections:
 * - `low`: The boundary for 0s (everything before `low` is 0).
 * - `mid`: The current element being considered.
 * - `high`: The boundary for 2s (everything after `high` is 2).
 * 
 * 2. Initialization:
 * - `low` = 0
 * - `mid` = 0
 * - `high` = nums.length - 1
 * 
 * 3. We iterate while `mid <= high`:
 * - If `nums[mid] == 0`:
 * - Swap `nums[mid]` with `nums[low]`.
 * - Increment both `low` and `mid` because the 0 is now in its correct place at
 * the beginning, and we can move to the next element.
 * - If `nums[mid] == 1`:
 * - This element is already in the middle section. Just increment `mid` to
 * proceed.
 * - If `nums[mid] == 2`:
 * - Swap `nums[mid]` with `nums[high]`.
 * - Decrement `high`.
 * - Note: We do NOT increment `mid` here because the element swapped from
 * `high` could be a 0 or a 2 (or a 1), and we need to check it in the next
 * iteration.
 * 
 * Complexity Analysis:
 * - Time Complexity: O(N), where N is the number of elements in the array. We
 * pass through the array only once.
 * - Space Complexity: O(1), as we are sorting the array in-place using only a
 * constant amount of extra space for pointers.
 */
