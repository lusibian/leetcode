package lusibian.leetcode.array;

import java.util.Arrays;

public class TaskScheduler {
    public static void main(String[] args) {
        char[] tasks = new char[]{'A', 'A', 'A', 'A', 'A', 'A', 'B', 'B', 'B', 'B', 'B', 'C', 'C', 'C'};
//        System.out.println(leastInterval(tasks, 1));
    }

    public int leastInterval(char[] tasks, int n) {
        int[] map = new int[26];
        for (char c: tasks)
            map[c - 'A']++;
        Arrays.sort(map);
        int maxCount = map[25] - 1;
        int idleSlots = maxCount * (n + 1);
        for (int i : map) {
            idleSlots -= Math.min(i, maxCount);
        }
        return idleSlots > 0 ? idleSlots + tasks.length : tasks.length;
    }
}
