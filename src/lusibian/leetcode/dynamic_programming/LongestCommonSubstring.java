package lusibian.leetcode.dynamic_programming;

public class LongestCommonSubstring {
    public static void main(String[] args) {
        String text1 = "abc";
        String text2 = "acbc";
        System.out.println(longestCommonSubstring(text1, text2));
    }

    // 最长公共子串问题
    // 问题转化：求字符串1的长度为i的前缀子串与字符串2的长度为j的前缀子串的最长公共后缀
    // 在求解转化后问题的过程中，记录找到的最长公共子串
    // 记录最长公共后缀，是因为子串必须是连续的，状态转移时，前序状态一定是已找到的公共后缀
    // 记c1[i]为字符串1的第i个字符，c2[j]为字符串2的第j个字符
    // 记h[i, j]为 c1[i]是否等于c2[j]，相等为1，不等为0
    // 状态转移方程：dp[i, j] = h[i, j] * (dp[i-1, j-1] + h[i, j])
    // 时间复杂度O(mn)，空间复杂度O(m)或O(n)
    // 可以选择更短的那个字符串作为dp数组长度，空间复杂度O(min(m,n))
    public static int longestCommonSubstring(String text1, String text2) {
        int n = text1.length();
        int m = text2.length();
        int[] dp = new int[n];
        int[] currentDp = new int[n];
        int maxCommonSubstringLength = 0;
        for (int i = 0; i < m; i++) {
            if (dp[0] == 0 && text2.charAt(i) == text1.charAt(0)) {
                currentDp[0] = 1;
            }
            for (int j = 1; j < n; j++) {
                if (text2.charAt(i) == text1.charAt(j)) {
                    currentDp[j] = dp[j - 1] + 1;
                    if (currentDp[j] > maxCommonSubstringLength) {
                        maxCommonSubstringLength = currentDp[j];
                    }
                }
            }
            int[] tempDp = dp;
            dp = currentDp;
            currentDp = tempDp;
        }
        return maxCommonSubstringLength;
    }
}
