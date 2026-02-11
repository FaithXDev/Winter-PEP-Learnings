public class Count_distinct_substring {

    // Node structure for Trie
    static class Node {
        Node[] links = new Node[26];

        boolean containsKey(char ch) {
            return links[ch - 'a'] != null;
        }

        void put(char ch, Node node) {
            links[ch - 'a'] = node;
        }

        Node get(char ch) {
            return links[ch - 'a'];
        }
    }

    public static int countSubs(String s) {
        Node root = new Node();
        int count = 0;
        int n = s.length();

        for (int i = 0; i < n; i++) {
            Node node = root;

            for (int j = i; j < n; j++) {
                if (!node.containsKey(s.charAt(j))) {
                    node.put(s.charAt(j), new Node());
                    count++; // new substring found
                }
                node = node.get(s.charAt(j));
            }
        }

        return count;
    }

    public static void main(String[] args) {
        // Test Case 1
        String s1 = "ab";
        System.out.println("String: " + s1);
        System.out.println("Distinct Substrings: " + countSubs(s1)); // Expected: 3 ("a", "ab", "b")

        // Test Case 2
        String s2 = "aa";
        System.out.println("String: " + s2);
        System.out.println("Distinct Substrings: " + countSubs(s2)); // Expected: 2 ("a", "aa")

        // Test Case 3
        String s3 = "abab";
        System.out.println("String: " + s3);
        System.out.println("Distinct Substrings: " + countSubs(s3)); // Expected: 7 ("a", "ab", "aba", "abab", "b",
                                                                     // "ba", "bab")
    }
}

/**
 * Approach:
 * 1. Use a Trie data structure to store substrings.
 * 2. Iterate through all possible suffixes of the string (starting from each
 * index `i`).
 * 3. For each suffix, iterate through its characters.
 * 4. Insert characters into the Trie.
 * 5. If a character is not present in the current node's children, it means we
 * found a new distinct substring.
 * - Create a new node.
 * - Increment the count.
 * 6. Move to the child node and continue.
 * 
 * Complexity Analysis:
 * - Time Complexity: O(N^2), where N is the length of the string.
 * - We have nested loops: outer loop runs N times, inner loop runs up to N
 * times.
 * - Trie operations (put, get, containsKey) are O(1).
 * - Total operations are roughly N * (N+1) / 2.
 * - Space Complexity: O(N^2) in the worst case.
 * - In the worst case (e.g., all characters are unique), we might create a node
 * for every substring.
 * - Total substrings are N*(N+1)/2.
 * - However, since alphabet size is small (26), the branching is limited.
 * - Strictly speaking, the number of nodes is bounded by the number of distinct
 * substrings.
 */
