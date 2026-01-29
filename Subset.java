import java.util.ArrayList;
import java.util.List;

public class Subset {
    public List<List<Integer>> subsets(int[] nums) {
        List<List<Integer>> result = new ArrayList<>();
        // Start backtracking from index 0
        backtrack(0, nums, new ArrayList<>(), result);
        return result;
    }

    /**
     * Helper method for backtracking to generate all subsets.
     */
    private void backtrack(int start, int[] nums, List<Integer> current, List<List<Integer>> result) {
        // Add the current subset to the result list.
        // We add a NEW ArrayList copy because 'current' is mutable and will change.
        result.add(new ArrayList<>(current));

        // Iterate starting from 'start' index to explore further element options
        for (int i = start; i < nums.length; i++) {
            // Choose: Add nums[i] to the current subset
            current.add(nums[i]);

            // Explore: Recurse to build larger subsets using subsequent elements
            backtrack(i + 1, nums, current, result);

            // Un-choose: Remove the last added element to backtrack and try the next
            // candidate
            current.remove(current.size() - 1);
        }
    }

    public static void main(String[] args) {
        Subset solution = new Subset();

        // Test case 1
        int[] nums1 = { 1, 2, 3 };
        System.out.println("Input: [1, 2, 3]");
        System.out.println("Output: " + solution.subsets(nums1));

        // Test case 2
        int[] nums2 = { 0 };
        System.out.println("Input: [0]");
        System.out.println("Output: " + solution.subsets(nums2));
    }
}

/*
 * EXPLANATION:
 * 
 * Approach: Backtracking
 * ----------------------
 * The problem is to finding all possible subsets (the power set) of a set of
 * distinct integers.
 * 
 * 1. Logic:
 * - We use a recursive function `backtrack(start, current)`.
 * - `start`: ensures we only move forward in the array (to avoid duplicates
 * like [1,2] and [2,1], as sets are unordered).
 * - At every function call, we add the `current` list to `result` because every
 * step in the path represents a valid subset.
 * - We iterate from `i = start` to `nums.length - 1`.
 * - In each iteration, we include `nums[i]` in our `current` subset, recurse
 * with `i + 1`, and then backtrack (remove `nums[i]`).
 * 
 * 2. Visual Trace (nums = [1, 2]):
 * - [] (added)
 * - include 1 -> [1] (added)
 * - include 2 -> [1, 2] (added)
 * - backtrack 2 -> [1]
 * - backtrack 1 -> []
 * - include 2 -> [2] (added)
 * - backtrack 2 -> []
 * - Done.
 * 
 * Complexity Analysis:
 * --------------------
 * Time Complexity: O(N * 2^N)
 * - There are 2^N total subsets.
 * - For each subset, we copy it to the result list, taking O(N) time on
 * average.
 * 
 * Space Complexity: O(N)
 * - Recursion depth matches N.
 * - Temporary list size matches N.
 */
