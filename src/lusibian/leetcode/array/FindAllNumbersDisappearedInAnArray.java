package lusibian.leetcode.array;

import java.util.ArrayList;
import java.util.List;

public class FindAllNumbersDisappearedInAnArray {

    public static void main(String[] args) {
        int[] nums = new int[]{4,3,2,7,8,2,3,1};
        System.out.println(findDisappearedNumbers(nums));
    }

    public static List<Integer> findDisappearedNumbers(int[] nums) {
        List<Integer> resList = new ArrayList<>();
        int idx = 0;
        while (idx < nums.length) {
            if (nums[idx] == idx + 1 || nums[idx] == 0) {
                idx++;
            } else if (nums[idx] == nums[nums[idx] - 1]) {
                nums[idx] = 0;
                idx++;
            } else {
                int temp = nums[idx];
                nums[idx] = nums[temp - 1];
                nums[temp - 1] = temp;
            }
        }
        for (int i = 0; i < nums.length; i++) {
            if (nums[i] == 0) {
                resList.add(i + 1);
            }
        }
        return resList;
    }
}
