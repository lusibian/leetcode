package lusibian.leetcode.linked_list;

import java.util.ArrayList;
import java.util.List;

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
    public ListNode removeNthFromEnd1(ListNode head, int n) {
        int listLength = 0;
        ListNode pointer = head;
        while (pointer != null) {
            listLength++;
            pointer = pointer.next;
        }
        int step = listLength - n;
        if (step == 0) {    // 删除头节点
            return head.next;
        }
        pointer = head;
        while (step > 1) {
            pointer = pointer.next;
            step--;
        }
        pointer.next = pointer.next.next;   // 此时，pointer为倒数第k+1个节点
        return head;
    }

    // 双指针，两个指针间隔n进行遍历
    public ListNode removeNthFromEnd2(ListNode head, int n) {
        ListNode pointerLeft = head;
        ListNode pointerRight = head;
        int step = n;
        while (step > 0) {  // pointerRight先走n步
            pointerRight = pointerRight.next;
            step--;
        }
        if(pointerRight == null) {  // 说明倒数第n个节点是头节点
            return head.next;
        }
        while (pointerRight.next != null) {
            pointerLeft = pointerLeft.next;
            pointerRight = pointerRight.next;
        }
        pointerLeft.next = pointerLeft.next.next;
        return head;
    }

    // 使用容器，一次遍历放入容器，使用下标找到倒数第n的节点
    public ListNode removeNthFromEnd3(ListNode head, int n) {
        List<ListNode> nodeList = new ArrayList<>();
        ListNode pointer = head;
        while (pointer != null) {
            nodeList.add(pointer);
            pointer = pointer.next;
        }
        int length = nodeList.size();
        if (length == n) {
            return head.next;
        }
        ListNode preNode = nodeList.get(length - n - 1);
        preNode.next = preNode.next.next;
        return head;
    }
}
