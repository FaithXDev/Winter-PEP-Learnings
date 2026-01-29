import java.util.Arrays;

public class Remove_Element {
    public int removeElement(int[] nums, int val) {
        int temp = 0; // 'temp' acts as a pointer for the position of valid elements

        for (int i = 0; i < nums.length; ++i) {
            // If the current element is NOT the value to remove...
            if (nums[i] != val) {
                // ...place it at the 'temp' index.
                nums[temp] = nums[i];
                // Increment 'temp' to point to the next available slot.
                temp++;
            }
            // If it IS the value to remove, we simply skip it (do nothing).
            // 'i' increments, but 'temp' stays put, waiting to be overwritten by the next
            // valid number.
        }
        // 'temp' now represents the count of valid elements.
        return temp;
    }

    public static void main(String[] args) {
        Remove_Element solution = new Remove_Element();

        // Test case 1
        int[] nums1 = { 3, 2, 2, 3 };
        int val1 = 3;
        int len1 = solution.removeElement(nums1, val1);
        System.out.println("Input: " + Arrays.toString(new int[] { 3, 2, 2, 3 }) + ", val = " + val1);
        System.out.print("Output: " + len1 + ", nums = [");
        for (int i = 0; i < len1; i++) {
            System.out.print(nums1[i] + (i < len1 - 1 ? "," : ""));
        }
        System.out.println("]");

        // Test case 2
        int[] nums2 = { 0, 1, 2, 2, 3, 0, 4, 2 };
        int val2 = 2;
        int len2 = solution.removeElement(nums2, val2);
        System.out.println("Input: " + Arrays.toString(new int[] { 0, 1, 2, 2, 3, 0, 4, 2 }) + ", val = " + val2);
        System.out.print("Output: " + len2 + ", nums = [");
        for (int i = 0; i < len2; i++) {
            System.out.print(nums2[i] + (i < len2 - 1 ? "," : ""));
        }
        System.out.println("]");
    }
}

/*
 * EXPLANATION:
 * 
 * Approach: Two Pointers
 * ----------------------
 * The problem is to remove all occurrences of `val` in-place and return the new
 * length.
 * 
 * 1. Pointers:
 * - `i`: Fast pointer that scans the entire array.
 * - `temp` (or `k`): Slow pointer that tracks the position where the next
 * "kept" element should go.
 * 
 * 2. Logic:
 * - We iterate through the array.
 * - Whenever `nums[i]` is a value we want to KEEP (`nums[i] != val`), we copy
 * `nums[i]` to `nums[temp]` and increment `temp`.
 * - Whenever `nums[i]` is a value we want to REMOVE (`nums[i] == val`), we just
 * skip it. `temp` does not increment.
 * - This effectively overwrites the "to-be-removed" values with subsequent
 * valid values.
 * 
 * Complexity Analysis:
 * --------------------
 * Time Complexity: O(N)
 * - We traverse the array exactly once.
 * 
 * Space Complexity: O(1)
 * - We modify the array in-place without using extra space.
 */