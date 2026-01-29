// package LeetCode;

import java.util.HashMap;
import java.util.Map;
import static java.lang.System.out;

public class Two_Sum {
    public int[] twoSum(int[] nums, int target) {
        // Map to store number -> index mapping
        Map<Integer, Integer> map = new HashMap<>();

        for (int i = 0; i < nums.length; i++) {
            int complement = target - nums[i];

            // Check if component exists in map
            if (map.containsKey(complement)) {
                // If found, return the index of the complement and current index
                return new int[] { map.get(complement), i };
            }

            // Otherwise, add the current number and its index to the map
            map.put(nums[i], i);
        }

        throw new IllegalArgumentException("No two sum solution");
    }

    public static void main(String[] args) {
        Two_Sum solution = new Two_Sum();
        int[] nums = { 2, 7, 11, 15 };
        int target = 9;
        try {
            int[] result = solution.twoSum(nums, target);
            out.println("Indices: [" + result[0] + ", " + result[1] + "]");
        } catch (Exception e) {
            out.println(e.getMessage());
        }
    }
}

/*
 * EXPLANATION:
 * 
 * Approach: One-pass Hash Table
 * -----------------------------
 * The problem is to find two indices `i` and `j` such that `nums[i] + nums[j]
 * == target`.
 * Or `nums[j] == target - nums[i]`.
 * 
 * As we iterate through the array, for each element `nums[i]`, we need to know
 * if `target - nums[i]` (the "complement")
 * has already been seen in the array.
 * 
 * 1. Data Structure:
 * - A Hash Map is used to map `value -> index`.
 * - This allows O(1) lookups.
 * 
 * 2. Algorithm:
 * - Iterate `i` from 0 to N-1.
 * - Calculate `complement = target - nums[i]`.
 * - Check if `complement` is in the map.
 * - Yes? We found the pair! Return `[map.get(complement), i]`.
 * - No? Put `nums[i]` and `i` into the map for future checks.
 * 
 * Complexity Analysis:
 * --------------------
 * Time Complexity: O(N)
 * - We traverse the list containing N elements only once.
 * - Each look up in the table costs only O(1) average time.
 * 
 * Space Complexity: O(N)
 * - The extra space required depends on the number of items stored in the hash
 * table, which stores at most N elements.
 */