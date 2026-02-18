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
