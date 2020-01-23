package lusibian.leetcode.array;

import java.util.Stack;

public class TrappingRainWater {

    public static void main(String[] args) {
        int[] height = new int[]{0, 1, 0, 2, 1, 0, 1, 3, 2, 1, 2, 1};
        TrappingRainWater trappingRainWater = new TrappingRainWater();
        System.out.println(trappingRainWater.trap3(height));
    }

    // 数组法
    public int trap1(int[] height) {
        if (height == null || height.length == 0) {
            return 0;
        }
        int[] leftHeightMaxArray = new int[height.length];
        int leftHeightMax = 0;
        for (int i = 0; i < height.length; i++) {
            leftHeightMaxArray[i] = leftHeightMax;
            if (height[i] > leftHeightMax) {
                leftHeightMax = height[i];
            }
        }
        int rightHeightMax = 0;
        int totalStorage = 0;
        for (int i = height.length - 1; i >= 0; i--) {
            int minHeightMax = Math.min(leftHeightMaxArray[i], rightHeightMax);
            if (minHeightMax > height[i]) {
                totalStorage += minHeightMax - height[i];
            }
            if (height[i] > rightHeightMax) {
                rightHeightMax = height[i];
            }
        }
        return totalStorage;
    }

    // 双指针法
    public int trap2(int[] height) {
        if (height == null || height.length == 0) {
            return 0;
        }
        int left = 0;
        int right = height.length - 1;
        int leftHeightMax = 0;
        int rightHeighMax = 0;
        int totalStorage = 0;
        while (left <= right) {
            if (leftHeightMax <= rightHeighMax) {
                if (leftHeightMax > height[left]) {
                    totalStorage += leftHeightMax - height[left];
                } else {
                    leftHeightMax = height[left];
                }
                left++;
            } else {
                if (rightHeighMax > height[right]) {
                    totalStorage += rightHeighMax - height[right];
                } else {
                    rightHeighMax = height[right];
                }
                right--;
            }
        }
        return totalStorage;
    }

    // 栈
    public int trap3(int[] height) {
        Stack<Integer> stack = new Stack<>();
        int totalStorage = 0;
        for (int i = 0; i < height.length; i++) {
            while (!stack.empty() && height[i] > height[stack.peek()]) {
                int top = stack.pop();
                if (!stack.empty()) {
                    int boundedHeight = Math.min(height[i], height[stack.peek()]) - height[top];
                    int distance = i - stack.peek() - 1;
                    totalStorage += boundedHeight * distance;
                }
            }
            stack.push(i);
        }
        return totalStorage;
    }
}
