class Longest_Palindromic_Subsequence {

    public int longestPalindromeSubseq(String s) {
        int n = s.length();
        String rev = new StringBuilder(s).reverse().toString();

        int[] dp = new int[n + 1];

        for (int i = 1; i <= n; i++) {
            int prev = 0; // stores dp[i-1][j-1] from 2D version
            for (int j = 1; j <= n; j++) {
                int temp = dp[j];
                if (s.charAt(i - 1) == rev.charAt(j - 1)) {
                    dp[j] = prev + 1;
                } else {
                    dp[j] = Math.max(dp[j], dp[j - 1]);
                }
                prev = temp;
            }
        }

        return dp[n];
    }
}

// LeetCode 516 - Longest Palindromic Subsequence
// Approach: Dynamic Programming (LCS-based, Space Optimized)
//
// LPS(s) = LCS(s, reverse(s))
// The longest palindromic subsequence of s is the
// longest common subsequence of s and its reverse.
//
// Space optimization: use a 1D dp array instead of 2D.
// A rolling 'prev' variable tracks dp[i-1][j-1] from the 2D version.
//
// Recurrence:
// If s[i-1] == rev[j-1] → dp[j] = prev + 1
// Else → dp[j] = max(dp[j], dp[j-1])
//
// Time Complexity : O(n²)
// Space Complexity : O(n)
