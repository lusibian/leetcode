package lusibian.leetcode.array;

import java.util.*;

public class LargestNumber {
    public static void main(String[] args) {
        int[] nums = new int[]{0, 0};
        LargestNumber largestNumber = new LargestNumber();
        System.out.println(largestNumber.largestNumber(nums));
    }

    public String largestNumber(int[] nums) {
        if (nums == null || nums.length == 0) {
            return "";
        }
        List<String> numStrs = new ArrayList<>();
        for (int num : nums) {
            numStrs.add(String.valueOf(num));
        }
        numStrs.sort(new numStrComparator());
        if (numStrs.get(0).equals("0")) {
            return "0";
        }
        StringBuilder stringBuilder = new StringBuilder();
        for (int i = 0; i < numStrs.size(); i++) {
            stringBuilder.append(numStrs.get(i));
        }
        return stringBuilder.toString();
    }

    // 从大到小，逆序
    private class numStrComparator implements Comparator<String> {
        @Override
        public int compare(String a, String b) {
            String str1 = a + b;
            String str2 = b + a;
            if (str1.compareTo(str2) <= 0) {
                return 1;
            } else {
                return -1;
            }
        }
    }
}
