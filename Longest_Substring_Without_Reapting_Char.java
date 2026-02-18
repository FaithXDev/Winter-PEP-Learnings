import java.util.HashMap;
import java.util.Map;

public class Longest_Substring_Without_Reapting_Char {
    public int lengthOfLongestSubstring(String s) {
        if (s == null || s.length() == 0) {
            return 0;
        }

        // Map stores the character and its most recent index
        Map<Character, Integer> map = new HashMap<>();
        int maxLength = 0;
        int left = 0; // Left pointer of the sliding window

        for (int right = 0; right < s.length(); right++) {
            char currentChar = s.charAt(right);

            // If we encounter a character that is already in the map
            if (map.containsKey(currentChar)) {
                // Determine new start position for the window.
                // We move 'left' to the right of the last occurrence of this character.
                // However, 'left' can only move forward, so we use Math.max to avoid moving it
                // back
                // if the repeating character was outside the current window.
                left = Math.max(left, map.get(currentChar) + 1);
            }

            // Update the last position of the character
            map.put(currentChar, right);

            // Calculate current window length and update max
            maxLength = Math.max(maxLength, right - left + 1);
        }

        return maxLength;
    }

}

/*
 * EXPLANATION:
 * 
 * Approach: Sliding Window with HashMap
 * -------------------------------------
 * The problem is to find the length of the longest substring without repeating
 * characters.
 * 
 * 1. Sliding Window:
 * - We use two pointers, `left` and `right`, to define a window `[left,
 * right]`.
 * - `right` moves forward from 0 to N-1.
 * - `left` only moves forward when a duplicate character is found.
 * 
 * 2. Hashing:
 * - A `HashMap` (or integer array for ASCII) is used to store the last known
 * index of each character.
 * - When we encounter a character at `right` that exists in the map:
 * - It implies a potential repeat.
 * - If the previous occurrence index is >= `left` (inside the current window),
 * we must shrink the window.
 * - We typically set `left` to `last_index + 1` so that the new window starts
 * just after the first occurrence of the repeated char.
 * - We use `Math.max(left, map.get(c) + 1)` because the repeated character
 * might have appeared BEFORE the current `left` pointer,
 * so we shouldn't move `left` backwards.
 * 
 * 3. Max Length:
 * - At every step, the window `[left, right]` is valid (contains unique chars).
 * - Length is `right - left + 1`. We update `maxLength`.
 * 
 * Complexity Analysis:
 * --------------------
 * Time Complexity: O(N)
 * - We traverse the string once with the `right` pointer.
 * - The `left` pointer also moves forward at most N times.
 * - HashMap operations are O(1) on average.
 * 
 * Space Complexity: O(min(N, M))
 * - O(N) in the worst case, or more specifically O(M) where M is the size of
 * the charset (e.g., 26 for lowercase English, 128 for ASCII).
 */
