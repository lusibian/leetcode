package lusibian.leetcode.array;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

public class MergeIntervals {
    public int[][] merge(int[][] intervals) {
        if(intervals.length == 0) {
            return intervals;
        }
        List<List<Integer>> intervalLists = getListFromArray(intervals);
        Collections.sort(intervalLists, new IntervalComparator());
        List<List<Integer>> merged = new ArrayList<>();
        List<Integer> tempList = null;
        for (List<Integer> intervalList : intervalLists) {
            if(tempList == null) {
                tempList = intervalList;
            } else if (tempList.get(1) >= intervalList.get(0) && tempList.get(1) < intervalList.get(1)) {
                tempList.set(1, intervalList.get(1));
            } else if (tempList.get(1) < intervalList.get(0)) {
                merged.add(tempList);
                tempList = intervalList;
            }
        }
        merged.add(tempList);
        return getArrayFromList(merged);
    }

    private List<List<Integer>> getListFromArray(int[][] intervals) {
        List<Integer> list = null;
        List<List<Integer>> list2 = new ArrayList<>();
        for (int i = 0; i < intervals.length; i++) {
            list = new ArrayList<>();
            for (int j = 0; j < intervals[i].length; j++) {
                list.add(intervals[i][j]);
            }
            list2.add(list);
        }
        return list2;
    }

    private class IntervalComparator implements Comparator<List<Integer>> {
        @Override
        public int compare(List<Integer> a, List<Integer> b) {
            return a.get(0) < b.get(0) ? -1 : a.get(0) == b.get(0) ? 0 : 1;
        }
    }

    private int[][] getArrayFromList(List<List<Integer>> merged) {
        int[][] list = new int[merged.size()][2];
        for (int i = 0; i < merged.size(); i++) {
            for (int j = 0; j < merged.get(i).size(); j++) {
                list[i][j] = merged.get(i).get(j);
            }
        }
        return list;
    }
}
