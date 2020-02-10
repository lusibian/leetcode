package lusibian.leetcode.linked_list;

public class _160IntersectionOfTwoLinkedLists {
    class ListNode {
        int val;
        ListNode next;

        ListNode(int x) {
            val = x;
            next = null;
        }
    }

    // 先遍历找到两个链表长度，n >= m，
    // 使用两个指针，从两个表头开始
    // 对长的那个链表，指针先走 n - m 步
    // 然后两个指针同步前进
    // 直到两个指针指向同一节点，或都为null时，返回找到的节点或null
    public ListNode getIntersectionNode(ListNode headA, ListNode headB) {
        ListNode pointer1 = headA, pointer2 = headB;
        int length1 = 0, length2 = 0;
        while (pointer1 != null) {
            length1++;
            pointer1 = pointer1.next;
        }
        while (pointer2 != null) {
            length2++;
            pointer2 = pointer2.next;
        }
        int dValue = length1 - length2;
        pointer1 = headA;
        pointer2 = headB;
        while (dValue > 0) {
            pointer1 = pointer1.next;
            dValue--;
        }
        while (dValue < 0) {
            pointer2 = pointer2.next;
            dValue++;
        }
        while (pointer1 != pointer2) {
            pointer1 = pointer1.next;
            pointer2 = pointer2.next;
        }
        return pointer1;
    }
}