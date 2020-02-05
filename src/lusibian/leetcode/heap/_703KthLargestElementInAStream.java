package lusibian.leetcode.heap;

import java.util.PriorityQueue;

public class _703KthLargestElementInAStream {
    // 维持大小为k的小顶堆，保存最大的k个数
    class KthLargest {
        PriorityQueue<Integer> minHeap;
        int heapSize;

        public KthLargest(int k, int[] nums) {
            minHeap = new PriorityQueue<>(k);
            heapSize = k;
            for (int num : nums) {
                if(minHeap.size() < k) {
                    minHeap.add(num);
                } else if(num > minHeap.peek()) {
                    minHeap.poll();
                    minHeap.add(num);
                }
            }
        }

        public int add(int val) {
            if(minHeap.size() < heapSize) {
                minHeap.add(val);
            } else if(val > minHeap.peek()) {
                minHeap.poll();
                minHeap.add(val);
            }
            return minHeap.peek();
        }
    }
}