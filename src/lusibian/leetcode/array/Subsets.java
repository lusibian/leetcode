package lusibian.leetcode.array;

import java.util.ArrayList;
import java.util.List;

public class Subsets {
    public List<List<Integer>> subsets(int[] nums) {
        return subsets(nums, new ArrayList<>(), 0);
    }

    private List<List<Integer>> subsets(int[] nums, List<Integer> currentList, int index) {
        List<List<Integer>> res = new ArrayList<>();
        if(index >= nums.length) {
            res.add(currentList);
            return res;
        }
        res.addAll(subsets(nums, currentList, index + 1));
        List<Integer> newList = new ArrayList<>(currentList);
        newList.add(nums[index]);
        res.addAll(subsets(nums, newList, index + 1));
        return res;
    }
}
