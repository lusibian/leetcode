package lusibian.leetcode.array;

import java.util.*;

public class _347TopKFrequentElements {
    public List<Integer> topKFrequent(int[] nums, int k) {
        Map<Integer, Integer> countMap = new HashMap<>();
        for (int num : nums) {
            countMap.put(num, countMap.getOrDefault(num, 0) + 1);
        }
        PriorityQueue<Integer> minHeap = new PriorityQueue<>(Comparator.comparing(countMap::get));
        for (Integer num : countMap.keySet()) {
            if (minHeap.size() < k) {
                minHeap.add(num);
            } else if (countMap.get(num) > countMap.get(minHeap.peek())) {
                minHeap.poll();
                minHeap.add(num);
            }
        }
        return new ArrayList<>(minHeap);
    }
}
