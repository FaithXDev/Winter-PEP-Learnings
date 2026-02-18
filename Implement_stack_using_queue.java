import java.util.LinkedList;
import java.util.Queue;

public class Implement_stack_using_queue {

    private Queue<Integer> q1;
    private Queue<Integer> q2;

    public Implement_stack_using_queue() {
        q1 = new LinkedList<>();
        q2 = new LinkedList<>();
    }

    // Push operation - O(1)
    public void push(int x) {
        q1.add(x);
    }

    // Pop operation - O(n)
    public int pop() {
        if (q1.isEmpty()) {
            throw new RuntimeException("Stack is empty");
        }

        // Move all elements except the last one from q1 to q2
        while (q1.size() > 1) {
            q2.add(q1.poll());
        }

        // The last element in q1 is the top element
        int topElement = q1.poll();

        // Swap the names of q1 and q2
        Queue<Integer> temp = q1;
        q1 = q2;
        q2 = temp;

        return topElement;
    }

    // Top operation - O(n)
    public int top() {
        if (q1.isEmpty()) {
            throw new RuntimeException("Stack is empty");
        }

        // Move all elements except the last one from q1 to q2
        while (q1.size() > 1) {
            q2.add(q1.poll());
        }

        // The last element in q1 is the top element
        int topElement = q1.poll();
        q2.add(topElement); // Add it back to q2

        // Swap the names of q1 and q2
        Queue<Integer> temp = q1;
        q1 = q2;
        q2 = temp;

        return topElement;
    }

    // Empty operation - O(1)
    public boolean empty() {
        return q1.isEmpty();
    }

}

/*
 * Approach: Using Two Queues (Push Efficient)
 * 
 * This implementation makes the push operation efficient (O(1)) at the cost of
 * making pop and top operations less efficient (O(n)).
 * 
 * Algorithm:
 * 1. Push Operation:
 * - Simply add the element to the primary queue (q1).
 * 
 * 2. Pop Operation:
 * - To remove the last inserted element (LIFO), we need to access the end of
 * the queue.
 * - Move all elements from q1 to q2 except the last one.
 * - The last element remaining in q1 is the top element of the stack.
 * - Remove and return it.
 * - Swap the names of q1 and q2 so that q1 is again the primary queue.
 * 
 * 3. Top Operation:
 * - Similar to pop, move all elements except the last one from q1 to q2.
 * - The last element is the top element.
 * - Add it back to q2 (since we are just peeking, not removing).
 * - Swap the names of q1 and q2.
 * 
 * Complexity Analysis:
 * - Push: O(1)
 * - Pop: O(n)
 * - Top: O(n)
 * - Empty: O(1)
 * 
 * Alternative (Pop Efficient):
 * - Push: O(n) - Move all existing elements to q2, add new element to q1,
 * move back from q2 to q1.
 * - Pop: O(1) - Just poll from q1.
 * - Top: O(1) - Just peek at q1.
 */
