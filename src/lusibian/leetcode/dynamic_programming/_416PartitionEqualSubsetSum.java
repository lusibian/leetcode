package lusibian.leetcode.dynamic_programming;

public class _416PartitionEqualSubsetSum {
    // 动态规划，记数字总数为n，所有数字之和为m，则时间复杂度O(mn)，空间复杂度O(m)
    public boolean canPartition1(int[] nums) {
        int numsSum = 0;
        for (int num : nums) {
            numsSum += num;
        }
        if (numsSum % 2 != 0) {
            return false;
        }
        int length = numsSum / 2;
        boolean[] assistArray = new boolean[length + 1];
        assistArray[0] = true;
        for (int num : nums) {
            if (length - num >= 0 && assistArray[length - num]) {
                return true;
            }
            for (int i = length - num - 1; i >= 0; i--) {
                if (assistArray[i]) {
                    assistArray[i + num] = true;
                }
            }
        }
        return false;
    }
}
