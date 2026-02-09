
public class Minimum_Size_Subarray_Sum {

    /**
     * Solves the Minimum Size Subarray Sum problem using the sliding window
     * technique.
     * 
     * @param target The target sum to achieve.
     * @param nums   An array of positive integers.
     * @return The minimal length of a subarray whose sum is >= target, or 0 if
     *         none exists.
     */
    public int minSubArrayLen(int target, int[] nums) {
        if (nums == null || nums.length == 0) {
            return 0;
        }

        int n = nums.length;
        int minLength = Integer.MAX_VALUE;
        int currentSum = 0;
        int left = 0;

        // Iterate through the array with the right pointer
        for (int right = 0; right < n; right++) {
            // Add the current element to the sum
            currentSum += nums[right];

            // While the current sum is greater than or equal to the target, try to
            // shrink the window from the left
            while (currentSum >= target) {
                // Update the minimum length found so far
                minLength = Math.min(minLength, right - left + 1);

                // Subtract the leftmost element and move the left pointer
                currentSum -= nums[left];
                left++;
            }
        }

        // If minLength is still Integer.MAX_VALUE, it means no subarray was found
        return minLength == Integer.MAX_VALUE ? 0 : minLength;
    }

    public static void main(String[] args) {
        Minimum_Size_Subarray_Sum solver = new Minimum_Size_Subarray_Sum();

        // Test Case 1
        int target1 = 7;
        int[] nums1 = { 2, 3, 1, 2, 4, 3 };
        System.out.println("Test Case 1:");
        System.out.println("Target: " + target1 + ", Nums: " + arrayToString(nums1));
        System.out.println("Result: " + solver.minSubArrayLen(target1, nums1));
        System.out.println("Expected: 2");
        System.out.println();

        // Test Case 2
        int target2 = 4;
        int[] nums2 = { 1, 4, 4 };
        System.out.println("Test Case 2:");
        System.out.println("Target: " + target2 + ", Nums: " + arrayToString(nums2));
        System.out.println("Result: " + solver.minSubArrayLen(target2, nums2));
        System.out.println("Expected: 1");
        System.out.println();

        // Test Case 3
        int target3 = 11;
        int[] nums3 = { 1, 1, 1, 1, 1, 1, 1, 1 };
        System.out.println("Test Case 3:");
        System.out.println("Target: " + target3 + ", Nums: " + arrayToString(nums3));
        System.out.println("Result: " + solver.minSubArrayLen(target3, nums3));
        System.out.println("Expected: 0");
        System.out.println();

        // Test Case 4
        int target4 = 15;
        int[] nums4 = { 1, 2, 3, 4, 5 };
        System.out.println("Test Case 4:");
        System.out.println("Target: " + target4 + ", Nums: " + arrayToString(nums4));
        System.out.println("Result: " + solver.minSubArrayLen(target4, nums4));
        System.out.println("Expected: 5");
        System.out.println();
    }

    private static String arrayToString(int[] arr) {
        StringBuilder sb = new StringBuilder("[");
        for (int i = 0; i < arr.length; i++) {
            sb.append(arr[i]);
            if (i < arr.length - 1) {
                sb.append(", ");
            }
        }
        sb.append("]");
        return sb.toString();
    }
}

/*
 * Approach: Sliding Window
 * 
 * This problem asks for the smallest subarray that meets a certain condition
 * (sum >= target). This is a classic pattern for the sliding window technique.
 * 
 * Algorithm:
 * 1. Initialize two pointers, 'left' and 'right', both starting at index 0.
 * 2. Initialize 'currentSum' to 0 and 'minLength' to a value larger than any
 * possible result (e.g., Integer.MAX_VALUE).
 * 3. Expand the window by moving the 'right' pointer one step at a time:
 * a. Add the element at the 'right' index to 'currentSum'.
 * b. Check if 'currentSum' is greater than or equal to 'target'.
 * 4. If 'currentSum' >= 'target', we have a valid subarray. Now try to minimize
 * its size:
 * a. Update 'minLength' with the current window size (right - left + 1).
 * b. Shrink the window from the left by subtracting the element at the 'left'
 * index from 'currentSum' and incrementing 'left'.
 * c. Repeat this step as long as 'currentSum' remains >= 'target'.
 * 5. Continue expanding the window by moving 'right' until it reaches the end
 * of the array.
 * 6. After the loop, if 'minLength' is still its initial large value, it means
 * no valid subarray was found, so return 0. Otherwise, return 'minLength'.
 * 
 * Why this works:
 * The sliding window approach ensures that we check every possible contiguous
 * subarray in an efficient manner. When the sum condition is met, we
 * immediately
 * try to shrink the window from the left to find the smallest possible subarray
 * starting at that 'left' position. By systematically expanding and shrinking,
 * we guarantee that we find the overall minimum length.
 * 
 * Complexity Analysis:
 * - Time Complexity: O(n)
 * Although there is a nested while loop, the 'left' pointer only moves forward
 * and can traverse the array at most once. Therefore, each element is visited
 * at most twice (once by the 'right' pointer and once by the 'left' pointer).
 * - Space Complexity: O(1)
 * We only use a few variables to keep track of the sum and pointers, regardless
 * of the input size.
 */
