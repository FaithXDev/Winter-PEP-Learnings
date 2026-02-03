import java.util.Arrays;
import java.util.Stack;

/**
 * LeetCode 735: Asteroid Collision
 */
public class asteroid_collision {

    public int[] asteroidCollision(int[] asteroids) {
        Stack<Integer> stack = new Stack<>();

        for (int asteroid : asteroids) {
            boolean survived = true;

            while (!stack.isEmpty() && asteroid < 0 && stack.peek() > 0) {
                int top = stack.peek();

                if (top < Math.abs(asteroid)) {
                    stack.pop();
                } else if (top == Math.abs(asteroid)) {
                    stack.pop();
                    survived = false;
                    break;
                } else {
                    survived = false;
                    break;
                }
            }

            if (survived) {
                stack.push(asteroid);
            }
        }

        int[] result = new int[stack.size()];
        for (int i = result.length - 1; i >= 0; i--) {
            result[i] = stack.pop();
        }

        return result;
    }

    public static void main(String[] args) {
        asteroid_collision solution = new asteroid_collision();

        // Test Case 1
        int[] test1 = { 5, 10, -5 };
        System.out.println("Test 1: " + Arrays.toString(test1));
        System.out.println("Result: " + Arrays.toString(solution.asteroidCollision(test1)));
        System.out.println("Expected: [5, 10]");
        System.out.println();

        // Test Case 2
        int[] test2 = { 8, -8 };
        System.out.println("Test 2: " + Arrays.toString(test2));
        System.out.println("Result: " + Arrays.toString(solution.asteroidCollision(test2)));
        System.out.println("Expected: []");
        System.out.println();

        // Test Case 3
        int[] test3 = { 10, 2, -5 };
        System.out.println("Test 3: " + Arrays.toString(test3));
        System.out.println("Result: " + Arrays.toString(solution.asteroidCollision(test3)));
        System.out.println("Expected: [10]");
        System.out.println();

        // Test Case 4
        int[] test4 = { -2, -1, 1, 2 };
        System.out.println("Test 4: " + Arrays.toString(test4));
        System.out.println("Result: " + Arrays.toString(solution.asteroidCollision(test4)));
        System.out.println("Expected: [-2, -1, 1, 2]");
        System.out.println();

        // Test Case 5
        int[] test5 = { 1, 2, 3, -10 };
        System.out.println("Test 5: " + Arrays.toString(test5));
        System.out.println("Result: " + Arrays.toString(solution.asteroidCollision(test5)));
        System.out.println("Expected: [-10]");
    }
}

/*
 * ==================== APPROACH ====================
 * 
 * Problem Description:
 * We are given an array of integers representing asteroids in a row.
 * - Absolute value = size of asteroid
 * - Sign = direction (positive = right, negative = left)
 * - All asteroids move at the same speed
 * 
 * Rules:
 * - If two asteroids meet, the smaller one explodes
 * - If both are same size, both explode
 * - Asteroids moving same direction never meet
 * 
 * Key Insight:
 * Collision only happens when a RIGHT-moving asteroid (positive)
 * is followed by a LEFT-moving asteroid (negative).
 * 
 * Algorithm (Stack-based):
 * 1. Iterate through each asteroid
 * 2. If positive (moving right) → push to stack
 * 3. If negative (moving left) → check for collisions:
 * - While stack top is positive AND smaller → pop (it explodes)
 * - If stack top is positive AND equal → pop both (both explode)
 * - If stack top is positive AND larger → current explodes (don't push)
 * - If no collision possible → push current asteroid
 * 4. Convert stack to result array
 * 
 * Why Stack?
 * Stack naturally handles the "last in, first out" nature of collisions.
 * The most recent right-moving asteroid is the first to collide with
 * an incoming left-moving asteroid.
 * 
 * Time Complexity: O(n)
 * - Each asteroid is pushed at most once
 * - Each asteroid is popped at most once
 * - Total operations = O(2n) = O(n)
 * 
 * Space Complexity: O(n)
 * - In worst case (no collisions), stack holds all n asteroids
 * 
 * Example Walkthrough: [5, 10, -5]
 * - 5 (positive) → push → stack: [5]
 * - 10 (positive) → push → stack: [5, 10]
 * - -5 (negative) → collision check with 10
 * - |−5| < 10, so -5 explodes
 * - Final stack: [5, 10] ✓
 */
