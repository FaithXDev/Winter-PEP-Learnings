// LeetCode 91 - Decode Ways
// Approach: Dynamic Programming (Space Optimized)
// Time: O(n) | Space: O(1)

class decode_ways {
    public int numDecodings(String s) {
        if (s == null || s.length() == 0 || s.charAt(0) == '0') {
            return 0;
        }

        int n = s.length();
        int prev2 = 1; // dp[i-2], represents empty string base case
        int prev1 = 1; // dp[i-1], first character is valid (checked above)

        for (int i = 2; i <= n; i++) {
            int curr = 0;

            // Single digit decode: s[i-1] must be '1'-'9'
            int oneDigit = s.charAt(i - 1) - '0';
            if (oneDigit >= 1 && oneDigit <= 9) {
                curr += prev1;
            }

            // Two digit decode: s[i-2..i-1] must be '10'-'26'
            int twoDigit = (s.charAt(i - 2) - '0') * 10 + oneDigit;
            if (twoDigit >= 10 && twoDigit <= 26) {
                curr += prev2;
            }

            prev2 = prev1;
            prev1 = curr;
        }

        return prev1;
    }
}
