package lusibian.leetcode.dynamic_programming;

import java.util.LinkedList;

public class _862ShortestSubarrayWithSumAtLeastK {
    public static void main(String[] args) {
        int[] A = new int[]{2, -1, 2};
        int K = 3;
        System.out.println(shortestSubarray2(A, K));
    }
    // 使用单调队列保存当前数前面的子数组和，超时
    // 超时原因，每次加入一个新数时，得去更新队列中的所有值
    public static int shortestSubarray1(int[] A, int K) {
        int shortestSubarrayLength = Integer.MAX_VALUE;
        // 保存和值、子数组长度
        LinkedList<Integer> sumDeque = new LinkedList<>();
        LinkedList<Integer> lengthDeque = new LinkedList<>();
        for (int num : A) {
            while (!sumDeque.isEmpty() && sumDeque.getLast() <= 0) {
                sumDeque.pollLast();
                lengthDeque.pollLast();
            }
            for (int i = 0; i < sumDeque.size(); i++) {
                sumDeque.set(i, sumDeque.get(i) + num);
                lengthDeque.set(i, lengthDeque.get(i) + 1);
            }
            sumDeque.add(num);
            lengthDeque.add(1);
            while (!lengthDeque.isEmpty() && lengthDeque.getFirst() >= shortestSubarrayLength) {
                sumDeque.pollFirst();
                lengthDeque.pollFirst();
            }
            while (!sumDeque.isEmpty() && sumDeque.getFirst() >= K) {
                sumDeque.pollFirst();
                shortestSubarrayLength = lengthDeque.pollFirst();
            }
        }
        if (shortestSubarrayLength == Integer.MAX_VALUE) {
            shortestSubarrayLength = -1;
        }
        return shortestSubarrayLength;
    }

    // 记S[i]为从0到i的子数组和，单调队列来保存S[i]
    // 从i+1到j的子数组和，可以用S[j] - S[i]来计算
    // 记n为数组长度
    // 时间复杂度O(n)，空间复杂度O(n)
    public static int shortestSubarray2(int[] A, int K) {
        int shortestSubarrayLength = Integer.MAX_VALUE;
        // 保存S[i]、i值
        LinkedList<Integer> sumDeque = new LinkedList<>();
        LinkedList<Integer> idxDeque = new LinkedList<>();
        sumDeque.add(0);
        idxDeque.add(-1);
        int currentSum = 0;
        for (int i = 0; i < A.length; i++) {
            currentSum += A[i];

            while (!idxDeque.isEmpty() && i - idxDeque.getFirst() >= shortestSubarrayLength) {
                sumDeque.pollFirst();
                idxDeque.pollFirst();
            }
            while (!sumDeque.isEmpty() && currentSum - sumDeque.getFirst() >= K) {
                sumDeque.pollFirst();
                shortestSubarrayLength = i - idxDeque.pollFirst();
            }

            while (!sumDeque.isEmpty() && sumDeque.getLast() >= currentSum) {
                sumDeque.pollLast();
                idxDeque.pollLast();
            }
            sumDeque.add(currentSum);
            idxDeque.add(i);
        }
        if (shortestSubarrayLength == Integer.MAX_VALUE) {
            shortestSubarrayLength = -1;
        }
        return shortestSubarrayLength;
    }
}
