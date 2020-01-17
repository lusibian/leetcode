package lusibian.leetcode.array;

import java.util.HashMap;
import java.util.Map;

public class SubarraySumEqualsK {
    public int subarraySum(int[] nums, int k) {
        Map<Integer, Integer> sumCount = new HashMap<>();
        int res = 0;
        int sum = 0;
        sumCount.put(0, 1);
        for (int num : nums) {
            sum += num;
            if (sumCount.containsKey(sum - k)) {
                res += sumCount.get(sum - k);
            }
            sumCount.put(sum, sumCount.getOrDefault(sum, 0) + 1);
        }
        return res;
    }
}
