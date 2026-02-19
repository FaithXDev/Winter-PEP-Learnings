
class Unique_Paths {

    public int uniquePaths(int m, int n) {
        // dp[j] represents the number of unique paths to reach column j
        // in the current row
        int[] dp = new int[n];

        // Base case: there's only 1 way to reach any cell in the first row
        // (keep moving right)
        for (int j = 0; j < n; j++) {
            dp[j] = 1;
        }

        // Fill row by row starting from the second row
        for (int i = 1; i < m; i++) {
            for (int j = 1; j < n; j++) {
                // dp[j] = paths from above (old dp[j]) + paths from left (dp[j-1])
                dp[j] = dp[j] + dp[j - 1];
            }
        }

        return dp[n - 1];
    }

}

// ==================== APPROACH ====================
//
// Problem: LeetCode 62 - Unique Paths
// -------------------------------------
// A robot is at the top-left corner of an m x n grid.
// It can only move RIGHT or DOWN at each step.
// Find the total number of unique paths to reach the bottom-right corner.
//
// Approach: Dynamic Programming (Space Optimized — 1D Array)
//
// Intuition:
// - To reach cell (i, j), the robot must have come from either:
// 1. The cell above: (i-1, j) — moved DOWN
// 2. The cell to the left: (i, j-1) — moved RIGHT
// - So the number of paths to (i, j) = paths to (i-1, j) + paths to (i, j-1).
// - The first row and first column each have only 1 path (all right / all
// down).
//
// Recurrence:
// dp[i][j] = dp[i-1][j] + dp[i][j-1]
//
// Space Optimization:
// - We only need the current row and the previous row.
// - Since we process left-to-right, dp[j] still holds the value from the
// previous row (acts as dp[i-1][j]), and dp[j-1] is already updated
// for the current row (acts as dp[i][j-1]).
// - So a single 1D array suffices: dp[j] += dp[j-1]
//
// Algorithm:
// 1. Create dp[] of size n, initialized to all 1s (first row).
// 2. For each row i from 1 to m-1:
// For each col j from 1 to n-1:
// dp[j] = dp[j] + dp[j-1]
// 3. Return dp[n-1].
//
// Dry Run:
// m = 3, n = 3
//
// Initial (row 0): dp = [1, 1, 1]
//
// Row 1:
// j=1: dp[1] = 1 + 1 = 2 → dp = [1, 2, 1]
// j=2: dp[2] = 1 + 2 = 3 → dp = [1, 2, 3]
//
// Row 2:
// j=1: dp[1] = 2 + 1 = 3 → dp = [1, 3, 3]
// j=2: dp[2] = 3 + 3 = 6 → dp = [1, 3, 6]
//
// Answer = dp[2] = 6 ✓
//
// Grid visualization:
// 1 1 1
// 1 2 3
// 1 3 6
//
// Another Dry Run:
// m = 3, n = 7 → Answer = 28
//
// Time Complexity: O(m × n) — We fill each cell once.
// Space Complexity: O(n) — Single 1D array of size n.
//
// Alternative: Combinatorics Approach
// - Total moves = (m-1) downs + (n-1) rights = (m+n-2) moves.
// - We need to choose (m-1) downs out of (m+n-2) moves.
// - Answer = C(m+n-2, m-1) = (m+n-2)! / ((m-1)! × (n-1)!)
// - This gives O(min(m,n)) time and O(1) space, but DP is more
// intuitive and avoids overflow issues for larger inputs.
//
// Key Insight:
// - Each cell's value is the sum of the cell above and the cell to the left.
// - This is essentially computing entries in Pascal's Triangle.
// - The 1D DP trick works because we process left-to-right, so dp[j-1]
// is already the current row's value, while dp[j] is still the
// previous row's value before we update it.
// =================================================
