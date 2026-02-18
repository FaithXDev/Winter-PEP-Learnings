
class N_th_Tribonacci_Number {

    public int tribonacci(int n) {
        // Base cases
        if (n == 0)
            return 0;
        if (n == 1 || n == 2)
            return 1;

        // Space optimized: only need the last 3 values
        int t0 = 0, t1 = 1, t2 = 1;

        for (int i = 3; i <= n; i++) {
            int curr = t0 + t1 + t2;
            t0 = t1;
            t1 = t2;
            t2 = curr;
        }

        return t2;
    }

}

// ==================== APPROACH ====================
//
// Problem Understanding:
// - The Tribonacci sequence is defined as:
// T(0) = 0, T(1) = 1, T(2) = 1
// T(n) = T(n-1) + T(n-2) + T(n-3) for n >= 3
// - Given n, return the value of T(n).
//
// Approach: Dynamic Programming (Space Optimized)
//
// Intuition:
// - This is similar to Fibonacci, but instead of summing the last 2 values,
// we sum the last 3 values.
// - A naive recursive approach would have O(3^n) time due to repeated
// subproblems. DP eliminates this redundancy.
// - Since T(n) only depends on T(n-1), T(n-2), and T(n-3), we only need
// THREE variables instead of a full array.
//
// Recurrence Relation:
// T(n) = T(n-1) + T(n-2) + T(n-3)
//
// Base Cases:
// T(0) = 0
// T(1) = 1
// T(2) = 1
//
// Algorithm:
// 1. Handle base cases: return 0 if n==0, return 1 if n==1 or n==2.
// 2. Initialize three variables: t0=0, t1=1, t2=1.
// 3. Loop from i=3 to n:
// curr = t0 + t1 + t2
// Shift: t0 = t1, t1 = t2, t2 = curr
// 4. Return t2.
//
// Dry Run:
// n = 4
//
// Base values: t0=0, t1=1, t2=1
//
// i=3: curr = 0 + 1 + 1 = 2
// t0=1, t1=1, t2=2
//
// i=4: curr = 1 + 1 + 2 = 4
// t0=1, t1=2, t2=4
//
// Answer = 4 ✓
//
// Sequence: 0, 1, 1, 2, 4, 7, 13, 24, 44, ...
//
// Another Dry Run:
// n = 6
//
// Base values: t0=0, t1=1, t2=1
//
// i=3: curr = 0+1+1 = 2 → t0=1, t1=1, t2=2
// i=4: curr = 1+1+2 = 4 → t0=1, t1=2, t2=4
// i=5: curr = 1+2+4 = 7 → t0=2, t1=4, t2=7
// i=6: curr = 2+4+7 = 13 → t0=4, t1=7, t2=13
//
// Answer = 13 ✓
//
// Time Complexity: O(n) — Single loop from 3 to n.
// Space Complexity: O(1) — Only three variables used.
//
// Why DP Works:
// - OPTIMAL SUBSTRUCTURE: T(n) is directly defined in terms of smaller
// subproblems T(n-1), T(n-2), T(n-3).
// - OVERLAPPING SUBPROBLEMS: Recursive computation would recompute the
// same T(k) values many times. DP computes each value exactly once.
//
// Key Insight:
// - This is a direct extension of Fibonacci (which uses 2 previous values)
// to Tribonacci (which uses 3 previous values).
// - The space optimization from O(n) array → O(1) works because T(n)
// only depends on the 3 most recent values, so older values can be
// discarded as we slide forward.
// =================================================
