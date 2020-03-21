package lusibian.leetcode.dynamic_programming;

public class _122BestTimeToBuyAndSellStockII {
    public int maxProfit(int[] prices) {
        if (prices.length == 0) {
            return 0;
        }
        int n = prices.length;
        int[] profitMax = new int[n];
        for (int i = 1; i < n; i++) {
            profitMax[i] = profitMax[i - 1];
            if (prices[i] > prices[i - 1]) {
                profitMax[i] += prices[i] - prices[i - 1];
            }
        }
        return profitMax[n - 1];
    }
}