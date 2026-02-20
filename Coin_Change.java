class Coin_Change {

    public int coinChange(int[] coins, int amount) {
        int[] dp = new int[amount + 1];

        // Fill with a value greater than any possible answer
        java.util.Arrays.fill(dp, amount + 1);
        dp[0] = 0;

        for (int i = 1; i <= amount; i++) {
            for (int coin : coins) {
                if (coin <= i) {
                    dp[i] = Math.min(dp[i], dp[i - coin] + 1);
                }
            }
        }

        return dp[amount] > amount ? -1 : dp[amount];
    }
}

// LeetCode 322 - Coin Change
// Approach: Bottom-Up Dynamic Programming
//
// dp[i] = minimum number of coins needed to make amount i.
// Initialize dp[0] = 0, all others to (amount + 1) as sentinel for
// "impossible".
//
// For each amount i from 1 to target, try every coin:
// If coin <= i → dp[i] = min(dp[i], dp[i - coin] + 1)
//
// If dp[amount] is still the sentinel, return -1.
//
// Time Complexity : O(amount * n) — n = number of coin denominations
// Space Complexity : O(amount)
