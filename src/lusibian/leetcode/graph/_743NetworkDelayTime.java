package lusibian.leetcode.graph;

import javafx.util.Pair;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class _743NetworkDelayTime {
    public static void main(String[] args) {
        _743NetworkDelayTime temp = new _743NetworkDelayTime();
        int[][] times = new int[][]{{2,1,1},{2,3,1},{3,4,1}};
        System.out.println(temp.networkDelayTime(times, 4, 2));
    }

    // dijkstra
    public int networkDelayTime(int[][] times, int N, int K) {
        boolean[] isReached = new boolean[N + 1];
        int[] shortestPathLength = new int[N + 1];
        for (int i = 0; i < shortestPathLength.length; i++) {
            shortestPathLength[i] = Integer.MAX_VALUE;
        }
        Map<Integer, List<Pair<Integer, Integer>>> adjacencyListMap = new HashMap<>();
        for (int i = 0; i < times.length; i++) {
            if (adjacencyListMap.containsKey(times[i][0])) {
                adjacencyListMap.get(times[i][0]).add(new Pair<>(times[i][1], times[i][2]));
            } else {
                List<Pair<Integer, Integer>> tempList = new ArrayList<>();
                tempList.add(new Pair<>(times[i][1], times[i][2]));
                adjacencyListMap.put(times[i][0], tempList);
            }
        }

        shortestPathLength[K] = 0;
        while (true) {
            int node = findNodeWithMinDistance(isReached, shortestPathLength);
            // 坐标0代表为找到符合条件的点
            if (node == 0) {
                break;
            }
            isReached[node] = true;
            updateAdjacencyNodePathLength(adjacencyListMap, shortestPathLength, node);
        }

        int maxLength = 0;
        for (int i = 1; i < shortestPathLength.length; i++) {
            if (shortestPathLength[i] > maxLength) {
                maxLength = shortestPathLength[i];
            }
        }
        if (maxLength == Integer.MAX_VALUE) {
            return -1;
        }
        return maxLength;
    }

    private int findNodeWithMinDistance(boolean[] isReached, int[] shortestPathLength) {
        // 坐标0代表为找到符合条件的点
        int node = 0;
        for (int i = 1; i < isReached.length; i++) {
            if (!isReached[i] && shortestPathLength[i] < shortestPathLength[node]) {
                node = i;
            }
        }
        return node;
    }

    private void updateAdjacencyNodePathLength(Map<Integer, List<Pair<Integer, Integer>>> adjacencyListMap,
                                               int[] shortestPathLength, int node) {
        if (adjacencyListMap.containsKey(node)){
            List<Pair<Integer, Integer>> tempList = adjacencyListMap.get(node);
            for (Pair<Integer, Integer> pair : tempList) {
                int adjacencyNode = pair.getKey();
                int weight = pair.getValue();
                if (shortestPathLength[adjacencyNode] > shortestPathLength[node] + weight) {
                    shortestPathLength[adjacencyNode] = shortestPathLength[node] + weight;
                }
            }
        }
    }
}
