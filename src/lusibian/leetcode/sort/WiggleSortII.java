package lusibian.leetcode.sort;

public class WiggleSortII {
    public static void main(String[] args) {
        int[] nums = new int[]{1, 3, 2, 2, 3, 1};
        WiggleSortII wiggleSortII = new WiggleSortII();
        wiggleSortII.wiggleSort(nums);
        for (int num : nums) {
            System.out.println(num);
        }
    }

    // 找中位数
    public void wiggleSort(int[] nums) {
        int halfLength = (nums.length + 1) / 2; //较小数字的数量，若nums长度为奇数，则包含中位数，若nums长度为偶数，则包含较小中位数
        paritition(nums, 0, nums.length - 1, halfLength);
        int tempStep = 0;
        if (halfLength % 2 == 0) {
            tempStep = -1;
        }

        int median = nums[halfLength - 1];
        for (int i = 1; i < halfLength; i += 2) {
            swapNum(nums, i, halfLength + i + tempStep);
        }

        //将和中位数相等的数往两边放，防止与中位数相等的数字相邻
        int left = 0, right = nums.length - 1 - (nums.length % 2);
        for (int i = 0; i < nums.length; i += 2) {
            if (nums[i] == median) {
                swapNum(nums, i, left);
                left += 2;
            }
            if (i + 1 < right && nums[i + 1] == median) {
                swapNum(nums, i + 1, right);
                right -= 2;
            }
        }
    }

    private void paritition(int[] nums, int start, int end, int leftLength) {
        if (start > end || leftLength == 0) {
            return;
        }
        swapNum(nums, (start + end) / 2, end);
        int left = start;
        int right = end - 1;
        while (left < right) {
            while (left < right && nums[left] <= nums[end]) {
                left++;
            }
            while (left < right && nums[right] > nums[end]) {
                right--;
            }
            swapNum(nums, left, right);
        }
        if (nums[left] < nums[end]) {
            left++;
        }
        swapNum(nums, left, end);
        if (left + 1 < leftLength) {
            paritition(nums, left + 1, end, leftLength);
        } else if (left + 1 > leftLength) {
            paritition(nums, start, left - 1, leftLength);
        }
    }

    private void swapNum(int[] nums, int idx1, int idx2) {
        int temp = nums[idx1];
        nums[idx1] = nums[idx2];
        nums[idx2] = temp;
    }
}
