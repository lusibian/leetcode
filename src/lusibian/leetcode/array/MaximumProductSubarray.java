package lusibian.leetcode.array;

public class MaximumProductSubarray {
    public int maxProduct(int[] nums) {
        int res = Integer.MIN_VALUE;
        int max = 1;
        int min = 1;
        for (int num : nums) {
            int tempMax = max;
            max = Math.max(Math.max(num, num * max), num * min);
            min = Math.min(Math.min(num, num * tempMax), num * min);
            res = Math.max(max, res);
        }
        return res;
    }
}
