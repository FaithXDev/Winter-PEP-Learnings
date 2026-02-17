class Candy {

    public int candy(int[] ratings) {
        int n = ratings.length;
        int[] candies = new int[n];

        // Step 1: Give every child at least 1 candy
        for (int i = 0; i < n; i++) {
            candies[i] = 1;
        }

        // Step 2: Left-to-right pass
        // If current child has a higher rating than the left neighbor,
        // give them one more candy than the left neighbor
        for (int i = 1; i < n; i++) {
            if (ratings[i] > ratings[i - 1]) {
                candies[i] = candies[i - 1] + 1;
            }
        }

        // Step 3: Right-to-left pass
        // If current child has a higher rating than the right neighbor,
        // ensure they have more candies than the right neighbor
        for (int i = n - 2; i >= 0; i--) {
            if (ratings[i] > ratings[i + 1]) {
                candies[i] = Math.max(candies[i], candies[i + 1] + 1);
            }
        }

        // Step 4: Sum up all candies
        int totalCandies = 0;
        for (int candy : candies) {
            totalCandies += candy;
        }

        return totalCandies;
    }

    // Main method for testing
    public static void main(String[] args) {
        Candy solution = new Candy();

        // Test Case 1: ratings = [1, 0, 2] -> Output: 5
        int[] ratings1 = { 1, 0, 2 };
        System.out.println("Test 1: " + solution.candy(ratings1)); // Expected: 5

        // Test Case 2: ratings = [1, 2, 2] -> Output: 4
        int[] ratings2 = { 1, 2, 2 };
        System.out.println("Test 2: " + solution.candy(ratings2)); // Expected: 4

        // Test Case 3: ratings = [1, 3, 2, 2, 1] -> Output: 7
        int[] ratings3 = { 1, 3, 2, 2, 1 };
        System.out.println("Test 3: " + solution.candy(ratings3)); // Expected: 7
    }
}

// ==================== APPROACH ====================
//
// Problem Understanding:
// - We have n children in a line, each with a rating.
// - Each child must get at least 1 candy.
// - A child with a HIGHER rating than a neighbor must get MORE candies
// than that neighbor.
// - We need to find the MINIMUM total candies required.
//
// Approach: Two-Pass Greedy
//
// Intuition:
// - We can't satisfy both left and right neighbor constraints in a single pass.
// - So we break it into two passes:
// Pass 1 (Left to Right): Satisfy the left neighbor constraint.
// Pass 2 (Right to Left): Satisfy the right neighbor constraint.
// - Taking the maximum of both passes at each position gives us the
// minimum candies that satisfy BOTH constraints simultaneously.
//
// Algorithm:
// 1. Create a candies[] array of size n, initialized to 1 (minimum candy).
//
// 2. LEFT-TO-RIGHT pass (i = 1 to n-1):
// - If ratings[i] > ratings[i-1], then candies[i] = candies[i-1] + 1
// - This ensures every child with a higher rating than their LEFT
// neighbor gets more candies than them.
//
// 3. RIGHT-TO-LEFT pass (i = n-2 down to 0):
// - If ratings[i] > ratings[i+1], then
// candies[i] = max(candies[i], candies[i+1] + 1)
// - We use max() to preserve the result from the left pass.
// - This ensures every child with a higher rating than their RIGHT
// neighbor gets more candies than them.
//
// 4. Sum up all values in candies[] for the answer.
//
// Dry Run:
// ratings = [1, 0, 2]
//
// Step 1 - Initialize: candies = [1, 1, 1]
//
// Step 2 - Left to Right:
// i=1: ratings[1]=0 <= ratings[0]=1 → no change → candies = [1, 1, 1]
// i=2: ratings[2]=2 > ratings[1]=0 → candies[2]=2 → candies = [1, 1, 2]
//
// Step 3 - Right to Left:
// i=1: ratings[1]=0 <= ratings[2]=2 → no change → candies = [1, 1, 2]
// i=0: ratings[0]=1 > ratings[1]=0 → max(1, 1+1)=2 → candies = [2, 1, 2]
//
// Step 4 - Sum = 2 + 1 + 2 = 5 ✓
//
// Time Complexity: O(n) — Two linear passes + one summation pass.
// Space Complexity: O(n) — For the candies[] array.
//
// Why Greedy Works:
// - The left pass guarantees increasing candy counts for increasing
// ratings when viewed from left to right.
// - The right pass guarantees the same from right to left.
// - By taking the max at each index during the right pass, we satisfy
// both directions simultaneously with the minimum possible candies.
//
// Key Insight:
// - Children with equal ratings as their neighbor do NOT need more candies.
// Only STRICTLY higher ratings require more candies.
// =================================================
