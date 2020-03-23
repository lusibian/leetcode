package lusibian.leetcode.dynamic_programming;

public class _123BestTimeToBuyAndSellStockIII {
    // 状态机解法，也是dp
    // 通用状态转移方程：
    //  dp[i][k][0] = max(dp[i - 1][k][0], dp[i - 1][k][1] + price[i])
    //  dp[i][k][1] = max(dp[i - 1][k][1], dp[i - 1][k - 1][0] - price[i])
    //  i代表当前天数，k代表最大交易次数，0、1分别代表当前未持有或持有股票
    //
    // 对本题，k为2
    // 记输入天数为n
    // 最大交易次数为2，是常量
    // 时间复杂度O(n)，空间复杂度O(1)
    public int maxProfit(int[] prices) {
        if (prices.length == 0) {
            return 0;
        }
        int maxTransactionTimes = 2;
        int[][] dp = new int[maxTransactionTimes + 1][2];
        int[][] currentDp = new int[maxTransactionTimes + 1][2];
        for (int i = 0; i < dp.length; i++) {
            dp[i][1] = Integer.MIN_VALUE;
            currentDp[i][1] = Integer.MIN_VALUE;
        }
        for (int price : prices) {
            for (int k = 1; k < currentDp.length; k++) {
                currentDp[k][0] = Math.max(dp[k][0], dp[k][1] + price);
                currentDp[k][1] = Math.max(dp[k][1], dp[k - 1][0] - price);
            }
            int[][] tempDp = dp;
            dp = currentDp;
            currentDp = tempDp;
        }
        return dp[2][0];
    }
}
