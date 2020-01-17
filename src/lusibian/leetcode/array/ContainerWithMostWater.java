package lusibian.leetcode.array;

public class ContainerWithMostWater {
    public static void main(String[] args) {
        int[] height = new int[]{1,8,6,2,5,4,8,3,7};
        System.out.println(maxArea(height));
    }
    public static int maxArea(int[] height) {
        int res = 0;
        int left = 0;
        int right = height.length - 1;
        while (left < right) {
            int storage = Math.min(height[left], height[right]) * (right - left);
            if (storage > res) {
                res = storage;
            }
            if (height[left] > height[right]) {
                right--;
            } else {
                left++;
            }
        }
        return res;
    }
}
