package lusibian.leetcode.heap;

import java.util.Comparator;
import java.util.PriorityQueue;

public class _23MergekSortedLists {
    public class ListNode {
        int val;
        ListNode next;

        ListNode(int x) {
            val = x;
        }
    }

    // 分治法，归并，非递归
    public ListNode mergeKLists1(ListNode[] lists) {
        if (lists == null || lists.length == 0) {
            return null;
        }
        int listsLength = lists.length;
        for (int interval = 1; interval < listsLength; interval *= 2) {
            int listIdx1 = 0;
            int listIdx2 = interval;
            while (listIdx2 < listsLength) {
                merge1(lists, listIdx1, listIdx2);
                listIdx1 += 2 * interval;
                listIdx2 += 2 * interval;
            }
        }
        return lists[0];
    }

    private void merge1(ListNode[] lists, int listIdx1, int listIdx2) {
        ListNode newHead = new ListNode(0);
        ListNode newEnd = newHead;
        ListNode pointer1 = lists[listIdx1];
        ListNode pointer2 = lists[listIdx2];
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
        }
        if (pointer2 != null) {
            newEnd.next = pointer2;
        }
        lists[listIdx1] = newHead.next;
    }

    // 优先队列法，优先队列可使用最小堆实现
    public ListNode mergeKLists2(ListNode[] lists) {
        if (lists == null || lists.length == 0) {
            return null;
        }
        PriorityQueue<ListNode> queue = new PriorityQueue<ListNode>(lists.length, new ListNodeComparator());
        ListNode newHead = new ListNode(0);
        ListNode newEnd = newHead;
        for (ListNode list : lists) {
            if (list != null) {
                queue.add(list);
            }
        }
        while (!queue.isEmpty()) {
            ListNode tempNode = queue.poll();
            newEnd.next = tempNode;
            newEnd = newEnd.next;
            if (tempNode.next != null) {
                queue.add(tempNode.next);
            }
        }
        return newHead.next;
    }

    private class ListNodeComparator implements Comparator<ListNode> {
        @Override
        public int compare(ListNode a, ListNode b) {
            return Integer.compare(a.val, b.val);
        }
    }
}
