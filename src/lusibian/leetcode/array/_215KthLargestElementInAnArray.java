package lusibian.leetcode.array;

import java.util.PriorityQueue;

public class _215KthLargestElementInAnArray {
    // 堆
    public int findKthLargest1(int[] nums, int k) {
        PriorityQueue<Integer> topKHeap = new PriorityQueue<>();
        for (int num : nums) {
            if (topKHeap.size() < k) {
                topKHeap.add(num);
            } else if (topKHeap.peek() < num) {
                topKHeap.poll();
                topKHeap.add(num);
            }
        }
        return topKHeap.poll();
    }

    public static void main(String[] args) {
        int[] nums = new int[]{1};
        _215KthLargestElementInAnArray temp = new _215KthLargestElementInAnArray();
        System.out.println(temp.findKthLargest2(nums, 1));
    }

    // 快速选择，partition
    public int findKthLargest2(int[] nums, int k) {
        int start = 0, end = nums.length - 1;
        while (start <= end) {
            exchange(nums, (start + end) / 2, end);
            int left = start, right = end - 1;
            while (left < right) {
                while (left < right && nums[left] < nums[end]) {
                    left++;
                }
                while (left < right && nums[right] >= nums[end]) {
                    right--;
                }
                if (left < right) {
                    exchange(nums, left, right);
                }
            }
            if (nums[left] < nums[end]) {
                left++;
            }
            exchange(nums, left, end);
            if (nums.length - left == k) {
                return nums[left];
            } else if (nums.length - left > k) {
                start = left + 1;
            } else {
                end = left - 1;
            }
        }
        return 0;
    }

    private void exchange(int[] nums, int i, int j) {
        int temp = nums[i];
        nums[i] = nums[j];
        nums[j] = temp;
    }
}
