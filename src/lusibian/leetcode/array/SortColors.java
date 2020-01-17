package lusibian.leetcode.array;

public class SortColors {
    public void sortColors(int[] nums) {
        int[] counts = new int[3];
        for (int num : nums) {
            counts[num]++;
        }
        for (int i = 0; i < counts[0]; i++) {
            nums[i] = 0;
        }
        for (int i = counts[0]; i < counts[0] + counts[1]; i++){
            nums[i] = 1;
        }
        for (int i = counts[0] + counts[1]; i < nums.length; i++){
            nums[i] = 2;
        }
    }
}
