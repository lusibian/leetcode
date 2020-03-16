package lusibian.leetcode.dynamic_programming;

import java.util.Scanner;

public class TwoDimensionCostPack {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int numLine = sc.nextInt();
        int packVolumeSize = sc.nextInt();
        int packWeightSize = sc.nextInt();
        int[] volumes = new int[numLine];
        int[] weights = new int[numLine];
        int[] values = new int[numLine];
        for (int i = 0; i < numLine; i++) {
            volumes[i] = sc.nextInt();
            weights[i] = sc.nextInt();
            values[i] = sc.nextInt();
        }
        System.out.println(twoDimensionCostPack(volumes, weights, values, packVolumeSize, packWeightSize));
    }


    // 二维费用的01背包
    // 状态转移方程增加一维，dp数组也增加一维
    // 记背包体积大小为v，重量大小为m，物品种类为n
    // 时间复杂度O(vmn)，空间复杂度O(vm)
    public static int twoDimensionCostPack(int[] volumes, int[] weights, int[] values, int packVolumeSize, int packWeightSize) {
        int[][] dp = new int[packVolumeSize + 1][packWeightSize + 1];
        for (int i = 0; i < volumes.length; i++) {
            for (int j = packVolumeSize; j >= volumes[i]; j--) {
                for (int k = packWeightSize; k >= weights[i]; k--) {
                    if (dp[j - volumes[i]][k - weights[i]] + values[i] > dp[j][k]) {
                        dp[j][k] = dp[j - volumes[i]][k - weights[i]] + values[i];
                    }
                }
            }
        }
        return dp[packVolumeSize][packWeightSize];
    }
}
