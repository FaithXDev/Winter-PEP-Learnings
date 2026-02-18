import java.util.PriorityQueue;

public class K_Closest_Points_to_Origin {

    public int[][] kClosest(int[][] points, int k) {
        // Max-Heap: keeps the K smallest elements.
        // We use a custom comparator to order by distance in descending order.
        // This way, the farthest point is always at the root and can be removed when
        // the heap size exceeds K.
        PriorityQueue<int[]> maxHeap = new PriorityQueue<>((a, b) -> Integer.compare(
                (b[0] * b[0] + b[1] * b[1]),
                (a[0] * a[0] + a[1] * a[1])));

        for (int[] point : points) {
            maxHeap.offer(point);
            if (maxHeap.size() > k) {
                maxHeap.poll(); // Remove the farthest point
            }
        }

        int[][] result = new int[k][2];
        int i = 0;
        while (!maxHeap.isEmpty()) {
            result[i++] = maxHeap.poll();
        }

        return result;
    }

}
/**
 * Approach:
 * 1. Use a Max-Heap (PriorityQueue with reverse order comparator) to keep track
 * of the K closest points.
 * 2. The comparator calculates the squared Euclidean distance: x^2 + y^2. We
 * prefer squared distance to avoid floating point `Math.sqrt` operations.
 * 3. Iterate through each point in the input array:
 * - Add the point to the heap.
 * - If the heap size exceeds K, remove the top element (which is the point with
 * the largest distance in the heap).
 * 4. After iterating through all points, the heap will contain exactly the K
 * closest points.
 * 5. Extract the points from the heap into the result array.
 *
 * Complexity Analysis:
 * - Time Complexity: O(N log K)
 * - We iterate through N points.
 * - Each insertion and removal from the heap takes O(log K) time since the heap
 * size is capped at K.
 * - Space Complexity: O(K)
 * - The heap stores at most K elements at any time.
 */
