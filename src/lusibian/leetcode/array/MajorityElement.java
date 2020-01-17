package lusibian.leetcode.array;

public class MajorityElement {
    public int majorityElement(int[] nums) {
        int res = 0;
        int score = 0;
        for (int num : nums) {
            if (score == 0) {
                res = num;
                score++;
            } else if (num == res) {
                score++;
            } else {
                score--;
            }
        }
        return res;
    }
}
