package lusibian.leetcode.dynamic_programming;

public class _72EditDistance {
    // 编辑距离问题
    // 动态规划，二维矩阵
    public int minDistance1(String word1, String word2) {
        int m = word1.length();
        int n = word2.length();
        int[][] minDistanceMatrix = new int[m + 1][n + 1];
        for (int i = 1; i <= m; i++) {
            minDistanceMatrix[i][0] = i;
        }
        for (int i = 1; i <= n; i++) {
            minDistanceMatrix[0][i] = i;
        }
        for (int i = 1; i <= m; i++) {
            for (int j = 1; j <= n; j++) {
                minDistanceMatrix[i][j] = 1 + Math.min(minDistanceMatrix[i - 1][j], minDistanceMatrix[i][j - 1]);
                if (word1.charAt(i - 1) == word2.charAt(j - 1)) {
                    minDistanceMatrix[i][j] = Math.min(minDistanceMatrix[i][j], minDistanceMatrix[i - 1][j - 1]);
                } else {
                    minDistanceMatrix[i][j] = Math.min(minDistanceMatrix[i][j], 1 + minDistanceMatrix[i - 1][j - 1]);
                }
            }
        }
        return minDistanceMatrix[m][n];
    }

    // 编辑距离问题
    // 动态规划，一维数组
    // 删除一个字符和增加一个字符是可互换的，只使用二者之一，就能求出编辑距离。
    // 因此，这里只考虑使用操作增加和替换。
    // 子问题拆分：求字符串1的长度为i的前缀子串与字符串2的长度为j的前缀子串的编辑距离
    // 记c1[i]为字符串1的第i个字符，c2[j]为字符串2的第j个字符
    // 记h[i, j]为 c1[i]是否等于c2[j]，相等为1，不等为0
    // 状态转移方程：dp[i, j] = max(dp[i-1, j] + 1, dp[i, j-1] + 1, dp[i-1, j-1]  + 1 - h[i, j])
    // 时间复杂度O(mn)，空间复杂度O(m)或O(n)
    // 可以选择更短的那个字符串作为dp数组长度，空间复杂度O(min(m,n))
    public int minDistance2(String word1, String word2) {
        int m = word1.length();
        int n = word2.length();
        int[] dp = new int[n + 1];
        int[] currentDp = new int[n + 1];
        for (int i = 1; i <= n; i++) {
            dp[i] = i;
        }
        for (int i = 1; i <= m; i++) {
            currentDp[0] = i;
            for (int j = 1; j <= n; j++) {
                currentDp[j] = 1 + Math.min(currentDp[j - 1], dp[j]);
                if (word1.charAt(i - 1) == word2.charAt(j - 1)) {
                    currentDp[j] = Math.min(currentDp[j], dp[j - 1]);
                } else {
                    currentDp[j] = Math.min(currentDp[j], 1 + dp[j - 1]);
                }
            }
            int[] tempDp = dp;
            dp = currentDp;
            currentDp = tempDp;
        }
        return dp[n];
    }
}
