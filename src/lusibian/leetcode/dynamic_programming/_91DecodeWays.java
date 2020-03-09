package lusibian.leetcode.dynamic_programming;

public class _91DecodeWays {
    // 斐波那契数列变种，有条件相加的斐波那契数列
    public int numDecodings(String s) {
        int length = s.length();
        int[] decodingsCount = new int[length + 1];
        decodingsCount[0] = 1;
        for (int i = 0; i < length; i++) {
            if (s.charAt(i) != '0') {
                decodingsCount[i + 1] += decodingsCount[i];
            }
            if (i - 1 >= 0 && (s.charAt(i - 1) == '1' || (s.charAt(i - 1) == '2' && s.charAt(i) <= '6'))) {
                decodingsCount[i + 1] += decodingsCount[i - 1];
            }
        }
        return decodingsCount[length];
    }
}
