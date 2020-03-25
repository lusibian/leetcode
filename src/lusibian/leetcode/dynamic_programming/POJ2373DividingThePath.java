package lusibian.leetcode.dynamic_programming;

import java.util.*;

public class POJ2373DividingThePath {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int cowNumber = sc.nextInt();
        int pathLength = sc.nextInt();
        int minRadius = sc.nextInt();
        int maxRadius = sc.nextInt();
        List<Integer> preferredRanges = new ArrayList<Integer>(cowNumber * 2);
        for (int i = 0; i < cowNumber; i++) {
            preferredRanges.add(sc.nextInt());
            preferredRanges.add(sc.nextInt());
        }
        System.out.println(dividingThePath(pathLength, minRadius, maxRadius, preferredRanges));
    }

    private static int dividingThePath(int pathLength, int minRadius, int maxRadius, List<Integer>  preferredRanges) {
        int[] cowPreferred = new int[pathLength + 1];
        for (int i = 0; i < preferredRanges.size(); i+=2) {
            cowPreferred[preferredRanges.get(i) + 1] += 1;
            cowPreferred[preferredRanges.get(i + 1)] -= 1;
        }
        int positiveCount = 0;
        // 找到不能使用的中间状态，即某个状态不能存在与羊喜欢的区间中间，如羊喜欢[3,6]，那么状态4、5不能使用
        for (int i = 0; i < cowPreferred.length; i++) {
            positiveCount += cowPreferred[i];
            if (positiveCount > 0) {
                cowPreferred[i] = 1;
            } else {
                cowPreferred[i] = 0;
            }
        }
        // 坐标
        Deque<Integer> deque = new LinkedList<Integer>();
        int[] minSprinklersCount = new int[pathLength + 1];
        for (int i = 1; i < minSprinklersCount.length; i++) {
            minSprinklersCount[i] = -1;
        }
        for (int i = minRadius * 2; i <= pathLength; i+=2) {
            if (cowPreferred[i - minRadius * 2] == 0 && minSprinklersCount[i - minRadius * 2] != -1) {
                while (!deque.isEmpty() && minSprinklersCount[deque.getLast()] >= minSprinklersCount[i - minRadius * 2]) {
                    deque.pollLast();
                }
                deque.add(i - minRadius * 2);
            }
            if (cowPreferred[i] == 0) {
                while (!deque.isEmpty() && i - deque.getFirst() > maxRadius * 2) {
                    deque.pollFirst();
                }
                if (!deque.isEmpty()) {
                    minSprinklersCount[i] = minSprinklersCount[deque.getFirst()] + 1;
                } else {
                    minSprinklersCount[i] = -1;
                }
            }
        }
        return minSprinklersCount[pathLength];
    }
}
