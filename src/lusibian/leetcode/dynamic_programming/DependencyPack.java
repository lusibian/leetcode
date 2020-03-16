package lusibian.leetcode.dynamic_programming;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;

public class DependencyPack {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int numLine = sc.nextInt();
        int packSize = sc.nextInt();
        int[] weights = new int[numLine];
        int[] values = new int[numLine];
        int[] dependencies = new int[numLine];
        List<List<Integer>> childLists = new ArrayList<>();
        for (int i = 0; i < numLine; i++) {
            childLists.add(new ArrayList<>());
        }
        for (int i = 0; i < numLine; i++) {
            weights[i] = sc.nextInt();
            values[i] = sc.nextInt();
            dependencies[i] = sc.nextInt();
            if (dependencies[i] > 0) {
                childLists.get(dependencies[i] - 1).add(i);
            }
        }
        System.out.println(dependencyPack(weights, values, dependencies, childLists, packSize));
    }

    // 有依赖关系的背包问题
    // 依赖关系不存在循环依赖，不存在多重依赖，所有物品的依赖关系图形成一个森林
    // 对森林中某棵树A，把A中的物品集合转化成一个物品组，物品组中的每件物品代表取A中物品的一种选择
    //
    // 对每个物品组进行完全背包中提到的剪枝优化方法2，把物品组中组合级别的数量减到不超过背包大小的数量
    // 对本题，剪枝优化方法2的做法就是将树中的物品集合放入背包，然后把权重对应的最值都拿出来作为一个物品组，其实就是对树中物品的dp
    // 每稞树中物品进行dp时，可以从根节点开始向下，得到其每个子树的物品组，再与当前物品结合，构成新的物品组
    //
    // 对每个树进行dp后，将转化得到的物品组使用分组的01背包的解法进行求解
    public static int dependencyPack(int[] weights, int[] values, int[] dependencies, List<List<Integer>> childLists, int packSize) {
        int[] dp = new int[packSize + 1];
        for (int i = 0; i < dependencies.length; i++) {
            // 从每棵树的根节点开始
            if (dependencies[i] == -1) {
                List<List<Integer>> itemGroup = getItemGroupFromRoot(i, weights, values, dependencies, childLists, packSize);
                groupPackOneGroup(itemGroup, dp);
            }
        }
        return dp[packSize];
    }

    private static List<List<Integer>> getItemGroupFromRoot(int rootIdx, int[] weights, int[] values, int[] dependencies, List<List<Integer>> childLists, int packSize) {
        if (childLists.get(rootIdx).size() == 0) {
            List<List<Integer>> itemGroup = new ArrayList<>();
            itemGroup.add(new ArrayList<>(Arrays.asList(weights[rootIdx])));
            itemGroup.add(new ArrayList<>(Arrays.asList(values[rootIdx])));
            return itemGroup;
        } else {
            int[] dp = new int[packSize + 1];

            List<Integer> childs = childLists.get(rootIdx);
            for (Integer child : childs) {
                List<List<Integer>> childItemGroup = getItemGroupFromRoot(child, weights, values, dependencies, childLists, packSize);
                groupPackOneGroup(childItemGroup, dp);
            }

            // 把树的根节点放入背包，根节点必须放入
            addRootItem(weights[rootIdx], values[rootIdx], dp);

            // 清理dp数组，获取物品组
            // 去掉weight小于根节点weight的物品，这些物品没有放入根节点
            // 去掉weight更大，但value更小的物品，进行剪枝
            return getItemGroupFromDpArray(weights[rootIdx], dp);
        }
    }

    private static void groupPackOneGroup(List<List<Integer>> childItems, int[] dp) {
        for (int i = dp.length - 1; i >= 0; i--) {
            for (int j = 0; j < childItems.get(0).size(); j++) {
                int weight = childItems.get(0).get(j);
                int value = childItems.get(1).get(j);
                if (i >= weight && dp[i - weight] + value > dp[i]) {
                    dp[i] = dp[i - weight] + value;
                }
            }
        }
    }

    // 把树的根节点放入背包，根节点必须放入
    private static void addRootItem(int weight, int value, int[] dp) {
        for (int i = dp.length - 1; i >= weight; i--) {
            dp[i] = dp[i - weight] + value;
        }
    }

    private static List<List<Integer>> getItemGroupFromDpArray(int rootWeight, int[] dp) {
        List<Integer> tempWeights = new ArrayList<>();
        List<Integer> tempValues = new ArrayList<>();
        int currentMaxValue = 0;
        // 在目前保留物品中
        // 去掉weight小于根节点weight的物品，这些物品没有放入根节点
        // 去掉weight更大，但value更小的物品，进行剪枝
        // 将保留下的物品存入新数组
        for (int i = rootWeight; i < dp.length; i++) {
            if (dp[i] > 0) {
                if (dp[i] > currentMaxValue) {
                    currentMaxValue = dp[i];
                    tempWeights.add(i);
                    tempValues.add(dp[i]);
                }
            }
        }
        List<List<Integer>> tempList = new ArrayList<>();
        tempList.add(tempWeights);
        tempList.add(tempValues);
        return tempList;
    }
}
