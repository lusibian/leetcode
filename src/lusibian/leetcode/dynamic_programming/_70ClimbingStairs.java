package lusibian.leetcode.dynamic_programming;

public class _70ClimbingStairs {
    // 斐波那契数列，爬楼梯问题
    public int climbStairs(int n) {
        int climbWaysCount = 0;
        int beforeCount = 1;
        int twoBeforeCount = 0;
        while(n > 0) {
            climbWaysCount = beforeCount + twoBeforeCount;
            twoBeforeCount = beforeCount;
            beforeCount = climbWaysCount;
            n--;
        }
        return climbWaysCount;
    }
}
