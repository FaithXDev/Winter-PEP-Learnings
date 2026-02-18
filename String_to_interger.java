public class String_to_interger {
    public int myAtoi(String s) {
        if (s == null)
            return 0;

        int i = 0;
        int sign = 1;
        long result = 0; // Use long to detect overflow easily
        int n = s.length();

        // 1. Skip leading whitespace
        while (i < n && s.charAt(i) == ' ') {
            i++;
        }

        // 2. Check for sign (+ or -)
        if (i < n && (s.charAt(i) == '-' || s.charAt(i) == '+')) {
            sign = (s.charAt(i) == '-') ? -1 : 1;
            i++;
        }

        // 3. Convert digits
        while (i < n && Character.isDigit(s.charAt(i))) {
            int digit = s.charAt(i) - '0';

            // Accumulate the number
            result = result * 10 + digit;

            // 4. Handle Overflow/Underflow
            // Check if the current result (with sign) exceeds 32-bit integer limits
            if (sign == 1 && result > Integer.MAX_VALUE)
                return Integer.MAX_VALUE;
            if (sign == -1 && -result < Integer.MIN_VALUE)
                return Integer.MIN_VALUE;

            i++;
        }

        return (int) (result * sign);
    }

}

/*
 * EXPLANATION:
 * 
 * Approach: Deterministic Finite Automaton (Sequential Parsing)
 * -------------------------------------------------------------
 * The problem requires implementing the `atoi` function which converts a string
 * to an integer following specific rules:
 * 
 * 1. Whitespace: Ignore any leading whitespace.
 * 2. Sign: Check if the next character is '-' or '+'. This determines the sign
 * of the result.
 * 3. Digits: Read in characters until a non-digit character is found or the end
 * of the string is reached.
 * 4. Overflow: The result must be clamped within the 32-bit signed integer
 * range [-2^31, 2^31 - 1].
 * 
 * Implementation Details:
 * - We use a `long` variable for `result` to safely store numbers that might
 * slightly exceed `Integer.MAX_VALUE` before we clamp them.
 * - This simplifies the overflow check compared to using an `int` and checking
 * before multiplication (like in Reverse Integer),
 * although the int-only approach is also valid.
 * 
 * Complexity Analysis:
 * --------------------
 * Time Complexity: O(N)
 * - We traverse the string once.
 * 
 * Space Complexity: O(1)
 * - Constant extra space used.
 */
