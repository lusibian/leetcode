package lusibian.leetcode.backtracking;

import java.util.ArrayList;
import java.util.List;

public class _78Subsets {
    public List<List<Integer>> subsets(int[] nums) {
        List<List<Integer>> sets = new ArrayList<>();
        sets.add(new ArrayList<>());
        dfs(nums, sets, 0);
        return sets;
    }

    private void dfs(int[] nums, List<List<Integer>> sets, int idx) {
        if (idx >= nums.length) {
            return;
        }
        int length = sets.size();
        for (int i = 0; i < length; i++) {
            List<Integer> newSet = new ArrayList<>(sets.get(i));
            newSet.add(nums[idx]);
            sets.add(newSet);
        }
        dfs(nums, sets, idx + 1);
    }
}
