
class Minimum_Insertion_Steps_to_Make_a_String_Palindrome {

    public int minInsertions(String s) {
        int n = s.length();
        String rev = new StringBuilder(s).reverse().toString();

        int[][] dp = new int[n + 1][n + 1];

        for (int i = 1; i <= n; i++) {
            for (int j = 1; j <= n; j++) {
                if (s.charAt(i - 1) == rev.charAt(j - 1)) {
                    dp[i][j] = 1 + dp[i - 1][j - 1];
                } else {
                    dp[i][j] = Math.max(dp[i - 1][j], dp[i][j - 1]);
                }
            }
        }

        int lps = dp[n][n];
        return n - lps;
    }
}

// LeetCode 1312 - Minimum Insertion Steps to Make a String Palindrome
// Approach: Dynamic Programming (LCS-based)
//
// Minimum insertions = n - LPS(s)
// where LPS(s) = Longest Palindromic Subsequence of s.
//
// LPS(s) = LCS(s, reverse(s))
// Build a bottom-up DP table for LCS of s and its reverse,
// then subtract the LPS length from n.
//
// Recurrence:
// If s[i-1] == rev[j-1] → dp[i][j] = 1 + dp[i-1][j-1]
// Else → dp[i][j] = max(dp[i-1][j], dp[i][j-1])
//
// Time Complexity : O(n²)
// Space Complexity : O(n²)
