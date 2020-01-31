package lusibian.leetcode.sort;

public class SortList {
    public static void main(String[] args) {
        int[] nums = new int[]{-1, 5, 3, 4, 0};
        SortList sortList = new SortList();
        ListNode head = sortList.createListByArray(nums);
        ListNode newhead = sortList.sortList(head);
    }

    public ListNode createListByArray(int[] nums) {
        if (nums == null || nums.length == 0) {
            return null;
        }
        ListNode head = new ListNode(nums[0]);
        ListNode end = head;
        for (int i = 1; i < nums.length; i++) {
            end.next = new ListNode(nums[i]);
            end = end.next;
        }
        return head;
    }

    public class ListNode {
        int val;
        ListNode next;

        ListNode(int x) {
            val = x;
        }
    }

    public ListNode sortList(ListNode head) {
        if (head == null) {
            return null;
        }
        int length = 0;
        ListNode currentPointer = head;
        while (currentPointer != null) {
            length++;
            currentPointer = currentPointer.next;
        }
        ListNode newHead = head;    // 新链表头指针
        ListNode newEnd;    // 新链表尾指针
        ListNode headOne;   // 归并时，第一个链表头
        ListNode headTwo;   // 归并时，第二个链表头
        ListNode pointerOne;    // 归并时，第一个链表中还未归并的最小节点
        ListNode pointerTwo;    // 归并时，第二个链表中还未归并的最小节点
        int stepNow;    // 已完成归并的长度
        for (int interval = 1; interval < length; interval *= 2) {
            // 初始化，从头开始
            headOne = newHead;
            headTwo = headOne;
            newHead = null;
            newEnd = null;
            stepNow = 0;

            while (stepNow + interval < length) {
                int stepNumberOne = 0;
                int stepNumberTwo = 0;
                // 找到第二个链表的头
                for (int i = 0; i < interval; i++) {
                    headTwo = headTwo.next;
                }
                pointerOne = headOne;
                pointerTwo = headTwo;
                while (stepNumberOne < interval && stepNumberTwo < interval && pointerTwo != null) {
                    if (pointerOne.val <= pointerTwo.val) {
                        if (newHead == null) {
                            newHead = pointerOne;
                            newEnd = pointerOne;
                        } else {
                            newEnd.next = pointerOne;
                            newEnd = newEnd.next;
                        }
                        pointerOne = pointerOne.next;
                        stepNumberOne++;
                    } else {
                        if (newHead == null) {
                            newHead = pointerTwo;
                            newEnd = pointerTwo;
                        } else {
                            newEnd.next = pointerTwo;
                            newEnd = newEnd.next;
                        }
                        pointerTwo = pointerTwo.next;
                        stepNumberTwo++;
                    }
                }
                // 若链表一还有剩余节点，将其归并
                while (stepNumberOne < interval) {
                    newEnd.next = pointerOne;
                    newEnd = newEnd.next;
                    pointerOne = pointerOne.next;
                    stepNumberOne++;
                }
                // 若链表二还有剩余节点，将其归并
                while (stepNumberTwo < interval && pointerTwo != null) {
                    newEnd.next = pointerTwo;
                    newEnd = newEnd.next;
                    pointerTwo = pointerTwo.next;
                    stepNumberTwo++;
                }
                // 继续下一段归并，初始化
                headOne = pointerTwo;
                headTwo = headOne;
                stepNow += interval * 2;
            }
            // 将本轮未参与排序的末尾部分接上
            // 如[1,2,3]，第一轮排序中，[1],[2]归并，[3]没有参与，则把[3]接到末尾
            newEnd.next = headOne;
        }
        return newHead;
    }
}