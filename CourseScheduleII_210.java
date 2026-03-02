import java.util.*;

public class CourseScheduleII_210 {
    public int[] findOrder(int numCourses, int[][] prerequisites) {
        List<List<Integer>> adj = new ArrayList<>();
        for (int i = 0; i < numCourses; i++) {
            adj.add(new ArrayList<>());
        }

        int[] inDegree = new int[numCourses];
        for (int[] pre : prerequisites) {
            adj.get(pre[1]).add(pre[0]);
            inDegree[pre[0]]++;
        }

        Queue<Integer> q = new LinkedList<>();
        for (int i = 0; i < numCourses; i++) {
            if (inDegree[i] == 0) {
                q.offer(i);
            }
        }

        int[] order = new int[numCourses];
        int idx = 0;

        while (!q.isEmpty()) {
            int node = q.poll();
            order[idx++] = node;

            for (int neighbor : adj.get(node)) {
                inDegree[neighbor]--;
                if (inDegree[neighbor] == 0) {
                    q.offer(neighbor);
                }
            }
        }

        if (idx == numCourses) {
            return order;
        }

        return new int[0];
    }
}

/**
 * LeetCode 210. Course Schedule II
 * 
 * Approach: Topological Sort (Kahn's Algorithm)
 * 1. This is a follow-up to Course Schedule I. Instead of just checking if it
 * is possible to finish
 * all courses, we need to return the exact order in which they should be taken.
 * 2. We build a directed graph from the prerequisites array. `[a, b]` means an
 * edge `b -> a`.
 * 3. Compute the in-degree of each course. A course with an in-degree of 0 has
 * no prerequisites pending.
 * 4. Initialize a Queue and add all courses with an in-degree of 0.
 * 5. We maintain an `order` array of size `numCourses` and an `index` pointer
 * starting at 0 to store the result.
 * 6. Poll from the queue, place the polled course into the `order` array at
 * `index`, and increment `index`.
 * 7. For the polled course, decrement the in-degree of all its neighbors. If a
 * neighbor's in-degree
 * reaches 0, push it to the queue.
 * 8. If `index` matches `numCourses`, return the `order` array. Otherwise, a
 * cycle exists, so return an empty array.
 */
