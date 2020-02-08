package lusibian.leetcode.array;

import java.util.Stack;

public class _85MaximalRectangle {
    // 转换后使用84方法，直方图中最大矩形的解法
    // 时间复杂度O(nm)，空间复杂度O(m)
    public int maximalRectangle(char[][] matrix) {
        if(matrix == null || matrix.length == 0 || matrix[0].length == 0) {
            return 0;
        }
        int maxArea = 0;
        int n = matrix.length;
        int m = matrix[0].length;
        int[] assistArray = new int[m];
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < m; j++) {
                if (matrix[i][j] == '1') {
                    assistArray[j] += 1;
                } else {
                    assistArray[j] = 0;
                }
            }
            int assistMax = largestRectangleArea3(assistArray);
            if (assistMax > maxArea) {
                maxArea = assistMax;
            }
        }
        return maxArea;
    }

    // 下面复用84题代码，直方图中最大矩形的解法

    // 数组法，即动态规划，记录比较结果，优化比较，时间复杂度O(n)，空间复杂度O(n)
    // 比较时跳过已比较过的数，如果某个数a，后面已经有一个数b比a小，则从b往后找时，比较都会跳过a
    // 每次遍历时，总比较次数不会超过2n，则时间复杂度为O(n)
    public int largestRectangleArea3(int[] heights) {
        if (heights == null || heights.length == 0) {
            return 0;
        }
        int maxRectangleArea = 0;
        int[] leftBoundaryIdx = new int[heights.length];
        int[] rightBoundaryIdx = new int[heights.length];
        for (int i = 0; i < heights.length; i++) {
            leftBoundaryIdx[i] = i;
            int p = i - 1;
            while (p >= 0 && heights[p] >= heights[i]) {
                leftBoundaryIdx[i] = leftBoundaryIdx[p];
                p = leftBoundaryIdx[p] - 1;
            }
        }
        for (int i = heights.length - 1; i >= 0; i--) {
            rightBoundaryIdx[i] = i;
            int p = i + 1;
            while (p < heights.length && heights[p] >= heights[i]) {
                rightBoundaryIdx[i] = rightBoundaryIdx[p];
                p = rightBoundaryIdx[p] + 1;
            }
        }
        for (int i = 0; i < heights.length; i++) {
            int rectangleArea = heights[i] * (rightBoundaryIdx[i] - leftBoundaryIdx[i] + 1);
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
        while (!heightStack.empty()) {
            int top = heightStack.pop();
            int length = heights.length;
            if (!heightStack.empty()) {
                length = heights.length - 1 - heightStack.peek();
            }
            int rectangleArea = heights[top] * length;
            if (rectangleArea > maxRectangleArea) {
                maxRectangleArea = rectangleArea;
            }
        }
        return maxRectangleArea;
    }
}
