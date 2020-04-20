package lusibian.leetcode.other;

public class _200NumberOfIslands {
    public int numIslands(char[][] grid) {
        if (grid.length == 0 || grid[0].length == 0) {
            return 0;
        }
        int n = grid.length;
        int m = grid[0].length;
        int islandCount = 0;
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < m; j++) {
                if (grid[i][j] == '1') {
                    islandCount++;
                    dfs(i, j, grid);
                }
            }
        }
        return islandCount;
    }

    private void dfs(int row, int column, char[][] grid) {
        if (row < 0 || row >= grid.length || column < 0 || column >= grid[0].length || grid[row][column] == '0') {
            return;
        }
        grid[row][column] = '0';
        dfs(row - 1, column, grid);
        dfs(row + 1, column, grid);
        dfs(row, column - 1, grid);
        dfs(row, column + 1, grid);
    }
}
