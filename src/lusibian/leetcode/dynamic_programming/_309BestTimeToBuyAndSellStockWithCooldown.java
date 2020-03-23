package lusibian.leetcode.dynamic_programming;

public class _309BestTimeToBuyAndSellStockWithCooldown {
    // 状态机解法，也是dp
    // 通用状态转移方程：
    //  dp[i][k][0] = max(dp[i - 1][k][0], dp[i - 1][k][1] + price[i])
    //  dp[i][k][1] = max(dp[i - 1][k][1], dp[i - 1][k - 1][0] - price[i])
    //  i代表当前天数，k代表最大交易次数，0、1分别代表当前未持有或持有股票
    //
    // 对本题，k为positive infinity，并存在冷冻期，状态转移方程：
    //  dp[i][0] = max(dp[i - 1][0], dp[i - 1][1] + price[i])
    //  dp[i][1] = max(dp[i - 1][1], dp[i - 2][0] - price[i])
    public int maxProfit(int[] prices) {
        if (prices.length == 0) {
            return 0;
        }
        int dp_0 = 0, dp_1 = Integer.MIN_VALUE, dp_0_before = 0;
        for (int price : prices) {
            int temp = dp_0;
            dp_0 = Math.max(dp_0, dp_1 + price);
            dp_1 = Math.max(dp_1, dp_0_before - price);
            dp_0_before = temp;
        }
        return dp_0;
    }
}
