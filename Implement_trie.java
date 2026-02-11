
public class Implement_trie {

    // TrieNode class definition
    private class TrieNode {
        private TrieNode[] children;
        private boolean isEnd;

        public TrieNode() {
            children = new TrieNode[26]; // For lowercase English letters
            isEnd = false;
        }

        public boolean containsKey(char ch) {
            return children[ch - 'a'] != null;
        }

        public TrieNode get(char ch) {
            return children[ch - 'a'];
        }

        public void put(char ch, TrieNode node) {
            children[ch - 'a'] = node;
        }

        public void setEnd() {
            isEnd = true;
        }

        public boolean isEnd() {
            return isEnd;
        }
    }

    private TrieNode root;

    /** Initialize your data structure here. */
    public Implement_trie() {
        root = new TrieNode();
    }

    /** Inserts the word into the trie. */
    public void insert(String word) {
        TrieNode node = root;
        for (int i = 0; i < word.length(); i++) {
            char currentChar = word.charAt(i);
            if (!node.containsKey(currentChar)) {
                node.put(currentChar, new TrieNode());
            }
            node = node.get(currentChar);
        }
        node.setEnd();
    }

    /** Returns true if the word is in the trie. */
    public boolean search(String word) {
        TrieNode node = root;
        for (int i = 0; i < word.length(); i++) {
            char currentChar = word.charAt(i);
            if (!node.containsKey(currentChar)) {
                return false;
            }
            node = node.get(currentChar);
        }
        return node.isEnd();
    }

    /**
     * Returns true if there is any word in the trie that starts with the given
     * prefix.
     */
    public boolean startsWith(String prefix) {
        TrieNode node = root;
        for (int i = 0; i < prefix.length(); i++) {
            char currentChar = prefix.charAt(i);
            if (!node.containsKey(currentChar)) {
                return false;
            }
            node = node.get(currentChar);
        }
        return true;
    }

    public static void main(String[] args) {
        Implement_trie trie = new Implement_trie();

        // Test Case 1: Insert and Search
        trie.insert("apple");
        System.out.println("Search 'apple' (Expected: true): " + trie.search("apple")); // returns true
        System.out.println("Search 'app' (Expected: false): " + trie.search("app")); // returns false

        // Test Case 2: StartsWith
        System.out.println("StartsWith 'app' (Expected: true): " + trie.startsWith("app")); // returns true

        // Test Case 3: Insert new word 'app' and search again
        trie.insert("app");
        System.out.println("Search 'app' (Expected: true): " + trie.search("app")); // returns true
    }
}

/**
 * Approach:
 * 1. TrieNode Structure:
 * - Each node represents a character in a string.
 * - Each node contains an array of children nodes (size 26 for lowercase
 * English letters).
 * - A boolean flag `isEnd` marks the end of a valid word.
 * 2. Insert:
 * - Start from the root.
 * - Iterate through each character of the word.
 * - If the child node for the character doesn't exist, create it.
 * - Move to the child node.
 * - After processing all characters, mark the last node as `isEnd = true`.
 * 3. Search:
 * - Start from the root.
 * - Iterate through each character of the word.
 * - If a child node doesn't exist, the word is not present (return false).
 * - If we reach the end of the word, check if the current node is marked as
 * `isEnd`.
 * 4. StartsWith:
 * - Similar to search, but we only need to verify if the prefix exists in the
 * trie.
 * - We iterate through the prefix characters. If we successfully traverse all
 * characters, return true.
 *
 * Complexity Analysis:
 * - Time Complexity:
 * - Insert: O(L), where L is the length of the word.
 * - Search: O(L), where L is the length of the word.
 * - StartsWith: O(P), where P is the length of the prefix.
 * - Space Complexity:
 * - O(N * L), where N is the number of words an L is the average length.
 * In worst case, we might need new nodes for every character of every word.
 */