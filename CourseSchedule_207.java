import java.util.*;

public class CourseSchedule_207 {
    public boolean canFinish(int numCourses, int[][] prerequisites) {
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

        int count = 0;
        while (!q.isEmpty()) {
            int node = q.poll();
            count++;
            for (int neighbor : adj.get(node)) {
                inDegree[neighbor]--;
                if (inDegree[neighbor] == 0) {
                    q.offer(neighbor);
                }
            }
        }

        return count == numCourses;
    }
}

/**
 * LeetCode 207. Course Schedule
 * 
 * Approach: Topological Sort (Kahn's Algorithm)
 * 1. This problem can be modeled as a directed graph where each course is a
 * node and a prerequisite [a, b]
 * is a directed edge from b to a (meaning b must be taken before a).
 * 2. We can use Kahn's algorithm for Topological Sorting to find if a directed
 * cycle exists.
 * 3. Calculate the in-degree of all vertices. If a node has an in-degree of 0,
 * it means it has no prerequisites
 * and can be taken immediately.
 * 4. Add all courses with an in-degree of 0 to a queue.
 * 5. While the queue is not empty, pop a course, increment a count of completed
 * courses, and for each of
 * its neighbor courses (courses that depend on it), decrement their in-degree
 * by 1.
 * 6. If any neighbor's in-degree becomes 0, add it to the queue.
 * 7. If the final count of completed courses equals the total number of
 * courses, then it's possible
 * to finish all courses (no cycle exists). Otherwise, it's impossible.
 */
