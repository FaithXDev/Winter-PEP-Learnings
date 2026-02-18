import java.util.ArrayList;
import java.util.List;

public class Permutation {
    public List<List<Integer>> permute(int[] nums) {
        List<List<Integer>> result = new ArrayList<>();
        // Start backtracking with an empty temporary list
        backtrack(result, new ArrayList<>(), nums);
        return result;
    }

    private void backtrack(List<List<Integer>> result, List<Integer> tempList, int[] nums) {
        // Base Case: If the temporary list size equals the input array size,
        // we have found a valid permutation.
        if (tempList.size() == nums.length) {
            result.add(new ArrayList<>(tempList)); // Add a copy of the list to the result
        } else {
            // Recursive Step: Iterate through each number in the input array
            for (int i = 0; i < nums.length; i++) {
                // If the number is already in the current path, skip it (since distinct
                // permutations)
                if (tempList.contains(nums[i]))
                    continue;

                // Choose: Add the number to the current path
                tempList.add(nums[i]);

                // Explore: Recurse to build the rest of the permutation
                backtrack(result, tempList, nums);

                // Un-choose: Remove the last added number to backtrack and try the next number
                tempList.remove(tempList.size() - 1);
            }
        }
    }

}

/*
 * EXPLANATION:
 * 
 * Approach: Backtracking
 * ----------------------
 * The problem requires finding all possible distinct permutations of a given
 * array of numbers.
 * We use a recursive backtracking strategy to build permutations step by step.
 * 
 * 1. We maintain a `tempList` to represent the current permutation being built.
 * 2. We iterate through the input `nums` array.
 * 3. For each number, we check if it is already in `tempList`.
 * - If yes, we skip it (since we need distinct numbers in each permutation).
 * - If no, we add it to `tempList` (Choose).
 * 4. We then recursively call the function to continue building the permutation
 * (Explore).
 * 5. Once the recursive call returns, we remove the last added element from
 * `tempList` (Un-choose/Backtrack)
 * so we can try the next number in the iteration.
 * 6. Base Case: When `tempList` size equals `nums` length, we have a complete
 * permutation.
 * We add a NEW copy of `tempList` to our `result` list.
 * 
 * Complexity Analysis:
 * --------------------
 * Time Complexity: O(N * N!)
 * - There are N! possible permutations.
 * - For each permutation, we take O(N) time to copy the list into the result.
 * - Ideally, we visit each state once.
 * 
 * Space Complexity: O(N) (excluding result storage)
 * - The recursion depth is O(N).
 * - The `tempList` takes O(N) space.
 * - The `result` list stores N! lists of size N, which is technically O(N *
 * N!).
 */
