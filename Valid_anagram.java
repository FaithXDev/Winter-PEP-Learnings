import java.util.HashMap;

class Valid_anagram {
    public boolean isAnagram(String s, String t) {
        // If lengths are different, they cannot be anagrams
        if (s.length() != t.length())
            return false;

        // Map to count character frequencies
        HashMap<Character, Integer> map = new HashMap<>();

        // Count frequencies for string s
        for (char c : s.toCharArray()) {
            map.put(c, map.getOrDefault(c, 0) + 1);
        }

        // Decrement frequencies for string t
        for (char c : t.toCharArray()) {
            // If char from t is not in map, or count is already 0 (handled by removing),
            // fail
            if (!map.containsKey(c))
                return false;

            map.put(c, map.get(c) - 1);

            // If count reaches 0, remove the key to keep the map clean (and for potential
            // size check)
            if (map.get(c) == 0)
                map.remove(c);
        }

        // If map is empty, all counts balanced out exactly
        return map.isEmpty();
    }

    public static void main(String[] args) {
        Valid_anagram solution = new Valid_anagram();

        // Test case 1
        String s1 = "anagram", t1 = "nagaram";
        System.out.println("Input: s = \"" + s1 + "\", t = \"" + t1 + "\"");
        System.out.println("Output: " + solution.isAnagram(s1, t1) + " (Expected: true)");

        // Test case 2
        String s2 = "rat", t2 = "car";
        System.out.println("Input: s = \"" + s2 + "\", t = \"" + t2 + "\"");
        System.out.println("Output: " + solution.isAnagram(s2, t2) + " (Expected: false)");
    }
}

/*
 * EXPLANATION:
 * 
 * Approach: Frequency Counter (Hash Map)
 * --------------------------------------
 * An anagram is a word (or phrase) formed by rearranging the letters of a
 * different word or phrase,
 * typically using all the original letters exactly once.
 * This implies two conditions:
 * 1. Both strings must have the same length.
 * 2. Both strings must have the exact same count of every character.
 * 
 * 1. Implementation:
 * - Use a Hash Map to store characters and their counts from the first string
 * `s`.
 * - Iterate through the second string `t` and decrement the counts in the map.
 * - If at any point we find a character in `t` that is not in the map (or count
 * is 0), return false.
 * - Finally, check if the map is empty (or all counts are zero).
 * 
 * Alternative Approach: Sorting
 * - Sort both strings and compare them. Time: O(N log N).
 * 
 * Complexity Analysis:
 * --------------------
 * Time Complexity: O(N)
 * - We iterate through count string once. Hash Map operations are O(1) average.
 * 
 * Space Complexity: O(1)
 * - Although we use a map, the number of distinct characters is bounded (e.g.,
 * 26 for English alphabet).
 * Thus, space complexity is O(1) in terms of constant upper bound on charset
 * size.
 */