package lusibian.leetcode.linked_list;

public class _206ReverseLinkedList {
    class ListNode {
        int val;
        ListNode next;

        ListNode(int x) {
            val = x;
            next = null;
        }
    }

    public ListNode reverseList(ListNode head) {
        ListNode currentHeadReversed = null;    // 存放已翻转部分的链表头
        ListNode currentHead = head;    // 存放未翻转部分的链表头
        ListNode currentNext;   // 存放未翻转部分表头的下一跳
        while (currentHead != null) {
            currentNext = currentHead.next;
            currentHead.next = currentHeadReversed;
            currentHeadReversed = currentHead;
            currentHead = currentNext;
        }
        return currentHeadReversed;
    }
}
