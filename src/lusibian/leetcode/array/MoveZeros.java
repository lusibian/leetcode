package lusibian.leetcode.array;

public class MoveZeros {
    public void moveZeroes(int[] nums) {
        int now = 0;
        for (int num : nums) {
            if(num != 0) {
                nums[now] = num;
                now++;
            }
        }
        for(int i = now; i < nums.length; i++){
            nums[i] = 0;
        }
    }
}
