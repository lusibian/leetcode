package lusibian.leetcode.graph;

import java.util.*;

public class _207CourseSchedule {
    public boolean canFinish(int numCourses, int[][] prerequisites) {
        int[] inDegree = new int[numCourses];
        Map<Integer, List<Integer>> adjacencyListMap = new HashMap<>();
        for (int i = 0; i < prerequisites.length; i++) {
            inDegree[prerequisites[i][0]]++;
            if (adjacencyListMap.containsKey(prerequisites[i][1])) {
                adjacencyListMap.get(prerequisites[i][1]).add(prerequisites[i][0]);
            } else {
                List<Integer> tempList = new ArrayList<>();
                tempList.add(prerequisites[i][0]);
                adjacencyListMap.put(prerequisites[i][1], tempList);
            }
        }
        Deque<Integer> deque = new LinkedList<>();
        for (int i = 0; i < numCourses; i++) {
            if (inDegree[i] == 0) {
                deque.add(i);
            }
        }
        while (!deque.isEmpty()) {
            int node = deque.poll();
            if (adjacencyListMap.containsKey(node)) {
                List<Integer> tempList = adjacencyListMap.get(node);
                for (Integer adjacencyNode : tempList) {
                    inDegree[adjacencyNode]--;
                    if (inDegree[adjacencyNode] == 0) {
                        deque.add(adjacencyNode);
                    }
                }
            }
        }
        for (int i = 0; i < numCourses; i++) {
            if (inDegree[i] != 0) {
                return false;
            }
        }
        return true;
    }
}
