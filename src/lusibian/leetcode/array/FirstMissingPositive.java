package lusibian.leetcode.array;

public class FirstMissingPositive {
    public int firstMissingPositive(int[] nums) {
        if(nums == null || nums.length == 0) {
            return 1;
        }
        int i = 0;
        while (i < nums.length) {
            if (nums[i] == i + 1) {
                i++;
            } else if(nums[i] <= 0 || nums[i] > nums.length || nums[i] == nums[nums[i] - 1]) {
                nums[i] = 0;
                i++;
            } else {
                int temp = nums[i];
                nums[i] = nums[temp - 1];
                nums[temp - 1] = temp;
            }
        }
        for (int j = 0; j < nums.length; j++) {
            if(nums[j] == 0) {
                return j + 1;
            }
        }
        return nums.length + 1;
    }
}
