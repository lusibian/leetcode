package lusibian.leetcode.array;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class IntersectionOfTwoArraysII {
    public int[] intersect(int[] nums1, int[] nums2) {
        if(nums1 == null || nums2 == null || nums1.length == 0 || nums2.length == 0) {
            return new int[0];
        }
        if (nums1.length <= nums2.length) {
            return intersectHelper(nums1, nums2);
        } else {
            return intersectHelper(nums2, nums1);
        }
    }

    // hash
    private int[] intersectHelper(int[] nums1, int[] nums2) {
        List<Integer> intersection = new ArrayList<>();
        Map<Integer, Integer> numCountMap = new HashMap<>();
        for (int num : nums1) {
            int count = 0;
            if(numCountMap.containsKey(num)) {
                count = numCountMap.get(num);
            }
            numCountMap.put(num, count + 1);
        }
        for (int num : nums2) {
            if(numCountMap.containsKey(num)) {
                int count = numCountMap.get(num);
                if(count > 0) {
                    intersection.add(num);
                    numCountMap.put(num, count - 1);
                }
            }
        }
        return intersection.stream().mapToInt(Integer::valueOf).toArray();
    }
}
