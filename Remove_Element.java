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
