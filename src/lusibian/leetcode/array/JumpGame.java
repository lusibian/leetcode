package lusibian.leetcode.array;

public class JumpGame {
    public boolean canJump(int[] nums) {
        int reachIdx = 0;
        for (int i = 0; i < nums.length; i++) {
            if(i <= reachIdx) {
                if(nums[i] + i > reachIdx) {
                    reachIdx = nums[i] + i;
                }
            } else {
                return false;
            }
        }
        return true;
    }
}
