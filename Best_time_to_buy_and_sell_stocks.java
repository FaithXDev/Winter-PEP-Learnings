public class Best_time_to_buy_and_sell_stocks {
    public int maxProfit(int[] prices) {
        // Handle edge cases: null or array with less than 2 elements
        if (prices == null || prices.length < 2) {
            return 0;
        }

        int maxProfit = 0;
        int minPrice = prices[0]; // Assume the first day is the minimum price initially

        for (int i = 1; i < prices.length; i++) {
            // Calculate potential profit if we sell today
            int profitToday = prices[i] - minPrice;

            // If profit today is greater than maxProfit seen so far, update maxProfit
            if (profitToday > maxProfit) {
                maxProfit = profitToday;
            }

            // If prices[i] is lower than minPrice, update minPrice for future transactions
            // Note: We update minPrice AFTER checking profit because we can't buy and sell
            // on the same day
            // to make a profit (technically we can but profit is 0), but logically we look
            // for a low buy price
            // for FUTURE sell dates.
            if (prices[i] < minPrice) {
                minPrice = prices[i];
            }
        }

        return maxProfit;
    }

}

/*
 * EXPLANATION:
 * 
 * Approach: One Pass (Greedy)
 * ---------------------------
 * The problem basically asks us to find the maximum difference between two
 * numbers in the array `prices[j] - prices[i]`
 * such that `j > i` (sell day must be after buy day).
 * 
 * 1. Tracking Variables:
 * - `minPrice`: Tracks the lowest price we have seen so far as we iterate
 * through the list.
 * - `maxProfit`: Tracks the maximum profit we have calculated so far.
 * 
 * 2. Iteration:
 * - For every price `prices[i]`, we treat it as a potential "selling price".
 * - The maximum profit we can make selling on day `i` is `prices[i] - minPrice`
 * (where `minPrice` is the lowest price from day 0 to i-1).
 * - We update `maxProfit` if this potential profit is higher than previous
 * `maxProfit`.
 * - We update `minPrice` if `prices[i]` is lower than our current `minPrice`.
 * 
 * Complexity Analysis:
 * --------------------
 * Time Complexity: O(N)
 * - We iterate through the `prices` array exactly once.
 * 
 * Space Complexity: O(1)
 * - We only use two variables (`minPrice` and `maxProfit`) regardless of the
 * input size.
 */
