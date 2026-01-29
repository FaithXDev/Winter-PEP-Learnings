import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Subset2 {
    public List<List<Integer>> subsetsWithDup(int[] nums) {
        List<List<Integer>> result = new ArrayList<>();
        // Sorting is crucial to handle duplicates by grouping them together
        Arrays.sort(nums);
        backtrack(0, nums, new ArrayList<>(), result);
        return result;
    }

    /**
     * Helper method to generate subsets ensuring no duplicate subsets are created.
     * 
     * @param start   The starting index to consider for candidates.
     * @param nums    The sorted input array.
     * @param current The current subset being built.
     * @param result  The list to store all unique subsets.
     */
    private void backtrack(int start, int[] nums, List<Integer> current, List<List<Integer>> result) {
        // Add the current subset to result immediately
        result.add(new ArrayList<>(current));

        for (int i = start; i < nums.length; i++) {
            // Duplicate Skip Logic:
            // If the current element is the same as the previous element (nums[i] ==
            // nums[i-1])
            // AND the previous element was NOT processed in this current loop iteration (i
            // > start),
            // then skip it.
            // Why? Because if i > start, it means this is NOT the first instance of this
            // number
            // being considered at this specific depth of the recursion tree.
            if (i > start && nums[i] == nums[i - 1])
                continue;

            current.add(nums[i]);
            backtrack(i + 1, nums, current, result);
            current.remove(current.size() - 1);
        }
    }

    public static void main(String[] args) {
        Subset2 solution = new Subset2();

        // Test case 1
        int[] nums1 = { 1, 2, 2 };
        System.out.println("Input: [1, 2, 2]");
        System.out.println("Output: " + solution.subsetsWithDup(nums1));

        // Test case 2
        int[] nums2 = { 0 };
        System.out.println("Input: [0]");
        System.out.println("Output: " + solution.subsetsWithDup(nums2));
    }
}

/*
 * EXPLANATION:
 * 
 * Approach: Backtracking with Sorting
 * -----------------------------------
 * The problem is to finding all unique subsets (power set) of an integer array
 * that may contain duplicates.
 * 
 * 1. Sorting:
 * - We first sort the array. This allows us to easily detect duplicates because
 * they will be adjacent.
 * 
 * 2. Backtracking:
 * - Similar to the standard `Subsets` problem, we iterate through the array to
 * include/exclude elements.
 * 
 * 3. Handling Duplicates:
 * - Specifically, at any given recursion level (`start` index), if we encounter
 * a number that is the same
 * as the previous number (`nums[i] == nums[i-1]`), we must decide whether to
 * use it.
 * - The condition `if (i > start && nums[i] == nums[i - 1]) continue;` ensures
 * we only pick the FIRST occurrence
 * of a duplicate number *at the current specific position* in the subset being
 * formed.
 * - `i > start` check ensures we don't skip the first time a number appears in
 * the current recursion branch.
 * 
 * Complexity Analysis:
 * --------------------
 * Time Complexity: O(N * 2^N)
 * - Worst case (all distinct), same as Subsets. With duplicates, it's strictly
 * less than this upper bound.
 * 
 * Space Complexity: O(N)
 * - Recursion depth.
 */