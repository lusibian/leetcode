package lusibian.leetcode.linked_list;

public class _19RemoveNthNodeFromEndOfList {

    public class ListNode {
        int val;
        ListNode next;

        ListNode(int x) {
            val = x;
        }
    }

    // 题意标明n一定合法，不考虑要删掉节点不存在的情况
    // 需考虑被删除节点是头节点的情况
    // 遍历两次链表，第一次查看链表长度，第二次找到倒数第K个节点进行删除
    public ListNode removeNthFromEnd(ListNode head, int n) {
        int listLength = 0;
        ListNode pointer = head;
        while (pointer != null) {
            listLength++;
            pointer = pointer.next;
        }
        int step = listLength - n;
        if (step == 0) {
            return head.next;
        }
        ListNode before = head;
        pointer = head;
        while (step > 0) {
            before = pointer;
            pointer = pointer.next;
            step--;
        }
        before.next = pointer.next;
        return head;
    }
}
