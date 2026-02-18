import java.util.Stack;

public class Daily_Temperatures {

    /**
     * Solves the Daily Temperatures problem using a monotonic stack.
     * 
     * @param temperatures An array of daily temperatures.
     * @return An array where answer[i] is the number of days to wait for a warmer
     *         temperature.
     */
    public int[] dailyTemperatures(int[] temperatures) {
        if (temperatures == null || temperatures.length == 0) {
            return new int[0];
        }

        int n = temperatures.length;
        int[] result = new int[n];
        // Stack stores indices of the temperatures array
        Stack<Integer> stack = new Stack<>();

        for (int i = 0; i < n; i++) {
            int currentTemp = temperatures[i];

            // While stack is not empty and the current temperature is warmer than the
            // temperature at the index on top of the stack
            while (!stack.isEmpty() && currentTemp > temperatures[stack.peek()]) {
                int prevIndex = stack.pop();
                result[prevIndex] = i - prevIndex;
            }

            // Push the current index onto the stack
            stack.push(i);
        }

        // Any indices remaining in the stack have no warmer future day
        // result[i] is already 0 by default initialization

        return result;
    }


}

/*
 * Approach: Monotonic Stack
 * 
 * This problem asks for the next greater element for each element in the array,
 * but specifically the next greater element in terms of index (i.e., the first
 * element to the right that is greater).
 * 
 * A monotonic stack (in this case, a monotonically decreasing stack) is ideal
 * for this type of problem.
 * 
 * Algorithm:
 * 1. Initialize an empty stack to store indices of the temperatures array.
 * 2. Initialize a result array of the same size as temperatures, filled with
 * 0s.
 * 3. Iterate through the temperatures array from left to right (index i):
 * a. While the stack is not empty AND the current temperature is greater than
 * the temperature at the index on top of the stack:
 * i. Pop the index from the stack (let's call it prevIndex).
 * ii. The current index i is the next warmer day for prevIndex.
 * iii. Calculate the difference: result[prevIndex] = i - prevIndex.
 * b. Push the current index i onto the stack.
 * 4. After the loop finishes, any indices remaining in the stack do not have a
 * warmer future day, so their corresponding values in the result array remain
 * 0.
 * 
 * Why this works:
 * The stack maintains a list of indices whose next warmer day has not yet been
 * found. The stack is kept in decreasing order of temperatures (from bottom to
 * top). When we encounter a new temperature, we check if it's warmer than the
 * elements at the top of the stack. If it is, we've found the next warmer day
 * for those elements and can pop them. We continue this process until we find
 * an element on the stack that is greater than or equal to the current
 * temperature, or until the stack is empty.
 * 
 * Complexity Analysis:
 * - Time Complexity: O(n)
 * Each index is pushed onto the stack exactly once and popped from the stack
 * at most once. Therefore, the total number of stack operations is proportional
 * to n.
 * - Space Complexity: O(n)
 * In the worst case (e.g., a strictly decreasing temperature array like [90,
 * 80,
 * 70, 60]), all indices will be stored in the stack simultaneously.
 */
