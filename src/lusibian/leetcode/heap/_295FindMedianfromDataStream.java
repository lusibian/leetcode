package lusibian.leetcode.heap;

import java.util.PriorityQueue;

public class _295FindMedianfromDataStream {
    // 两个平衡的堆（大小最多相差1）
    // 一个最大堆，记录较小的一半数字
    // 一个最小堆，记录较大的一半数字
    // 当数字总数为奇数时，最小堆的大小比最大堆大1，此时中位数为最小堆的堆顶
    // 当数字总数为偶数时，最小堆的大小与最大堆相等，此时中位数为两个堆的堆顶和除2
    class MedianFinder {
        PriorityQueue<Integer> heapForSmallerHalf;
        PriorityQueue<Integer> heapForBiggerHalf;

        /** initialize your data structure here. */
        public MedianFinder() {
            heapForSmallerHalf = new PriorityQueue<>((x,y) -> y - x);
            heapForBiggerHalf = new PriorityQueue<>();
        }

        public void addNum(int num) {
            if(heapForBiggerHalf.size() == heapForSmallerHalf.size()) {
                int temp = num;
                if(!heapForSmallerHalf.isEmpty() && temp < heapForSmallerHalf.peek()){
                    temp = heapForSmallerHalf.poll();
                    heapForSmallerHalf.add(num);
                }
                heapForBiggerHalf.add(temp);
            } else {
                int temp = num;
                if(!heapForBiggerHalf.isEmpty() && temp > heapForBiggerHalf.peek()) {
                    temp = heapForBiggerHalf.poll();
                    heapForBiggerHalf.add(num);
                }
                heapForSmallerHalf.add(temp);
            }
        }

        public double findMedian() {
            if(!heapForBiggerHalf.isEmpty()){
                if(heapForBiggerHalf.size() == heapForSmallerHalf.size()) {
                    return (heapForSmallerHalf.peek() + heapForBiggerHalf.peek()) / 2.0;
                } else {
                    return heapForBiggerHalf.peek();
                }
            }
            return -1.0;
        }
    }
}
