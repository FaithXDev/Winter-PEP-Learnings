public class Word_Search {
    public boolean exist(char[][] board, String word) {
        int m = board.length;
        int n = board[0].length;

        // Iterate through every cell in the grid to find the starting character
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                // If we find the starting character and the DFS returns true, solution found
                if (dfs(board, word, i, j, 0)) {
                    return true;
                }
            }
        }
        return false;
    }

    /**
     * Depth First Search to find the word path.
     */
    private boolean dfs(char[][] board, String word, int r, int c, int idx) {
        // Base case: If we have matched all characters in the word
        if (idx == word.length())
            return true;

        // Bounds check and character match check
        // Also check if the cell is already visited (marked with '#')
        if (r < 0 || c < 0 || r >= board.length || c >= board[0].length
                || board[r][c] != word.charAt(idx)) {
            return false;
        }

        // Mark current cell as visited
        char temp = board[r][c];
        board[r][c] = '#';

        // Recursively check all 4 directions (Down, Up, Right, Left)
        boolean found = dfs(board, word, r + 1, c, idx + 1) ||
                dfs(board, word, r - 1, c, idx + 1) ||
                dfs(board, word, r, c + 1, idx + 1) ||
                dfs(board, word, r, c - 1, idx + 1);

        // Backtrack: Restore the original character so other paths can use it
        board[r][c] = temp;

        return found;
    }

    public static void main(String[] args) {
        Word_Search solution = new Word_Search();

        char[][] board = {
                { 'A', 'B', 'C', 'E' },
                { 'S', 'F', 'C', 'S' },
                { 'A', 'D', 'E', 'E' }
        };

        String word1 = "ABCCED";
        System.out.println("Board:");
        for (char[] row : board) {
            System.out.println(java.util.Arrays.toString(row));
        }
        System.out.println("Word: " + word1 + " -> " + solution.exist(board, word1) + " (Expected: true)");

        String word2 = "SEE";
        System.out.println("Word: " + word2 + " -> " + solution.exist(board, word2) + " (Expected: true)");

        String word3 = "ABCB";
        System.out.println("Word: " + word3 + " -> " + solution.exist(board, word3) + " (Expected: false)");
    }
}

/*
 * EXPLANATION:
 * 
 * Approach: Depth-First Search (DFS) with Backtracking
 * ----------------------------------------------------
 * The problem is to check if a word exists in a grid of characters, where
 * adjacent characters are horizontally or vertically neighboring.
 * The same letter cell may not be used more than once.
 * 
 * 1. Traversal:
 * - We iterate through every cell (r, c) in the grid.
 * - If `board[r][c]` matches the first character of the word, we start a DFS
 * traversal from there.
 * 
 * 2. DFS Recursive Function:
 * - Parameters: current position (r, c) and current index in the word `idx`.
 * - Base Case (Success): If `idx == word.length()`, we have successfully found
 * the whole word. Return `true`.
 * - Base Case (Failure): If (r, c) is out of bounds OR `board[r][c]` does not
 * match `word[idx]`, return `false`.
 * 
 * 3. Backtracking (Visited Marking):
 * - To ensure we don't reuse the same cell in the current path, we mark the
 * cell as visited.
 * We do this efficiently by temporarily changing `board[r][c]` to a special
 * character (e.g., '#').
 * - We then recurse in all 4 directions: `(r+1, c)`, `(r-1, c)`, `(r, c+1)`,
 * `(r, c-1)` looking for `word[idx+1]`.
 * - If any of these return `true`, then we found the path.
 * - IMPORTANT: Before returning, we MUST "backtrack" by restoring `board[r][c]`
 * to its original value. This allows the cell to be used in other potential
 * paths starting from different origins or branches.
 * 
 * Complexity Analysis:
 * --------------------
 * Time Complexity: O(M * N * 4^L)
 * - M * N is the number of cells. We start a search potentially from each cell.
 * - 4^L represents the branching factor of 4 for the length of the word L.
 * (Actually closer to 3^L because we don't go back to where we came from).
 * 
 * Space Complexity: O(L)
 * - The recursion stack depth is limited by the length of the word L.
 */