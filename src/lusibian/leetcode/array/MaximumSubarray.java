package lusibian.leetcode.array;

public class MaximumSubarray {
    public static void main(String[] args) {

    }

    public int maxSubArray(int[] nums) {
        int max = nums[0];
        int now = 0;
        for (int i : nums) {
            int temp = now + i;
            if (temp > max) {
                max = temp;
            }
            if (temp > 0) {
                now = temp;
            } else {
                now = 0;
            }
        }
        return max;
    }
}
