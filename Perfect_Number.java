public class Perfect_Number {
    /**
     * Checks if a number is a "Perfect Number".
     * A perfect number is a positive integer that is equal to the sum of its
     * positive divisors, excluding the number itself.
     */
    public boolean checkPerfectNumber(int num) {
        // 1 is not a perfect number (sum of divisors excluding itself is 0)
        if (num <= 1)
            return false;

        int sum = 1; // Start with 1 because 1 is always a divisor

        // Iterate from 2 to sqrt(num) to find divisors
        for (int i = 2; i * i <= num; i++) {
            if (num % i == 0) {
                sum += i; // Add the divisor

                // Add the corresponding divisor pair (num / i), if it's different (avoid adding
                // square root twice)
                if (i != num / i) {
                    sum += num / i;
                }
            }
        }
        // Check if the sum of divisors equals the original number
        return sum == num;
    }

    public static void main(String[] args) {
        Perfect_Number solution = new Perfect_Number();

        // Test case 1
        int num1 = 28;
        System.out.println("Input: " + num1);
        System.out.println("Output: " + solution.checkPerfectNumber(num1) + " (Expected: true)");
        // Divisors of 28: 1, 2, 4, 7, 14. Sum = 1 + 2 + 4 + 7 + 14 = 28.

        // Test case 2
        int num2 = 7;
        System.out.println("Input: " + num2);
        System.out.println("Output: " + solution.checkPerfectNumber(num2) + " (Expected: false)");
        // Divisors of 7: 1. Sum = 1 != 7.

        // Test case 3
        int num3 = 496;
        System.out.println("Input: " + num3);
        System.out.println("Output: " + solution.checkPerfectNumber(num3) + " (Expected: true)");
    }
}

/*
 * EXPLANATION:
 * 
 * Approach: Math / Divisor Enumeration
 * ------------------------------------
 * A perfect number is equal to the sum of its proper divisors (divisors
 * excluding the number itself).
 * 
 * 1. Constraints/Base Cases:
 * - If `num` <= 1, it generally isn't considered perfect (1's proper sum is 0).
 * 
 * 2. Finding Divisors:
 * - To find all divisors efficiently, we iterate from `i = 2` up to
 * `sqrt(num)`.
 * - If `num % i == 0`, then `i` is a divisor.
 * - Consequently, `num / i` is also a divisor.
 * - We add both `i` and `num / i` to the sum.
 * - Special Case: If `i * i == num`, we only add `i` once.
 * 
 * 3. Initial Sum:
 * - We start `sum = 1` because 1 is a divisor for all positive integers > 1.
 * 
 * Complexity Analysis:
 * --------------------
 * Time Complexity: O(sqrt(N))
 * - We only iterate up to the square root of N.
 * 
 * Space Complexity: O(1)
 * - Constant extra space used.
 */