import java.util.ArrayList;
import java.util.List;

public class PascalsTriangle {
    public List<List<Integer>> generate(int numRows) {
        List<List<Integer>> triangle = new ArrayList<>();

        for (int i = 0; i < numRows; i++) {
            List<Integer> row = new ArrayList<>();

            for (int j = 0; j <= i; j++) {
                if (j == 0 || j == i) {
                    row.add(1);
                } else {
                    List<Integer> prevRow = triangle.get(i - 1);
                    row.add(prevRow.get(j - 1) + prevRow.get(j));
                }
            }

            triangle.add(row);
        }

        return triangle;
    }
}

/**
 * LeetCode 118. Pascal's Triangle
 * 
 * Approach: Dynamic Programming (Iterative Row Construction)
 * 1. Pascal's Triangle is built row by row. Each row `i` has `i + 1` elements.
 * 2. The first and last elements of every row are always 1.
 * 3. Every other element at position `j` in row `i` is the sum of the two
 * elements directly above it from the previous row:
 * triangle[i][j] = triangle[i-1][j-1] + triangle[i-1][j].
 * 4. We iterate from row 0 to row `numRows - 1`, constructing each row by:
 * - Setting the first element to 1.
 * - Computing middle elements as the sum of two adjacent elements from the
 * previous row.
 * - Setting the last element to 1.
 * - Appending the completed row to the result list.
 * 5. Time Complexity: O(numRows^2) — each row i has i+1 elements, so total
 * elements generated = 1 + 2 + ... + numRows = numRows*(numRows+1)/2.
 * Space Complexity: O(numRows^2) — to store the entire triangle.
 */
