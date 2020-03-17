package lusibian.leetcode.array;

public class MinimumPathSum {

    // 二维辅助数组
    public int minPathSum1(int[][] grid) {
        int m = grid.length;
        int n = grid[0].length;
        int[][] sums = new int[m][n];
        sums[0][0] = grid[0][0];
        for (int i = 1; i < m; i++) {
            sums[i][0] = sums[i - 1][0] + grid[i][0];
        }
        for (int i = 1; i < n; i++) {
            sums[0][i] = sums[0][i - 1] + grid[0][i];
        }
        for (int i = 1; i < m; i++) {
            for (int j = 1; j < n; j++) {
                int minTemp = Math.min(sums[i - 1][j], sums[i][j - 1]);
                sums[i][j] = minTemp + grid[i][j];
            }
        }
        return sums[m - 1][n - 1];
    }

    // 一维辅助数组
    public int minPathSum2(int[][] grid) {
        int m = grid.length;
        int n = grid[0].length;
        int[] sums = new int[n];
        sums[0] = grid[0][0];
        for (int i = 1; i < n; i++) {
            sums[i] = sums[i - 1] + grid[0][i];
        }
        for (int i = 1; i < m; i++) {
            sums[0] += grid[i][0];
            for (int j = 1; j < n; j++) {
                int minTemp = Math.min(sums[j], sums[j - 1]);
                sums[j] = minTemp + grid[i][j];
            }
        }
        return sums[n - 1];
    }
}
