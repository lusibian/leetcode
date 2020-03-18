package lusibian.leetcode.dynamic_programming;

public class _1143LongestCommonSubsequence {
    // 最长公共子序列问题，LCS问题
    // 子问题拆分：求字符串1的长度为i的前缀子串与字符串2的长度为j的前缀子串的最长公共子序列
    // 记c1[i]为字符串1的第i个字符，c2[j]为字符串2的第j个字符
    // 记h[i, j]为 c1[i]是否等于c2[j]，相等为1，不等为0
    // 状态转移方程：dp[i, j] = max(dp[i-1, j], dp[i, j], dp[i-1, j-1] + h[i, j])
    // 时间复杂度O(mn)，空间复杂度O(m)或O(n)
    // 可以选择更短的那个字符串作为dp数组长度，空间复杂度O(min(m,n))
    public int longestCommonSubsequence(String text1, String text2) {
        int n = text1.length();
        int m = text2.length();
        int[] dp = new int[n];
        int[] currentDp = new int[n];
        for (int i = 0; i < m; i++) {
            if (dp[0] == 0 && text2.charAt(i) == text1.charAt(0)) {
                currentDp[0] = 1;
            }
            for (int j = 1; j < n; j++) {
                currentDp[j] = Math.max(currentDp[j - 1], dp[j]);
                if (text2.charAt(i) == text1.charAt(j)) {
                    currentDp[j] = Math.max(currentDp[j], dp[j - 1] + 1);
                }
            }
            int[] tempDp = dp;
            dp = currentDp;
            currentDp = tempDp;
        }
        return dp[n - 1];
    }
}
