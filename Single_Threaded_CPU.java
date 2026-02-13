import java.util.Arrays;
import java.util.PriorityQueue;

public class Single_Threaded_CPU {

    public int[] getOrder(int[][] tasks) {
        int n = tasks.length;
        // Store tasks with their original index: {enqueueTime, processingTime, index}
        int[][] sortedTasks = new int[n][3];
        for (int i = 0; i < n; i++) {
            sortedTasks[i][0] = tasks[i][0];
            sortedTasks[i][1] = tasks[i][1];
            sortedTasks[i][2] = i;
        }

        // Sort based on enqueueTime
        Arrays.sort(sortedTasks, (a, b) -> Integer.compare(a[0], b[0]));

        // Min-Heap to store available tasks.
        // Priority:
        // 1. Shortest processing time (a[1] vs b[1])
        // 2. Smallest index (a[2] vs b[2])
        PriorityQueue<int[]> minHeap = new PriorityQueue<>((a, b) -> {
            if (a[1] != b[1]) {
                return Integer.compare(a[1], b[1]);
            } else {
                return Integer.compare(a[2], b[2]);
            }
        });

        int[] result = new int[n];
        int resultIndex = 0;
        int taskIndex = 0;
        int currentTime = 0;

        while (resultIndex < n) {
            // Add all tasks that have arrived by currentTime to the heap
            while (taskIndex < n && sortedTasks[taskIndex][0] <= currentTime) {
                minHeap.offer(sortedTasks[taskIndex]);
                taskIndex++;
            }

            if (minHeap.isEmpty()) {
                // If the heap is empty but we still have tasks to process,
                // it means the CPU is idle. Fast-forward time to the next task's arrival.
                currentTime = sortedTasks[taskIndex][0];
            } else {
                // Process the task with the highest priority (shortest job / smallest index)
                int[] currentTask = minHeap.poll();
                currentTime += currentTask[1];
                result[resultIndex++] = currentTask[2];
            }
        }

        return result;
    }

    public static void main(String[] args) {
        Single_Threaded_CPU solution = new Single_Threaded_CPU();

        // Test Case 1
        int[][] tasks1 = { { 1, 2 }, { 2, 4 }, { 3, 2 }, { 4, 1 } };
        int[] result1 = solution.getOrder(tasks1);
        System.out.println("Order for tasks [[1,2],[2,4],[3,2],[4,1]]: " + Arrays.toString(result1));

        // Test Case 2
        int[][] tasks2 = { { 7, 10 }, { 7, 12 }, { 7, 5 }, { 7, 4 }, { 7, 2 } };
        int[] result2 = solution.getOrder(tasks2);
        System.out.println("Order for tasks [[7,10],[7,12],[7,5],[7,4],[7,2]]: " + Arrays.toString(result2));
    }
}
/**
 * Approach:
 * 1. Store the original index of each task since sorting will change the order.
 * We create an extended array where each element is {enqueueTime,
 * processingTime, originalIndex}.
 * 2. Sort all tasks based on their `enqueueTime`. This allows us to process
 * tasks in the order they arrive.
 * 3. Use a Min-Heap (PriorityQueue) to manage the "Ready Queue".
 * The heap orders tasks based on:
 * - Shortest `processingTime` first.
 * - If processing times are equal, smallest `originalIndex` first.
 * 4. Maintain a `currentTime` variable to simulate the CPU clock.
 * 5. Iterate until all tasks are processed:
 * - Add all tasks that have `enqueueTime <= currentTime` to the heap.
 * - If the heap is empty (CPU idle), fast-forward `currentTime` to the next
 * task's arrival time to avoid TLE.
 * - Poll the best task from the heap, adding its index to the result and
 * advancing `currentTime` by its duration.
 *
 * Complexity Analysis:
 * - Time Complexity: O(N log N)
 * - Sorting tasks takes O(N log N).
 * - Each task is pushed and popped from the heap exactly once. Heap operations
 * take O(log N).
 * - Total time: O(N log N).
 * - Space Complexity: O(N)
 * - To store the sorted tasks array and the elements in the heap.
 */
