package lusibian.leetcode.backtracking;

public class _37SudokuSolver {
    public static void main(String[] args) {
        char[][] board = new char[][]{
                {'5','3','.','.','7','.','.','.','.'},
                {'6','.','.','1','9','5','.','.','.'},
                {'.','9','8','.','.','.','.','6','.'},
                {'8','.','.','.','6','.','.','.','3'},
                {'4','.','.','8','.','3','.','.','1'},
                {'7','.','.','.','2','.','.','.','6'},
                {'.','6','.','.','.','.','2','8','.'},
                {'.','.','.','4','1','9','.','.','5'},
                {'.','.','.','.','8','.','.','7','9'}};
        _37SudokuSolver temp = new _37SudokuSolver();
        temp.solveSudoku(board);
    }

    public void solveSudoku(char[][] board) {
        boolean[][] rows = new boolean[9][9];
        boolean[][] columns = new boolean[9][9];
        boolean[][] grids = new boolean[9][9];
        for (int i = 0; i < 9; i++) {
            for (int j = 0; j < 9; j++) {
                if (board[i][j] != '.') {
                    int num = board[i][j] - '1';
                    int grid = i / 3 * 3 + j / 3;
                    rows[i][num] = true;
                    columns[j][num] = true;
                    grids[grid][num] = true;
                }
            }
        }
        backTracking(board, 0, 0, rows, columns, grids);
    }

    private boolean backTracking(char[][] board, int row, int column, boolean[][] rows, boolean[][] columns, boolean[][] grids) {
        if (row >= 9) {
            return true;
        }
        int nextRow = row;
        int nextColumn = column;
        if (nextColumn >= 9) {
            nextRow++;
            nextColumn = 0;
        }
        if (board[row][column] == '.') {
            int grid = row / 3 * 3 + column / 3;
            for (int num = 0; num < 9; num++) {
                if (!rows[row][num] && !columns[column][num] && !grids[grid][num]) {
                    rows[row][num] = true;
                    columns[column][num] = true;
                    grids[grid][num] = true;
                    board[row][column] = (char)('1' + num);

                    if(backTracking(board, nextRow, nextColumn, rows, columns, grids)) {
                        return true;
                    }

                    // 回溯
                    rows[row][num] = false;
                    columns[column][num] = false;
                    grids[grid][num] = false;
                    board[row][column] = '.';
                }
            }
            return false;
        } else {
            return backTracking(board, nextRow, nextColumn, rows, columns, grids);
        }
    }
}
