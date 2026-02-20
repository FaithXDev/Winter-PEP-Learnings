// LeetCode 1143 - Longest Common Subsequence
// Approach: Bottom-Up Dynamic Programming (Space Optimized)
//
// dp[j] represents the length of the LCS of text1[0..i-1] and text2[0..j-1].
// We only need the previous row to compute the current row, so we use a 1D array.
//
// Recurrence:
//   If text1[i-1] == text2[j-1] → dp[j] = prev_diagonal + 1
//   Else                        → dp[j] = max(dp[j], dp[j-1])
//
// Time Complexity:  O(m * n)
// Space Complexity: O(min(m, n))

class Longest_Common_Subsequence {
    public int longestCommonSubsequence(String text1, String text2) {
        // Ensure text2 is the shorter string for space optimization
        if (text1.length() < text2.length()) {
            String temp = text1;
            text1 = text2;
            text2 = temp;
        }

        int m = text1.length(), n = text2.length();
        int[] dp = new int[n + 1];

        for (int i = 1; i <= m; i++) {
            int prev = 0; // stores dp[i-1][j-1]
            for (int j = 1; j <= n; j++) {
                int temp = dp[j]; // save current dp[j] (will become prev for next j)
                if (text1.charAt(i - 1) == text2.charAt(j - 1)) {
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
