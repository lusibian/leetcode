package lusibian.leetcode.linked_list;

public class _328OddEvenLinkedList {
    public class ListNode {
        int val;
        ListNode next;

        ListNode(int x) {
            val = x;
        }
    }

    public ListNode oddEvenList(ListNode head) {
        if (head == null || head.next == null) {
            return head;
        }
        ListNode evenHead = head.next;
        ListNode oddEnd = head;
        ListNode evenEnd = evenHead;
        ListNode pointer = evenHead.next;
        while (pointer != null) {
            oddEnd.next = pointer;
            oddEnd = pointer;
            pointer = pointer.next;
            if (pointer == null) {
                break;
            }
            evenEnd.next = pointer;
            evenEnd = pointer;
            pointer = pointer.next;
        }
        oddEnd.next = evenHead;
        evenEnd.next = null;
        return head;
    }
}
