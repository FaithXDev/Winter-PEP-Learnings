import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Permutation2 {
    public List<List<Integer>> permuteUnique(int[] nums) {
        List<List<Integer>> result = new ArrayList<>();
        // Sort the array first. This is crucial to handle duplicates easily.
        Arrays.sort(nums);
        backtrack(nums, new boolean[nums.length], new ArrayList<>(), result);
        return result;
    }

    /**
     * Helper method to generate unique permutations using backtracking.
     */
    private void backtrack(int[] nums, boolean[] used, List<Integer> current, List<List<Integer>> result) {
        // Base case: If we have used all numbers, we have a complete permutation.
        if (current.size() == nums.length) {
            result.add(new ArrayList<>(current));
            return;
        }

        for (int i = 0; i < nums.length; i++) {
            // Early exit conditions:

            // 1. If the number is already used in the current permutation path, skip it.
            if (used[i])
                continue;

            // 2. Handling Duplicates:
            // If the current number is same as the previous number (nums[i] == nums[i-1])
            // AND the previous number was NOT used (!used[i-1]), it means we are trying to
            // use a duplicate number in the same position where the previous identical
            // number
            // has effectively "finished" its turn (backtracked). This would lead to a
            // duplicate permutation.
            if (i > 0 && nums[i] == nums[i - 1] && !used[i - 1])
                continue;

            // Choose
            used[i] = true;
            current.add(nums[i]);

            // Explore
            backtrack(nums, used, current, result);

            // Un-choose (Backtrack)
            current.remove(current.size() - 1);
            used[i] = false;
        }
    }

}

/*
 * EXPLANATION:
 * 
 * Approach: Backtracking with Sorting needed
 * ------------------------------------------
 * The problem is to finding unique permutations of an array that may contain
 * duplicates.
 * 
 * 1. Sorting:
 * - We first sort the array. This keeps all identical numbers adjacent to each
 * other.
 * 
 * 2. Backtracking:
 * - We maintain a `used` array to track which elements are currently included
 * in the path.
 * - Loop through the array.
 * - If `used[i]` is true, skip.
 * 
 * 3. Handling Duplicates (The Critical Part):
 * - The condition `if (i > 0 && nums[i] == nums[i - 1] && !used[i - 1])` is
 * used to skip duplicates.
 * - Why `!used[i-1]`?
 * - `used[i-1]` being TRUE means the previous identical number is currently in
 * the recursion stack (it's an ancestor node).
 * This is fine; we are building a sequence like `1, 1, ...`.
 * - `used[i-1]` being FALSE means the previous identical number was just used
 * and removed (backtracked).
 * If we use the current `nums[i]` (which is same as `nums[i-1]`) as the
 * starting point for this position,
 * we will generate the exact same set of permutations we just did with
 * `nums[i-1]`. So we skip it.
 * 
 * Complexity Analysis:
 * --------------------
 * Time Complexity: O(N * N!)
 * - In the worst case (all distinct), it generates N! permutations.
 * 
 * Space Complexity: O(N)
 * - Heap space for recursion and auxiliary arrays.
 */
