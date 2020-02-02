package lusibian.leetcode.heap;

import java.util.ArrayDeque;

public class _239SlidingWindowMaximum {
    // 双向队列法（双向链表）
    // 队列中存放的是元素下标，队列维护方法见代码
    // 队头的值代表当前滑窗中的最大值
    // 从队头到队尾是从旧到新，并是降序排列的
    // 队列中的每个元素都代表一个区间内的最大值
    // 例，若滑窗大小为k，当前滑窗的右边界到了下标i，当前队列为[a,b,c]
    // 则nums[a]一定为(i-k,i]中的最大值，nums[b]一定为(a,i]中的最大值，nums[c]一定为(b,i]中的最大值
    public int[] maxSlidingWindow1(int[] nums, int k) {
        if (nums == null || nums.length == 0 || k <= 0) {
            return new int[0];
        }
        ArrayDeque<Integer> windowDeque = new ArrayDeque<>(k);
        for (int i = 0; i < k - 1; i++) {
            cleanDeque(nums, windowDeque, i, k);
            windowDeque.addLast(i);
        }
        int[] maxSlidingWindowNums = new int[nums.length - k + 1];
        for (int i = k - 1; i < nums.length; i++) {
            cleanDeque(nums, windowDeque, i, k);
            windowDeque.addLast(i);
            maxSlidingWindowNums[i - k + 1] = nums[windowDeque.getFirst()];
        }
        return maxSlidingWindowNums;
    }

    private void cleanDeque(int[] nums, ArrayDeque<Integer> deque, int i, int k) {
        // 如果队头不在滑窗内，移除
        if (!deque.isEmpty() && deque.getFirst() <= i - k) {
            deque.removeFirst();
        }
        // 如果队尾小于当前值，移除
        // 因为，若当前队尾元素a小于当前值，a会比当前元素更早出队
        // 当滑窗继续移动时，若a存在，当前元素一定存在，则a不可能为滑窗最大值
        while (!deque.isEmpty() && nums[deque.getLast()] <= nums[i]) {
            deque.removeLast();
        }
    }

    // 动态规划
    // 将长度为n的数组分割成大小为k的多个区间
    // 使用两个长度为n的辅助数组leftWindowMax，rightWindowMax
    // 记区间[a,b]中的最大值为Max[a,b]
    // 对每个下标i，若其所在区间为[left, right]
    // 将区间[left,i]的最大值记录到leftWindowMax[i]中，将区间[i,right]的最大值记录到rightWindowMax[i]中
    // 在窗口滑动过程中，若当前窗口为[a,b]，b=a+k-1
        // aRight为之前分割时a所在区间右边界，bLeft为之前分割时b所在区间左边界
        // 若a、b在分割时不在同一区间，aRight = bLeft - 1
        // 则此时，Max[a,b] = max(Max[a, aRight], Max[bLeft, b]) = max(rightWindowMax[a],leftWindowMax[b])
        // 若a、b在分割时在同一区间，aRight = b，bLeft = a
        // 则此时，Max[a,b] = max(Max[a,b], Max[a,b]) = max(Max[a, aRight], Max[bLeft, b]) = max(rightWindowMax[a],leftWindowMax[b])
    // 可得对任意窗口[a,b]，Max[a,b] = max(rightWindowMax[a],leftWindowMax[b])
    public int[] maxSlidingWindow2(int[] nums, int k) {
        if (nums == null || nums.length == 0 || k <= 0) {
            return new int[0];
        }
        int[] leftWindowMax = new int[nums.length];
        int[] rightWindowMax = new int[nums.length];
        int tempMax = nums[0];
        for (int i = 0; i < nums.length; i++) {
            if (i % k == 0) {
                tempMax = nums[i];
            } else if(nums[i] > tempMax){
                tempMax = nums[i];
            }
            leftWindowMax[i] = tempMax;
        }
        tempMax = nums[nums.length - 1];
        for (int i = nums.length - 1; i >= 0; i--) {
            if (i % k == k - 1) {
                tempMax = nums[i];
            } else if(nums[i] > tempMax){
                tempMax = nums[i];
            }
            rightWindowMax[i] = tempMax;
        }
        int[] maxSlidingWindowNums = new int[nums.length - k + 1];
        for (int i = 0; i < maxSlidingWindowNums.length; i++) {
            maxSlidingWindowNums[i] = Math.max(rightWindowMax[i], leftWindowMax[i + k - 1]);
        }
        return maxSlidingWindowNums;
    }
}
