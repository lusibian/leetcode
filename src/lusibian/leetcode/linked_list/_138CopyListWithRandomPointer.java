package lusibian.leetcode.linked_list;

public class _138CopyListWithRandomPointer {
    class Node {
        int val;
        Node next;
        Node random;

        public Node(int val) {
            this.val = val;
            this.next = null;
            this.random = null;
        }
    }

    // 第一步：先复制节点，把链表变成1->1'->2->2'->3->3'->null
    // 第二步：给random指针赋值
    // 第三步：分离复制链表和源链表
    // 第四步：返回
    public Node copyRandomList(Node head) {
        if (head == null) {
            return null;
        }
        Node pointer = head;
        while (pointer != null) {
            Node newNode = new Node(pointer.val);
            newNode.next = pointer.next;
            pointer.next = newNode;
            pointer = newNode.next;
        }
        pointer = head;
        while (pointer != null) {
            if (pointer.random == null) {
                pointer.next.random = null;
            } else {
                pointer.next.random = pointer.random.next;
            }
            pointer = pointer.next.next;
        }
        Node newHead = head.next;
        Node newEnd = newHead;
        pointer = newEnd.next;
        head.next = pointer;
        while (pointer != null) {
            newEnd.next = pointer.next;
            newEnd = newEnd.next;
            pointer.next = newEnd.next;
            pointer = pointer.next;
        }
        return newHead;
    }
}
