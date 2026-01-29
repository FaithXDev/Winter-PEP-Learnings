public class Two_Sum2 {
    /**
     * Finds two numbers in a sorted array that add up to a specific target number.
     * Uses the Two Pointer technique.
     */
    public int[] twoSum(int[] numbers, int target) {
        // Initialize two pointers: left at the start, right at the end
        int left = 0;
        int right = numbers.length - 1;

        while (left < right) {
            int sum = numbers[left] + numbers[right];

            if (sum == target) {
                // Return 1-based indices as per problem requirement
                return new int[] { left + 1, right + 1 };
            } else if (sum < target) {
                // If sum is too small, we need a larger number, so move left pointer to the
                // right
                left++;
            } else {
                // If sum is too large, we need a smaller number, so move right pointer to the
                // left
                right--;
            }
        }
        // Return [-1, -1] if no solution is found (though problem statement guarantees
        // one solution)
        return new int[] { -1, -1 };
    }

    public static void main(String[] args) {
        Two_Sum2 solution = new Two_Sum2();

        // Test case 1
        int[] numbers1 = { 2, 7, 11, 15 };
        int target1 = 9;
        int[] result1 = solution.twoSum(numbers1, target1);
        System.out.println("Input: numbers = [2,7,11,15], target = 9");
        System.out.println("Output: [" + result1[0] + ", " + result1[1] + "] (Expected: [1, 2])");

        // Test case 2
        int[] numbers2 = { 2, 3, 4 };
        int target2 = 6;
        int[] result2 = solution.twoSum(numbers2, target2);
        System.out.println("Input: numbers = [2,3,4], target = 6");
        System.out.println("Output: [" + result2[0] + ", " + result2[1] + "] (Expected: [1, 3])");

        // Test case 3
        int[] numbers3 = { -1, 0 };
        int target3 = -1;
        int[] result3 = solution.twoSum(numbers3, target3);
        System.out.println("Input: numbers = [-1,0], target = -1");
        System.out.println("Output: [" + result3[0] + ", " + result3[1] + "] (Expected: [1, 2])");
    }
}

/*
 * EXPLANATION:
 * 
 * Approach: Two Pointers
 * ----------------------
 * Since the input array is already sorted, we can use the Two Pointer technique
 * to find the solution efficiently.
 * 
 * 1. Initialize two pointers: `left` at the beginning (index 0) and `right` at
 * the end (index n-1).
 * 2. Calculate the sum of the elements at `left` and `right`.
 * 3. Compare the `sum` with the `target`:
 * - If `sum == target`: We found the pair. Return their indices (incremented by
 * 1 for 1-based indexing).
 * - If `sum < target`: The current sum is too small. To increase the sum, we
 * need a larger number.
 * Since the array is sorted, moving the `left` pointer to the right gives us a
 * potentially larger or equal number.
 * - If `sum > target`: The current sum is too large. To decrease the sum, we
 * need a smaller number.
 * Moving the `right` pointer to the left gives us a potentially smaller or
 * equal number.
 * 4. Repeat until `left` meets `right`.
 * 
 * Complexity Analysis:
 * --------------------
 * Time Complexity: O(N)
 * - The input array is traversed at most once. The `left` pointer only moves
 * forward, and the `right` pointer only moves backward.
 * 
 * Space Complexity: O(1)
 * - We only use a constant amount of extra space for the two pointers and the
 * sum variable.
 */