
class House_Robber {

    public int rob(int[] nums) {
        int n = nums.length;

        // Edge cases
        if (n == 0)
            return 0;
        if (n == 1)
            return nums[0];

        // dp[i] = max money we can rob from house 0 to house i
        int prev2 = nums[0]; // dp[i-2]
        int prev1 = Math.max(nums[0], nums[1]); // dp[i-1]

        for (int i = 2; i < n; i++) {
            int curr = Math.max(prev1, prev2 + nums[i]);
            prev2 = prev1;
            prev1 = curr;
        }

        return prev1;
    }

}

// ==================== APPROACH ====================
//
// Problem Understanding:
// - Array of houses, each with some money.
// - Cannot rob two ADJACENT houses (index i and i+1).
// - Goal: Maximize the total money robbed.
//
// Approach: Dynamic Programming (Space Optimized)
//
// Intuition:
// - At each house i, we have two choices:
// 1. ROB house i → take nums[i] + best from houses 0..i-2
// 2. SKIP house i → take the best from houses 0..i-1
// - We pick whichever gives more money.
// - This is a classic DP problem with overlapping subproblems.
//
// Recurrence Relation:
// dp[i] = max(dp[i-1], dp[i-2] + nums[i])
//
// Where:
// dp[i-1] = max money if we SKIP house i
// dp[i-2] + nums[i] = max money if we ROB house i
//
// Base Cases:
// dp[0] = nums[0] (only one house, rob it)
// dp[1] = max(nums[0], nums[1]) (two houses, rob the richer one)
//
// Space Optimization:
// - dp[i] only depends on dp[i-1] and dp[i-2].
// - So we only need TWO variables instead of a full array.
// - prev2 = dp[i-2], prev1 = dp[i-1]
//
// Algorithm:
// 1. Handle edge cases (n == 0 or n == 1).
// 2. Initialize prev2 = nums[0], prev1 = max(nums[0], nums[1]).
// 3. For each house i from 2 to n-1:
// curr = max(prev1, prev2 + nums[i])
// Shift: prev2 = prev1, prev1 = curr
// 4. Return prev1 (the answer for all houses).
//
// Dry Run:
// nums = [2, 7, 9, 3, 1]
//
// Base Cases:
// prev2 = nums[0] = 2
// prev1 = max(2, 7) = 7
//
// i=2: curr = max(7, 2 + 9) = max(7, 11) = 11
// prev2 = 7, prev1 = 11
//
// i=3: curr = max(11, 7 + 3) = max(11, 10) = 11
// prev2 = 11, prev1 = 11
//
// i=4: curr = max(11, 11 + 1) = max(11, 12) = 12
// prev2 = 11, prev1 = 12
//
// Answer = 12 ✓ (Rob houses at index 0, 2, 4 → 2 + 9 + 1 = 12)
//
// Another Dry Run:
// nums = [1, 2, 3, 1]
//
// Base Cases:
// prev2 = 1
// prev1 = max(1, 2) = 2
//
// i=2: curr = max(2, 1 + 3) = max(2, 4) = 4
// prev2 = 2, prev1 = 4
//
// i=3: curr = max(4, 2 + 1) = max(4, 3) = 4
// prev2 = 4, prev1 = 4
//
// Answer = 4 ✓ (Rob houses at index 0, 2 → 1 + 3 = 4)
//
// Time Complexity: O(n) — Single pass through the array.
// Space Complexity: O(1) — Only two variables (prev1, prev2).
//
// Why DP Works:
// - The problem has OPTIMAL SUBSTRUCTURE: the best solution for houses 0..i
// depends on the best solutions for houses 0..i-1 and 0..i-2.
// - The problem has OVERLAPPING SUBPROBLEMS: computing dp[i] for different
// values of i reuses previously computed dp values.
//
// Key Insight:
// - At every house, it's always a binary decision: ROB or SKIP.
// - We don't need to track which houses we robbed — only the max money
// achievable up to each point.
// - Space optimization from O(n) → O(1) is possible because dp[i] only
// depends on the two most recent values.
// =================================================
