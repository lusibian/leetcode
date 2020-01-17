package lusibian.leetcode.array;

public class FindTheDuplicateNumber {
    // 快慢指针，把数组中的值看作一个指针，将整个数组变成一个链表，如果有数字相等，说明有多个指针指向同一个节点，数组有环
    // 数字大于0，则没有指针指向nums[0]，nums[0]不在环中（）
    public int findDuplicate(int[] nums) {
        int fast = 0;
        int slow = 0;
        while(true) {
            fast = nums[nums[fast]];
            slow = nums[slow];
            if(fast == slow) {
                break;
            }
        }
        int res = 0;
        while (true) {
            res = nums[res];
            slow = nums[slow];
            if(res == slow) {
                return res;
            }
        }
    }
}
