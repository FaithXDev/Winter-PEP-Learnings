
class House_Robber_II {

    public int rob(int[] nums) {
        int n = nums.length;

        // Edge cases
        if (n == 0)
            return 0;
        if (n == 1)
            return nums[0];
        if (n == 2)
            return Math.max(nums[0], nums[1]);

        // Since houses are in a circle, house 0 and house n-1 are adjacent.
        // So we can NEVER rob both. Break into two linear subproblems:
        // Case 1: Rob from house 0 to house n-2 (exclude last)
        // Case 2: Rob from house 1 to house n-1 (exclude first)
        return Math.max(robLinear(nums, 0, n - 2), robLinear(nums, 1, n - 1));
    }

    /**
     * Standard House Robber I (linear) with space-optimized DP.
     * Solves for the subarray nums[start..end] (inclusive).
     */
    private int robLinear(int[] nums, int start, int end) {
        int prev2 = 0; // dp[i-2]
        int prev1 = 0; // dp[i-1]

        for (int i = start; i <= end; i++) {
            int curr = Math.max(prev1, prev2 + nums[i]);
            prev2 = prev1;
            prev1 = curr;
        }

        return prev1;
    }

}

// ==================== APPROACH ====================
//
// Problem: LeetCode 213 - House Robber II
// -----------------------------------------
// Same as House Robber I, but houses are arranged in a CIRCLE.
// This means house[0] and house[n-1] are neighbors — you cannot
// rob both.
//
// Approach: Two Linear Subproblems (DP)
//
// Intuition:
// - The circular constraint only adds ONE extra restriction:
// house[0] and house[n-1] cannot both be robbed.
// - This means either house[0] is NOT robbed, or house[n-1] is NOT robbed
// (or both are not robbed — which is covered by either case).
// - So we reduce the circular problem into TWO linear House Robber problems:
// Case 1: Consider houses[0 .. n-2] (exclude last house)
// Case 2: Consider houses[1 .. n-1] (exclude first house)
// - The answer is max(Case 1, Case 2).
//
// Algorithm:
// 1. Handle edge cases: n == 0, n == 1, n == 2.
// 2. Run standard House Robber DP on nums[0..n-2].
// 3. Run standard House Robber DP on nums[1..n-1].
// 4. Return the maximum of the two results.
//
// The robLinear helper uses space-optimized DP:
// prev2 = dp[i-2], prev1 = dp[i-1]
// curr = max(prev1, prev2 + nums[i])
//
// Dry Run:
// nums = [1, 2, 3, 1]
//
// Case 1: nums[0..2] = [1, 2, 3]
// i=0: curr = max(0, 0+1) = 1, prev2=0, prev1=1
// i=1: curr = max(1, 0+2) = 2, prev2=1, prev1=2
// i=2: curr = max(2, 1+3) = 4, prev2=2, prev1=4
// Result = 4
//
// Case 2: nums[1..3] = [2, 3, 1]
// i=1: curr = max(0, 0+2) = 2, prev2=0, prev1=2
// i=2: curr = max(2, 0+3) = 3, prev2=2, prev1=3
// i=3: curr = max(3, 2+1) = 3, prev2=3, prev1=3
// Result = 3
//
// Answer = max(4, 3) = 4 ✓ (Rob houses 0 and 2 → 1 + 3 = 4)
//
// Another Dry Run:
// nums = [2, 3, 2]
//
// Case 1: nums[0..1] = [2, 3]
// i=0: curr=2, i=1: curr=max(2,0+3)=3 → Result = 3
//
// Case 2: nums[1..2] = [3, 2]
// i=1: curr=3, i=2: curr=max(3,0+2)=3 → Result = 3
//
// Answer = max(3, 3) = 3 ✓ (Rob only house 1 → 3)
//
// Time Complexity: O(n) — Two linear passes through the array.
// Space Complexity: O(1) — Only two variables per pass.
//
// Why This Works:
// - By excluding one endpoint in each subproblem, we guarantee that
// house[0] and house[n-1] are never BOTH included.
// - Each subproblem is a standard linear House Robber, which is
// solved optimally with DP.
// - Taking the max of both covers ALL valid combinations.
//
// Key Insight:
// - A circular constraint with N items can often be broken into
// two linear problems by fixing one element's inclusion/exclusion.
// This is a common pattern in circular DP problems.
// =================================================
