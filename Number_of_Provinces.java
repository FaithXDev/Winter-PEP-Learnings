class Number_of_Provinces {

    public int findCircleNum(int[][] isConnected) {
        int n = isConnected.length;
        boolean[] visited = new boolean[n];
        int provinces = 0;

        for (int i = 0; i < n; i++) {
            if (!visited[i]) {
                dfs(isConnected, visited, i);
                provinces++;
            }
        }

        return provinces;
    }

    private void dfs(int[][] isConnected, boolean[] visited, int city) {
        visited[city] = true;
        for (int j = 0; j < isConnected.length; j++) {
            if (isConnected[city][j] == 1 && !visited[j]) {
                dfs(isConnected, visited, j);
            }
        }
    }
}

// LeetCode 547 - Number of Provinces
// Approach: DFS (Depth-First Search)
//
// The adjacency matrix isConnected[i][j] tells if city i and j are directly
// connected.
// A province is a group of cities that are connected (directly or indirectly).
//
// For each unvisited city, run DFS to mark all reachable cities as visited.
// Each DFS call from the main loop discovers one new province.
//
// Time Complexity : O(n²) — every cell in the matrix is checked
// Space Complexity : O(n) — visited array + recursion stack
