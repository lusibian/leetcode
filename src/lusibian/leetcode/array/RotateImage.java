package lusibian.leetcode.array;

public class RotateImage {
    public void rotate(int[][] matrix) {
        int size = matrix.length;
        for (int tempSize = 0; tempSize < size / 2; tempSize++) {
            for (int i = tempSize; i < size - 1 - tempSize; i++) {
                int temp = matrix[tempSize][i];
                matrix[tempSize][i] = matrix[size - 1 - i][tempSize];
                matrix[size - 1 - i][tempSize] = matrix[size - 1 - tempSize][size - 1 - i];
                matrix[size - 1 - tempSize][size - 1 - i] = matrix[i][size - 1 - tempSize];
                matrix[i][size - 1 - tempSize] = temp;
            }
        }
    }
}
