package lusibian.leetcode.array;

import java.util.ArrayList;
import java.util.List;

public class CombinationSum {

    public static void main(String[] args) {
        CombinationSum combinationSum = new CombinationSum();
        int[] candidates = new int[]{2,3,6,7};
        int target = 7;
        System.out.println(combinationSum.combinationSum(candidates, target));
    }

    public List<List<Integer>> combinationSum(int[] candidates, int target) {
        return combinationSum(candidates, target, new ArrayList<>(), 0, 0);
    }

    private List<List<Integer>> combinationSum(int[] candidates, int target,
                                               List<Integer> currentList, int sumNum, int currentIdx) {
        List<List<Integer>> res = new ArrayList<>();
        for (int i = currentIdx; i < candidates.length; i++) {
            int candidate = candidates[i];
            int newSumNum = sumNum + candidate;
            if (newSumNum == target) {
                List<Integer> tempList = new ArrayList<>(currentList);
                tempList.add(candidate);
                res.add(tempList);
            } else if (newSumNum < target) {
                List<Integer> tempList = new ArrayList<>(currentList);
                tempList.add(candidate);
                res.addAll(combinationSum(candidates, target, tempList, newSumNum, i));
            }
        }
        return res;
    }
}
