package lusibian.leetcode.array;

import java.util.Stack;

public class LargestRectangleInHistogram {

    public static void main(String[] args) {
        int[] heights = new int[]{5, 4, 1, 2};
        LargestRectangleInHistogram largestRectangleInHistogram = new LargestRectangleInHistogram();
        System.out.println(largestRectangleInHistogram.largestRectangleArea4(heights));
    }

    // 分治法，平均时间复杂度O(n*lg(n))，最坏时间复杂度O(n^2)，空间复杂度O(1)
    public int largestRectangleArea1(int[] heights) {
        if (heights == null || heights.length == 0) {
            return 0;
        }
        return largestRectangleArea1(heights, 0, heights.length - 1);
    }

    private int largestRectangleArea1(int[] heights, int begin, int end) {
        if (begin > end) {
            return 0;
        }
        int minHeightIdx = end;
        for (int i = begin; i < end; i++) {
            if (heights[minHeightIdx] > heights[i]) {
                minHeightIdx = i;
            }
        }
        return Math.max(heights[minHeightIdx] * (end - begin + 1),
                Math.max(largestRectangleArea1(heights, begin, minHeightIdx - 1),
                        largestRectangleArea1(heights, minHeightIdx + 1, end)));
    }

    //暴力法，时间复杂度O(n^2)，空间复杂度O(1)
    public int largestRectangleArea2(int[] heights) {
        if (heights == null || heights.length == 0) {
            return 0;
        }
        int maxRectangleArea = 0;
        for (int i = 0; i < heights.length; i++) {
            int length = 1;
            int left = i - 1;
            while (left >= 0 && heights[left] >= heights[i]) {
                length++;
                left--;
            }
            int right = i + 1;
            while (right < heights.length && heights[right] >= heights[i]) {
                length++;
                right++;
            }
            int rectangleArea = length * heights[i];
            if (rectangleArea > maxRectangleArea) {
                maxRectangleArea = rectangleArea;
            }
        }
        return maxRectangleArea;
    }

    // 数组法，记录比较结果，优化比较，时间复杂度O(n)，空间复杂度O(n)
    // 比较时跳过已比较过的数，如果某个数a，后面已经有一个数b比a小，则从b往后找时，比较都会跳过a
    // 每次遍历时，总比较次数不会超过2n，则时间复杂度为O(n)
    public int largestRectangleArea3(int[] heights) {
        if (heights == null || heights.length == 0) {
            return 0;
        }
        int maxRectangleArea = 0;
        int[] leftLargerIdx = new int[heights.length];
        int[] rightLargerIdx = new int[heights.length];
        for (int i = 0; i < heights.length; i++) {
            leftLargerIdx[i] = i;
            int p = i - 1;
            while (p >= 0 && heights[p] >= heights[i]) {
                leftLargerIdx[i] = leftLargerIdx[p];
                p = leftLargerIdx[p] - 1;
            }
        }
        for (int i = heights.length - 1; i >= 0; i--) {
            rightLargerIdx[i] = i;
            int p = i + 1;
            while (p < heights.length && heights[p] >= heights[i]) {
                rightLargerIdx[i] = rightLargerIdx[p];
                p = rightLargerIdx[p] + 1;
            }
        }
        for (int i = 0; i < heights.length; i++) {
            int rectangleArea = heights[i] * (rightLargerIdx[i] - leftLargerIdx[i] + 1);
            if (rectangleArea > maxRectangleArea) {
                maxRectangleArea = rectangleArea;
            }
        }
        return maxRectangleArea;
    }

    // 栈，时间复杂度O(n)，空间复杂度O(n)
    public int largestRectangleArea4(int[] heights) {
        if (heights == null || heights.length == 0) {
            return 0;
        }
        int maxRectangleArea = 0;
        Stack<Integer> heightStack = new Stack<>();
        for (int i = 0; i < heights.length; i++) {
            while (!heightStack.empty() && heights[i] < heights[heightStack.peek()]) {
                int top = heightStack.pop();
                int length = i;
                if (!heightStack.empty()) {
                    length = i - heightStack.peek() - 1;
                }
                int rectangleArea = heights[top] * length;
                if (rectangleArea > maxRectangleArea) {
                    maxRectangleArea = rectangleArea;
                }
            }
            heightStack.push(i);
        }
        int length = 0;
        while (!heightStack.empty()) {
            int top = heightStack.pop();
            if (!heightStack.empty()) {
                length += top - heightStack.peek();
            } else {
                length += top + 1;
            }
            int rectangleArea = heights[top] * length;
            if (rectangleArea > maxRectangleArea) {
                maxRectangleArea = rectangleArea;
            }
        }
        return maxRectangleArea;
    }
}
