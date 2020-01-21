package lusibian.leetcode.array;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class FourSum {
    public List<List<Integer>> fourSum(int[] nums, int target) {
        List<List<Integer>> quads = new ArrayList<>();
        Arrays.sort(nums);
        for (int i = 0; i < nums.length - 3; i++) {
            if (nums[i] + 3 * nums[nums.length - 1] < target)
                continue;
            if (nums[i] * 4 > target) {
                break;
            }
            if (i == 0 || nums[i] != nums[i - 1]) {
                for (int i1 = i + 1; i1 < nums.length - 2; i1++) {
                    if (nums[i] + nums[i1] * 3 > target) {
                        break;
                    }
                    if (i1 == i + 1 || nums[i1] != nums[i1 - 1]) {
                        int left = i1 + 1;
                        int right = nums.length - 1;
                        int tempSum = nums[i] + nums[i1];
                        while (left < right) {
                            if (left != i1 + 1 && nums[left] == nums[left - 1]) {
                                left++;
                            } else if (right != nums.length - 1 && nums[right] == nums[right + 1]) {
                                right--;
                            } else if (tempSum + nums[left] + nums[right] == target) {
                                List<Integer> quad = new ArrayList<>();
                                quad.add(nums[i]);
                                quad.add(nums[i1]);
                                quad.add(nums[left]);
                                quad.add(nums[right]);
                                quads.add(quad);
                                left++;
                                right--;
                            } else if (tempSum + nums[left] + nums[right] < target) {
                                left++;
                            } else {
                                right--;
                            }
                        }
                    }
                }
            }
        }
        return quads;
    }
}