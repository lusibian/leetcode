package lusibian.leetcode.array;

public class NextPermutation {
    public static void main(String[] args) {
        int[] nums = new int[]{1, 2, 3};
        nextPermutation(nums);
        for (int num : nums) {
            System.out.println(num);
        }
    }

    public static void nextPermutation(int[] nums) {
        if (nums.length <= 1) {
            return;
        }
        int left = nums.length - 1;
        for (int i = nums.length - 2; i >= 0; i--) {
            if (nums[i] >= nums[left]) {
                left = i;
            } else {
                break;
            }
        }
        if (left > 0) {
            for (int i = nums.length - 1; i >= left; i--) {
                if (nums[left - 1] < nums[i]) {
                    int temp = nums[left - 1];
                    nums[left - 1] = nums[i];
                    nums[i] = temp;
                    break;
                }
            }
        }
        reverse(nums, left, nums.length - 1);
    }

    private static void reverse(int[] nums, int begin, int end) {
        int left = begin;
        int right = end;
        while (left < right) {
            int temp = nums[left];
            nums[left] = nums[right];
            nums[right] = temp;
            left++;
            right--;
        }
    }
}
