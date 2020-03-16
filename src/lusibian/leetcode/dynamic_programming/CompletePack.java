package lusibian.leetcode.dynamic_programming;

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
    public static int completerPack(int[] weights, int[] values, int packSize) {
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
}
