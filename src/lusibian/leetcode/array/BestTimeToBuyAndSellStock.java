package lusibian.leetcode.array;

public class BestTimeToBuyAndSellStock {
    public int maxProfit(int[] prices) {
        if(prices.length == 0){
            return 0;
        }
        int res = 0;
        int minPrice = prices[0];
        for (int i : prices) {
            if (i < minPrice) {
                minPrice = i;
            } else if (i - minPrice > res) {
                res = i - minPrice;
            }
        }
        return res;
    }
}
