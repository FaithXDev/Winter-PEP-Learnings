
public class Implement_trie_II {

    static class Node {
        Node[] links = new Node[26];
        int cntEndWith = 0;
        int cntPrefix = 0;

        boolean containsKey(char ch) {
            return links[ch - 'a'] != null;
        }

        Node get(char ch) {
            return links[ch - 'a'];
        }

        void put(char ch, Node node) {
            links[ch - 'a'] = node;
        }

        void increaseEnd() {
            cntEndWith++;
        }

        void increasePrefix() {
            cntPrefix++;
        }

        void deleteEnd() {
            cntEndWith--;
        }

        void reducePrefix() {
            cntPrefix--;
        }

        int getEnd() {
            return cntEndWith;
        }

        int getPrefix() {
            return cntPrefix;
        }
    }

    private Node root;

    public Implement_trie_II() {
        root = new Node();
    }

    public void insert(String word) {
        Node node = root;
        for (int i = 0; i < word.length(); i++) {
            if (!node.containsKey(word.charAt(i))) {
                node.put(word.charAt(i), new Node());
            }
            node = node.get(word.charAt(i));
            node.increasePrefix();
        }
        node.increaseEnd();
    }

    public int countWordsEqualTo(String word) {
        Node node = root;
        for (int i = 0; i < word.length(); i++) {
            if (node.containsKey(word.charAt(i))) {
                node = node.get(word.charAt(i));
            } else {
                return 0;
            }
        }
        return node.getEnd();
    }

    public int countWordsStartingWith(String prefix) {
        Node node = root;
        for (int i = 0; i < prefix.length(); i++) {
            if (node.containsKey(prefix.charAt(i))) {
                node = node.get(prefix.charAt(i));
            } else {
                return 0;
            }
        }
        return node.getPrefix();
    }

    public void erase(String word) {
        Node node = root;
        for (int i = 0; i < word.length(); i++) {
            if (node.containsKey(word.charAt(i))) {
                node = node.get(word.charAt(i));
                node.reducePrefix();
            } else {
                return;
            }
        }
        node.deleteEnd();
    }

    public static void main(String[] args) {
        Implement_trie_II trie = new Implement_trie_II();

        trie.insert("apple");
        trie.insert("apple");
        trie.insert("app");

        System.out.println("Count words equal to 'apple': " + trie.countWordsEqualTo("apple")); // Expected: 2
        System.out.println("Count words equal to 'app': " + trie.countWordsEqualTo("app")); // Expected: 1
        System.out.println("Count words starting with 'app': " + trie.countWordsStartingWith("app")); // Expected: 3

        trie.erase("apple");
        System.out.println("After erasing one 'apple':");
        System.out.println("Count words equal to 'apple': " + trie.countWordsEqualTo("apple")); // Expected: 1
        System.out.println("Count words starting with 'app': " + trie.countWordsStartingWith("app")); // Expected: 2
    }
}
/**
 * Approach:
 * 1. TrieNode Structure:
 * - `links`: Array of size 26 to store children nodes.
 * - `cntEndWith`: Counter to track how many words end at this specific node.
 * - `cntPrefix`: Counter to track how many words pass through this node (prefix
 * count).
 * 
 * 2. Operations:
 * - **Insert**:
 * - Traverse the Trie. Create nodes if they don't exist.
 * - For every node visited (except root maybe, but conceptually the child
 * nodes), increment `cntPrefix`.
 * - At the last node, increment `cntEndWith`.
 * - **CountWordsEqualTo**:
 * - Traverse the Trie matching characters.
 * - If key not found, return 0.
 * - If end reached, return `cntEndWith`.
 * - **CountWordsStartingWith**:
 * - Traverse the Trie matching characters.
 * - If key not found, return 0.
 * - If end reached, return `cntPrefix`.
 * - **Erase**:
 * - Traverse the Trie.
 * - Decrement `cntPrefix` for each node visited along the path.
 * - At the last node, decrement `cntEndWith`.
 * - (Assumption: The word exists in the Trie before erase is called).
 * 
 * Complexity Analysis:
 * - Time Complexity: O(N) for all operations, where N is the length of the
 * word.
 * - Space Complexity: O(N * M) in the worst case, where N is average length and
 * M is number of words.
 */
