package lusibian.leetcode.array;

import java.util.HashMap;
import java.util.Map;

public class LongestConsecutiveSequence {
    public int longestConsecutive(int[] nums) {
        if (nums == null || nums.length == 0) {
            return 0;
        }
        Map<Integer, Boolean> numsMap = new HashMap<>();
        for (int num : nums) {
            numsMap.put(num, false);
        }
        int res = 0;
        for (int num : nums) {
            if (!numsMap.get(num)) {
                int left = num, right = num;
                int intervalLength = 1;
                while (numsMap.containsKey(--left)) {
                    intervalLength++;
                    numsMap.put(left, true);
                }
                while (numsMap.containsKey(++right)) {
                    intervalLength++;
                    numsMap.put(right, true);
                }
                if (intervalLength > res) {
                    res = intervalLength;
                }
            }
        }
        return res;
    }
}
