public class Reversed_integer {
    public int reverse(int x) {
        int result = 0;

        while (x != 0) {
            // Get the last digit
            int pop = x % 10;
            // Remove the last digit from x
            x /= 10;

            // Check for overflow BEFORE updating the result
            // Integer.MAX_VALUE is 2147483647
            // If result > MAX/10, multiplying by 10 will definitely overflow.
            // If result == MAX/10, we can only add up to 7 without overflowing.
            if (result > Integer.MAX_VALUE / 10 || (result == Integer.MAX_VALUE / 10 && pop > 7)) {
                return 0;
            }
            // Integer.MIN_VALUE is -2147483648
            // Similar logic for negative numbers. We can subtract down to -8.
            if (result < Integer.MIN_VALUE / 10 || (result == Integer.MIN_VALUE / 10 && pop < -8)) {
                return 0;
            }

            // Update result: shift left and add new digit
            result = result * 10 + pop;
        }
        return result;
    }

}

/*
 * EXPLANATION:
 * 
 * Approach: Pop and Push Digits & Check for Overflow
 * --------------------------------------------------
 * The problem is to reverse the digits of a 32-bit signed integer.
 * If the reversed integer overflows the 32-bit range, return 0.
 * 
 * 1. Digit Extraction:
 * - Use modulo 10 (`x % 10`) to get the last digit (`pop`).
 * - Use division by 10 (`x / 10`) to remove the last digit.
 * 
 * 2. Reversal:
 * - Accumulate the result: `result = result * 10 + pop`.
 * 
 * 3. Overflow Check (Critical):
 * - Since we are restricted to a 32-bit environment (conceptually), we must
 * check for overflow *before* the operation `result * 10 + pop` happens.
 * - `Integer.MAX_VALUE` = 2,147,483,647.
 * - `Integer.MIN_VALUE` = -2,147,483,648.
 * - Before multiplying `result` by 10, check if `result > Integer.MAX_VALUE /
 * 10`.
 * - If `result == Integer.MAX_VALUE / 10`, then the next digit (`pop`) must not
 * be greater than 7.
 * - Similar logic applies for the negative limit (check for <-8).
 * 
 * Complexity Analysis:
 * --------------------
 * Time Complexity: O(log(x))
 * - The number of digits in `x` is roughly log10(x).
 * 
 * Space Complexity: O(1)
 * - Constant space logic.
 */
