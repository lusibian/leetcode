package lusibian.leetcode.linked_list;

public class _237DeleteNodeInALinkedList {
    public class ListNode {
        int val;
        ListNode next;

        ListNode(int x) {
            val = x;
        }
    }

    // 将想要删除的节点的值替换为它后面节点中的值，然后删除它之后的节点
    public void deleteNode(ListNode node) {
        node.val = node.next.val;
        node.next = node.next.next;
    }
}
