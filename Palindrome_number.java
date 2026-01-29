public class Palindrome_number {
    /**
     * Checks if an integer is a palindrome.
     * An integer is a palindrome when it reads the same backward as forward.
     * 
     * @param x The integer to check.
     * @return true if x is a palindrome, false otherwise.
     */
    public boolean isPalindrome(int x) {
        // Negative numbers are not palindromes (e.g., -121 reads as 121- backwards)
        if (x < 0)
            return false;

        int original = x;
        long reversed = 0; // Use long to prevent potential overflow during reversal (though not strictly
                           // needed if we check properly)

        // Reconstruct the number by reversing its digits
        while (x > 0) {
            int digit = x % 10;
            reversed = reversed * 10 + digit;
            x /= 10;
        }

        // Check if the reversed number equals the original
        return original == reversed;
    }

    public static void main(String[] args) {
        Palindrome_number solution = new Palindrome_number();

        int[] tests = { 121, -121, 10, 12321, 0 };

        for (int x : tests) {
            System.out.println("Input: " + x + " -> Output: " + solution.isPalindrome(x));
        }
    }
}

/*
 * EXPLANATION:
 * 
 * Approach: Revert Half of the Number (or Full Reversal)
 * ------------------------------------------------------
 * The problem asks to determine if an integer is a palindrome.
 * 
 * 1. Initial Checks:
 * - If `x` is negative, it cannot be a palindrome because of the negative sign.
 * - (Optional optimization) If `x` ends with 0 but is not 0, it asks cannot be
 * a palindrome.
 * 
 * 2. Reversing the Number:
 * - We can build the reversed number by repeatedly extracting the last digit
 * (`x % 10`) and adding it
 * to a `reversed` variable (`reversed * 10 + digit`).
 * - Removing the last digit from `x` is done by `x /= 10`.
 * 
 * 3. Comparison:
 * - After the loop, if the `reversed` number equals the `original` number, then
 * it is a palindrome.
 * 
 * Note: A common optimization is to reverse only half of the number to avoid
 * potential overflow issues with `int`,
 * but since we used `long` for `reversed`, full reversal is safe and simple to
 * implement.
 * 
 * Complexity Analysis:
 * --------------------
 * Time Complexity: O(log10(N))
 * - We iterate through the digits of the number. The number of digits in N is
 * approximately log10(N).
 * 
 * Space Complexity: O(1)
 * - We use a constant amount of extra space for variables.
 */