package lusibian.leetcode.dynamic_programming;

public class _188BestTimeToBuyAndSellStockIV {
    // 状态机解法，也是dp
    // 通用状态转移方程：
    //  dp[i][k][0] = max(dp[i - 1][k][0], dp[i - 1][k][1] + price[i])
    //  dp[i][k][1] = max(dp[i - 1][k][1], dp[i - 1][k - 1][0] - price[i])
    //  i代表当前天数，k代表最大交易次数，0、1分别代表当前未持有或持有股票
    //
    // 记输入天数为n，最大交易次数为k
    // 时间复杂度O(n*min(k, n/2))，空间复杂度O(min(k, n/2))
    public int maxProfit(int k, int[] prices) {
        if (prices.length == 0) {
            return 0;
        }
        int maxTransactionTimes = Math.min(k, prices.length / 2);
        int[][] dp = new int[maxTransactionTimes + 1][2];
        int[][] currentDp = new int[maxTransactionTimes + 1][2];
        for (int i = 0; i < dp.length; i++) {
            dp[i][1] = Integer.MIN_VALUE;
            currentDp[i][1] = Integer.MIN_VALUE;
        }
        for (int price : prices) {
            for (int k0 = 1; k0 < currentDp.length; k0++) {
                currentDp[k0][0] = Math.max(dp[k0][0], dp[k0][1] + price);
                currentDp[k0][1] = Math.max(dp[k0][1], dp[k0 - 1][0] - price);
            }
            int[][] tempDp = dp;
            dp = currentDp;
            currentDp = tempDp;
        }
        return dp[maxTransactionTimes][0];
    }
}
