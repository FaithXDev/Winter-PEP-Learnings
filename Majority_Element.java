public class Majority_Element {

    /**
     * Finds the majority element in an integer array.
     * The majority element is the element that appears more than n / 2 times.
     * 
     * @param nums The input array of integers
     * @return The majority element
     */
    public static int majorityElement(int[] nums) {
        int candidate = 0;
        int count = 0;

        for (int num : nums) {
            if (count == 0) {
                candidate = num;
            }

            count += (num == candidate) ? 1 : -1;
        }

        return candidate;
    }

    public static void main(String[] args) {
        // Test Case 1: Simple case
        int[] nums1 = { 3, 2, 3 };
        System.out.println("Test Case 1: Input: [3, 2, 3]");
        System.out.println("Majority Element: " + majorityElement(nums1));
        // Expected: 3
        System.out.println();

        // Test Case 2: Array with more elements
        int[] nums2 = { 2, 2, 1, 1, 1, 2, 2 };
        System.out.println("Test Case 2: Input: [2, 2, 1, 1, 1, 2, 2]");
        System.out.println("Majority Element: " + majorityElement(nums2));
        // Expected: 2
        System.out.println();

        // Test Case 3: Single element
        int[] nums3 = { 5 };
        System.out.println("Test Case 3: Input: [5]");
        System.out.println("Majority Element: " + majorityElement(nums3));
        // Expected: 5
        System.out.println();
    }
}

/*
 * Approach: Boyer-Moore Voting Algorithm
 * 
 * 1. Initialize two variables: `candidate` and `count`.
 * 2. Iterate through the array:
 * - If `count` is 0, set the current element as the `candidate`.
 * - If the current element is equal to the `candidate`, increment `count`.
 * - If the current element is different from the `candidate`, decrement
 * `count`.
 * 3. By the end of the iteration, the `candidate` will hold the majority
 * element.
 * 
 * Intuition:
 * The algorithm works by cancelling out each occurrence of an element with a
 * different element.
 * Since the majority element appears more than n/2 times, it will survive the
 * cancellations.
 * 
 * Time Complexity:
 * - O(n): We pass through the array exactly once.
 * 
 * Space Complexity:
 * - O(1): We use only two variables (`candidate` and `count`), regardless of
 * the input size.
 */
