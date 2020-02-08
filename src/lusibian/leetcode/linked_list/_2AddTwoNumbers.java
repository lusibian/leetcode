package lusibian.leetcode.linked_list;

public class _2AddTwoNumbers {
    public class ListNode {
        int val;
        ListNode next;

        ListNode(int x) {
            val = x;
        }
    }

    // 两个指针遍历两个链表，一个数字记录进位
    public ListNode addTwoNumbers(ListNode l1, ListNode l2) {
        ListNode newHead = new ListNode(0);
        ListNode newEnd = newHead;
        ListNode pointer1 = l1, pointer2 = l2;
        int carryTemp = 0;
        while (pointer1 != null && pointer2 != null) {
            int temp = pointer1.val + pointer2.val + carryTemp;
            ListNode node = new ListNode(temp % 10);
            carryTemp = temp / 10;
            newEnd.next = node;
            newEnd = node;

            pointer1 = pointer1.next;
            pointer2 = pointer2.next;
        }
        while (pointer1 != null) {
            int temp = pointer1.val + carryTemp;
            ListNode node = new ListNode(temp % 10);
            carryTemp = temp / 10;
            newEnd.next = node;
            newEnd = node;

            pointer1 = pointer1.next;
        }
        while (pointer2 != null) {
            int temp = pointer2.val + carryTemp;
            ListNode node = new ListNode(temp % 10);
            carryTemp = temp / 10;
            newEnd.next = node;
            newEnd = node;

            pointer2 = pointer2.next;
        }
        if (carryTemp > 0) {
            newEnd.next = new ListNode(carryTemp);
        }
        return newHead.next;
    }
}
