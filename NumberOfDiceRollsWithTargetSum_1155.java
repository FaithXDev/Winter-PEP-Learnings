public class NumberOfDiceRollsWithTargetSum_1155 {
    public int numRollsToTarget(int n, int k, int target) {
        int MOD = 1000000007;

        // dp[j] stores the number of ways to achieve sum j with current number of dice.
        int[] dp = new int[target + 1];
        dp[0] = 1; // 1 way to get sum 0 with 0 dice

        for (int i = 1; i <= n; i++) {
            int[] nextDp = new int[target + 1];
            for (int j = 1; j <= target; j++) {
                for (int face = 1; face <= k; face++) {
                    if (j >= face) {
                        nextDp[j] = (nextDp[j] + dp[j - face]) % MOD;
                    } else {
                        // Optimizing: if face is strictly greater than the current sum, we can break.
                        break;
                    }
                }
            }
            dp = nextDp;
        }

        return dp[target];
    }
}

/**
 * LeetCode 1155. Number of Dice Rolls With Target Sum
 * 
 * Approach: Dynamic Programming (Bottom-Up)
 * 1. This problem can be solved using dynamic programming.
 * 2. Let dp[i][j] represent the number of ways to get a sum of `j` using
 * exactly `i` dice.
 * 3. Base case: a sum of 0 using 0 dice has exactly 1 way. dp[0][0] = 1.
 * 4. We iterate the number of dice `i` from 1 to `n`.
 * 5. For each die `i`, we iterate the target sum `j` from 1 to `target`.
 * 6. For a fixed dice `i` and target `j`, the current die can roll any face
 * value `f` from 1 to `k`.
 * 7. If `j >= f`, then dp[i][j] = (dp[i][j] + dp[i - 1][j - f]) % (10^9 + 7).
 * This means the number of ways to get sum `j` with `i` dice is the sum of ways
 * to get
 * sum `j - f` with `i - 1` dice, considering all possible face values `f`.
 * 8. To optimize space, we can also use a 1D DP table since the current state
 * `dp[i]`
 * only depends on the previous state `dp[i-1]`.
 */
