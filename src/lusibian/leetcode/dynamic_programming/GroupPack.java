package lusibian.leetcode.dynamic_programming;

import java.util.Scanner;

public class GroupPack {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int groupNum = sc.nextInt();
        int packSize = sc.nextInt();
        int[][] weights = new int[groupNum][];
        int[][] values = new int[groupNum][];
        int[] groupSizes = new int[groupNum];
        for (int i = 0; i < groupNum; i++) {
            groupSizes[i] = sc.nextInt();
            weights[i] = new int[groupSizes[i]];
            values[i] = new int[groupSizes[i]];
            for (int j = 0; j < groupSizes[i]; j++) {
                weights[i][j] = sc.nextInt();
                values[i][j] = sc.nextInt();
            }
        }
        System.out.println(groupPack(weights, values, packSize));
    }

    // 分组的01背包
    public static int groupPack(int[][] weights, int[][] values, int packSize) {
        int[] dp = new int[packSize + 1];
        for (int i = 0; i < weights.length; i++) {
            for (int j = packSize; j >= 0; j--) {
                for (int k = 0; k < weights[i].length; k++) {
                    if (j >= weights[i][k] && dp[j - weights[i][k]] + values[i][k] > dp[j]) {
                        dp[j] = dp[j - weights[i][k]] + values[i][k];
                    }
                }
            }
        }
        return dp[packSize];
    }
}
