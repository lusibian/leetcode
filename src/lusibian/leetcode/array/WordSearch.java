package lusibian.leetcode.array;

public class WordSearch {
    public boolean exist(char[][] board, String word) {
        for (int i = 0; i < board.length; i++) {
            for (int j = 0; j < board[0].length; j++) {
                if (searchOne(board, word, 0, i, j)) {
                    return true;
                }
            }
        }
        return false;
    }

    private boolean searchOne(char[][] board, String word, int idx, int x, int y) {
        if (word.charAt(idx) == board[x][y]) {
            char tempChar = board[x][y];
            board[x][y] = '0';
            if (idx + 1 >= word.length()) {
                return true;
            }
            if (x > 0 && searchOne(board, word, idx + 1, x - 1, y)) {
                return true;
            }
            if (x + 1 < board.length && searchOne(board, word, idx + 1, x + 1, y)) {
                return true;
            }
            if (y > 0 && searchOne(board, word, idx + 1, x, y - 1)) {
                return true;
            }
            if (y + 1 < board[0].length && searchOne(board, word, idx + 1, x, y + 1)) {
                return true;
            }
            board[x][y] = tempChar;
            return false;
        } else {
            return false;
        }
    }
}
