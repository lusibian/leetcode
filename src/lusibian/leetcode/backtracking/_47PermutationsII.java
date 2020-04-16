package lusibian.leetcode.backtracking;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class _47PermutationsII {
    public static void main(String[] args) {
        int[] nums = new int[]{1, 1, 2};
        _47PermutationsII temp = new _47PermutationsII();
        System.out.println(temp.permuteUnique(nums));
    }

    public List<List<Integer>> permuteUnique(int[] nums) {
        Arrays.sort(nums);
        List<List<Integer>> permuteList = new ArrayList<>();
        boolean[] isInPermute = new boolean[nums.length];
        backTracking(nums, isInPermute, new Integer[nums.length], permuteList, 0);
        return permuteList;
    }

    private void backTracking(int[] nums, boolean[] isInPermute, Integer[] currentPermute, List<List<Integer>> permuteList, int numCountInPermute) {
        if (numCountInPermute == nums.length) {
            permuteList.add(new ArrayList<>(Arrays.asList(currentPermute)));
            return;
        }

        for (int i = 0; i < isInPermute.length; i++) {
            if (!isInPermute[i] && (i == 0 || nums[i] != nums[i - 1] || isInPermute[i - 1])) {
                isInPermute[i] = true;
                currentPermute[numCountInPermute] = nums[i];

                backTracking(nums, isInPermute, currentPermute, permuteList, numCountInPermute + 1);

                // 回溯
                isInPermute[i] = false;
            }
        }
    }
}
