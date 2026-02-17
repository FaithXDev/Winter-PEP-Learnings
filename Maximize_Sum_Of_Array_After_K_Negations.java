import java.util.Arrays;

class Maximize_Sum_Of_Array_After_K_Negations {

    public int largestSumAfterKNegations(int[] nums, int k) {
        // Step 1: Sort the array
        Arrays.sort(nums);

        // Step 2: Negate the smallest (most negative) elements first
        for (int i = 0; i < nums.length && k > 0; i++) {
            if (nums[i] < 0) {
                nums[i] = -nums[i];
                k--;
            }
        }

        // Step 3: If k is still remaining and odd, negate the smallest element
        // (If k is even, negations cancel out, so no change needed)
        if (k % 2 == 1) {
            // Find the minimum element in the array
            Arrays.sort(nums);
            nums[0] = -nums[0];
        }

        // Step 4: Calculate and return the sum
        int sum = 0;
        for (int num : nums) {
            sum += num;
        }
        return sum;
    }

    // Main method for testing
    public static void main(String[] args) {
        Maximize_Sum_Of_Array_After_K_Negations solution = new Maximize_Sum_Of_Array_After_K_Negations();

        // Test Case 1: nums = [4,2,3], k = 1 -> Output: 5
        int[] nums1 = { 4, 2, 3 };
        System.out.println("Test 1: " + solution.largestSumAfterKNegations(nums1, 1)); // Expected: 5

        // Test Case 2: nums = [3,-1,0,2], k = 3 -> Output: 6
        int[] nums2 = { 3, -1, 0, 2 };
        System.out.println("Test 2: " + solution.largestSumAfterKNegations(nums2, 3)); // Expected: 6

        // Test Case 3: nums = [2,-3,-1,5,-4], k = 2 -> Output: 13
        int[] nums3 = { 2, -3, -1, 5, -4 };
        System.out.println("Test 3: " + solution.largestSumAfterKNegations(nums3, 2)); // Expected: 13
    }
}

// ==================== APPROACH ====================
//
// Problem Understanding:
// - We have an array of integers and k operations.
// - In each operation, we pick any element and negate it (multiply by -1).
// - We must perform EXACTLY k operations (can repeat same index).
// - Goal: Maximize the sum of the array after all k operations.
//
// Approach: Greedy (Sort + Negate Smallest)
//
// Intuition:
// - To maximize the sum, we want all elements to be as POSITIVE as possible.
// - Negating a negative number makes it positive → increases the sum.
// - So we should negate the MOST NEGATIVE numbers first (greedy choice).
// - If all numbers are already positive and k operations remain:
// - If k is even → negate the same element twice (cancels out, no effect).
// - If k is odd → negate the SMALLEST positive number to minimize loss.
//
// Algorithm:
// 1. SORT the array in ascending order.
// - Negative numbers come first (smallest/most negative at index 0).
//
// 2. NEGATE negative numbers from left to right, decrementing k each time.
// - Stop when k == 0 or no more negative numbers.
//
// 3. CHECK remaining k:
// - If k is even → do nothing (negations cancel out).
// - If k is odd → sort again and negate the smallest element (nums[0]).
//
// 4. COMPUTE the sum and return it.
//
// Dry Run:
// nums = [2, -3, -1, 5, -4], k = 2
//
// Step 1 - Sort: nums = [-4, -3, -1, 2, 5]
//
// Step 2 - Negate negatives:
// i=0: nums[0]=-4 < 0 → negate → nums = [4, -3, -1, 2, 5], k=1
// i=1: nums[1]=-3 < 0 → negate → nums = [4, 3, -1, 2, 5], k=0
// k == 0, stop.
//
// Step 3 - k=0 (even) → no change.
//
// Step 4 - Sum = 4 + 3 + (-1) + 2 + 5 = 13 ✓
//
// Another Dry Run (remaining k is odd):
// nums = [3, -1, 0, 2], k = 3
//
// Step 1 - Sort: nums = [-1, 0, 2, 3]
//
// Step 2 - Negate negatives:
// i=0: nums[0]=-1 < 0 → negate → nums = [1, 0, 2, 3], k=2
// i=1: nums[1]=0, not < 0 → stop.
//
// Step 3 - k=2 (even) → no change.
//
// Step 4 - Sum = 1 + 0 + 2 + 3 = 6 ✓
//
// Time Complexity: O(n log n) — Due to sorting.
// Space Complexity: O(1) — Sorting is in-place, only constant extra space.
//
// Why Greedy Works:
// - Negating the most negative number gives the maximum increase in sum
// (flipping -4 to 4 adds 8 to the sum, while flipping -1 to 1 adds only 2).
// - Once all negatives are handled, flipping the smallest positive minimizes
// the loss if we still have an odd number of operations left.
//
// Key Insight:
// - If k is even after handling negatives, the remaining operations cancel out.
// - If k is odd, we sacrifice the smallest element (negate it once).
// =================================================
