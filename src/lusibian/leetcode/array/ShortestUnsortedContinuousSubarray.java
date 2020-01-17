package lusibian.leetcode.array;

public class ShortestUnsortedContinuousSubarray {
    public static void main(String[] args) {
        int[] nums = new int[]{1,2};
//        System.out.println(findUnsortedSubarray(nums));
    }

    public int findUnsortedSubarray(int[] nums) {
        int max = nums[0];
        int min = nums[nums.length - 1];
        int start = 0;
        int end = -1;
        for (int i = 1; i < nums.length; i++) {
            if (nums[i] >= max) {
                max = nums[i];
            } else {
                end = i;
            }
            if (nums[nums.length - 1 - i] <= min) {
                min = nums[nums.length - 1 - i];
            } else {
                start = nums.length - 1 - i;
            }
        }
        return end - start + 1;
    }
}
