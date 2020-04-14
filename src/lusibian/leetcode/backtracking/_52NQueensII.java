package lusibian.leetcode.backtracking;

public class _52NQueensII {
    public int totalNQueens(int n) {
        boolean[] columns = new boolean[n];
        boolean[] mainDiagonal = new boolean[2 * n - 1];
        boolean[] counterDiagonal = new boolean[2 * n - 1];
        return setQueen(0, columns, mainDiagonal, counterDiagonal);
    }

    private int setQueen(int row, boolean[] columns, boolean[] mainDiagonal, boolean[] counterDiagonal) {
        if (row >= columns.length) {
            return 1;
        }

        int count = 0;
        for (int i = 0; i < columns.length; i++) {
            if (!columns[i] && !mainDiagonal[i + row] && !counterDiagonal[i - row + columns.length - 1]) {
                columns[i] = true;
                mainDiagonal[i + row] = true;
                counterDiagonal[i - row + columns.length - 1] = true;

                count += setQueen(row + 1, columns, mainDiagonal, counterDiagonal);

                // 回溯
                columns[i] = false;
                mainDiagonal[i + row] = false;
                counterDiagonal[i - row + columns.length - 1] = false;
            }
        }
        return count;
    }
}
