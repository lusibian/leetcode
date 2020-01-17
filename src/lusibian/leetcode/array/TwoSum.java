package lusibian.leetcode.array;

import java.util.HashMap;
import java.util.Map;

public class TwoSum {
    public static void main(String[] args) {

    }

    public int[] twoSum(int[] nums, int target) {
        Map<Integer, Integer> sumMap = new HashMap<>();
        int[] resArray = new int[]{-1, -1};
        for (int i = 0; i < nums.length; i++) {
            if (sumMap.containsKey(target - nums[i])) {
                resArray[0] = sumMap.get(target - nums[i]);
                resArray[1] = i;
                return resArray;
            }
            else {
                sumMap.put(nums[i], i);
            }
        }
        return resArray;
    }
}
