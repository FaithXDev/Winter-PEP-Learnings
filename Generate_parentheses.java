import java.util.ArrayList;
import java.util.List;

public class Generate_parentheses {
    public List<String> generateParenthesis(int n) {
        List<String> result = new ArrayList<>();
        backtrack(result, new StringBuilder(), 0, 0, n);
        return result;
    }

    private void backtrack(List<String> result, StringBuilder sb, int open, int close, int n) {
        // Base Case: If the current string length is 2*n, we have a complete valid
        // combination
        if (sb.length() == 2 * n) {
            result.add(sb.toString());
            return;
        }

        // Decision 1: Add an open parenthesis if we haven't used all n yet
        if (open < n) {
            sb.append('(');
            backtrack(result, sb, open + 1, close, n);
            sb.deleteCharAt(sb.length() - 1); // Backtrack
        }

        // Decision 2: Add a close parenthesis if it doesn't exceed the number of open
        // ones
        // This ensures the parentheses remain balanced.
        if (close < open) {
            sb.append(')');
            backtrack(result, sb, open, close + 1, n);
            sb.deleteCharAt(sb.length() - 1); // Backtrack
        }
    }

}

/*
 * EXPLANATION:
 * 
 * Approach: Backtracking
 * ----------------------
 * The problem is to generate all well-formed parentheses combinations for `n`
 * pairs.
 * We can model this as building a string character by character with two
 * constraints:
 * 
 * 1. Open Parentheses Constraint: We can add an open parenthesis `(` if we have
 * used fewer than `n` open parentheses so far.
 * 2. Close Parentheses Constraint: We can add a close parenthesis `)` if the
 * number of close parentheses used so far is LESS than
 * the number of open parentheses used. This ensures that every closing
 * parenthesis has a matching opening parenthesis before it.
 * 
 * Algorithm:
 * - Start with an empty string, 0 open count, and 0 close count.
 * - At each step, try adding `(` if `open < n`. Recurse.
 * - Then, try adding `)` if `close < open`. Recurse.
 * - Base case: When string length reaches `2 * n`, add it to results.
 * 
 * Complexity Analysis:
 * --------------------
 * Time Complexity: O(4^n / sqrt(n))
 * - This is related to the Catalan number, which gives the number of valid
 * parentheses sequences.
 * - The nth Catalan number is approximately 4^n / (n * sqrt(n)).
 * 
 * Space Complexity: O(N)
 * - The recursion depth is at most 2*N.
 * - The StringBuilder stores a string of length 2*N.
 */
