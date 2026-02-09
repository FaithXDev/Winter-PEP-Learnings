public class capacity_to_ship_packages_within_d_day {
    public int shipWithinDays(int[] weights, int days) {
        int low = 0;
        int high = 0;

        // Determine the range for binary search
        // low = max weight (minimum possible capacity to carry the heaviest single
        // package)
        // high = sum of weights (maximum capacity needed to ship everything in 1 day)
        for (int w : weights) {
            low = Math.max(low, w);
            high += w;
        }

        int ans = high;

        while (low <= high) {
            int mid = low + (high - low) / 2;

            if (canShip(weights, days, mid)) {
                ans = mid; // This capacity works, try smaller
                high = mid - 1;
            } else {
                low = mid + 1; // Need more capacity
            }
        }
        return ans;
    }

    /**
     * Helper function to check if it's possible to ship all packages within 'days'
     * with a given capacity.
     */
    private boolean canShip(int[] weights, int days, int capacity) {
        int daysUsed = 1;
        int currentLoad = 0;

        for (int w : weights) {
            if (currentLoad + w > capacity) {
                daysUsed++;
                currentLoad = 0;
            }
            currentLoad += w;
        }

        return daysUsed <= days;
    }

    public static void main(String[] args) {
        capacity_to_ship_packages_within_d_day solution = new capacity_to_ship_packages_within_d_day();

        // Test Case 1
        int[] weights1 = { 1, 2, 3, 4, 5, 6, 7, 8, 9, 10 };
        int days1 = 5;
        System.out.println("Test Case 1:");
        System.out.println("Expected: 15, Actual: " + solution.shipWithinDays(weights1, days1));
        System.out.println();

        // Test Case 2
        int[] weights2 = { 3, 2, 2, 4, 1, 4 };
        int days2 = 3;
        System.out.println("Test Case 2:");
        System.out.println("Expected: 6, Actual: " + solution.shipWithinDays(weights2, days2));
        System.out.println();

        // Test Case 3
        int[] weights3 = { 1, 2, 3, 1, 1 };
        int days3 = 4;
        System.out.println("Test Case 3:");
        System.out.println("Expected: 3, Actual: " + solution.shipWithinDays(weights3, days3));
        System.out.println();
    }
}

/*
 * Approach:
 * We use Binary Search on the set of possible answer values (the capacity).
 * The search space is defined by:
 * - Lower Bound (low): The maximum weight in the array. The ship must be able
 * to carry the heaviest package.
 * - Upper Bound (high): The sum of all weights. This is the capacity if we
 * shipped everything in 1 day.
 * 
 * Algorithm:
 * 1. Initialize low and high.
 * 2. While low <= high:
 * a. Calculate mid (current capacity to test).
 * b. Check if it's possible to ship within 'days' using 'mid' capacity using a
 * greedy approach.
 * c. If possible, 'mid' might be the answer, but we try to find a smaller one
 * (high = mid - 1).
 * d. If not possible, we need a larger capacity (low = mid + 1).
 * 3. Return the smallest valid capacity found.
 * 
 * Complexity Analysis:
 * - Time Complexity: O(N * log(S)), where N is the number of weights and S is
 * the sum of weights (range size).
 * - The canShip function takes O(N).
 * - The binary search runs log(S) times (or log(sum - max)).
 * 
 * - Space Complexity: O(1)
 * - We only use a few integer variables.
 */