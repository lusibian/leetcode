package lusibian.leetcode.linked_list;

public class _21MergeTwoSortedLists {
    public class ListNode {
        int val;
        ListNode next;

        ListNode(int x) {
            val = x;
        }
    }

    // 有序链表归并
    public ListNode mergeTwoLists(ListNode l1, ListNode l2) {
        ListNode newHead = new ListNode(0);
        ListNode newEnd = newHead;
        ListNode pointer1 = l1;
        ListNode pointer2 = l2;
        while (pointer1 != null && pointer2 != null) {
            if (pointer1.val <= pointer2.val) {
                newEnd.next = pointer1;
                newEnd = pointer1;

                pointer1 = pointer1.next;
            } else {
                newEnd.next = pointer2;
                newEnd = pointer2;

                pointer2 = pointer2.next;
            }
        }
        if (pointer1 != null) {
            newEnd.next = pointer1;
        } else {
            newEnd.next = pointer2;
        }
        return newHead.next;
    }
}
