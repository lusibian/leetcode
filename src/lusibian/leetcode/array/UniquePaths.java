package lusibian.leetcode.array;

import java.math.BigInteger;

public class UniquePaths {

    public int uniquePaths(int m, int n) {
        int[][] steps = new int[m][n];
        steps[0][0] = 1;
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                if (i + 1 < m) {
                    steps[i + 1][j] += steps[i][j];
                }
                if (j + 1 < n) {
                    steps[i][j + 1] += steps[i][j];
                }
            }
        }
        return steps[m - 1][n - 1];
    }

//    public int uniquePaths(int m, int n) {
//        java.math.BigInteger res = new BigInteger("1");
//        for (int i = 1; i <= m + n - 2; i++) {
//            BigInteger temp = new BigInteger(String.valueOf(i));
//            res = res.multiply(temp);
//        }
//        for (int i = 1; i <= m - 1; i++) {
//            BigInteger temp = new BigInteger(String.valueOf(i));
//            res = res.divide(temp);
//        }
//        for (int i = 1; i <= n - 1; i++) {
//            BigInteger temp = new BigInteger(String.valueOf(i));
//            res = res.divide(temp);
//        }
//        return res.intValue();
//    }
}
