package lusibian.leetcode.array;

public class SortColors {
    // count
    public void sortColors1(int[] nums) {
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

    // swap
    public void sortColors2(int[] nums) {
        int zeroPointer = 0;
        int twoPointer = nums.length - 1;
        for (int i = 0; i <= twoPointer; i++) {
            if(nums[i] == 0) {
                nums[i] = nums[zeroPointer];
                nums[zeroPointer] = 0;
                zeroPointer++;
            } else if(nums[i] == 2) {
                nums[i] = nums[twoPointer];
                nums[twoPointer] = 2;
                twoPointer--;
            }
        }
    }
}
