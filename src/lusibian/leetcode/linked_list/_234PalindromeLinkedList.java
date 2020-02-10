package lusibian.leetcode.linked_list;

import java.util.ArrayList;
import java.util.List;

public class _234PalindromeLinkedList {
    public static void main(String[] args) {
        int[] nums = new int[]{-129, -129};
        _234PalindromeLinkedList temp = new _234PalindromeLinkedList();
        System.out.println(temp.isPalindrome2(temp.newListFromArray(nums)));
    }

    public ListNode newListFromArray(int[] nums) {
        ListNode newHead = new ListNode(0);
        ListNode newEnd = newHead;
        for (int num : nums) {
            newEnd.next = new ListNode(num);
            newEnd = newEnd.next;
        }
        return newHead.next;
    }

    class ListNode {
        int val;
        ListNode next;

        ListNode(int x) {
            val = x;
            next = null;
        }
    }

    // 转换成数组，用双指针从两端向中间遍历，时间O(n)，空间O(n)
    public boolean isPalindrome1(ListNode head) {
        if (head == null || head.next == null) {
            return true;
        }
        List<Integer> nums = new ArrayList<>();
        ListNode pointer = head;
        while (pointer != null) {
            nums.add(pointer.val);
            pointer = pointer.next;
        }
        int left = 0, right = nums.size() - 1;
        while (left < right) {
            if (!nums.get(left).equals(nums.get(right))) {
                return false;
            }
            left++;
            right--;
        }
        return true;
    }

    // 快慢指针找到链表中点，反转前（或后）半部分链表，进行一一对比
    // 链表长度有奇偶，如果是长度为奇数，则进行对比的链表不包含中点
    // 时间O(n)，空间O(1)
    public boolean isPalindrome2(ListNode head) {
        if (head == null || head.next == null) {
            return true;
        }
        ListNode faster = head, slower = head;
        ListNode endNext1 = null, head2 = null;  // 记录前半部分的end的next，后半部分的head
        while (faster != null) {
            faster = faster.next;
            if (faster == null) {   // 链表长度为奇数
                endNext1 = slower;  // slower此时指向中点
                head2 = slower.next;    // 跳过中点
                break;
            }
            faster = faster.next;
            slower = slower.next;
        }
        if (endNext1 == null) { // 链表长度为偶数
            endNext1 = slower;  // slower此时指向右中点（两个中点，记为左中点、右中点）
            head2 = slower;
        }

        // 翻转链表前半部分，翻转后，前半部分尾节点的下一跳仍是endNext1
        // 例，[1,2,3,4,5]，翻转后[2,1,3,4,5]
        // 例，[1,2,3,4,5,6]，翻转后[3,2,1,4,5,6]
        ListNode head1 = reverseList(head, endNext1);

        ListNode pointer1 = head1, pointer2 = head2;
        boolean isPalindromeFlag = true;

        // 两个指针同步遍历前半部分和后半部分，判断是否一致
        while (pointer1 != endNext1) {
            if (pointer1.val != pointer2.val) {
                isPalindromeFlag = false;
                break;
            }
            pointer1 = pointer1.next;
            pointer2 = pointer2.next;
        }

        // 链表还原
        head = reverseList(head1, endNext1);
        return isPalindromeFlag;
    }

    // 翻转endNext前的链表部分，翻转后仍是一个完整的链表
    // 返回翻转后的头节点
    // 例，[1,2,3,4,5,6,7,8]，endNext = 6，则翻转后链表[5,4,3,2,1,6,7,8]
    private ListNode reverseList(ListNode head, ListNode endNext) {
        if (head == endNext) {
            return null;
        }
        ListNode currentHeadReversed = endNext;    // 存放已翻转部分的链表头
        ListNode currentHead = head;    // 存放未翻转部分的链表头
        ListNode currentNext;   // 存放未翻转部分表头的下一跳
        while (currentHead != endNext) {
            currentNext = currentHead.next;
            currentHead.next = currentHeadReversed;
            currentHeadReversed = currentHead;
            currentHead = currentNext;
        }
        return currentHeadReversed;
    }
}
