package lusibian.leetcode.sort;

public class CountOfRangeSum {
    public static void main(String[] args) {
        int[] nums = new int[]{-2147483647, 0, -2147483647, 2147483647};
        CountOfRangeSum countOfRangeSum = new CountOfRangeSum();
        System.out.println(countOfRangeSum.countRangeSum(nums, -564, 3864));
    }

    public int countRangeSum(int[] nums, int lower, int upper) {
        long rangeSumFromStart = 0L;
        long[] rangeSumsFromStart = new long[nums.length];
        long[] assistArray = new long[nums.length];
        for (int i = 0; i < nums.length; i++) {
            rangeSumFromStart += nums[i];
            rangeSumsFromStart[i] = rangeSumFromStart;
        }
        return mergeCountSum(rangeSumsFromStart, assistArray, 0, nums.length - 1, lower, upper);
    }

    private int mergeCountSum(long[] sums, long[] assist, int start, int end, int lower, int upper) {
        if (start > end) {
            return 0;
        }
        if (start == end) {
            if (sums[start] >= lower && sums[start] <= upper) {
                return 1;
            } else {
                return 0;
            }
        }
        int totalCount = 0;
        int mid = (start + end) / 2;
        totalCount += mergeCountSum(sums, assist, start, mid, lower, upper);
        totalCount += mergeCountSum(sums, assist, mid + 1, end, lower, upper);

        // 统计区间和
        int leftPointer = start;
        int lowerPointer = mid + 1;
        int upperPointer = mid + 1;
        while (leftPointer <= mid && lowerPointer <= end) {
            while (lowerPointer <= end && sums[lowerPointer] - sums[leftPointer] < lower) {
                lowerPointer++;
            }
            while (upperPointer <= end && sums[upperPointer] - sums[leftPointer] <= upper) {
                upperPointer++;
            }
            totalCount += upperPointer - lowerPointer;
            leftPointer++;
        }

        // 归并排序
        int start1 = start;
        int start2 = mid + 1;
        int assistPointer = start;
        while (start1 <= mid && start2 <= end) {
            if (sums[start1] <= sums[start2]) {
                assist[assistPointer] = sums[start1];
                start1++;
            } else {
                assist[assistPointer] = sums[start2];
                start2++;
            }
            assistPointer++;
        }
        while (start1 <= mid) {
            assist[assistPointer] = sums[start1];
            start1++;
            assistPointer++;
        }
        while (start2 <= end) {
            assist[assistPointer] = sums[start2];
            start2++;
            assistPointer++;
        }
        for (int i = start; i <= end; i++) {
            sums[i] = assist[i];
        }

        return totalCount;
    }
}
