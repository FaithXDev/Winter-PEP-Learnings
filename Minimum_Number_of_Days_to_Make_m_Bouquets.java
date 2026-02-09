
public class Minimum_Number_of_Days_to_Make_m_Bouquets {

    public int minDays(int[] bloomDay, int m, int k) {
        long val = (long) m * k;
        if (val > bloomDay.length)
            return -1; // Not enough flowers to make m bouquets

        int min = Integer.MAX_VALUE;
        int max = Integer.MIN_VALUE;
        for (int day : bloomDay) {
            min = Math.min(min, day);
            max = Math.max(max, day);
        }

        int low = min;
        int high = max;
        int ans = -1;

        while (low <= high) {
            int mid = low + (high - low) / 2;
            if (possible(bloomDay, mid, m, k)) {
                ans = mid;
                high = mid - 1; // Try for a smaller number of days
            } else {
                low = mid + 1; // Need more days
            }
        }
        return ans;
    }

    // Helper function to check if we can make m bouquets by 'day'
    private boolean possible(int[] bloomDay, int day, int m, int k) {
        int count = 0;
        int bouquets = 0;
        for (int val : bloomDay) {
            if (val <= day) {
                count++;
                if (count == k) {
                    bouquets++;
                    count = 0; // Reset count after forming a bouquet
                }
            } else {
                count = 0; // Reset count if flowers are not adjacent
            }
        }
        return bouquets >= m;
    }

    public static void main(String[] args) {
        Minimum_Number_of_Days_to_Make_m_Bouquets solver = new Minimum_Number_of_Days_to_Make_m_Bouquets();

        // Test Case 1
        int[] bloomDay1 = { 1, 10, 3, 10, 2 };
        int m1 = 3;
        int k1 = 1;
        System.out.println("Test Case 1: " + solver.minDays(bloomDay1, m1, k1)); // Expected: 3

        // Test Case 2
        int[] bloomDay2 = { 1, 10, 3, 10, 2 };
        int m2 = 3;
        int k2 = 2;
        System.out.println("Test Case 2: " + solver.minDays(bloomDay2, m2, k2)); // Expected: -1

        // Test Case 3
        int[] bloomDay3 = { 7, 7, 7, 7, 12, 7, 7 };
        int m3 = 2;
        int k3 = 3;
        System.out.println("Test Case 3: " + solver.minDays(bloomDay3, m3, k3)); // Expected: 12
    }
}

/*
 * Approach: Binary Search on Answer
 * 1. The range of possible days is from the minimum bloom day to the maximum
 * bloom day in the array.
 * 2. We can perform a binary search on this range.
 * 3. For a given 'day' (mid), we check if it's possible to make 'm' bouquets.
 * 4. To check, we iterate through the bloomDay array. We count consecutive
 * flowers that have bloomed by 'day'.
 * If the count reaches 'k', we form a bouquet and reset the counter.
 * 5. If we can form at least 'm' bouquets, it means this 'day' is a possible
 * answer,
 * so we try for a smaller day (move right pointer to mid - 1) to find the
 * minimum.
 * 6. If we cannot form 'm' bouquets, we need more days (move left pointer to
 * mid + 1).
 * 7. Early exit: If (long) m * k > n (total flowers), it's impossible, return
 * -1.
 *
 * Complexity Analysis:
 * - Time Complexity: O(N * log(max(bloomDay) - min(bloomDay))), where N is the
 * number of flowers.
 * - Space Complexity: O(1).
 */
