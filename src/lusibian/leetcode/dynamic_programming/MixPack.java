package lusibian.leetcode.dynamic_programming;

import java.util.Deque;
import java.util.LinkedList;
import java.util.Scanner;

public class MixPack {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int numLine = sc.nextInt();
        int packSize = sc.nextInt();
        int[] weights = new int[numLine];
        int[] values = new int[numLine];
        int[] itemNums = new int[numLine];
        for (int i = 0; i < numLine; i++) {
            weights[i] = sc.nextInt();
            values[i] = sc.nextInt();
            itemNums[i] = sc.nextInt();
        }
        System.out.println(mixPack(weights, values, itemNums, packSize));
    }


    // 混合背包
    // 对不同类型物品，使用对应类型背包的解法
    public static int mixPack(int[] weights, int[] values, int[] itemNums, int packSize) {
        int[] dp = new int[packSize + 1];
        int[] tempDpArray = new int[packSize + 1];
        Deque<Integer> idxQueue = new LinkedList<>();
        for (int i = 0; i < weights.length; i++) {
            if (itemNums[i] == -1) {
                zeroOnePackOneItem(weights[i], values[i], dp);
            } else if (itemNums[i] == 0) {
                completerPackOneItem(weights[i], values[i], dp);
            } else {
                multiplePackOneItem(weights[i], values[i], itemNums[i], dp, tempDpArray, idxQueue);
            }
        }
        return dp[packSize];
    }

    private static void zeroOnePackOneItem(int weight, int value, int[] dp) {
        for (int j = dp.length - 1; j >= weight; j--) {
            if (value + dp[j - weight] > dp[j]) {
                dp[j] = value + dp[j - weight];
            }
        }
    }

    private static void completerPackOneItem(int weight, int value, int[] dp) {
        for (int j = weight; j < dp.length; j++) {
            if (value + dp[j - weight] > dp[j]) {
                dp[j] = value + dp[j - weight];
            }
        }
    }

    private static void multiplePackOneItem(int weight, int value, int itemNum, int[] dp, int[] tempDpArray, Deque<Integer> idxQueue) {
        for (int reminder = 0; reminder < weight; reminder++) {
            idxQueue.clear();
            for (int j = 0; reminder + j * weight < dp.length; j++) {
                if (!idxQueue.isEmpty() && j - idxQueue.getFirst() > itemNum) {
                    idxQueue.pop();
                }
                int valueForIdx = dp[reminder + weight * j] - value * j;
                while (!idxQueue.isEmpty()) {
                    int last = idxQueue.getLast();
                    int valueForLast = dp[reminder + weight * last] - value * last;
                    if (valueForLast < valueForIdx) {
                        idxQueue.removeLast();
                    } else {
                        break;
                    }
                }
                idxQueue.add(j);
                int first = idxQueue.getFirst();
                int valueForFirst = dp[reminder + weight * first] - value * first;
                tempDpArray[reminder + j * weight] = valueForFirst + value * j;
            }
        }
        System.arraycopy(tempDpArray, 0, dp, 0, dp.length);
    }
}
