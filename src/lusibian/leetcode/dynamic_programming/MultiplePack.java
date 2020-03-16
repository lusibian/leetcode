package lusibian.leetcode.dynamic_programming;

import java.util.*;

public class MultiplePack {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int numLine = sc.nextInt();
        int packSize = sc.nextInt();
        int[] weights = new int[numLine];
        int[] values = new int[numLine];
        int[] itemNums = new int[numLine];
        for (int i = 0; i < numLine; i++) {
            weights[i] = sc.nextInt();
            values[i] = sc.nextInt();
            itemNums[i] = sc.nextInt();
        }
//        System.out.println(multiplePack1(weights, values, itemNums, packSize));
//        System.out.println(multiplePack2(weights, values, itemNums, packSize));
//        System.out.println(multiplePack3(weights, values, itemNums, packSize));
        System.out.println(multiplePack4(weights, values, itemNums, packSize));
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
    //
    // 算法有问题，辅助数组只记录当前item最少放入的数量，会导致错误的剪枝，应该保存所有中间结果才行
    // 在输入为：
    // 2 5
    // 3 5 1
    // 1 2 3
    // 正确结果为9，算法输出7
    public static int multiplePack1(int[] weights, int[] values, int[] itemNums, int packSize) {
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
    public static int multiplePack2(int[] weights, int[] values, int[] itemNums, int packSize) {
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
    public static int multiplePack3(int[] weights, int[] values, int[] itemNums, int packSize) {
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

    // 多重背包
    // 使用单调队列优化
    // 优化的原理是啥呢？w[i]表示物品重量，v[i]表示价值，c[i]表示数量，我们知道朴素状态转移方程：
    // f[i][j]=max(f[i−1][j−w[i]∗k]+v[i]∗k);(k<=c[i])
    // 现在我们要把这个方程变成一个单调队列可以优化的形式，于是我们假设d=j mod w[i],s=⌊jw[i]⌋
    // f[i][j]=max(f[i−1][d+w[i]∗k]−v[i]∗k)+v[i]∗s(s-k<=c[i])
    // 所以我们枚举余数d，然后对于每个余数d都用单调队列优化即可。
    //
    // 记背包大小为m，物品种类为n
    // 时间复杂度O(mn)，空间复杂度O(m)
    public static int multiplePack4(int[] weights, int[] values, int[] itemNums, int packSize) {
        int[] dp = new int[packSize + 1];
        int[] tempDpArray = new int[packSize + 1];
        Deque<Integer> idxQueue = new LinkedList<>();
        for (int i = 0; i < weights.length; i++) {
            for (int reminder = 0; reminder < weights[i]; reminder++) {
                idxQueue.clear();
                for (int j = 0; reminder + j * weights[i] <= packSize; j++) {
                    if (!idxQueue.isEmpty() && j - idxQueue.getFirst() > itemNums[i]) {
                        idxQueue.pop();
                    }
                    int valueForIdx = dp[reminder + weights[i] * j] - values[i] * j;
                    while (!idxQueue.isEmpty()) {
                        int last = idxQueue.getLast();
                        int valueForLast = dp[reminder + weights[i] * last] - values[i] * last;
                        if (valueForLast < valueForIdx) {
                            idxQueue.removeLast();
                        } else {
                            break;
                        }
                    }
                    idxQueue.add(j);
                    int first = idxQueue.getFirst();
                    int valueForFirst = dp[reminder + weights[i] * first] - values[i] * first;
                    tempDpArray[reminder + j * weights[i]] = valueForFirst + values[i] * j;
                }
            }
            System.arraycopy(tempDpArray, 0, dp, 0, packSize + 1);
        }
        return dp[packSize];
    }

}
