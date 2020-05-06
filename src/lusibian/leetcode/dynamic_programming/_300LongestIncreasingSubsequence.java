package lusibian.leetcode.dynamic_programming;

import java.util.ArrayList;
import java.util.List;

public class _300LongestIncreasingSubsequence {
    // 动态规划
    public int lengthOfLIS1(int[] nums) {
        if (nums.length == 0) {
            return 0;
        }
        int length = nums.length;
        int[] dp = new int[length];
        dp[0] = 1;
        for (int i = 1; i < length; i++) {
            dp[i] = 1;
            for (int j = 0; j < i; j++) {
                if (nums[i] > nums[j] && dp[j] + 1 > dp[i]) {
                    dp[i] = dp[j] + 1;
                }
            }
        }

        int maxLength = 0;
        for (int i = 0; i < length; i++) {
            if (maxLength < dp[i]) {
                maxLength = dp[i];
            }
        }
        return maxLength;
    }

    // 贪心+二分
    // 思路有点厉害
    // 最后整个算法流程为：
    //    设当前已求出的最长上升子序列的长度为 len（初始时为 1），从前往后遍历数组 nums，在遍历到 nums[i] 时：
    //        如果 nums[i]>d[len] ，则直接加入到 d 数组末尾，并更新 len=len+1；
    //        否则，在 d 数组中二分查找，找到第一个比 nums[i] 小的数 d[k] ，并更新 d[k+1]=nums[i]。
    //以输入序列 [0,8,4,12,2] 为例：
    //    第一步插入 0，d=[0]；
    //    第二步插入 8，d=[0,8]；
    //    第三步插入 4，d=[0,4]；
    //    第四步插入 12，d=[0,4,12]；
    //    第五步插入 2，d=[0,2,12]。
    public int lengthOfLIS(int[] nums) {
        if (nums.length == 0) {
            return 0;
        }
        List<Integer> assistList = new ArrayList<>();
        assistList.add(nums[0]);
        for (int i = 1; i < nums.length; i++) {
            addInAssistList(assistList, nums[i]);
        }
        return assistList.size();
    }

    private void addInAssistList(List<Integer> assistList, int num) {
        if (num > assistList.get(assistList.size() - 1)) {
            assistList.add(num);
        } else {
            int left = 0;
            int right = assistList.size() - 1;
            while (left <= right) {
                int mid = (left + right) / 2;
                if (num <= assistList.get(mid) && (mid == 0 || num > assistList.get(mid - 1))) {
                    assistList.set(mid, num);
                    break;
                } else if (num <= assistList.get(mid)) {
                    right = mid - 1;
                } else {
                    left = mid + 1;
                }
            }
        }
    }
}
