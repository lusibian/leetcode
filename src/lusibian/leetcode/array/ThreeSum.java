package lusibian.leetcode.array;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class ThreeSum {
    public List<List<Integer>> threeSum(int[] nums) {
        int target = 0;
        List<List<Integer>> res = new ArrayList<>();
        Arrays.sort(nums);
        for (int i = 0; i < nums.length - 2; i++) {
            if (i == 0 || nums[i] > nums[i - 1]){
                int leftBegin = i + 1;
                int left = leftBegin;
                int rightBegin = nums.length - 1;
                int right = rightBegin;
                int tempTarget = target - nums[i];
                while (left < right) {
                    if(left != leftBegin && nums[left] == nums[left - 1]){
                        left++;
                        continue;
                    }
                    if(right != rightBegin && nums[right] == nums[right + 1]){
                        right--;
                        continue;
                    }
                    if(nums[left] + nums[right] == tempTarget){
                        List<Integer> list = new ArrayList<>();
                        list.add(nums[i]);
                        list.add(nums[left]);
                        list.add(nums[right]);
                        res.add(list);
                        left++;
                        right--;
                    } else if(nums[left] + nums[right] < tempTarget) {
                        left++;
                    } else {
                        right--;
                    }
                }
            }
        }
        return res;
    }
}
