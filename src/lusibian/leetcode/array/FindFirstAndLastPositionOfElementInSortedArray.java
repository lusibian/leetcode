package lusibian.leetcode.array;

public class FindFirstAndLastPositionOfElementInSortedArray {
    public int[] searchRange(int[] nums, int target) {
        int[] res = new int[2];
        res[0] = searchBegin(nums,target);
        res[1] = searchEnd(nums,target);
        return res;
    }

    private int searchBegin(int[] nums, int target) {
        int left = 0;
        int right = nums.length - 1;
        while (left <= right) {
            int mid = (left + right) / 2;
            if(nums[mid] == target) {
                if (mid == 0 || nums[mid] > nums[mid - 1]) {
                    return mid;
                } else {
                    right = mid - 1;
                }
            } else if(nums[mid] > target) {
                right = mid - 1;
            } else {
                left = mid + 1;
            }
        }
        return -1;
    }

    private int searchEnd(int[] nums, int target) {
        int left = 0;
        int right = nums.length - 1;
        while (left <= right) {
            int mid = (left + right) / 2;
            if(nums[mid] == target) {
                if (mid == nums.length - 1 || nums[mid] < nums[mid + 1]) {
                    return mid;
                } else {
                    left = mid + 1;
                }
            } else if(nums[mid] > target) {
                right = mid - 1;
            } else {
                left = mid + 1;
            }
        }
        return -1;
    }
}
