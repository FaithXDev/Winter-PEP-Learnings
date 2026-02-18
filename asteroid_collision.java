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
