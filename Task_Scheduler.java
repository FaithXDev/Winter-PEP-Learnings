import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.PriorityQueue;

public class Task_Scheduler {

    public int leastInterval(char[] tasks, int n) {
        // Frequency map (since tasks are 'A'-'Z', size 26 is sufficient)
        int[] map = new int[26];
        for (char c : tasks) {
            map[c - 'A']++;
        }

        // Max-Heap to store the frequencies of tasks
        PriorityQueue<Integer> maxHeap = new PriorityQueue<>(Collections.reverseOrder());
        for (int f : map) {
            if (f > 0) {
                maxHeap.offer(f);
            }
        }

        int time = 0;
        // Process tasks until the heap is empty
        while (!maxHeap.isEmpty()) {
            List<Integer> temp = new ArrayList<>();
            // Try to execute 'n + 1' tasks in a cycle (chunk of time)
            int cycle = n + 1;

            for (int i = 0; i < cycle; i++) {
                if (!maxHeap.isEmpty()) {
                    temp.add(maxHeap.poll());
                }
            }

            // Decrease frequency of processed tasks and add back to heap if they still
            // exist
            for (int freq : temp) {
                if (--freq > 0) {
                    maxHeap.offer(freq);
                }
            }

            // Calculate time taken for this cycle:
            // If the heap is empty after re-adding, it means we finished all tasks.
            // The time taken in this last cycle is exactly the number of tasks processed
            // (temp.size()).
            // If the heap is NOT empty, we must have used the full 'cycle' duration (n +
            // 1),
            // filling any gaps with idle time.
            time += maxHeap.isEmpty() ? temp.size() : cycle;
        }

        return time;
    }

}
/**
 * Approach:
 * 1. Count the frequency of each task.
 * 2. Use a Max-Heap to store the frequencies. This allows us to always
 * prioritize the task with the highest remaining count.
 * 3. Simulate the process in cycles of length `n + 1`.
 * - In each cycle, we try to pick `n + 1` distinct tasks from the heap to fill
 * the interval without idle time.
 * - If we cannot find `n + 1` tasks (heap runs out), the remaining slots in the
 * cycle must be idle time.
 * 4. After each cycle, updated frequencies (decremented by 1) are pushed back
 * into the heap if they are still > 0.
 * 5. If the heap becomes empty, it means we processed the last batch of tasks.
 * In this case, we don't count the idle slots at the end.
 *
 * Complexity Analysis:
 * - Time Complexity: O(N), where N is the number of tasks.
 * - Getting frequencies takes O(N).
 * - The heap operations take constant time O(1) effectively because the heap
 * size is capped at 26 (constant number of task types).
 * - Space Complexity: O(1)
 * - The frequency array and heap store at most 26 elements.
 */
