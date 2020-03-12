package lusibian.leetcode.dynamic_programming;

import java.util.Scanner;

public class MultiplePack {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int numLine = sc.nextInt();
        int packSize = sc.nextInt();
        int[] weights = new int[numLine];
        int[] values = new int[numLine];
        int[] itemNums = new int[numLine];
        for(int i = 0; i < numLine; i++){
            weights[i] = sc.nextInt();
            values[i] = sc.nextInt();
            itemNums[i] = sc.nextInt();
        }
        System.out.println(multiplePack3(weights, values, itemNums, packSize));
    }

    // 多重背包
    // 使用类完全背包的方法求解，增加一个辅助数组，记录已放入的item数量
    // 记背包大小为m，物品种类为n
    // 计算背包最大价值时，在新增辅助数组中可只记录当前item最少放入的数量
    // 时间复杂度O(mn)，空间复杂度O(m)
    // 下面的备注忽略：
    // 若需要计算放满背包的方式数量，则新增辅助数组需记录当前item放入时的各种组合
    // 记item的总数为t，t为所有物品的数量之和
    // 时间复杂度O(mt)，空间复杂度O(mt/n)
    public static int multiplePack1(int[] weights, int[] values, int[] itemNums,int packSize) {
        int[] dp = new int[packSize + 1];
        int[] numInPackForCurrentItem = new int[packSize + 1];
        for (int i = 0; i < weights.length; i++) {
            for (int j = weights[i]; j <= packSize; j++) {
                if (numInPackForCurrentItem[j - weights[i]] < itemNums[i] &&
                        values[i] + dp[j - weights[i]] > dp[j]) {
                    dp[j] = values[i] + dp[j - weights[i]];
                    numInPackForCurrentItem[j] = numInPackForCurrentItem[j - weights[i]] + 1;
                }
            }
            for (int i1 = 0; i1 < numInPackForCurrentItem.length; i1++) {
                numInPackForCurrentItem[i1] = 0;
            }
        }
        return dp[packSize];
    }

    // 多重背包
    // 简单的转化为01背包，将每种物品i拆分为itemNums[i]件物品
    // 记背包大小为m，物品种类为n
    // 记item的总数为t
    // t为所有物品的数量之和，记l(k)为物品k的数量，t = ∑l(k)，k属于1到n
    // 时间复杂度O(mt)，空间复杂度O(m)
    public static int multiplePack2(int[] weights, int[] values, int[] itemNums,int packSize) {
        int[] dp = new int[packSize + 1];
        for (int i = 0; i < weights.length; i++) {
            for (int k = 0; k < itemNums[i]; k++) {
                for (int j = packSize; j >= weights[i]; j--) {
                    if (values[i] + dp[j - weights[i]] > dp[j]) {
                        dp[j] = values[i] + dp[j - weights[i]];
                    }
                }
            }
        }
        return dp[packSize];
    }

    // 多重背包
    // 转化为01背包
    // 将每种物品i分成分别有1、2、4、8...2^k、rest件物品i组合成的新物品， 1 + 2 + 4 + ... + 2^k + rest = itemNums[i]
    // 记背包大小为m，物品种类为n
    // 记l(k)为物品k的数量，z = ∑(log2(l(k)) + 1)，k属于1到n
    // 记item的总数为t，由z的计算公式可知，z的大致范围：n + log2(t-n) <= z <= n * (log2(t/n) + 1)
    // 时间复杂度O(mz)，空间复杂度O(m)
    // 本拆分方式，需证明：
    // 对数字n，有1 + 2 + 4 + ... + 2^k + rest = n
    // 集合{1、2、4、8...2^k、rest}，对任意数字x属于范围1到n，存在子集，使子集中的数字和等于x
    // 数学证明略
    public static int multiplePack3(int[] weights, int[] values, int[] itemNums,int packSize) {
        int[] dp = new int[packSize + 1];
        for (int i = 0; i < weights.length; i++) {
            int currentItemSize = 1;
            int restItemNums = itemNums[i] - currentItemSize;
            while (restItemNums >= 0) {
                for (int j = packSize; j >= weights[i] * currentItemSize; j--) {
                    if (values[i] * currentItemSize + dp[j - weights[i] * currentItemSize] > dp[j]) {
                        dp[j] = values[i] * currentItemSize + dp[j - weights[i] * currentItemSize];
                    }
                }
                currentItemSize *= 2;
                if (restItemNums < currentItemSize) {
                    break;
                } else {
                    restItemNums -= currentItemSize;
                }
            }
            if (restItemNums > 0) {
                for (int j = packSize; j >= weights[i] * restItemNums; j--) {
                    if (values[i] * restItemNums + dp[j - weights[i] * restItemNums] > dp[j]) {
                        dp[j] = values[i] * restItemNums + dp[j - weights[i] * restItemNums];
                    }
                }
            }
        }
        return dp[packSize];
    }
}
