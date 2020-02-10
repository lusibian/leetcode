package lusibian.leetcode.linked_list;

public class _141LinkedListCycle {
    class ListNode {
        int val;
        ListNode next;

        ListNode(int x) {
            val = x;
            next = null;
        }
    }

    // 快慢指针
    public boolean hasCycle(ListNode head) {
        ListNode faster = head;
        ListNode slower = head;
        while (faster != null) {
            faster = faster.next;
            if (faster == null) {
                return false;
            }
            if (faster == slower) {
                return true;
            }
            faster = faster.next;
            slower = slower.next;
        }
        return false;
    }
}
