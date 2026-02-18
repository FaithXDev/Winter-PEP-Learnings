import java.util.ArrayList;
import java.util.List;

public class Find_all_duplicates_in_an_array {
    public List<Integer> findDuplicates(int[] nums) {
        List<Integer> duplicates = new ArrayList<>();

        for (int i = 0; i < nums.length; ++i) {
            // Get the value at the current index.
            // We use Math.abs because the element might have been negated in a previous
            // step.
            int x = Math.abs(nums[i]);

            // Calculate the index that this value 'points' to (0-indexed)
            int idx = x - 1;

            // If the value at the mapped index is already negative, it means we have seen
            // 'x' before.
            if (nums[idx] < 0) {
                duplicates.add(x);
            } else {
                // Mark the value 'x' as seen by negating the element at index 'idx'.
                // This acts as a flag without losing the information of what was stored at
                // 'idx'.
                nums[idx] = -nums[idx];
            }
        }

        // Note: The array is modified. If the problem required restoring the array, we
        // would do another pass:
        // for(int i=0; i<nums.length; i++) nums[i] = Math.abs(nums[i]);

        return duplicates;
    }

}

/*
 * EXPLANATION:
 * 
 * Approach: Index Marking (In-place)
 * ----------------------------------
 * The problem requires an algorithm that runs in O(N) time and uses constant
 * extra space O(1).
 * Typically, finding duplicates involves a Set (O(N) space) or Sorting (O(N log
 * N) time).
 * 
 * To meet the constraints, we leverage the fact that 1 <= nums[i] <= n.
 * This property allows us to use the indices of the array itself as a
 * "HashTable".
 * 
 * 1. Logic:
 * - For each number `x` in the array, we treat `abs(x) - 1` as an index.
 * - We visit `nums[abs(x) - 1]`.
 * - If `nums[abs(x) - 1]` is POSITIVE, it means we haven't seen `x` before.
 * We negate `nums[abs(x) - 1]` to mark it "seen".
 * - If `nums[abs(x) - 1]` is NEGATIVE, it means we HAVE seen `x` before
 * (because we negated it earlier).
 * So, `x` is a duplicate. Add it to the result list.
 * 
 * 2. Example:
 * [4, 3, 2, 7, 8, 2, ... ]
 * - i=0, val=4. Go to index 3. Negate nums[3]. Array: [4, 3, 2, -7, ...]
 * ... later ...
 * - i=5, val=2. Go to index 1. nums[1] is 3 (positive). Negate it. Array: [4,
 * -3, 2, -7, ...]
 * ... later ...
 * - i=... val=2 again. Go to index 1. nums[1] is -3 (negative). Duplicate
 * found! Add 2.
 * 
 * Complexity Analysis:
 * --------------------
 * Time Complexity: O(N)
 * - We iterate through the array once. All operations are O(1).
 * 
 * Space Complexity: O(1)
 * - We use the input array for marking and do not allocate any extra data
 * structures proportional to N
 * (ignoring the result list as it's the output).
 */
