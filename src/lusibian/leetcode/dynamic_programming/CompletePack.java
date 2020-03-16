package lusibian.leetcode.dynamic_programming;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class CompletePack {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int numLine = sc.nextInt();
        int packSize = sc.nextInt();
        int[] weights = new int[numLine];
        int[] values = new int[numLine];
        for (int i = 0; i < numLine; i++) {
            weights[i] = sc.nextInt();
            values[i] = sc.nextInt();
        }
        System.out.println(completerPack(weights, values, packSize));
    }

    // 完全背包
    // 记背包大小为m，物品种类为n
    // 时间复杂度O(mn)，空间复杂度O(m)
    public static int completerPack(int[] weights, int[] values, int packSize) {
        // 去掉weight更大，但value更小的物品，进行剪枝
        List<List<Integer>> tempList = optimizeItems1(weights, values);
//        List<List<Integer>> tempList = optimizeItems2(weights, values, packSize);
        weights = tempList.get(0).stream().mapToInt(Integer::valueOf).toArray();
        values = tempList.get(1).stream().mapToInt(Integer::valueOf).toArray();

        int[] pack = new int[packSize + 1];
        for (int i = 0; i < weights.length; i++) {
            for (int j = weights[i]; j <= packSize; j++) {
                if (values[i] + pack[j - weights[i]] > pack[j]) {
                    pack[j] = values[i] + pack[j - weights[i]];
                }
            }
        }
        return pack[packSize];
    }

    // 去掉weight更大，但value更小的物品，进行剪枝，时间复杂度O(n^2)
    private static List<List<Integer>> optimizeItems1(int[] weights, int[] values) {
        // 去掉weight更大，但value更小的物品
        for (int i = 0; i < weights.length; i++) {
            for (int j = i + 1; j < weights.length; j++) {
                if (weights[i] <= weights[j] && values[i] >= values[j]) {
                    weights[j] = -1;
                    values[j] = -1;
                }
            }
        }

        List<Integer> tempWeights = new ArrayList<>();
        List<Integer> tempValues = new ArrayList<>();
        for (int i = 0; i < weights.length; i++) {
            if (weights[i] > -1) {
                tempWeights.add(weights[i]);
                tempValues.add(values[i]);
            }
        }
        List<List<Integer>> tempList = new ArrayList<>();
        tempList.add(tempWeights);
        tempList.add(tempValues);
        return tempList;
    }

    // 去掉weight更大，但value更小的物品，进行剪枝，时间复杂度O(n + m)
    private static List<List<Integer>> optimizeItems2(int[] weights, int[] values, int packSize) {
        int[] pack = new int[packSize + 1];
        // 对每个weight，只保留value最大的物品
        for (int i = 0; i < weights.length; i++) {
            if (weights[i] <= packSize && pack[weights[i]] < values[i]) {
                pack[weights[i]] = values[i];
            }
        }
        List<Integer> tempWeights = new ArrayList<>();
        List<Integer> tempValues = new ArrayList<>();
        int currentMaxValue = 0;
        // 在目前保留物品中，去掉weight更大，但value更小的物品，并将保留下的物品存入新数组
        for (int i = 1; i <= packSize; i++) {
            if (pack[i] > 0) {
                if (pack[i] > currentMaxValue) {
                    currentMaxValue = pack[i];
                    tempWeights.add(i);
                    tempValues.add(pack[i]);
                }
            }
        }
        List<List<Integer>> tempList = new ArrayList<>();
        tempList.add(tempWeights);
        tempList.add(tempValues);
        return tempList;
    }
}
