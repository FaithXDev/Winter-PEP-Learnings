
class Edit_Distance {

    public int minDistance(String word1, String word2) {
        int m = word1.length();
        int n = word2.length();

        // dp[j] represents min operations to convert word1[0..i-1] to word2[0..j-1]
        int[] dp = new int[n + 1];

        // Base case: converting "" to word2[0..j-1] requires j insertions
        for (int j = 0; j <= n; j++) {
            dp[j] = j;
        }

        // Fill row by row
        for (int i = 1; i <= m; i++) {
            int prev = dp[0]; // stores dp[i-1][j-1] (diagonal)
            dp[0] = i; // converting word1[0..i-1] to "" requires i deletions

            for (int j = 1; j <= n; j++) {
                int temp = dp[j]; // save current dp[j] before overwriting (will be diagonal for next j)

                if (word1.charAt(i - 1) == word2.charAt(j - 1)) {
                    // Characters match — no operation needed
                    dp[j] = prev;
                } else {
                    // Take minimum of three operations + 1:
                    // prev = dp[i-1][j-1] → Replace
                    // dp[j] = dp[i-1][j] → Delete
                    // dp[j-1] = dp[i][j-1] → Insert
                    dp[j] = 1 + Math.min(prev, Math.min(dp[j], dp[j - 1]));
                }

                prev = temp; // move diagonal pointer forward
            }
        }

        return dp[n];
    }

}

// ==================== APPROACH ====================
//
// Problem: LeetCode 72 - Edit Distance
// --------------------------------------
// Given two strings word1 and word2, return the minimum number of
// operations required to convert word1 into word2.
//
// Allowed operations (each costs 1):
// 1. Insert a character
// 2. Delete a character
// 3. Replace a character
//
// Approach: Dynamic Programming (Space Optimized — 1D Array)
//
// Intuition:
// - Define dp[i][j] = min operations to convert word1[0..i-1] to word2[0..j-1].
// - If word1[i-1] == word2[j-1], characters match → dp[i][j] = dp[i-1][j-1].
// - Otherwise, we pick the cheapest of three operations:
// 1. Replace word1[i-1] with word2[j-1]: dp[i-1][j-1] + 1
// 2. Delete word1[i-1]: dp[i-1][j] + 1
// 3. Insert word2[j-1] into word1: dp[i][j-1] + 1
// → dp[i][j] = 1 + min(dp[i-1][j-1], dp[i-1][j], dp[i][j-1])
//
// Base Cases:
// - dp[0][j] = j → converting "" to word2[0..j-1] needs j insertions
// - dp[i][0] = i → converting word1[0..i-1] to "" needs i deletions
//
// Space Optimization:
// - We only need the previous row and the current row.
// - Use a single 1D array dp[] of size (n+1).
// - A variable `prev` tracks the diagonal value dp[i-1][j-1].
// - dp[j] (before update) acts as dp[i-1][j] (from above).
// - dp[j-1] (after update) acts as dp[i][j-1] (from left).
//
// Algorithm:
// 1. Initialize dp[j] = j for j = 0 to n (base case row).
// 2. For each row i from 1 to m:
// a. Save dp[0] as prev, set dp[0] = i.
// b. For each col j from 1 to n:
// - Save dp[j] in temp (it becomes prev for next iteration).
// - If characters match: dp[j] = prev.
// - Else: dp[j] = 1 + min(prev, dp[j], dp[j-1]).
// - Update prev = temp.
// 3. Return dp[n].
//
// Dry Run:
// word1 = "horse", word2 = "ros"
// m = 5, n = 3
//
// Full 2D table for reference:
// "" r o s
// "" [ 0 1 2 3 ]
// h [ 1 1 2 3 ]
// o [ 2 2 1 2 ]
// r [ 3 2 2 2 ]
// s [ 4 3 3 2 ]
// e [ 5 4 4 3 ]
//
// 1D simulation:
//
// Init: dp = [0, 1, 2, 3]
//
// i=1 (h):
// dp[0] = 1, prev = 0
// j=1: h≠r → dp[1] = 1+min(0,1,1) = 1, prev=1 → dp = [1, 1, 2, 3]
// j=2: h≠o → dp[2] = 1+min(1,2,1) = 2, prev=2 → dp = [1, 1, 2, 3]
// j=3: h≠s → dp[3] = 1+min(2,3,2) = 3, prev=3 → dp = [1, 1, 2, 3]
//
// i=2 (o):
// dp[0] = 2, prev = 1
// j=1: o≠r → dp[1] = 1+min(1,1,2) = 2, prev=1 → dp = [2, 2, 2, 3]
// j=2: o==o → dp[2] = prev = 1, prev=2 → dp = [2, 2, 1, 3]
// j=3: o≠s → dp[3] = 1+min(2,3,1) = 2, prev=3 → dp = [2, 2, 1, 2]
//
// i=3 (r):
// dp[0] = 3, prev = 2
// j=1: r==r → dp[1] = prev = 2, prev=2 → dp = [3, 2, 1, 2]
// j=2: r≠o → dp[2] = 1+min(2,1,2) = 2, prev=1 → dp = [3, 2, 2, 2]
// j=3: r≠s → dp[3] = 1+min(1,2,2) = 2, prev=2 → dp = [3, 2, 2, 2]
//
// i=4 (s):
// dp[0] = 4, prev = 3
// j=1: s≠r → dp[1] = 1+min(3,2,4) = 3, prev=2 → dp = [4, 3, 2, 2]
// j=2: s≠o → dp[2] = 1+min(2,2,3) = 3, prev=2 → dp = [4, 3, 3, 2]
// j=3: s==s → dp[3] = prev = 2, prev=2 → dp = [4, 3, 3, 2]
//
// i=5 (e):
// dp[0] = 5, prev = 4
// j=1: e≠r → dp[1] = 1+min(4,3,5) = 4, prev=3 → dp = [5, 4, 3, 2]
// j=2: e≠o → dp[2] = 1+min(3,3,4) = 4, prev=3 → dp = [5, 4, 4, 2]
// j=3: e≠s → dp[3] = 1+min(3,2,4) = 3, prev=2 → dp = [5, 4, 4, 3]
//
// Answer = dp[3] = 3 ✓
// (horse → rorse → rose → ros)
//
// Another Dry Run:
// word1 = "intention", word2 = "execution" → Answer = 5
//
// Time Complexity: O(m × n) — Each cell computed once.
// Space Complexity: O(n) — Single 1D array of size (n+1).
//
// Key Insight:
// - The 1D trick requires carefully tracking the diagonal value (prev)
// because dp[j] gets overwritten before we need it as the diagonal
// for dp[j+1]. We save it in `temp` before updating.
// - Edit Distance is the foundation of many string similarity algorithms
// (spell checkers, DNA sequence alignment, diff tools).
// ==================================================
